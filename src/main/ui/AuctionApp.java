package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.*;


// Represents the Car Auction App User Interface
public class AuctionApp {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final ZoneId zoneId = ZoneId.of("America/Los_Angeles");

    private static final String JSON_STORE = "./data/data.json";
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private Scanner input;
    private static User currentUser;
    private static Users users;
    public static boolean loggedIn;
    private boolean keepGoing = true;
    private static Cars listedCars;
    private ArrayList<Integer> listingID;
    private static HashMap<String, String> userMap;
    private static JFrame frame;
    private Dimension screenSize;
    private double width;
    private double height;
    private static CardLayout cardLayout;
    private static JPanel cards;
    public static boolean loaded;

    public AuctionApp() throws IOException {
        loaded = false;
        frame = new JFrame("Car Auction App");
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        setFrame();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        currentUser = new User();
        users = jsonReader.readUsers();
        userMap = jsonReader.getUserMap();
        runAuctionApp();
    }

    private void setFrame() {
        frame.setResizable(false);
        frame.setLayout(cardLayout);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (loggedIn) {
                    int exitConfirmation = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to exit?",
                            "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (exitConfirmation == JOptionPane.YES_OPTION) {
                        int saveConfirmation = JOptionPane.showConfirmDialog(null,
                                "Do you want to save your data?",
                                "Save Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (saveConfirmation == JOptionPane.YES_OPTION) {
                            save();
                            System.exit(0);
                        }
                    }
                } else {
                    int exitConfirmation = JOptionPane.showConfirmDialog(frame,
                            "You are not logged in. Are you sure you want to exit?",
                            "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (exitConfirmation == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    }
                }
        });

    }

    // MODIFIES: this
    // EFFECTS: runs the Car Auction App
    public void runAuctionApp() {
        listedCars = new Cars();
        /**for (User user : users.getUsers()) {
            for (Car car : user.getCars()) {
                listedCars.addCar(car);
            }
        }
         */
        int winWidth = (int) Math.round(width) / 2;
        int winHeight = (int) Math.round(height) / 2;
        frame.setSize(winWidth, winHeight);
        initUiState();
        frame.add(cards);
        cardLayout.show(cards, "loginMenu");
        frame.setVisible(true);

        /**
         * Everything below this line is near irrelevant besides logic
         */
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loggedIn = false;
        currentUser = new User();
        String command;
        System.out.println("username, password: " + userMap);
        while (keepGoing) {
            if (UiState.listedCars.getCars().size() > listedCars.getCars().size()) {
                listedCars = UiState.listedCars;
            } else {
                UiState.listedCars = listedCars;
            }
            if (currentUser == null & UiState.currentUser != null) {
                currentUser = UiState.currentUser;
            } else {
                UiState.currentUser = currentUser;
            }
            System.out.println("Current User: " + currentUser.getUsername());
            displayMenu(loggedIn);
            command = input.next();
            processCommand(command);
        }
    }

    public static void initUiState() {
        UiState.currentUser = currentUser;
        UiState.bids = currentUser.getBids();
        UiState.loadFont();
        UiState.users = users;
        UiState.listedCars = listedCars;
        UiState.frame = frame;
        UiState.cardLayout = cardLayout;
        UiState.cards = cards;
        UiState.userMap = userMap;
        UiState.loginUI = new Login();
        UiState.loginPanel = UiState.loginUI.initWin();
        UiState.createAccountUI = new CreateAccount();
        UiState.createAccountPanel = UiState.createAccountUI.initWin();
        UiState.mainMenuUI = new MainMenu();
        UiState.mainMenuPanel = UiState.mainMenuUI.initWin();
        UiState.createListingUI = new CreateListing();
        UiState.createListingPanel = UiState.createListingUI.initWin();
        UiState.viewListingsUI = new ViewListings();
        UiState.viewListingsPanel = UiState.viewListingsUI.initWin();
        UiState.viewYourListingsUI = new ViewYourListings();
        UiState.viewYourListingsPanel = UiState.viewYourListingsUI.initWin();
        UiState.viewBidsUI = new ViewBids();
        UiState.viewBidsPanel = UiState.viewBidsUI.initWin();
        UiState.viewBidsUI = new ViewBids();
        UiState.viewBidsPanel = UiState.viewBidsUI.initWin();
        UiState.viewWonUI = new ViewWon();
        UiState.viewWonPanel = UiState.viewWonUI.initWin();
        addCards();
    }

    private static void addCards() {
        cards.add(UiState.loginPanel, "loginMenu");
        cards.add(UiState.createAccountPanel, "createAccountMenu");
        cards.add(UiState.mainMenuPanel, "mainMenu");
        cards.add(UiState.createListingPanel, "createListing");
        cards.add(UiState.viewListingsPanel, "viewListing");
        cards.add(UiState.viewYourListingsPanel, "viewYourListing");
        cards.add(UiState.viewBidsPanel, "viewBids");
        cards.add(UiState.viewWonPanel, "viewWon");
    }

    // EFFECTS: displays the main menu
    private void displayMenu(boolean loggedIn) {
        if (!loggedIn) {
            System.out.println("\nWelcome to the Car Auction App!");
            System.out.println("Use numbers to select choices:");
            System.out.println("\t-> 1. Create an account.");
            System.out.println("\t-> 2. Login.");
            System.out.println("\t-> 3. Exit.");
        } else {
            System.out.println("\n==== Main menu ====");
            System.out.println("Use numbers to select choices:");
            System.out.println("\t-> 1. Create a listing.");
            System.out.println("\t-> 2. View listings.");
            System.out.println("\t-> 3. View your listings.");
            System.out.println("\t-> 4. View current bids.");
            System.out.println("\t-> 5. View won cars.");
            System.out.println("\t-> 6. Load up-to-date data.");
            System.out.println("\t-> 7. Save current data.");
            System.out.println("\t-> 8. Logout.");
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
                if (loggedIn) {
                    for (User user : users.getUsers()) {
                        if (user.getUsername().equals(usr)) {
                            currentUser = user;
                        }
                    }
                }
            } else if (command.equals("1")) {
                handleCreateAccount();
            } else {
                System.out.println("[STATUS]: Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        } else {
            if (command.equals("1")) {
                createListing();
            } else if (command.equals("2")) {
                viewListings();
            } else if (command.equals("3")) {
                viewUserListings();
            } else if (command.equals("4")) {
                showBids();
            } else if (command.equals("5")) {
                viewWonListings();
            } else if (command.equals("6")) {
                load();
            } else if (command.equals("7")) {
                save();
            } else if (command.equals("8")) {
                System.out.println("[STATUS]: Logged out.");
                loggedIn = false;
            } else {
                System.out.println("[STATUS]: Invalid entry. Try again:");
                String retry = input.next();
                processCommand(retry);
            }
        }
    }

    private void createListing() {
        Car car = new Car();
        System.out.println("==== Create a listing ==== ");
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
                System.out.println("\t-> " + bid.getBid());
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
        listingID = new ArrayList<>();
        int id = 1;
        int pos = 1;
        if (currentUser.getCars().isEmpty()) {
            System.out.println("[STATUS]: You have no listings.");
        } else {
            System.out.println("==== Your Listings ====");
            for (Car car : currentUser.getCars()) {
                System.out.println("\t-> " + pos + ". " + car.getListingCar());
                if (car.getHighestBid() == null) {
                    System.out.println("\tNo bids yet.");
                } else {
                    System.out.println("\tHighest bid: " + car.getHighestBid().getBidAmount()
                            + " from " + car.getHighestBid().getUser().getUsername());
                }
                if (car.isExpired()) {
                    System.out.println("\t[STATUS]: Listing expired.");
                }
                listingID.add(id);
                car.setId(id);
                id++;
                pos++;
            }
            System.out.println("Would you like to edit, or delete a listing? (or N to go back) (E/D/N)");
            String choice = input.next().toLowerCase();
            if (choice.equals("n")) {
                return;
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
                System.out.println("\t-> " + pos + ". " + car.getListingCar());
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

    @SuppressWarnings("methodlength")
    // MODIFIES: this, user
    // EFFECTS: creates a new account
    private void handleCreateAccount() {
        System.out.println("Enter a username:");
        String usr = input.next();
        for (User u : users.getUsers()) {
            if (u.getUsername().equals(usr)) {
                System.out.println("[STATUS]: Username already exists. Please try again.");
                handleCreateAccount();
                return;  // add UsernameExistsException
            }
        }
        System.out.println("Enter a password:");
        String pwd = input.next();
        System.out.println("Re-enter your password:");
        String checkPwd = input.next();
        currentUser = new User();
        if (currentUser.createUser(usr, pwd, checkPwd)) {
            System.out.println("[STATUS]: Account created successfully.");
            userMap.put(usr, pwd);
            users.add(currentUser);
        } else {
            System.out.println("[STATUS]: Passwords don't match. Please try again.");
            processCommand("1");
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the data to file
    public static void save() {
        try {
            jsonWriter.open();
            LocalDateTime now = LocalDateTime.now();
            ZonedDateTime zonedTime = ZonedDateTime.of(now, zoneId);
            String formattedNow = zonedTime.format(formatter);
            jsonWriter.write(formattedNow, users);
            jsonWriter.close();
            System.out.println("Saved date from " + formattedNow + " to " + JSON_STORE);
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public static void load() {
        if (!loaded) {
            try {
                for (User user : users.getUsers()) {
                    for (Car car : user.getCars()) {
                        listedCars.addCar(car);
                    }
                }
                String date = jsonReader.readDate();
                //initUiState();    problem is that currentUser is null because according to AuctionApp class,
                //                  currentUser not initialized
                System.out.println(currentUser.getUsername());
                System.out.println(currentUser.getCars().size());
                System.out.println("Loaded data from " + JSON_STORE + " from " + date);
                JOptionPane.showMessageDialog(frame, "Data loaded successfully!");
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
                JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Data already loaded.");
        }
        loaded = true;
    }
}
