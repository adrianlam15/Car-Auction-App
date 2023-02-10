package ui;

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
    private boolean inListingView = false;
    private boolean viewBids = false;
    private Cars listedCars;
    private boolean bidView = false;
    private ArrayList<Integer> listingID;

    public AuctionApp() {
        runAuctionApp();
    }

    public void runAuctionApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loggedIn = false;
        user = new User();
        listedCars = null;
        String command = null;

        while (keepGoing) {
            displayMenu(loggedIn);
            command = input.next();
            processCommand(command);
        };
    }

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
            System.out.println("\t2. Place a bid.");
            System.out.println("\t3. View listings.");
            System.out.println("\t4. View current bids.");
            System.out.println("\t5. Logout.");
        }
    }

    private void processCommand(String command) {
        if (!loggedIn) {
            if (command.equals("3")) {
                System.out.println("Goodbye!");
                keepGoing = false;
            } else if (command.equals("2")) {
                System.out.println("Username:");
                String usr = input.next();
                System.out.println("Password:");
                String pwd = input.next();
                loggedIn = user.login(usr, pwd);
            } else if (command.equals("1")) {
                handleCreateAccount();
            } else {
                System.out.println("Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        } else {
            if (command.equals("1")) {
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
                System.out.println("Enter the car's mileage:");
                car.setMileage(input.nextInt());
                System.out.println("Enter the car's price:");
                car.setPrice(input.nextInt());
                System.out.println("Enter the car's description:");
                car.setDescription(input.next());
                System.out.println(car.getMake());
                System.out.println(car.getModel());
                System.out.println(car.getColour());
                System.out.println(car.getTransmission());
                System.out.println(car.getDriveType());
                System.out.println(car.getCondition());
                System.out.println(car.getYear());
                System.out.println(car.getMileage());
                System.out.println(car.getPrice());
                System.out.println(car.getDescription());
                System.out.println("Is this correct? (y/n):");
                String confirm = input.next();
                confirm = confirm.toLowerCase();
                if (confirm.equals("n")) {
                    processCommand("1");
                }
                user.createCar(car);
                System.out.println("Listing created successfully.");
            } else if (command.equals("2")) {
                user.placeBid();
                bidView = true;
            } else if (command.equals("3")) {
                viewListings();
            } else if (command.equals("4")) {
                user.viewBids();
            } else if (command.equals("5")) {
                System.out.println("Goodbye!");
                keepGoing = false;
            } else {
                System.out.println("Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        }
    }

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

    private boolean bidView(String carPos) {
        int choicePos = Integer.parseInt(carPos);
        for (int pos : listingID) {
            if (choicePos == pos) {
                System.out.println("Enter the amount you'd like to bid:");
                int bid = input.nextInt();
                System.out.println("Bid placed successfully.");
                return true;
            }
        }
        System.out.println("Listing not found. Try again:");
        return false;
    }

    private void displayListings() {
        listingID = new ArrayList<Integer>();
        listedCars = new Cars();
        System.out.println("==== Listings ====");
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            car.setMake("0" + i);
            listedCars.addCar(car);
        }
        int id = 0;
        int pos = 1;
        for (Car car : listedCars.getCars()) {
            System.out.println("\t" + pos + ". [" + car.getCondition() + "] " + car.getYear() + " " +  car.getMake() + " " + car.getModel() + " for $" + car.getPrice());
            listingID.add(id);
            id++;
            pos++;
        }
        System.out.println("listingID: " + listingID);
    }

    private void handleCreateAccount() {
        System.out.println("Enter a username:");
        String usr = input.next();
        System.out.println("Enter a password:");
        String pwd = input.next();
        System.out.println("Re-enter your password:");
        String checkPwd = input.next();
        if (pwd.equals(checkPwd)) {
            if (user.createUser(usr, pwd, checkPwd)) {
                System.out.println("Account created successfully.");
                loggedIn = user.login(usr, pwd);
            } else {
                System.out.println("Error creating account. Please try again.");
            }
        } else {
            System.out.println("Passwords don't match. Please try again.");
            processCommand("1");
        }
    }
}
