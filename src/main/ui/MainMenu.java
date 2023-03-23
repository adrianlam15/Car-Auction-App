package ui;

import model.Car;
import model.Cars;
import model.User;
import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MainMenu class (including UI) for the Car Auction application
 */
public class MainMenu extends UiState {
    private Users users;
    private HashMap<String, String> userMap;
    private ArrayList<JButton> toSetButtons;
    private CreateListing createListingUI;
    private ViewListings viewListingsUI;
    private ViewYourListings viewYourListingsUI;
    private ViewBids viewBidsUI;
    private ViewWon viewWonUI;
    private ArrayList<UiState> uiStates;

    public MainMenu(CardLayout cardLayout, JPanel cards,
                    Users users, HashMap<String, String> userMap, JFrame frame) {
        super(cardLayout, cards, frame);
        this.users = users;
        this.userMap = userMap;
        this.toSetButtons = new ArrayList<>();
        createListingUI = new CreateListing(cardLayout, cards, users, userMap, frame, this);
        viewListingsUI = new ViewListings(cardLayout, cards, users, userMap, frame);
        viewYourListingsUI = new ViewYourListings(cardLayout, cards, users, userMap, frame);
        viewBidsUI = new ViewBids(cardLayout, cards, users, userMap, frame);
        viewWonUI = new ViewWon(cardLayout, cards, users, userMap, frame);
        uiStates = new ArrayList<>();
        uiStates.add(createListingUI);
        uiStates.add(viewListingsUI);
        uiStates.add(viewYourListingsUI);
        uiStates.add(viewBidsUI);
        uiStates.add(viewWonUI);

        JPanel createListingPanel = createListingUI.initWin();
        JPanel viewListingsPanel = viewListingsUI.initWin();
        JPanel viewYourListingsPanel = viewYourListingsUI.initWin();
        JPanel viewBidsPanel = viewBidsUI.initWin();
        JPanel viewWonPanel = viewWonUI.initWin();
        cards.add(createListingPanel, "createListing");
        cards.add(viewListingsPanel, "viewListings");
        cards.add(viewYourListingsPanel, "viewYourListings");
        cards.add(viewBidsPanel, "viewBids");
        cards.add(viewWonPanel, "viewWon");
    }

    /**
     * Initializes the UI for the MainMenu state
     * @return JPanel of the MainMenu UI
     */
    protected JPanel initWin() {
        super.initWin();
        return loadMainMenuPanel();
    }

    /**
     * Loads the UI for the MainMenu state
     * @return JPanel containing all components needed for UI
     */
    private JPanel loadMainMenuPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
    private ArrayList<JComponent> getJButtons() {
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);
        ArrayList<JComponent> buttons = new ArrayList<>();

        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.addActionListener(e -> {
            cardLayout.show(cards, "viewListings");
        });
        buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        viewYourListings.addActionListener(e -> {
            cardLayout.show(cards, "viewYourListings");
        });
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        viewCurrentBids.addActionListener(e -> {
            cardLayout.show(cards, "viewBids");
        });
        buttons.add(viewCurrentBids);

        JButton viewWonCars = new JButton("View Won Cars");
        viewWonCars.addActionListener(e -> {
            cardLayout.show(cards, "viewWon");
        });
        buttons.add(viewWonCars);

        JButton loadUpToDateData = new JButton("Load Up-to-Date Data");
        buttons.add(loadUpToDateData);

        JButton saveCurrentData = new JButton("Save Current Data");
        buttons.add(saveCurrentData);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            System.out.println("Logging out...");
            cardLayout.show(cards, "loginMenu");
            });
        buttons.add(logout);

        Border border = BorderFactory.createLineBorder(new java.awt.Color(15, 23, 42), 1);
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(buttonFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            button.setBorder(border);
            toSetButtons.add((JButton) button);
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
    }

    public void setUsers(Users users) {
        super.setUsers(users);
        updateUsers();
    }

    private void updateUsers() {
        for (UiState uiState : uiStates) {
            System.out.println("updating users in " + uiState.getClass().getName());
            System.out.println(users.getUsers().size());
            uiState.setUsers(users);
        }
    }

    public void setListedCars(Cars listedCars) {
        super.setListedCars(listedCars);
        updateListedCars();
    }

    private void updateListedCars() {
        for (UiState uiState : uiStates) {
            System.out.println("updating listed cars in " + uiState.getClass().getName());
            uiState.setListedCars(listedCars);
            System.out.println(listedCars.getCars().size());
        }
    }

    public void setCurrentUser(User currentUser) {

        for (UiState uiState : uiStates) {
            System.out.println(uiState.currentUser.getUsername());
        }
    }

    public void updateCurrentUser() {
        for (UiState uiState : uiStates) {
            System.out.println("updating current user in " + uiState.getClass().getName());
            uiState.setCurrentUser(currentUser);
        }
    }

    public void addCar(Car carToAdd) {
        super.addCar(carToAdd);
        for (UiState uiState : uiStates) {
            uiState.addCar(carToAdd);
            System.out.println(uiState.listedCars.getCars().size());
        }
        /**for (UiState uiState : uiStates) {
            uiState.addCars(carToAdd);
            System.out.println("adding car to " + uiState.getClass().getName());
            System.out.println(listedCars.getCars().size());
        }
         */
    }
}
