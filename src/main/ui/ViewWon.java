package ui;

import model.Car;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ViewWon extends UiState {

    public ViewWon() {
        super();
    }

    protected JPanel initWin() {
        super.initWin();
        return loadPanel();
    }

    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(15, 23, 42));
        buttonPanel.setBounds(0, 0, 100, (frame.getHeight()));
        getJButtons().forEach(button -> buttonPanel.add(button));

        panel.add(wonInfo());
        panel.add(buttonPanel);
        return panel;
    }

    private JScrollPane wonInfo() {
        JPanel listingPanel = new JPanel();
        listingPanel.setLayout(null);
        listingPanel.setBackground(new Color(15, 23, 42));
        getWon().forEach(listing -> listingPanel.add(listing));
        JLabel headerLabel = new JLabel("Your Won Cars!");
        headerLabel.setFont(robotoFont.deriveFont(20f));
        headerLabel.setForeground(new Color(148,163,184));
        headerLabel.setBounds(225, 0, 300, 100);
        listingPanel.add(headerLabel);
        int multiplier = bids.size() * 20;
        listingPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() + multiplier));

        JScrollPane scrollPane = new JScrollPane(listingPanel);
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
    private ArrayList<JComponent> getWon() {
        ArrayList<JComponent> wonCars = new ArrayList<>();
        int i = 2;
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        for (Car car : currentUser.getWonCars()) {
            String carInfo = car.getCondition() + " " + car.getYear() + " " + car.getMake() + " " + car.getModel();
            JButton listing = new JButton(carInfo);
            listing.setFocusPainted(false);
            listing.setBackground(new Color(30,41,59));
            listing.setForeground(new Color(148,163,184));
            listing.setFont(robotoFont.deriveFont(12f));
            listing.setBounds((frame.getWidth()) / 2 - 200, (frame.getHeight()) / 2 - 275 + (i * 50),
                    300, 40);
            listing.setBorder(border);
            listing.addActionListener(e -> {
                showCarInfo(car);
            });
            listing.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    listing.setBackground(new Color(30,41,59));
                    listing.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                }

                public void mouseExited(MouseEvent evt) {
                    listing.setBackground(new Color(30,41,59));
                    listing.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                }
            });
            wonCars.add(listing);
            i++;
        }
        return wonCars;
    }

    private void showCarInfo(Car car) {
        String message = "Condition: " + car.getCondition() + "\n"
                + "Transmission: " + car.getTransmission() + "\n"
                + "Color: " + car.getColour() + "\n"
                + "DriveType: " + car.getDriveType() + "\n"
                + "Year: " + car.getYear() + "\n"
                + "Make: " + car.getMake() + "\n"
                + "Model: " + car.getModel() + "\n"
                + "Mileage: " + car.getMileage() + "\n"
                + "Price: $" + car.getPrice() + "\n"
                + "Description: " + car.getDescription() + "\n"
                + "Time left: " + car.getTimer() + " seconds" + "\n"
                + "Highest Bid: $" + car.getHighestBid().getBidAmount() + "\n";
        JOptionPane.showMessageDialog(frame, message, "Car Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
    private ArrayList<JComponent> getJButtons() {
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cards.remove(createListingPanel);
            createListingPanel = new CreateListing().initWin();
            cards.add(createListingPanel, "createListing");
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.addActionListener(e -> {
            cards.remove(viewListingsPanel);
            viewListingsPanel = new ViewListings().initWin();
            cards.add(viewListingsPanel, "viewListings");
            cardLayout.show(cards, "viewListings");
        });
        buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        viewYourListings.addActionListener(e -> {
            cards.remove(viewYourListingsPanel);
            viewYourListingsPanel = new ViewYourListings().initWin();
            cards.add(viewYourListingsPanel, "viewYourListings");
            cardLayout.show(cards, "viewYourListings");
        });
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        viewCurrentBids.addActionListener(e -> {
            cards.remove(viewBidsPanel);
            viewBidsPanel = new ViewBids().initWin();
            cards.add(viewBidsPanel, "viewBids");
            cardLayout.show(cards, "viewBids");
        });
        buttons.add(viewCurrentBids);

        JButton viewWonCars = new JButton("View Won Cars");
        viewWonCars.setBackground(new java.awt.Color(30, 41, 59));
        viewWonCars.setFocusPainted(false);
        buttons.add(viewWonCars);

        addButton("load");
        addButton("save");
        addButton("logout");

        setStateButtons(buttons, viewWonCars);
        return buttons;

    }
}
