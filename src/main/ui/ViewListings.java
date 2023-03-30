package ui;

import model.Bid;
import model.Car;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * ViewListings class (including UI) for the Car Auction application
 */
public class ViewListings extends UiState {

    public ViewListings() {
        super();
    }

    // MODIFIES: UiState
    // EFFECTS: initializes the UI for the ViewListings state
    protected JPanel initWin() {
        super.initWin();
        return loadPanel();
    }

    // MODIFIES: UiState
    // EFFECTS: loads the UI for the ViewListings state
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

    // EFFECTS: initializes the buttons for the ViewListings state and scrollpane
    private JScrollPane listingInfo() {
        JPanel listingPanel = new JPanel();
        listingPanel.setLayout(null);
        listingPanel.setBackground(new Color(15, 23, 42));
        getListings().forEach(listing -> listingPanel.add(listing));
        JLabel headerLabel = new JLabel("Click a car to bid on it!");
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

    // EFFECTS: initializes the buttons for the ViewListings state
    private ArrayList<JComponent> getListings() {
        ArrayList<JComponent> listings = new ArrayList<>();
        Font buttonFont = robotoFont.deriveFont(12f);
        int i = 2;
        for (Car car : listedCars.getCars()) {
            String carInfo = car.getCondition() + " " + car.getYear() + " " + car.getMake() + " " + car.getModel();
            JButton listing = new JButton(carInfo);
            setAttrListing(listing, buttonFont, i);
            setListeners(listing);
            handleBidAbility(car, listing);
            listings.add(listing);
            car.setId(i);
            i++;
        }
        return listings;
    }

    // MODIFIES, User, Car, Cars, UiState
    // EFFECTS: handles if car is expired or current user owns it
    private void handleBidAbility(Car car, JButton listing) {
        if (car.isExpired()) {
            if (currentUser.getCars().contains(car)) {
                removeCarOption(listing, car);
            } else {
                handleCarBidError(listing, car);
            }
        } else {
            if (currentUser.getCars().contains(car)) {
                handleCarBidError(listing, car);
            } else {
                listing.addActionListener(e -> {
                    showCarInfo(car);
                });
            }
        }
    }

    // REQUIRES: listing is not null, car is not null
    // EFFECTS: if car is expired, show user, else show error that you can't bid on your own car
    private void handleCarBidError(JButton listing, Car car) {
        listing.setEnabled(false);
        JLabel hoverText = new JLabel();
        if (car.isExpired()) {
            hoverText.setText("This car has expired!");
        } else {
            hoverText.setText("You can't bid on your own car!");
        }
        hoverText.setForeground(Color.WHITE);
        hoverText.setFont(robotoFont.deriveFont(12f));
        String listingText = listing.getText();
        setMouseListener(listing, hoverText, listingText);
    }

    // REQUIRES: listing is not null, buttonFont is not null, i is not null
    // EFFECTS: sets the attributes for the listing button
    private void setAttrListing(JButton listing, Font buttonFont, int i) {
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        listing.setFocusPainted(false);
        listing.setBackground(new Color(30,41,59));
        listing.setForeground(new Color(148,163,184));
        listing.setFont(buttonFont);
        listing.setBounds((frame.getWidth()) / 2 - 200, (frame.getHeight()) / 2 - 275 + (i * 50),
                300, 40);
        listing.setBorder(border);
    }

    // EFFECTS: sets the mouse listeners for the error in bidding
    private void setMouseListener(JButton listing, JLabel hoverText, String listingText) {
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

    // EFFECTS: sets default listeners for the buttons
    private void setListeners(JButton listing) {
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
    }

    // REQUIRES: listing is not null, car is not null
    // MODIFIES, User, Car, Cars, UiState
    // EFFECTS: removes the car from the auction
    private void removeCarOption(JButton listing, Car car) {
        String listingText = listing.getText();
        JLabel hoverText = new JLabel("Remove this car from the auction?");
        hoverText.setForeground(Color.WHITE);
        hoverText.setFont(robotoFont.deriveFont(12f));
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

            @Override
            public void mousePressed(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to "
                                + "remove this car from the auction?",
                        "Remove Car", JOptionPane.YES_NO_OPTION);
                handleRemove(result, car);
            }
        });
    }

    // REQUIRES: result is not null, car is not null
    // MODIFIES, User, Car, Cars, UiState
    // EFFECTS: removes the car from the auction
    private void handleRemove(int result, Car car) {
        if (result == JOptionPane.YES_OPTION) {
            listedCars.removeCar(car);
            currentUser.getCars().remove(car);
            cards.remove(viewListingsPanel);
            viewListingsPanel = new ViewListings().initWin();
            cards.add(viewListingsPanel, "viewListings");
            cardLayout.show(cards, "viewListings");
            JOptionPane.showMessageDialog(frame,
                    "Listing removed!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // REQUIRES: car is not null
    // EFFECTS: shows the car information
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
                + "Time left: " + car.getTimer() + " seconds" + "\n";
        if (car.getHighestBid() == null) {
            message = message + "Highest bid: None";
        } else {
            message = message + "Highest bid: $" + car.getHighestBid().getBidAmount();
        }
        JOptionPane.showMessageDialog(frame, message, "Car Information", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(currentUser.getUsername());
        placeBid(car);
    }

    // REQUIRES: car is not null
    // MODIFIES, User, Car, Cars, UiState
    // EFFECTS: places a bid on the car
    private void placeBid(Car car) {
        String input = JOptionPane.showInputDialog(frame, "Enter your bid amount:");
        try {
            int bidAmount = Integer.parseInt(input);
            if (bidAmount < 0) {
                JOptionPane.showMessageDialog(frame, "Your bid must be a positive number!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (bidAmount < car.getPrice()) {
                JOptionPane.showMessageDialog(frame, "Your bid must be higher than the current price!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            handleBid(car, bidAmount);
            JOptionPane.showMessageDialog(frame, "Your bid of $" + bidAmount + " has been placed.",
                    "Bid Placed", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // REQUIRES: car is not null, bidAmount is not null
    // MODIFIES, User, Car, Cars, UiState
    // EFFECTS: removes the previous bid on the car (if applicable) and places a new bid
    private void handleBid(Car car, int bidAmount) {
        for (Bid bid : currentUser.getBids()) {
            if (bid.getCar() == car) {
                currentUser.getBids().remove(bid);
                currentUser.placeBid(car.getId(), bidAmount, listedCars);
                break;
            }
        }
        currentUser.placeBid(car.getId(), bidAmount, listedCars);
    }

    // MODIFIES: UiState
    // EFFECTS: initializes the menu buttons for the state
    @SuppressWarnings("methodlength")
    private ArrayList<JComponent> getJButtons() {
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cards.remove(createListingPanel);
            createListingUI = new CreateListing();
            createListingPanel = createListingUI.initWin();
            cards.add(createListingPanel, "createListing");
            System.out.println(UiState.listedCars.getCars().size());
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        setCurrentButton(viewListings);
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
            System.out.println(currentUser.getUsername());
            System.out.println(currentUser.getBids().size());
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

        setStateButtons(buttons, viewListings);
        return buttons;
    }
}
