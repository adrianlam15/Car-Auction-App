package ui;

import model.User;

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
            System.out.println(loggedIn);
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
            System.out.println("\nMain menu");
            System.out.println("Use numbers to select choices:");
            System.out.println("\t1. Create a listing.");
            System.out.println("\t4. Place a bid.");
            System.out.println("\t2. View listings.");
            System.out.println("\t3. View current bids.");
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
            } else {
                System.out.println("Enter a username:");
                String usr = input.next();
                System.out.println("Enter a password:");
                String pwd = input.next();
                System.out.println("Re-enter your password:");
                String checkPwd = input.next();
                if (user.createUser(usr, pwd, checkPwd)) {
                    System.out.println("Account created successfully.");
                    loggedIn = user.login(usr, pwd);
                } else {
                    System.out.println("Passwords don't match.");
                    processCommand("1");
                }
            }
        } else {
            System.out.println("Process main menu commands...");
        }
    }
}
