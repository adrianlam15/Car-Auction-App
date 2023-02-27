package ui;

import model.Bid;
import model.Car;
import model.Cars;
import model.User;
import persistence.JsonReader;

import java.io.IOException;
import java.util.*;

// Represents the Car Auction App User Interface
public class AuctionApp {
    private static final String JSON_STORE = "./data/setting.json";
    private JsonReader jsonReader;
    private Scanner input;
    private User currentUser;
    private ArrayList<User> users;
    private boolean loggedIn;
    private boolean keepGoing = true;
    private Cars listedCars;
    private ArrayList<Integer> listingID;
    private HashMap<String, String> userMap;

    public AuctionApp() throws IOException {
        jsonReader = new JsonReader(JSON_STORE);
        users = jsonReader.readUsers();
        userMap = jsonReader.getUserMap();
        System.out.println("username, password: " + userMap);
        runAuctionApp();
    }

    // MODIFIES: this
    // EFFECTS: runs the Car Auction App
    public void runAuctionApp() {

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loggedIn = false;
        currentUser = new User();
        listedCars = null;
        String command = null;
        listedCars = new Cars();
        while (keepGoing) {
            displayMenu(loggedIn);
            command = input.next();
            processCommand(command);
        }
    }

    // EFFECTS: displays the main menu
    private void displayMenu(boolean loggedIn) {
        if (!loggedIn) {
            System.out.println("\nWelcome to the Car Auction App!");
            System.out.println("Use numbers to select choices:");
            System.out.println("\t1. Create an account.");
            System.out.println("\t2. Login.");
            System.out.println("\t3. Exit.");
        } else {
            System.out.println("\n==== Main menu ====");
            System.out.println("Use numbers to select choices:");
            System.out.println("\t1. Create a listing.");
            System.out.println("\t2. View listings.");
            System.out.println("\t3. View your listings.");
            System.out.println("\t4. View current bids.");
            System.out.println("\t5. View won cars.");
            System.out.println("\t6. Logout.");
        }
    }

