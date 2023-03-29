package ui;

import model.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * CreateAccount class (including UI) for the Car Auction application
 */
public class CreateAccount extends UiState {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JPasswordField retypePasswordField;
    private String username;
    private String password;
    private String retypePassword;

    /**
     * Constructs a new CreateAccount UI state
     *
     */
    public CreateAccount() {
        super();
    }

    /**
     * Initializes the UI for the CreateAccount state
     * @return JPanel of the CreateAccount UI
     */
    protected JPanel initWin() {
        super.initWin();
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        this.retypePasswordField = new JPasswordField();
        return loadPanel();
    }

    /**
     * Loads the UI for the CreateAccount state
     * @return JPanel containing all components needed for UI
     */
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getInputFields().forEach(inputField -> panel.add(inputField));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    /**
     * Gets the input fields for the CreateAccount UI
     * @return ArrayList of JComponents (input fields)
     */
    private ArrayList<JComponent> getInputFields() {
        JLabel usernameLabel = addLabel("Username", usernameTextField,
                (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 90);
        setInputFields(usernameTextField, (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 70);

        JLabel passwordLabel = addLabel("Password", passwordTextField,
                (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 40);
        setInputFields(passwordTextField, (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 20);

        JLabel retypePasswordLabel = addLabel("Re-type Password", retypePasswordField,
                (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 10);
        setInputFields(retypePasswordField, (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 30);

        inputFields.add(createUnderline((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 60
        ));
        inputFields.add(createUnderline((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2
        ));
        inputFields.add(createUnderline((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 + 50
        ));
        inputFields.add(retypePasswordLabel);
        inputFields.add(retypePasswordField);
        inputFields.add(usernameTextField);
        inputFields.add(passwordTextField);
        inputFields.add(usernameLabel);
        inputFields.add(passwordLabel);

        return inputFields;
    }

    private JLabel addLabel(String text, JTextField labelField, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(148,163,184));
        label.setLabelFor(labelField);
        label.setFont(robotoFont.deriveFont(11f));
        setFixedBounds(label, x, y);
        return label;
    }

    private void setInputFields(JTextField field, int x, int y) {
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setOpaque(false);
        field.setForeground(Color.WHITE);
        setFixedBounds(field, x, y);
    }

    private void setFixedBounds(JComponent component, int x, int y) {
        component.setBounds(x, y, 200, 20);
    }

    private JLabel createUnderline(int x, int y) {
        JLabel underline = new JLabel("_________________________________________________");
        underline.setFont(robotoFont.deriveFont(9f));
        underline.setForeground(new Color(148,163,184));
        underline.setBounds(x, y, 300, 10);
        return underline;
    }

    /**
     * Makes buttons to be used for the CreateAccount UI
     * @return ArrayList of JComponents (buttons)
     */
    private ArrayList<JComponent> getJButtons() {
        Border buttonBorder = BorderFactory.createLineBorder(new Color(30,41,59), 2);

        JButton createAcc = new JButton("Create");
        createAcc.setFont(robotoFont.deriveFont(10f));
        createAcc.setBounds((frame.getWidth() - 100) / 2 + 70, (frame.getHeight() - 40) / 2 + 70, 80,
                20);
        createAcc.setBorder(buttonBorder);
        createAcc.addActionListener(e -> {
            username = usernameTextField.getText();
            password = passwordTextField.getText();
            retypePassword = retypePasswordField.getText();
            handleCreateAccount();
        });
        buttons.add(createAcc);
        toSetButtons.add(createAcc);
        super.setAttrButtons(toSetButtons);
        return buttons;
    }

    private void handleCreateAccount() {
        if (username.equals("") || password.equals("") || retypePassword.equals("")) {
            JOptionPane.showMessageDialog(frame, "Please fill out all fields",
                    "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (User u : users.getUsers()) {
            if (u.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(frame, "Username already exists",
                        "Username Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (currentUser.createUser(username, password, retypePassword)) {
            JOptionPane.showMessageDialog(frame, "Account created successfully",
                    "Account Created", JOptionPane.INFORMATION_MESSAGE);
            userMap.put(username, password);
            users.add(currentUser);
            cardLayout.show(cards, "mainMenu");
        } else {
            JOptionPane.showMessageDialog(frame, "Your passwords do not match",
                    "Password Mismatch", JOptionPane.ERROR_MESSAGE);
        }
    }
}