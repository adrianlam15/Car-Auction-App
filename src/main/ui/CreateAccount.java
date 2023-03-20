package ui;

import model.User;
import model.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * CreateAccount class (including UI) for the Car Auction application
 */
public class CreateAccount extends UiState{
    private Users users;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JPasswordField retypePasswordField;
    private String username;
    private String password;
    private String retypePassword;
    private User currentUser;
    private HashMap<String, String> userMap;

    /**
     * Constructs a new CreateAccount UI state
     * @param users
     * @param cardLayout
     * @param cards
     * @param frame
     * @param userMap
     */
    public CreateAccount(Users users, CardLayout cardLayout, JPanel cards, JFrame frame, HashMap<String, String> userMap) {
        super(cardLayout, cards, frame);
        this.users = users;
        this.userMap = userMap;
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
        this.currentUser = new User();
        return loadCreateAccountPanel();
    }

    /**
     * Loads the UI for the CreateAccount state
     * @return JPanel containing all components needed for UI
     */
    private JPanel loadCreateAccountPanel() {
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
        Font labelFont = new Font("Roboto", Font.PLAIN, 14);
        ArrayList<JComponent> inputFields = new ArrayList<>();

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setFont(labelFont);
        usernameLabel.setBounds((frame.getWidth() - 100) / 2 - 75, (frame.getHeight() - 40) / 2 - 53,
                100, 20);
        usernameTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 40) / 2 - 50,
                100, 20);
        usernameTextField.setBorder(BorderFactory.createEmptyBorder());

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setLabelFor(passwordTextField);
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds((frame.getWidth() - 100) / 2 - 75, (frame.getHeight() - 20) / 2 - 33,
                100, 20);
        passwordTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 20) / 2 - 30,
                100, 20);
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());

        JLabel retypePasswordLabel = new JLabel("Re-type Password");
        retypePasswordLabel.setForeground(Color.WHITE);
        retypePasswordLabel.setLabelFor(passwordTextField);
        retypePasswordLabel.setFont(labelFont);
        retypePasswordLabel.setBounds((frame.getWidth() - 100) / 2 - 120, (frame.getHeight() - 20) / 2 - 3,
                150, 20);
        retypePasswordField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 20) / 2,
                100, 20);
        retypePasswordField.setBorder(BorderFactory.createEmptyBorder());

        inputFields.add(retypePasswordLabel);
        inputFields.add(retypePasswordField);
        inputFields.add(usernameTextField);
        inputFields.add(passwordTextField);
        inputFields.add(usernameLabel);
        inputFields.add(passwordLabel);

        return inputFields;
    }

    /**
     * Makes buttons to be used for the CreateAccount UI
     * @return ArrayList of JComponents (buttons)
     */
    private ArrayList<JComponent> getJButtons() {
        ArrayList<JComponent> buttons = new ArrayList<>();
        ArrayList<JButton> toSetButtons = new ArrayList<>();
        Font buttonFont = new Font("Roboto", Font.PLAIN, 14);

        JButton createAcc = new JButton("Create");
        createAcc.setFont(buttonFont.deriveFont(10f));
        createAcc.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 20) / 2 + 30, 100,
                40);
        createAcc.addActionListener(e -> {
            username = usernameTextField.getText();
            password = passwordTextField.getText();
            retypePassword = retypePasswordField.getText();
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists",
                            "Username Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (currentUser.createUser(username, password, retypePassword)) {
                System.out.println("Account created");
                userMap.put(username, password);
                System.out.println(userMap);
                users.add(currentUser);
                cardLayout.show(cards, "mainMenu");
            } else {
                JOptionPane.showMessageDialog(frame, "Your passwords do not match",
                        "Password Mismatch", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttons.add(createAcc);
        toSetButtons.add(createAcc);
        super.setAttrButtons(toSetButtons);
        return buttons;
    }
}