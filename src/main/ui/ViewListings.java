package ui;

import model.Car;
import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ViewListings class (including UI) for the Car Auction application
 */
public class ViewListings extends UiState {

    /**
     * Constructor for the ViewListings class
     */
    public ViewListings() {
        super();
    }

    /**
     * Initializes the UI for the ViewListings state
     * @return JPanel of the ViewListings UI
     */
    protected JPanel initWin() {
        super.initWin();
        return loadPanel();
    }

    /**
     * Loads the UI for the ViewListings state
     * @return JPanel of the ViewListings UI
     */
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(15, 23, 42));
        buttonPanel.setBounds(0, 0, 100, (frame.getHeight()));
        getJButtons().forEach(button -> buttonPanel.add(button));
        panel.add(buttonPanel);

        panel.add(getListingPanel());
        return panel;
    }


    private JScrollPane getListingPanel() {
        JPanel listingsPanel = new JPanel();
        listingsPanel.setLayout(null);
        listingsPanel.setBackground(new Color(15, 23, 42));
        getListings().forEach(listing -> listingsPanel.add(listing));
        System.out.println(frame.getHeight());
        int multiplier = UiState.listedCars.getCars().size() * 20;
        listingsPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() + multiplier));

        JScrollPane scrollPane = new JScrollPane(listingsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(new Color(15, 23, 42));
        scrollPane.setBounds(100, 0, frame.getWidth() - 110, frame.getHeight());
        return scrollPane;
    }



    /**
     * Gets the list of JButtons for the ViewListings state
     * @return ArrayList of JButtons
     */
    private ArrayList<JComponent> getListings() {
        ArrayList<JComponent> listings = new ArrayList<>();
        int i = 1;
        String carInfo;
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        for (Car car : UiState.listedCars.getCars()) {
            carInfo = car.getCondition() + " " + car.getYear() + " " + car.getMake() + " " + car.getModel();
            JButton listing = new JButton(carInfo);
            listing.setFocusPainted(false);
            listing.setBackground(new Color(30,41,59));
            listing.setForeground(new Color(148,163,184));
            listing.setFont(robotoFont.deriveFont(12f));
            listing.setBounds((frame.getWidth()) / 2 - 250, (frame.getHeight()) / 2 - 275 + (i * 50),
                    300, 40);
            listing.setBorder(border);
            listing.addActionListener(e -> {

            });
            listing.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    listing.setBackground(new Color(30,41,59));
                    listing.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                    listing.setForeground(Color.WHITE);
                }

                public void mouseExited(MouseEvent evt) {
                    listing.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                    listing.setForeground(new Color(148,163,184));
                }
            });
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
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            System.out.println(UiState.listedCars.getCars().size());
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
            System.out.println("bidding view");
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
            System.out.println("Data loaded");
            AuctionApp.load();
        });
        buttons.add(loadUpToDateData);

        JButton saveCurrentData = new JButton("Save Current Data");
        saveCurrentData.addActionListener(e -> {
            System.out.println("Data saved");
            AuctionApp.save();
        });
        buttons.add(saveCurrentData);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            cardLayout.show(cards, "loginMenu");
        });
        buttons.add(logout);

        setButtons(buttons, viewListings);
        return buttons;
    }

    private void setButtons(ArrayList<JComponent> buttons, JButton viewListings) {
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            if ((JButton) button != viewListings) {
                toSetButtons.add((JButton) button);
            } else {
                viewListings.setBorder(border);
                viewListings.setForeground(Color.WHITE);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
    }
}
