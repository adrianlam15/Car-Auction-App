package ui;

import model.Car;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ViewYourListings extends UiState {

    public ViewYourListings() {
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

        panel.add(listingInfo());
        panel.add(buttonPanel);
        return panel;
    }

    private JScrollPane listingInfo() {
        JPanel listingPanel = new JPanel();
        listingPanel.setLayout(null);
        listingPanel.setBackground(new Color(15, 23, 42));
        getYourListings().forEach(listing -> listingPanel.add(listing));
        JLabel headerLabel = new JLabel("Your Listings!");
        headerLabel.setFont(robotoFont.deriveFont(20f));
        headerLabel.setForeground(new Color(148, 163, 184));
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
     *
     * @return ArrayList of JButtons
     */
    private ArrayList<JComponent> getYourListings() {
        ArrayList<JComponent> listings = new ArrayList<>();
        Font buttonFont = robotoFont.deriveFont(12f);
        int i = 2;
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        for (Car car : currentUser.getCars()) {
            String carInfo = car.getCondition() + " " + car.getYear() + " " + car.getMake() + " " + car.getModel();
            JButton listing = new JButton(carInfo);
            listing.setFocusPainted(false);
            listing.setBackground(new Color(30, 41, 59));
            listing.setForeground(new Color(148, 163, 184));
            listing.setFont(buttonFont);
            listing.setBounds((frame.getWidth()) / 2 - 200, (frame.getHeight()) / 2 - 275 + (i * 50)
                    , 300, 40);
            listing.setBorder(border);
            listing.addActionListener(e -> {
                showCarInfo(car);
            });
            listing.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    listing.setBackground(new Color(30, 41, 59));
                    listing.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                    listing.setForeground(Color.WHITE);
                }

                public void mouseExited(MouseEvent evt) {
                    listing.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                    listing.setForeground(new Color(148, 163, 184));
                }
            });
            if (car.isExpired()) {
                listing.setEnabled(false);
                listing.setBackground(new Color(30, 41, 59));
                listing.setForeground(new Color(148, 163, 184));
                listing.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                JLabel hoverText = new JLabel("Your car listing has expired!");
                hoverText.setForeground(Color.WHITE);
                hoverText.setFont(robotoFont.deriveFont(12f));
                String listingText = listing.getText();
                listing.addActionListener(e -> {
                    handleExpiredListing(car);
                });
                listing.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        listing.setText("");
                        listing.add(hoverText);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        listing.remove(hoverText);
                        listing.setText(listingText);
                    }
                });
            }
            listings.add(listing);
            car.setId(i);
            i++;
        }
        return listings;
    }

    private void handleExpiredListing(Car car) {
        String message = "Your car listing has expired! Would you like to remove it from your listings?";
        int reply = JOptionPane.showConfirmDialog(frame, message, "Expired Listing", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            currentUser.getCars().remove(car);
            cards.remove(viewListingsPanel);
            viewListingsPanel = new ViewYourListings().initWin();
            cards.add(viewListingsPanel, "viewListingsPanel");
            cardLayout.show(cards, "viewListingsPanel");
        }
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
        placeBid(car);
    }

    private void placeBid(Car car) {
        String input = JOptionPane.showInputDialog(frame, "Enter your bid amount:");
        try {
            double bidAmount = Double.parseDouble(input);
            if (bidAmount < 0) {
                JOptionPane.showMessageDialog(frame, "Your bid must be a positive number!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (bidAmount < car.getPrice()) {
                JOptionPane.showMessageDialog(frame, "Your bid must be higher than the current price!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(frame, "Your bid of $" + bidAmount + " has been placed.",
                    "Bid Placed", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     *
     * @return ArrayList of JButtons
     */
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
        viewYourListings.setBackground(new java.awt.Color(30, 41, 59));
        viewYourListings.setFocusPainted(false);
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

        setButtons(buttons, viewYourListings);
        return buttons;
    }

    private void setButtons(ArrayList<JComponent> buttons, JButton viewYourListings) {
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            if ((JButton) button != viewYourListings) {
                toSetButtons.add((JButton) button);
            } else {
                viewYourListings.setBorder(border);
                viewYourListings.setForeground(Color.WHITE);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
    }
}