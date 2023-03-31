package ui;

import model.Car;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * CreateListing class (including UI) for the Car Auction application
 */
public class CreateListing extends UiState {
    private final ArrayList<JComponent> inputFields;
    private final Car carToCreate;
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

    // MODIFIES: UiState
    // EFFECTS: initializes the UI for the CreateListing state
    protected JPanel initWin() {
        super.initWin();
        return loadPanel();
    }

    // MODIFIES: UiState
    // EFFECTS: loads the UI for the CreateListing state
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getInputFields().forEach(field -> panel.add(field));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    // MODIFIES: this, UiState
    // EFFECTS: gets the input fields for the CreateListing UI
    @SuppressWarnings("methodlength")
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

    // REQUIRES: inputFields is not null
    // MODIFIES: UiState
    // EFFECTS: sets the attributes of the input fields
    private void setInputFields(ArrayList<JComponent> inputFields) {
        int x = (frame.getWidth() - 100) / 2 - 50;
        int y = (frame.getHeight() - 40) / 2 - 180;
        int labelWidth = 200;
        int labelHeight = 20;
        int textFieldWidth = 100;
        int textFieldHeight = 20;
        int underlineWidth = 100;
        int underlineHeight = 10;
        setAttrFields(inputFields);
        setBoundsJLabel(inputFields, x, y, labelWidth, labelHeight);
        y = (frame.getHeight() - 40) / 2 - 160;
        setBoundsField(inputFields, x, y, textFieldWidth, textFieldHeight);
        y = (frame.getHeight() - 40) / 2 - 150;
        setBoundsUnderlines(inputFields, x, y, underlineWidth, underlineHeight);
    }

    // REQUIRES: inputFields is not null, x, y, underlineWidth, underlineHeight are not null
    // EFFECTS: sets the bounds of the underlines
    private void setBoundsUnderlines(ArrayList<JComponent> inputFields, int x, int y, int underlineWidth,
                                     int underlineHeight) {
        for (JComponent field : inputFields) {
            if (field instanceof JLabel && ((JLabel) field).getText().contains("_")) {
                field.setBounds(x, y, underlineWidth, underlineHeight);
                y += 40;
            }
        }
    }

    // REQUIRES: inputFields is not null, x, y, textFieldWidth, textFieldHeight are not null
    // EFFECTS: sets the bounds of the text fields
    private void setBoundsField(ArrayList<JComponent> inputFields, int x, int y, int textFieldWidth,
                                int textFieldHeight) {
        for (JComponent field : inputFields) {
            if (field instanceof JTextField) {
                field.setBounds(x, y, textFieldWidth, textFieldHeight);
                y += 40;
            }
        }
    }

    // REQUIRES: inputFields is not null, x, y, labelWidth, labelHeight are not null
    // EFFECTS: sets the bounds of the labels
    private void setBoundsJLabel(ArrayList<JComponent> inputFields, int x, int y, int labelWidth, int labelHeight) {
        for (JComponent field : inputFields) {
            if (field instanceof JLabel && !((JLabel) field).getText().contains("_")) {
                field.setBounds(x, y, labelWidth, labelHeight);
                y += 40;
            }
        }
    }

    // REQUIRES: inputFields is not null
    // EFFECTS: sets the attributes of the input fields
    private void setAttrFields(ArrayList<JComponent> inputFields) {
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
    }

    // MODIFIES: UiState
    // EFFECTS: returns the list of menu JButtons for the CreateListing state
    @SuppressWarnings("methodlength")
    private ArrayList<JComponent> getJButtons() {
        JButton createListing = new JButton("Create Listing");
        setCurrentButton(createListing);
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

        JButton createCar = new JButton("Create");
        createCar.addActionListener(e -> {
            tryCreateCar();
        });
        buttons.add(createCar);
        setStateButtons(buttons, createListing);
        return buttons;
    }

    // REQUIRES: buttons is not null, ignoreButton is not null
    // MODIFIES: UiState
    // EFFECTS: sets the attributes of the buttons
    @Override
    protected void setStateButtons(ArrayList<JComponent> buttons, JButton ignoreButton) {
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(robotoFont.deriveFont(10f));
            button.setBounds(0, (frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            if (button instanceof JButton && ((JButton) button).getText().equals("Create")) {
                button.setBounds(450, 200, 100, 40);
            }
            if (button != ignoreButton) {
                toSetButtons.add((JButton) button);
            } else {
                Border border = BorderFactory.createLineBorder(new Color(30, 41, 59), 2);
                ignoreButton.setBorder(border);
                ignoreButton.setForeground(Color.WHITE);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
    }

    // MODIFIES: Car, Cars
    // EFFECTS: handles creation of car
    private void tryCreateCar() {
        try {
            setCar();
            int dialogRes = JOptionPane.showConfirmDialog(null, carToCreate.getListingCar()
                    + "\n" + carToCreate.getDescription(), "Confirm", JOptionPane.YES_NO_OPTION);
            if (dialogRes == JOptionPane.YES_OPTION) {
                handleCreate();
                resetFields();
            } else {
                JOptionPane.showMessageDialog(null, "Listing creation cancelled.");
            }
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(null, "Invalid year, mileage, or price.");
        }
    }

    // EFFECTS: resets the fields of the input fields
    private void resetFields() {
        for (JComponent field : inputFields) {
            if (field instanceof JTextField && !((JTextField) field).getText().equals("")
                    && !((JTextField) field).getText().contains("_")) {
                ((JTextField) field).setText("");
            }
        }
    }

    // MODIFIES: Car, UiState, Cars
    // EFFECTS: creates the car and adds it to the list of cars
    private void handleCreate() {
        currentUser.createCar(carToCreate);
        listedCars.addCar(carToCreate);
        JOptionPane.showMessageDialog(null, "Listing created successfully.");
    }

    // MODIFIES: Car
    private void setCar() {
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
    }
}

