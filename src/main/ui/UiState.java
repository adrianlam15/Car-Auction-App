package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * UiState class (including UI) for the Car Auction application
 */
// use singleton design pattern for simplifying static variables
public abstract class UiState {
    protected static JFrame frame;
    protected static CardLayout cardLayout;
    protected static ArrayList<Bid> bids = new ArrayList<>();
    protected JPanel panel;
    protected static JPanel cards;
    protected static User currentUser;
    protected static Cars listedCars;
    protected static Users users;
    protected static Font robotoFont;
    protected static HashMap<String, String> userMap;
    protected ArrayList<JButton> toSetButtons;

    protected static Login loginUI;
    protected static MainMenu mainMenuUI;
    protected static CreateListing createListingUI;
    protected static ViewListings viewListingsUI;
    protected static ViewYourListings viewYourListingsUI;
    protected static CreateAccount createAccountUI;
    protected static ViewBids viewBidsUI;
    protected static ViewWon viewWonUI;
    protected static JPanel createAccountPanel;
    protected static JPanel loginPanel;
    protected static JPanel mainMenuPanel;
    protected static JPanel createListingPanel;
    protected static JPanel viewListingsPanel;
    protected static JPanel viewYourListingsPanel;
    protected static JPanel viewBidsPanel;
    protected static JPanel viewWonPanel;
    protected ArrayList<JComponent> buttons;
    protected ArrayList<JComponent> inputFields = new ArrayList<>();
    protected static boolean loaded = false;
    protected static double width;
    protected static double height;
    protected static JsonReader jsonReader;
    protected static JsonWriter jsonWriter;
    protected static final String JSON_STORE = "./data/data.json";
    private static Dimension screenSize;
    protected static boolean loggedIn = false;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final ZoneId zoneId = ZoneId.of("America/Los_Angeles");
    protected static final HashMap<String, Boolean> states = new HashMap<>();

    public UiState() {
        toSetButtons = new ArrayList<>();
        buttons = new ArrayList<>();
        inputFields = new ArrayList<>();
        panel = new JPanel();

    }

