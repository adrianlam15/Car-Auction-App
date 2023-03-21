package ui;

import model.Users;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MainMenu class (including UI) for the Car Auction application
 */
public class MainMenu extends UiState {
    private Users users;
    private HashMap<String, String> userMap;
    private ArrayList<JButton> toSetButtons;

    public MainMenu(CardLayout cardLayout, JPanel cards,
                    Users users, HashMap<String, String> userMap, JFrame frame) {
        super(cardLayout, cards, frame);
        this.users = users;
        this.userMap = userMap;
        this.toSetButtons = new ArrayList<>();
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
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        buttons.add(viewCurrentBids);

        JButton viewWonCars = new JButton("View Won Cars");
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

        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(buttonFont);
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
    }

}
