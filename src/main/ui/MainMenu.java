package ui;

import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MainMenu class (including UI) for the Car Auction application
 */
public class MainMenu extends UiState {

    public MainMenu() {
        super();
        UiState.createListingUI = new CreateListing();
        UiState.viewListingsUI = new ViewListings();
        UiState.viewYourListingsUI = new ViewYourListings();
        UiState.viewBidsUI = new ViewBids();
        UiState.viewWonUI = new ViewWon();

        UiState.createListingPanel = UiState.createListingUI.initWin();
        UiState.viewListingsPanel = UiState.viewListingsUI.initWin();
        UiState.viewYourListingsPanel = UiState.viewYourListingsUI.initWin();
        UiState.viewBidsPanel = UiState.viewBidsUI.initWin();
        UiState.viewWonPanel = UiState.viewWonUI.initWin();

        UiState.cards.add(UiState.createListingPanel, "createListing");
        UiState.cards.add(UiState.viewListingsPanel, "viewListings");
        UiState.cards.add(UiState.viewYourListingsPanel, "viewYourListings");
        UiState.cards.add(UiState.viewBidsPanel, "viewBids");
        UiState.cards.add(UiState.viewWonPanel, "viewWon");
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
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            button.setBorder(border);
            toSetButtons.add((JButton) button);
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
    }
}
