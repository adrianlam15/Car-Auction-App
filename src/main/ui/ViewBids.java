package ui;

import model.Bid;
import model.Car;

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
        panel.add(buttonPanel);

        panel.add(getBidPanel());
        return panel;
    }

    private JScrollPane getBidPanel() {
        JPanel bidPanel = new JPanel();
        bidPanel.setLayout(null);
        bidPanel.setBackground(new Color(15, 23, 42));
        getBids().forEach(bid -> bidPanel.add(bid));
        JLabel bidInfo = new JLabel("Your Current Bids");
        bidInfo.setFont(robotoFont.deriveFont(20f));
        bidInfo.setForeground(new Color(148,163,184));
        bidInfo.setBounds(225, 0, 300, 100);
        bidPanel.add(bidInfo);
        int multiplier = UiState.bids.size() * 20;
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
        int i = 1;
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        for (Bid bid : currentUser.getBids()) {
            JButton bidButton = new JButton(bid.getBid() + " " + bid.getBidAmount());
            bidButton.setFocusPainted(false);
            bidButton.setBackground(new Color(30,41,59));
            bidButton.setForeground(new Color(148,163,184));
            bidButton.setFont(robotoFont.deriveFont(12f));
            bidButton.setBounds((frame.getWidth()) / 2 - 250, (frame.getHeight()) / 2 - 275 + (i * 50),
                    300, 40);
            bidButton.setBorder(border);
            bidButton.addActionListener(e -> {

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
            bids.add(bidButton);
            i++;
            }
        return bids;
        }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
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
        viewCurrentBids.setBackground(new java.awt.Color(30, 41, 59));
        viewCurrentBids.setFocusPainted(false);
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
        saveCurrentData.addActionListener(e -> {
            save();
        });
        buttons.add(saveCurrentData);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            cardLayout.show(cards, "loginMenu");
        });
        buttons.add(logout);

        setButtons(buttons, viewCurrentBids);
        return buttons;
    }

    private void setButtons(ArrayList<JComponent> buttons, JButton viewCurrentBids) {
        Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            if ((JButton) button != viewCurrentBids) {
                toSetButtons.add((JButton) button);
            } else {
                viewCurrentBids.setBorder(border);
                viewCurrentBids.setForeground(Color.WHITE);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
    }
}