package ui;


import javax.swing.*;
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
     * @return JPanel of the MainMenu UI
     */
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));

        addButton("Create Listing", "createListing");
        addButton("View Listings", "viewListings");
        addButton("View Your Listings", "viewYourListings");
        addButton("View Current Bids", "viewBids");
        addButton("View Won Cars", "viewWon");
        addButton("Load Up-to-Date Data", null);
        addButton("Save Current Data", null);
        addButton("Logout", "loginMenu");
        int i = 1;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 275 + (i * 50), 100, 40);
            toSetButtons.add((JButton) button);
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return panel;
    }

    /**
     * Adds a button to the MainMenu UI
     * @param text text to be displayed on the button
     * @param cardName name of the card to be displayed when the button is clicked
     */
    protected void addButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setFont(robotoFont.deriveFont(10f));

        if (cardName != null) {
            button.addActionListener(e -> cardLayout.show(cards, cardName));
        } else if (text.equals("Load Up-to-Date Data")) {
            button.addActionListener(e -> AuctionApp.load());
        } else if (text.equals("Save Current Data")) {
            button.addActionListener(e -> AuctionApp.save());
        } else if (text.equals("Logout")) {
            button.addActionListener(e -> {
                System.out.println("Logging out...");
                cardLayout.show(cards, "loginMenu");
            });
        }
        buttons.add(button);
        toSetButtons.add(button);
        panel.add(button);
    }

    /**
     * Loads the UI for the MainMenu state
     * @return JPanel containing all components needed for UI
     */
    /**
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }
    /*

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
    /**
    private ArrayList<JComponent> getJButtons() {
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
        loadUpToDateData.addActionListener(e -> {
            AuctionApp.load();
        });
        buttons.add(loadUpToDateData);

        JButton saveCurrentData = new JButton("Save Current Data");
        saveCurrentData.addActionListener(e -> {
            AuctionApp.save();
        });
        buttons.add(saveCurrentData);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            System.out.println("Logging out...");
            cardLayout.show(cards, "loginMenu");
            });
        buttons.add(logout);

        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            toSetButtons.add((JButton) button);
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
    }*/
}
