package ui;

import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateListing extends UiState{
    private Users users;
    private ArrayList<JButton> toSetButtons;

    public CreateListing(CardLayout cardLayout, JPanel cards, Users users, HashMap<String, String> userMap,
                         JFrame frame) {
        super(cardLayout, cards, frame, null);
        this.users = users;
        this.toSetButtons = new ArrayList<>();
    }

    protected JPanel initWin() {
        super.initWin();
        return loadCreateListingPanel();
    }

    private JPanel loadCreateListingPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getInputFields().forEach(field -> panel.add(field));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    private ArrayList<JComponent> getInputFields() {
        ArrayList<JComponent> inputFields = new ArrayList<>();

        JLabel carMakeLabel = new JLabel("Car Make");
        inputFields.add(carMakeLabel);

        JTextField carMake = new JTextField();
        inputFields.add(carMake);

        carMakeLabel.setLabelFor(carMake);

        JLabel carMakeUnderline = new JLabel("____________________");
        inputFields.add(carMakeUnderline);

        JLabel carModelLabel = new JLabel("Car Model");
        carModelLabel.setLabelFor(carMake);
        inputFields.add(carModelLabel);

        JTextField carModel = new JTextField();
        inputFields.add(carModel);

        JLabel carModelUnderline = new JLabel("____________________");
        inputFields.add(carModelUnderline);

        JLabel carColourLabel = new JLabel("Car Colour");
        inputFields.add(carColourLabel);

        JTextField carColour = new JTextField();
        inputFields.add(carColour);

        JLabel carColourUnderline = new JLabel("____________________");
        inputFields.add(carColourUnderline);

        JLabel carTransmissionLabel = new JLabel("Car Transmission");
        inputFields.add(carTransmissionLabel);

        JTextField carTransmission = new JTextField();
        inputFields.add(carTransmission);

        JLabel carTransmissionUnderline = new JLabel("____________________");
        inputFields.add(carTransmissionUnderline);

        JLabel carDriveTypeLabel = new JLabel("Car Drive Type");
        inputFields.add(carDriveTypeLabel);

        JTextField carDriveType = new JTextField();
        inputFields.add(carDriveType);

        JLabel carDriveTypeUnderline = new JLabel("____________________");
        inputFields.add(carDriveTypeUnderline);

        JLabel carConditionLabel = new JLabel("Car Condition");
        inputFields.add(carConditionLabel);

        JTextField carCondition = new JTextField();
        inputFields.add(carCondition);

        JLabel carConditionUnderline = new JLabel("____________________");
        inputFields.add(carConditionUnderline);

        JLabel carYearLabel = new JLabel("Car Year");
        inputFields.add(carYearLabel);

        JTextField carYear = new JTextField();
        inputFields.add(carYear);

        JLabel carYearUnderline = new JLabel("____________________");
        inputFields.add(carYearUnderline);

        JLabel carMileageLabel = new JLabel("Car Mileage");
        inputFields.add(carMileageLabel);

        JTextField carMileage = new JTextField();
        inputFields.add(carMileage);

        JLabel carMileageUnderline = new JLabel("____________________");
        inputFields.add(carMileageUnderline);

        JLabel carPriceLabel = new JLabel("Car Price");
        inputFields.add(carPriceLabel);

        JTextField carPrice = new JTextField();
        inputFields.add(carPrice);

        JLabel carPriceUnderline = new JLabel("____________________");
        inputFields.add(carPriceUnderline);

        JLabel carDescriptionLabel = new JLabel("Car Description");
        inputFields.add(carDescriptionLabel);

        JTextField carDescription = new JTextField();
        inputFields.add(carDescription);

        JLabel carDescriptionUnderline = new JLabel("____________________");
        inputFields.add(carDescriptionUnderline);
        setInputFields(inputFields);
        return inputFields;
    }

    private void setInputFields(ArrayList<JComponent> inputFields) {
        int x = (frame.getWidth() - 100) / 2 - 50;
        int y = (frame.getHeight() - 40) / 2 - 180;
        int labelWidth = 200;
        int labelHeight = 20;
        int textFieldWidth = 100;
        int textFieldHeight = 20;
        int underlineWidth = 100;
        int underlineHeight = 10;

        for (JComponent field : inputFields) {
            System.out.println(y);
            if (field instanceof JLabel && !((JLabel) field).getText().contains("_")) {
                field.setFont(new Font("Roboto", Font.PLAIN, 12));
                field.setForeground(new Color(148, 163, 184));
            } else if (field instanceof JTextField) {
                field.setFont(new Font("Roboto", Font.PLAIN, 12));
                field.setForeground(Color.WHITE);
                field.setBorder(BorderFactory.createEmptyBorder());
                field.setOpaque(false);
            } else if (field instanceof JLabel && ((JLabel) field).getText().contains("_")) {
                field.setFont(new Font("Roboto", Font.PLAIN, 9));
                field.setForeground(new Color(148, 163, 184));
            }
        }
        for (JComponent field : inputFields) {
            if (field instanceof JLabel && !((JLabel) field).getText().contains("_")) {
                field.setBounds(x, y, labelWidth, labelHeight);
                y += 40;
            }
        }
        y = (frame.getHeight() - 40) / 2 - 160;
        for (JComponent field : inputFields) {
            if (field instanceof JTextField) {
                field.setBounds(x, y, textFieldWidth, textFieldHeight);
                y += 40;
            }
        }
        y = (frame.getHeight() - 40) / 2 - 150;
        for (JComponent field : inputFields) {
            if (field instanceof JLabel && ((JLabel) field).getText().contains("_")) {
                field.setBounds(x, y, underlineWidth, underlineHeight);
                y += 40;
            }
        }
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
     private ArrayList<JComponent> getJButtons() {
        ArrayList<JComponent> buttons = new ArrayList<>();
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);
        JButton createListing = new JButton("Create Listing");
        createListing.setBackground(new java.awt.Color(30, 41, 59));
        createListing.setFocusPainted(false);
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

        JButton create = new JButton("Create");
        create.addActionListener(e -> {
            System.out.println("Creating...");
            cardLayout.show(cards, "mainMenu");
        });
        buttons.add(create);

        Border border = BorderFactory.createLineBorder(new java.awt.Color(15, 23, 42), 1);
        int i = 0;
        for (JComponent button : buttons) {
            button.setBorder(border);
            button.setFont(buttonFont.deriveFont(10f));
            if (button == create) {
                button.setBounds((frame.getWidth() - 100) / 2 + 100, (frame.getHeight() - 40) / 2,
                        100, 40);
                toSetButtons.add((JButton) button);
            } else {
                if (button != createListing) {
                    toSetButtons.add((JButton) button);
                } else {
                    createListing.setForeground(Color.WHITE);
                }
                button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
     }
}
