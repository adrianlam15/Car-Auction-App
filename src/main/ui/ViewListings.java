package ui;

import model.Car;
import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewListings extends UiState{
    private ArrayList<JButton> toSetButtons;

    public ViewListings(CardLayout cardLayout, JPanel cards, Users users, HashMap<String, String> userMap,
                        JFrame frame) {
        super(cardLayout, cards, frame);
        this.toSetButtons = new ArrayList<>();
    }

    protected JPanel initWin() {
        super.initWin();
        return loadViewListings();
    }

    private JPanel loadViewListings() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getJButtons().forEach(button -> panel.add(button));
        getListings().forEach(listing -> panel.add(listing));
        return panel;
    }

    private ArrayList<JComponent> getListings() {
        ArrayList<JComponent> listings = new ArrayList<>();
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);
        int i = 1;
        String carInfo;
        for (Car car : UiState.listedCars.getCars()) {
            carInfo = car.getCondition() + " " + car.getYear() + " " + car.getMake() + " " + car.getModel();
            JButton listing = new JButton(carInfo);
            listing.setFont(buttonFont);
            listing.setBounds((frame.getWidth()) / 2 - 200, (frame.getHeight()) / 2 - 275 + (i * 50)
                    , 500, 40);
            listings.add(listing);
            car.setId(i);
            i++;
        }
        return listings;
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
    private ArrayList<JComponent> getJButtons() {
        ArrayList<JComponent> buttons = new ArrayList<>();
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.setBackground(new java.awt.Color(30, 41, 59));
        viewListings.setFocusPainted(false);
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
            if ((JButton) button != viewListings) {
                toSetButtons.add((JButton) button);
            } else {
                viewListings.setForeground(Color.WHITE);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
    }
}
