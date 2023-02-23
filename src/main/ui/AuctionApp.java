package ui;

import model.Bid;
import model.Car;
import model.Cars;
import model.User;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Represents the Car Auction App User Interface
public class AuctionApp {
    private Scanner input;
    private User user;
    private boolean loggedIn;
    private boolean keepGoing = true;
    private Cars listedCars;
    private ArrayList<Integer> listingID;

    public AuctionApp() {
        runAuctionApp();
    }

    // MODIFIES: this
    // EFFECTS: runs the Car Auction App
    public void runAuctionApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loggedIn = false;
        user = new User();
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
            System.out.println("\t5. Logout.");
        }
    }

    // REQUIRES: command must be one of the following: "1", "2", "3", "4", "5"
    // MODIFIES: this
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
                // loggedIn = user.login(usr, pwd);  *** UNCOMMENT FOR PHASE 2 ***
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
                System.out.print(car.getListingCar());
                System.out.println("\nDescription of vehicle:\n" + car.getDescription());
                System.out.println("\nIs this correct? (y/n):");
                String confirm = input.next();
                confirm = confirm.toLowerCase();
                if (confirm.equals("n")) {
                    processCommand("1");
                }
                user.createCar(car);
                listedCars.addCar(car);
                System.out.println("[STATUS]: Listing created successfully.");
            } else if (command.equals("2")) {
                viewListings();
            } else if (command.equals("3")) {
                viewUserListings();
            } else if (command.equals("4")) {
                showBids();
            } else if (command.equals("5")) {
                System.out.println("[STATUS]: Goodbye!");
                keepGoing = false;
            } else {
                System.out.println("[STATUS]: Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        }
    }

    // EFFECTS: displays the user's bidded listings
    private void showBids() {
        if (user.getBids().isEmpty()) {
            System.out.println("[STATUS]: You have no bids.");
        } else {
            System.out.println("==== Your Bids ====");
            int pos = 1;
            for (Bid bid : user.getBids()) {
                System.out.println("\t" + pos + ". \n\t" + bid.getBid());
            }
        }
    }

    // EFFECTS: displays the listings
    private void viewListings() {
        displayListings();
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

    // MODIFIES: this
    // EFFECTS: displays the user's listings
    private void viewUserListings() {
        listingID = new ArrayList<Integer>();
        int id = 1;
        int pos = 1;
        if (user.getCars().isEmpty()) {
            System.out.println("[STATUS]: You have no listings.");
        } else {
            System.out.println("==== Your Listings ====");
            for (Car car : user.getCars()) {
                System.out.println("\t" + pos + ". " + car.getListingCar());
                listingID.add(id);
                car.setId(id);
                id++;
                pos++;
            }
            System.out.println("Would you like to edit, or delete a listing? (E/D)");
            String choice = input.next().toLowerCase();
            while (!editView(choice)) {
                choice = input.next();
            }
        }
    } // ADD EDIT, BIDS, TIME, DELETE

    // REQUIRES: choice is "e" or "d"
    // EFFECTS: edits or deletes a listing
    private boolean editView(String choice) {
        if (choice.equals("e")) {
            System.out.println("Enter the number of the listing you'd like to edit:");
            String carPos = input.next();
            int carPosInt = Integer.parseInt(carPos);
            System.out.println("What would you like to edit? Enter a number:\n\t1. Make\n\t2. Model\n\t3. Colour\n\t4. Transmission\n\t5. Drive Type\n\t6. Condition\n\t7. Year\n\t8. Mileage\n\t9. Price\n\t10. Description");
            String editChoice = input.next();
            int editChoiceInt = Integer.parseInt(editChoice);
            System.out.println("Enter the new value:");
            String newValue = input.next();
            if (user.editCar(carPosInt, editChoiceInt, newValue)) {
                System.out.println("[STATUS]: Listing edited successfully.");
                return true;
            } else {
                return false;
            }
        } else if (choice.equals("d")) {
            System.out.println("Enter the number of the listing you'd like to delete:");
            String carPos = input.next();
            int carPosInt = Integer.parseInt(carPos);
            Car toRemove = user.deleteCar(carPosInt);
            if (toRemove != null) {
                listedCars.removeCar(toRemove);
                System.out.println("[STATUS]: Listing deleted successfully.");
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("[STATUS]: Invalid entry. Try again:");
            return false;
        }
    }

    // REQUIRES: carPos is a number that represents valid listing
    // EFFECTS: places a bid on a listing
    private boolean bidView(String carPos) {
        int choicePos = Integer.parseInt(carPos);
        System.out.println("Enter the amount you'd like to bid:");
        int bid = input.nextInt();
        boolean success = user.placeBid(choicePos, bid, listedCars);
        System.out.println("[STATUS]: Bid placed successfully.");
        return success;
    }

    // MODIFIES: this
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
                listingID.add(id);
                car.setId(id);
                id++;
                pos++;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new account
    private void handleCreateAccount() {
        System.out.println("Enter a username:");
        String usr = input.next();
        System.out.println("Enter a password:");
        String pwd = input.next();
        System.out.println("Re-enter your password:");
        String checkPwd = input.next();
        if (pwd.equals(checkPwd)) {
            if (user.createUser(usr, pwd, checkPwd)) {
                System.out.println("[STATUS]: Account created successfully.");
                // loggedIn = user.login(usr, pwd);  // ** UNCOMMENT FOR PHASE 2 **
                loggedIn = true;  // ** COMMENT FOR PHASE 2 **
            } else {
                System.out.println("[STATUS]: Error creating account. Please try again.");
            }
        } else {
            System.out.println("[STATUS]: Passwords don't match. Please try again.");
            processCommand("1");
        }
    }
}
