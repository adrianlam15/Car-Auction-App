package ui;

import model.Car;
import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.View;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateListing extends UiState {
    private ArrayList<JComponent> inputFields;
    private Car carToCreate;
    private JTextField carMake;
    private JTextField carModel;
    private JTextField carColour;
    private JTextField carTransmission;
    private JTextField carDriveType;
    private JTextField carCondition;
    private JTextField carYear;
    private JTextField carMileage;
    private JTextField carPrice;
    private JTextField carDescription;

    public CreateListing() {
        super();
        this.carToCreate = new Car();
        inputFields = new ArrayList<>();
    }

    protected JPanel initWin() {
        super.initWin();
        return loadPanel();
    }

    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getInputFields().forEach(field -> panel.add(field));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    private ArrayList<JComponent> getInputFields() {
        JLabel carMakeLabel = new JLabel("Car Make");
        inputFields.add(carMakeLabel);

        carMake = new JTextField();
        inputFields.add(carMake);

        carMakeLabel.setLabelFor(carMake);

        JLabel carMakeUnderline = new JLabel("________________________");
        inputFields.add(carMakeUnderline);

        JLabel carModelLabel = new JLabel("Car Model");
        carModelLabel.setLabelFor(carMake);
        inputFields.add(carModelLabel);

        carModel = new JTextField();
        inputFields.add(carModel);

        JLabel carModelUnderline = new JLabel("________________________");
        inputFields.add(carModelUnderline);

        JLabel carColourLabel = new JLabel("Car Colour");
        inputFields.add(carColourLabel);

        carColour = new JTextField();
        inputFields.add(carColour);

        JLabel carColourUnderline = new JLabel("________________________");
        inputFields.add(carColourUnderline);

        JLabel carTransmissionLabel = new JLabel("Car Transmission");
        inputFields.add(carTransmissionLabel);

        carTransmission = new JTextField();
        inputFields.add(carTransmission);

        JLabel carTransmissionUnderline = new JLabel("________________________");
        inputFields.add(carTransmissionUnderline);

        JLabel carDriveTypeLabel = new JLabel("Car Drive Type");
        inputFields.add(carDriveTypeLabel);

        carDriveType = new JTextField();
        inputFields.add(carDriveType);

        JLabel carDriveTypeUnderline = new JLabel("________________________");
        inputFields.add(carDriveTypeUnderline);

        JLabel carConditionLabel = new JLabel("Car Condition");
        inputFields.add(carConditionLabel);

        carCondition = new JTextField();
        inputFields.add(carCondition);

        JLabel carConditionUnderline = new JLabel("________________________");
        inputFields.add(carConditionUnderline);

        JLabel carYearLabel = new JLabel("Car Year");
        inputFields.add(carYearLabel);

        carYear = new JTextField();
        inputFields.add(carYear);

        JLabel carYearUnderline = new JLabel("________________________");
        inputFields.add(carYearUnderline);

        JLabel carMileageLabel = new JLabel("Car Mileage");
        inputFields.add(carMileageLabel);

        carMileage = new JTextField();
        inputFields.add(carMileage);

        JLabel carMileageUnderline = new JLabel("________________________");
        inputFields.add(carMileageUnderline);

        JLabel carPriceLabel = new JLabel("Car Price");
        inputFields.add(carPriceLabel);

        carPrice = new JTextField();
        inputFields.add(carPrice);

        JLabel carPriceUnderline = new JLabel("________________________");
        inputFields.add(carPriceUnderline);

        JLabel carDescriptionLabel = new JLabel("Car Description");
        inputFields.add(carDescriptionLabel);

        carDescription = new JTextField();
        inputFields.add(carDescription);

        JLabel carDescriptionUnderline = new JLabel("________________________");
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
            if (field instanceof JLabel && !((JLabel) field).getText().contains("_")) {
                field.setFont(robotoFont.deriveFont(12f));
                field.setForeground(new Color(148, 163, 184));
            } else if (field instanceof JTextField) {
                field.setFont(robotoFont.deriveFont(12f));
                field.setForeground(Color.WHITE);
                field.setBorder(BorderFactory.createEmptyBorder());
                field.setOpaque(false);
            } else if (field instanceof JLabel && ((JLabel) field).getText().contains("_")) {
                field.setFont(robotoFont.deriveFont(9f));
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
        JButton createListing = new JButton("Create Listing");
        createListing.setBackground(new java.awt.Color(30, 41, 59));
        createListing.setFocusPainted(false);
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.addActionListener(e -> {
            System.out.println("Entering view listings");
            cardLayout.show(cards, "viewListings");
        });
         buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        viewYourListings.addActionListener(e -> {
            System.out.println(currentUser.getUsername());
            cardLayout.show(cards, "viewYourListings");
        });
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        viewCurrentBids.addActionListener(e -> {
            System.out.println(currentUser.getUsername());
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

        JButton createCar = new JButton("Create");
        createCar.addActionListener(e -> {
            System.out.println(UiState.listedCars.getCars().size());
            try {
                carToCreate.setMake(carMake.getText());
                carToCreate.setModel(carModel.getText());
                carToCreate.setColour(carColour.getText());
                carToCreate.setTransmission(carTransmission.getText());
                carToCreate.setDriveType(carDriveType.getText());
                carToCreate.setCondition(carCondition.getText());
                carToCreate.setYear(Integer.parseInt(carYear.getText()));
                carToCreate.setMileage(Integer.parseInt(carMileage.getText()));
                carToCreate.setPrice(Integer.parseInt(carPrice.getText()));
                carToCreate.setDescription(carDescription.getText());
                int dialogRes = JOptionPane.showConfirmDialog(null, carToCreate.getListingCar() + "\n" +
                        carToCreate.getDescription(), "Confirm", JOptionPane.YES_NO_OPTION);
                if (dialogRes == JOptionPane.YES_OPTION) {
                    currentUser.createCar(carToCreate);
                    UiState.listedCars.addCar(carToCreate);
                    UiState.updateListingPanel();
                    JOptionPane.showMessageDialog(null, "Listing created successfully.");
                    for (JComponent field : inputFields) {
                        if (field instanceof JTextField && !((JTextField) field).getText().equals("")
                                && !((JTextField) field).getText().contains("_")) {
                            ((JTextField) field).setText("");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Listing creation cancelled.");
                }
            } catch (NumberFormatException err) {
                System.out.println(err.getMessage());
                JOptionPane.showMessageDialog(null, "Invalid year, mileage, or price.");
            }
        });
        buttons.add(createCar);

        Border border = BorderFactory.createLineBorder(new java.awt.Color(15, 23, 42), 1);
        int i = 0;
        for (JComponent button : buttons) {
            button.setBorder(border);
            button.setFont(robotoFont.deriveFont(10f));
            if (button == createCar) {
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
