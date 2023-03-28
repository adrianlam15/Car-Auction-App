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

        ArrayList<JComponent> buttons = new ArrayList<>();
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.addActionListener(e -> {
            System.out.println(listedCars.getCars().size());
            viewListingsUI = new ViewListings();
            viewListingsPanel = viewListingsUI.initWin();
            cardLayout.show(cards, "viewListings");
        });
        buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        viewYourListings.addActionListener(e -> {
            viewYourListingsUI = new ViewYourListings();
            viewYourListingsPanel = viewYourListingsUI.initWin();
            cardLayout.show(cards, "viewYourListings");
        });
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        viewCurrentBids.addActionListener(e -> {
            System.out.println(currentUser.getUsername());
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
            load();
        });
        buttons.add(loadUpToDateData);

        JButton saveCurrentData = new JButton("Save Current Data");
        buttons.add(saveCurrentData);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            System.out.println("Logging out...");
            cardLayout.show(cards, "loginMenu");
        });
        buttons.add(logout);
        int i = 1;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 275 + (i * 50), 100, 40);
            toSetButtons.add((JButton) button);
            i++;
        }
        super.setAttrButtons(toSetButtons);
        panel.add(createListing);
        panel.add(viewListings);
        panel.add(viewYourListings);
        panel.add(viewCurrentBids);
        panel.add(viewWonCars);
        panel.add(loadUpToDateData);
        panel.add(saveCurrentData);
        panel.add(logout);
        return panel;
    }

    /**
     * Adds a button to the MainMenu UI
     * @param text text to be displayed on the button
     * @param cardName name of the card to be displayed when the button is clicked
     */
    /**
    protected void addButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setFont(robotoFont.deriveFont(10f));

        if (cardName != null) {
            button.addActionListener(e -> cardLayout.show(cards, cardName));
        } else if (text.equals("Load Up-to-Date Data")) {
            button.addActionListener(e -> load());
        } else if (text.equals("Save Current Data")) {
            button.addActionListener(e -> save());
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
     */
}