    // MODIFIES: this
    // EFFECTS: initializes the UI with panel
    protected JPanel initWin() {
        panel.setVisible(true);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: sets the attributes of the buttons
    protected void setAttrButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setBackground(new Color(99, 102, 241));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
            button.setForeground(Color.WHITE);
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(30, 41, 59));
                    button.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(new Color(99, 102, 241));
                    button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                }
            });
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the attributes of the buttons
    protected static void loadFont() {
        try {
            File fontFile = new File("./data/Roboto/Roboto-Regular.ttf");
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(robotoFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract JPanel loadPanel();

    // MODIFIES: this
    // EFFECTS: saves data of program to JSON_STORE
    public static void save() {
        try {
            jsonWriter.open();
            LocalDateTime now = LocalDateTime.now();
            ZonedDateTime zonedTime = ZonedDateTime.of(now, zoneId);
            String formattedNow = zonedTime.format(formatter);
            jsonWriter.write(formattedNow, users);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads data of program from JSON_STORE
    // SIDE NOTE: program no longer lazy-loads
    protected static void load() {
        if (!loaded) {
            try {
                for (User user : users.getUsers()) {
                    for (Car car : user.getCars()) {
                        listedCars.addCar(car);
                    }
                }
                String date = jsonReader.readDate();
                for (User user : users.getUsers()) {
                    if (user.getUsername().equals(currentUser.getUsername())) {
                        currentUser = user;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Data loaded successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data already loaded.");
        }
        loaded = true;
    }

    protected static void updateState(UiState state) {
        // implement method
    }

    // MODIFIES: this
    // EFFECTS: sets appropriate JSON reader and writer
    protected static void init() {
        setReadAndWrite();
    }

    // MODIFIES: this
    // EFFECTS: initializes the frame
    protected static void initFrame() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        frame = new JFrame("Car Auction App");
        setScreen();
        frame.setResizable(false);
        frame.setLayout(cardLayout);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        setFrameListener();
    }

    // MODIFIES: this
    // EFFECTS: applies appropriate window listener to frame
    protected static void setFrameListener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int exitConfirmation = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);
                handleExit(exitConfirmation);
            }
        });
    }

    // REQUIRES: exitConfirmation is either JOptionPane.YES_OPTION or JOptionPane.NO_OPTION
    // EFFECTS: determines if user wants to exit program
    private static void handleExit(int exitConfirmation) {
        if (loggedIn) {
            if (exitConfirmation == JOptionPane.YES_OPTION) {
                int saveConfirmation = JOptionPane.showConfirmDialog(null,
                        "Do you want to save your data?",
                        "Save Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (saveConfirmation == JOptionPane.YES_OPTION) {
                    save();
                }
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDate() + ": " + event.getDescription());
                }
                System.exit(0);
            }
        } else {
            if (exitConfirmation == JOptionPane.YES_OPTION) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDate() + ": " + event.getDescription());
                }
                System.exit(0);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the size of the frame
    protected static void setScreen() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        int winWidth = (int) (width * 0.5);
        int winHeight = (int) (height * 0.5);
        frame.setSize(winWidth, winHeight);
    }

    // MODIFIES: this
    // EFFECTS: initializes the initial state of the program
    protected static void initUIState() {
        currentUser = new User();
        listedCars = new Cars();
        users = new Users();
        bids = new ArrayList<>();
        loadFont();
        userMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: sets the appropriate JSON reader and writer
    protected static void setReadAndWrite() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: loads users from JSON_STORE
    protected static void initUsers() throws IOException {
        currentUser = new User();
        users = jsonReader.readUsers();
        userMap = jsonReader.getUserMap();
    }

    // MODIFIES: this
    // EFFECTS: initializes the panels
    protected static void initPanels() {
        loginPanel = loginUI.initWin();
        mainMenuPanel = mainMenuUI.initWin();
        createAccountPanel = createAccountUI.initWin();
        createListingPanel = createListingUI.initWin();
        viewListingsPanel = viewListingsUI.initWin();
        viewYourListingsPanel = viewYourListingsUI.initWin();
        viewBidsPanel = viewBidsUI.initWin();
        viewWonPanel = viewWonUI.initWin();
    }

    // MODIFIES: this
    // EFFECTS: adds the panels to the cards
    protected static void addCards() {
        cards.add(loginPanel, "loginMenu");
        cards.add(mainMenuPanel, "mainMenu");
        cards.add(createAccountPanel, "createAccount");
        cards.add(createListingPanel, "createListing");
        cards.add(viewListingsPanel, "viewListings");
        cards.add(viewYourListingsPanel, "viewYourListings");
        cards.add(viewBidsPanel, "viewBids");
        cards.add(viewWonPanel, "viewWon");
    }

    // MODIFIES: this
    // EFFECTS: helper function for creating common buttons found in all states
    protected void addButton(String option) {
        String optionName = option.substring(0, 1).toUpperCase() + option.substring(1);
        JButton optionButton = new JButton(optionName);
        optionButton.addActionListener(e -> {
            if (option.equals("save")) {
                save();
            } else if (option.equals("load")) {
                load();
            } else if (option.equals("logout")) {
                loggedIn = false;
                currentUser = new User();
                cards.remove(loginPanel);
                loginPanel = loginUI.initWin();
                cards.add(loginPanel, "loginMenu");
                cardLayout.show(cards, "loginMenu");
            } else {
                throw new UnsupportedOperationException("Invalid option");
            }
        });
        buttons.add(optionButton);
    }

    // REQUIRES: buttons is not null, ignoreButton is not null
    // MODIFIES: this
    // EFFECTS: helper function for creating common buttons found in all states
    protected void setStateButtons(ArrayList<JComponent> buttons, JButton ignoreButton) {
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            if (button != ignoreButton) {
                toSetButtons.add((JButton) button);
            } else {
                Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
                ignoreButton.setBorder(border);
                ignoreButton.setForeground(Color.WHITE);
            }
            i++;
        }
        setAttrButtons(toSetButtons);
    }

    // REQUIRES: button is not null
    // MODIFIES: this
    // EFFECTS: sets the attributes of the current, active button
    protected void setCurrentButton(JButton button) {
        button.setBackground(new java.awt.Color(30, 41, 59));
        button.setFocusPainted(false);
    }
}