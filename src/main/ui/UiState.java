package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
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
public abstract class UiState {
    protected static JFrame frame;
    protected static CardLayout cardLayout;
    protected static ArrayList<Bid> bids;
    protected JPanel panel;
    protected static JPanel cards;
    protected static User currentUser;
    protected static Cars listedCars;
    protected static Users users;
    protected static Font robotoFont;
    protected static HashMap<String, String> userMap;
    protected ArrayList<JButton> toSetButtons;
    protected static volatile Login loginUI;
    protected static volatile MainMenu mainMenuUI;
    protected static volatile CreateListing createListingUI;
    protected static volatile ViewListings viewListingsUI;
    protected static volatile ViewYourListings viewYourListingsUI;
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
    protected static HashMap<String, Boolean> activeClass = new HashMap<>();
    protected static ArrayList<JComponent> timers = new ArrayList<>();
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

    /**
     * Constructs a new UiState
     */
    public UiState() {
        toSetButtons = new ArrayList<>();
        buttons = new ArrayList<>();
        inputFields = new ArrayList<>();
        panel = new JPanel();

    }

    /**
     * Initializes the UI state
     * @return the JPanel of the UI state
     */
    protected JPanel initWin() {
        panel.setVisible(true);
        return panel;
    }

    /**
     * Sets the attributes of the buttons
     * @param buttons
     */
    protected void setAttrButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setBackground(new Color(99, 102, 241));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
            button.setForeground(Color.WHITE);
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(30,41,59));
                    button.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(new Color(99,102,241));
                    button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                }
            });
        }
    }

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

    protected static void updateListingPanel() {
        viewListingsPanel = viewListingsUI.initWin();
    }

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

    protected static void load() {
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

    protected static void updateState(UiState state) {
        // implement method
    }

    protected static void init() {
        setReadAndWrite();
    }

    protected static void initFrame() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        frame = new JFrame("Car Auction App");
        setScreen();
        frame.setResizable(false);
        frame.setLayout(cardLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        setFrameListener();
    }

    protected static void setFrameListener() {
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
    
    protected static void setScreen() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        int winWidth = (int) (width * 0.5);
        int winHeight = (int) (height * 0.5);
        frame.setSize(winWidth, winHeight);
    }
    
    protected static void initUIState() {
        UiState.currentUser = new User();
        UiState.listedCars = new Cars();
        UiState.users = new Users();
        UiState.bids = new ArrayList<>();
        UiState.loadFont();
        UiState.userMap = new HashMap<>();
    }

    protected static void setReadAndWrite() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    protected static void initUsers() throws IOException {
        currentUser = new User();
        users = jsonReader.readUsers();
        userMap = jsonReader.getUserMap();
    }

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

    protected void loadSave(String option) {
        JButton optionButton = null;
        if (option.equals("save")) {
            optionButton = new JButton("Save");
            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    save();
                }
            });
        } else if (option.equals("load")) {
            optionButton = new JButton("Load");
            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    load();
                }
            });
        }
        buttons.add(optionButton);
    }
}