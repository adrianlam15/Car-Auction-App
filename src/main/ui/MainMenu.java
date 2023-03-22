package ui;

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
    private Login login;
    private Users users;
    private HashMap<String, String> userMap;
    private ArrayList<JButton> toSetButtons;
    private CreateListing createListing;
    private ViewListings viewListings;
    private ViewYourListings viewYourListings;
    private ViewBids viewBids;
    private ViewWon viewWon;

    public MainMenu(CardLayout cardLayout, JPanel cards,
                    Users users, HashMap<String, String> userMap, JFrame frame, User currentUser) {
        super(cardLayout, cards, frame, currentUser);
        this.users = users;
        this.userMap = userMap;
        this.toSetButtons = new ArrayList<>();
        createListing = new CreateListing(cardLayout, cards, users, userMap, frame);
        viewListings = new ViewListings(cardLayout, cards, users, userMap, frame);
        viewYourListings = new ViewYourListings(cardLayout, cards, users, userMap, frame);
        viewBids = new ViewBids(cardLayout, cards, users, userMap, frame);
        viewWon = new ViewWon(cardLayout, cards, users, userMap, frame);

        JPanel createListingPanel = createListing.initWin();
        JPanel viewListingsPanel = viewListings.initWin();
        JPanel viewYourListingsPanel = viewYourListings.initWin();
        JPanel viewBidsPanel = viewBids.initWin();
        JPanel viewWonPanel = viewWon.initWin();
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
            System.out.println("logged in as: " + currentUser.getUsername());
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

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