    // REQUIRES: command must be one of the following: "1", "2", "3", "4", "5", "6"
    // MODIFIES: this, user, car
    // EFFECTS: processes the user's command and calls the appropriate method
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        if (!loggedIn) {
            if (command.equals("3")) {
                System.out.println("[STATUS]: Goodbye!");
                keepGoing = false;
            } else if (command.equals("2")) {
                System.out.println("Username:");
                String usr = input.next();
                System.out.println("Password:");
                String pwd = input.next();
                loggedIn = currentUser.login(usr, pwd, userMap);
            } else if (command.equals("1")) {
                handleCreateAccount();
            } else {
                System.out.println("[STATUS]: Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        } else {
            if (command.equals("1")) {
                System.out.println("==== Create a listing ==== ");
                Car car = new Car();
                System.out.println("Enter the car's make:");
                car.setMake(input.next());
                System.out.println("Enter the car's model:");
                car.setModel(input.next());
                System.out.println("Enter the car's colour:");
                car.setColour(input.next());
                System.out.println("Enter the car's transmission:");
                car.setTransmission(input.next());
                System.out.println("Enter the car's drive type:");
                car.setDriveType(input.next());
                System.out.println("Enter the car's condition:");
                car.setCondition(input.next());
                System.out.println("Enter the car's year:");
                car.setYear(input.nextInt());
                System.out.println("Enter the car's mileage (in kms):");
                car.setMileage(input.nextInt());
                System.out.println("Enter the car's price (in CAD):");
                car.setPrice(input.nextInt());
                System.out.println("Enter the car's description:");
                car.setDescription(input.next());
                System.out.println("How long would you like to list the car for (in seconds)?");
                int toSetTime = input.nextInt();
                System.out.print(car.getListingCar());
                System.out.println("\nDescription of vehicle:\n" + car.getDescription());
                System.out.println("\nIs this correct? (y/n):");
                car.setTimer(toSetTime);
                String confirm = input.next();
                confirm = confirm.toLowerCase();
                if (confirm.equals("n")) {
                    processCommand("1");
                }
                currentUser.createCar(car);
                listedCars.addCar(car);
                System.out.println("[STATUS]: Listing created successfully.");
            } else if (command.equals("2")) {
                viewListings();
            } else if (command.equals("3")) {
                viewUserListings();
            } else if (command.equals("4")) {
                showBids();
            } else if (command.equals("5")) {
                viewWonListings();
            } else if (command.equals("6")) {
                System.out.println("[STATUS]: Goodbye!");
                keepGoing = false;
            } else {
                System.out.println("[STATUS]: Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        }
    }

    // EFFECTS: lets user view won listings
    private void viewWonListings() {
        int id = 1;
        if (currentUser.getWonCars().isEmpty()) {
            System.out.println("[STATUS]: You have no won listings.");
        } else {
            System.out.println("==== Your Won Listings ====");
            for (Car car : currentUser.getWonCars()) {
                System.out.println("\t" + id + ". " + car.getListingCar());
            }
        }
    }

    // EFFECTS: displays the user's bidded listings
    private void showBids() {
        if (currentUser.getBids().isEmpty()) {
            System.out.println("[STATUS]: You have no bids.");
        } else {
            System.out.println("==== Your Bids ====");
            int pos = 1;
            for (Bid bid : currentUser.getBids()) {
                System.out.println("\t" + pos + ". \n\t" + bid.getBid());
            }
        }
    }

    // EFFECTS: displays the listings
    private void viewListings() {
        displayListings();
        if (!listedCars.getCars().isEmpty()) {
            System.out.println("Would you like to place a bid? (y/n)");
            String choice = input.next().toLowerCase();
            if (choice.equals("y")) {
                System.out.println("Enter the number of the listing you'd like to view:");
                String carPos = input.next();
                while (!bidView(carPos)) {
                    carPos = input.next();
                }
            }
        }
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this, car
    // EFFECTS: displays the user's listings, and allows user to edit or delete listing
    private void viewUserListings() {
        listingID = new ArrayList<Integer>();
        int id = 1;
        int pos = 1;
        if (currentUser.getCars().isEmpty()) {
            System.out.println("[STATUS]: You have no listings.");
        } else {
            System.out.println("==== Your Listings ====");
            for (Car car : currentUser.getCars()) {
                System.out.println("\t" + pos + ". " + car.getListingCar());
                if (car.isExpired()) {
                    System.out.println("\t[STATUS]: Listing expired.");
                }
                listingID.add(id);
                car.setId(id);
                id++;
                pos++;
            }
            System.out.println("Would you like to edit, or delete a listing? (E/D/N)");
            String choice = input.next().toLowerCase();
            if (choice.equals("n")) {
                processCommand("3");
            }
            while (!editView(choice)) {
                choice = input.next();
            }
        }
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: choice is "e" or "d" or "n"
    // MODIFIES: car
    // EFFECTS: edits or deletes a listing
    private boolean editView(String choice) {
        if (choice.equals("e")) {
            System.out.println("Enter the number of the listing you'd like to edit:");
            String carPos = input.next();
            int carPosInt = Integer.parseInt(carPos);
            System.out.println("What would you like to edit? Enter a number:\n\t1. Make\n\t2. "
                    + "Model\n\t3. Colour\n\t4. Transmission\n\t5. Drive Type\n\t6. Condition\n\t7. "
                    + "Year\n\t8. Mileage\n\t9. Price\n\t10. Description");
            String editChoice = input.next();
            int editChoiceInt = Integer.parseInt(editChoice);
            System.out.println("Enter the new value:");
            String newValue = input.next();
            if (currentUser.editCar(carPosInt, editChoiceInt, newValue)) {
                System.out.println("[STATUS]: Listing edited successfully.");
                return true;
            } else {
                return false;
            }
        } else if (choice.equals("d")) {
            System.out.println("Enter the number of the listing you'd like to delete:");
            String carPos = input.next();
            int carPosInt = Integer.parseInt(carPos);
            Car toRemove = currentUser.deleteCar(carPosInt);
            if (toRemove != null) {
                listedCars.removeCar(toRemove);
                System.out.println("[STATUS]: Listing deleted successfully.");
                return true;
            } else {
                return false;
            }
        } else if (choice.equals("n")) {
            return true;
        } else {
            System.out.println("[STATUS]: Invalid entry. Try again:");
            return false;
        }
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: carPos is a number that represents valid listing
    // EFFECTS: places a bid on a listing
    private boolean bidView(String carPos) {
        int choicePos = Integer.parseInt(carPos);
        System.out.println("=== Place a Bid ===");
        int i = 1;
        for (Car car : listedCars.getCars()) {
            car.setId(i);
            if (car.getId() == choicePos) {
                for (Car userCar : currentUser.getCars()) {
                    if (car.equals(userCar)) {
                        System.out.println("[STATUS]: You can not bid on your own listing.");
                        return true;
                    }
                }
                if (car.isExpired()) {
                    System.out.println("[STATUS]: You can not bid on this listing, it has expired.");
                    return true;
                } else {
                    System.out.println(car.getListingCar());
                    System.out.println("Description: \n" + car.getDescription());
                    if (car.getHighestBid() == null) {
                        System.out.println("[STATUS]: No bids on this listing yet.");
                    } else if (car.getHighestBid() != null) {
                        System.out.println("[STATUS]: Current bid: $" + car.getHighestBid().getBidAmount()
                                + " by " + car.getHighestBid().getUser().getUsername());
                    }
                }
            }
            i++;
        }
        System.out.println("Enter the amount you'd like to bid:");
        int bid = input.nextInt();
        currentUser.placeBid(choicePos, bid, listedCars);
        System.out.println("[STATUS]: Bid placed successfully.");
        return true;
    }

    // MODIFIES: this, car
    // EFFECTS: displays the listings
    private void displayListings() {
        listingID = new ArrayList<Integer>();
        int id = 1;
        int pos = 1;
        if (listedCars.getCars().isEmpty()) {
            System.out.println("[STATUS]: No listings found.");
        } else {
            System.out.println("==== Listings ====");
            for (Car car : listedCars.getCars()) {
                System.out.println("\t" + pos + ". " + car.getListingCar());
                if (car.isExpired()) {
                    System.out.println("\t[STATUS]: Listing expired.");
                }
                listingID.add(id);
                car.setId(id);
                id++;
                pos++;
            }
        }
    }

    // MODIFIES: this, user
    // EFFECTS: creates a new account
    private void handleCreateAccount() {
        System.out.println("Enter a username:");
        String usr = input.next();
        System.out.println("Enter a password:");
        String pwd = input.next();
        System.out.println("Re-enter your password:");
        String checkPwd = input.next();
        if (pwd.equals(checkPwd)) {
            if (currentUser.createUser(usr, pwd, checkPwd)) {
                System.out.println("[STATUS]: Account created successfully.");
                // users.put(usr, pwd);  // add to HashMap
                // loggedIn = user.login(usr, pwd);  // ** UNCOMMENT FOR PHASE 2 **
                // add to json file using write
            } else {
                System.out.println("[STATUS]: Error creating account. Please try again.");
            }
        } else {
            System.out.println("[STATUS]: Passwords don't match. Please try again.");
            processCommand("1");
        }
    }
}
