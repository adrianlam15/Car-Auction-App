package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;

/**
 * MainMenu class (including UI) for the Car Auction application
 */
public class MainMenu extends UiState {

    public MainMenu() {
        super();
    }

    /**
     * Initializes the UI for the MainMenu state
     * @return JPanel of the MainMenu UI
     */
    protected JPanel initWin() {
        super.initWin();
        return loadPanel();
    }

    /**
     * Loads the UI for the MainMenu state
     * @return JPanel containing all components needed for UI
     */
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
    @SuppressWarnings("methodlength")
    private ArrayList<JComponent> getJButtons() {
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cards.remove(createListingPanel);
            createListingUI = new CreateListing();
            createListingPanel = createListingUI.initWin();
            cards.add(createListingPanel, "createListing");
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.addActionListener(e -> {
            cards.remove(viewListingsPanel);
            viewListingsUI = new ViewListings();
            viewListingsPanel = viewListingsUI.initWin();
            cards.add(viewListingsPanel, "viewListings");
            cardLayout.show(cards, "viewListings");
        });
        buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        viewYourListings.addActionListener(e -> {
            cards.remove(viewYourListingsPanel);
            viewYourListingsUI = new ViewYourListings();
            viewYourListingsPanel = viewYourListingsUI.initWin();
            cards.add(viewYourListingsPanel, "viewYourListings");
            cardLayout.show(cards, "viewYourListings");
        });
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        viewCurrentBids.addActionListener(e -> {
            cards.remove(viewBidsPanel);
            viewBidsUI = new ViewBids();
            viewBidsPanel = viewBidsUI.initWin();
            cards.add(viewBidsPanel, "viewBids");
            cardLayout.show(cards, "viewBids");
        });
        buttons.add(viewCurrentBids);

        JButton viewWonCars = new JButton("View Won Cars");
        viewWonCars.addActionListener(e -> {
            cards.remove(viewWonPanel);
            viewWonUI = new ViewWon();
            viewWonPanel = viewWonUI.initWin();
            cards.add(viewWonPanel, "viewWon");
            cardLayout.show(cards, "viewWon");
        });
        buttons.add(viewWonCars);

        addButton("load");
        addButton("save");
        addButton("logout");

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