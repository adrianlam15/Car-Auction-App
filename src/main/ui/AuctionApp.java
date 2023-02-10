package ui;

import model.Car;
import model.User;

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

    public AuctionApp() {
        runAuctionApp();
    }

    public void runAuctionApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loggedIn = false;
        user = new User();
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
            } else if (command.equals("3")) {
                user.viewListings();
            } else if (command.equals("4")) {
                user.viewBids();
            } else {
                System.out.println("Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        }
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
