package ui;

import model.Car;
import model.ListOfListing;
import model.Listing;
import model.User;

import java.util.Locale;
import java.util.Scanner;

// Represents the Car Auction App User Interface
public class AuctionApp {
    Scanner input;
    boolean loggedIn;
    User user = null;
    ListOfListing listings = null;

    public AuctionApp() {
        runApp();
    }

    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu(loggedIn);
            System.out.println(loggedIn);
            command = input.next();
            if (!loggedIn) {
                while (!command.equals("1") && !command.equals("2") && !command.equals("3")) {
                    System.out.println("Please enter a valid command.");
                    command = input.next();
                }
                if (command.equals("3")) {
                    keepGoing = false;
                } else {
                    processCommand(command);
                }
            } else {
                while (!command.equals("1") && !command.equals("2") && !command.equals("3") && !command.equals("4")
                        && !command.equals("5") && !command.equals("6") && !command.equals("7")) {
                    System.out.println("Please enter a valid command.");
                    command = input.next();
                }
                if (command.equals("6")) {
                    loggedIn = false;
                    user = null;
                } else if (command.equals("7")) {
                    keepGoing = false;
                } else {
                    processCommand(command);
                }
            }
        }
        System.out.println("Goodbye!");
    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu(boolean loggedIn) {
        if (!loggedIn) {
            System.out.println("Welcome to the Car Auction App!");
            System.out.println("Please select from the following options (use numbers):");
            System.out.println("\t1. Create a new account");
            System.out.println("\t2. Log in to an existing account");
            System.out.println("\t3. Exit");
        } else {
            System.out.println("Welcome to the Car Auction App!");
            System.out.println("Please select from the following options (use numbers):");
            System.out.println("\t1. Create a new listing");
            System.out.println("\t2. View all listings");
            System.out.println("\t3. View saved listings");
            System.out.println("\t4. View your listings");
            System.out.println("\t5. View all bids");
            System.out.println("\t6. Logout");
            System.out.println("\t7. Exit");
        }
    }

    private void processCommand(String command) {
        if (!loggedIn) {
            if (command.equals("1")) {
                createAccount();
            } else if (command.equals("2")) {
                login();
            }
        }
        else {
            if (command.equals("1")) {
                createListing();
            } else if (command.equals("2")) {
                viewAllListings();
            } else if (command.equals("3")) {
                viewSavedListings();
            } else if (command.equals("4")) {
                viewYourListings();
            } else if (command.equals("5")) {
                viewAllBids();
            } else if (command.equals("6")) {
                loggedIn = false;
                System.out.println("You have been logged out.");
            }
        }
    }

    private void createAccount() {
        System.out.println("username:");
        String username = input.next();
        System.out.println("password:");
        String password = input.next();
        System.out.println("Your username is:");
        System.out.println(username);
        System.out.println("and your password is:");
        System.out.println(password);
        System.out.println("Is this correct?");
        System.out.println("Y/N");
        String command = input.next();
        command = command.toLowerCase();
        while (!command.equals("y") && !command.equals("n")) {
            System.out.println("Invalid choice. Please retry.");
            command = input.next();
            command = command.toLowerCase();
        }
        if (command.equals("y")) {
            user = new User(username, password);
            this.loggedIn = true;
        } else {
            System.out.println("Please re-enter your username and password.");
            createAccount();
        }

    }

    private void login() {
        // TODO: implement login
    }

    private void createListing() {
        user.createListing();
    }

    private void viewAllListings() {
        // TODO: implement viewAllListings
    }

    private void viewSavedListings() {
        // TODO: implement viewSavedListings
    }

    private void viewYourListings() {
        // TODO: implement viewYourListings
    }

    private void viewAllBids() {

    }
}
