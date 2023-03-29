package ui;

import model.Bid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ViewBids extends UiState {

    public ViewBids() {
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

        panel.add(getBidPanel());
        panel.add(buttonPanel);
        return panel;
    }

    private JScrollPane getBidPanel() {
        JPanel bidPanel = new JPanel();
        bidPanel.setLayout(null);
        bidPanel.setBackground(new Color(15, 23, 42));
        getBids().forEach(bidPanel::add);
        JLabel headerLabel = new JLabel("Your Current Bids! (Click any to increase bid amount)");
        headerLabel.setFont(robotoFont.deriveFont(16f));
        headerLabel.setForeground(new Color(148,163,184));
        headerLabel.setBounds(150, 0, 400, 100);
        bidPanel.add(headerLabel);
        int multiplier = bids.size() * 20;
        bidPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() + multiplier));

        JScrollPane scrollPane = new JScrollPane(bidPanel);
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
    private ArrayList<JComponent> getBids() {
        ArrayList<JComponent> bids = new ArrayList<>();
        int i = 2;
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        System.out.println("bids: " + currentUser.getBids().size());
        for (Bid bid : currentUser.getBids()) {
            String bidInfo = "Car: " + bid.getCar().getMake() + " " + bid.getCar().getModel()
                    + " " + bid.getCar().getYear() + " | Bid: $" + bid.getBidAmount();
            JButton bidButton = new JButton(bidInfo);
            bidButton.setFocusPainted(false);
            bidButton.setBackground(new Color(30,41,59));
            bidButton.setForeground(new Color(148,163,184));
            bidButton.setFont(robotoFont.deriveFont(12f));
            bidButton.setBounds((frame.getWidth()) / 2 - 200, (frame.getHeight()) / 2 - 275 + (i * 50),
                    300, 40);
            bidButton.setBorder(border);
            setListeners(bid, bidButton);
            bids.add(bidButton);
            i++;
        }
        return bids;
    }

    private void setListeners(Bid bid, JButton bidButton) {
        bidButton.addActionListener(e -> {
            editBid(bid);
        });
        bidButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                bidButton.setBackground(new Color(30,41,59));
                bidButton.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
            }

            public void mouseExited(MouseEvent evt) {
                bidButton.setBackground(new Color(30,41,59));
                bidButton.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
            }
        });
    }

    private void editBid(Bid bid) {
        try {
            for (Bid userBid : currentUser.getBids()) {
                if (userBid.equals(bid)) {
                    JOptionPane.showMessageDialog(null, "Your previous bid was $"
                            + userBid.getBidAmount());
                    String bidAmount = JOptionPane.showInputDialog("Enter new bid amount");
                    int newBid = Integer.parseInt(bidAmount);
                    currentUser.getBids().remove(userBid);
                    currentUser.placeBid(userBid.getCar().getId(), newBid, listedCars);
                    JOptionPane.showMessageDialog(null, "Your new bid is $" + newBid);
                    return;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
        }
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
        setCurrentButton(viewCurrentBids);
        buttons.add(viewCurrentBids);

        JButton viewWonCars = new JButton("View Won Cars");
        viewWonCars.addActionListener(e -> {
            cards.remove(viewWonPanel);
            viewWonPanel = new ViewWon().initWin();
            cards.add(viewWonPanel, "viewWon");
            cardLayout.show(cards, "viewWon");
        });
        buttons.add(viewWonCars);

        addButton("load");
        addButton("save");
        addButton("logout");

        setStateButtons(buttons, viewCurrentBids);
        return buttons;
    }
}