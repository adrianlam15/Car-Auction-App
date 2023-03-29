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
        for (Car car : currentUser.getCars()) {
            String carInfo = car.getCondition() + " " + car.getYear() + " " + car.getMake() + " " + car.getModel();
            JButton listing = new JButton(carInfo);
            setAttrListing(listing, buttonFont, i);
            JLabel hoverText = new JLabel("Click for more info!");
            hoverText.setForeground(Color.WHITE);
            hoverText.setFont(buttonFont);
            String listingText = listing.getText();
            setListener(listing, car, listingText, hoverText);
            listings.add(listing);
            car.setId(i);
            i++;
        }
        return listings;
    }

    private void setAttrListing(JButton listing, Font buttonFont, int i) {
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        listing.setFocusPainted(false);
        listing.setBackground(new Color(30, 41, 59));
        listing.setForeground(new Color(148, 163, 184));
        listing.setFont(buttonFont);
        listing.setBounds((frame.getWidth()) / 2 - 200, (frame.getHeight()) / 2 - 275 + (i * 50),
                300, 40);
        listing.setBorder(border);
    }

    private void setListener(JButton listing, Car car, String listingText, JLabel hoverText) {
        listing.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                listing.setText("");
                listing.setBackground(new Color(30, 41, 59));
                listing.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                listing.setForeground(Color.WHITE);
                listing.add(hoverText);
            }

            public void mouseExited(MouseEvent evt) {
                listing.remove(hoverText);
                listing.setText(listingText);
                listing.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                listing.setForeground(new Color(148, 163, 184));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showCarInfo(car);
                int result = JOptionPane.showConfirmDialog(null,
                        "Do you want to delete this listing?",
                        "Remove Car", JOptionPane.YES_NO_OPTION);
                handleRemoveCar(result, car);
            }
        });
    }

    private void handleRemoveCar(int result, Car car) {
        if (result == JOptionPane.YES_OPTION) {
            listedCars.removeCar(car);
            currentUser.getCars().remove(car);
            cards.remove(viewListingsPanel);
            viewYourListingsPanel = new ViewYourListings().initWin();
            cards.add(viewYourListingsPanel, "viewYourListings");
            cardLayout.show(cards, "viewYourListings");
            JOptionPane.showMessageDialog(frame,
                    "Listing removed!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
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
                + "Time left: " + car.getTimer() + " seconds" + "\n";
        if (car.getHighestBid() == null) {
            message = message + "Highest bid: None";
        } else {
            message = message + "Highest bid: $" + car.getHighestBid().getBidAmount();
        }
        JOptionPane.showMessageDialog(frame, message, "Car Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     *
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
        setCurrentButton(viewYourListings);
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

        setStateButtons(buttons, viewYourListings);
        return buttons;
    }
}