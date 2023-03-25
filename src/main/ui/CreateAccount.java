package ui;

import model.User;
import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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
    private MainMenu mainMenuUI;

    /**
     * Constructs a new CreateAccount UI state
     *
     * @param users
     * @param userMap
     * @param frame
     */
    public CreateAccount(Users users, HashMap<String, String> userMap,
                         JFrame frame, MainMenu mainMenuUI) {
        super();
        this.users = users;
        this.userMap = userMap;
        this.mainMenuUI = mainMenuUI;
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
        ArrayList<JComponent> inputFields = new ArrayList<>();

        usernameTextField.setForeground(Color.WHITE);
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(148,163,184));
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setFont(robotoFont.deriveFont(11f));
        usernameLabel.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 90,
                200, 20);
        usernameTextField.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 70,
                200, 20);
        usernameTextField.setBorder(BorderFactory.createEmptyBorder());
        usernameTextField.setOpaque(false);
        JLabel usernameUnderline = new JLabel("_________________________________________________");
        usernameUnderline.setFont(robotoFont.deriveFont(9f));
        usernameUnderline.setForeground(new Color(148,163,184));
        usernameUnderline.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 60,
                300, 10);

        passwordTextField.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(148,163,184));
        passwordLabel.setLabelFor(passwordTextField);
        passwordLabel.setFont(robotoFont.deriveFont(11f));
        passwordLabel.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 40,
                200, 20);
        passwordTextField.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 20,
                200, 20);
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());
        passwordTextField.setOpaque(false);
        JLabel passwordUnderline = new JLabel("_________________________________________________");
        passwordUnderline.setFont(robotoFont.deriveFont(9f));
        passwordUnderline.setForeground(new Color(148,163,184));
        passwordUnderline.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 10,
                300, 10);

        retypePasswordField.setForeground(Color.WHITE);
        JLabel retypePasswordLabel = new JLabel("Re-type Password");
        retypePasswordLabel.setForeground(new Color(148,163,184));
        retypePasswordLabel.setLabelFor(passwordTextField);
        retypePasswordLabel.setFont(robotoFont.deriveFont(11f));
        retypePasswordLabel.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 10,
                200, 20);
        retypePasswordField.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 30,
                200, 20);
        retypePasswordField.setBorder(BorderFactory.createEmptyBorder());
        retypePasswordField.setOpaque(false);
        JLabel retypeUnderline = new JLabel("_________________________________________________");
        retypeUnderline.setFont(robotoFont.deriveFont(9f));
        retypeUnderline.setForeground(new Color(148,163,184));
        retypeUnderline.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 40,
                300, 10);

        inputFields.add(usernameUnderline);
        inputFields.add(passwordUnderline);
        inputFields.add(retypeUnderline);
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
        Border buttonBorder = BorderFactory.createLineBorder(new Color(30,41,59), 2);
        Font buttonFont = new Font("Roboto", Font.PLAIN, 14);

        JButton createAcc = new JButton("Create");
        createAcc.setFont(buttonFont.deriveFont(10f));
        createAcc.setBounds((frame.getWidth() - 100) / 2 + 70, (frame.getHeight() - 40) / 2 + 70, 80,
                20);
        createAcc.setBorder(buttonBorder);
        createAcc.addActionListener(e -> {
            username = usernameTextField.getText();
            password = passwordTextField.getText();
            retypePassword = retypePasswordField.getText();
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
                userMap.put(username, password);
                users.add(currentUser);
                UiState.currentUser = currentUser;
                System.out.println(userMap);
                UiState.users = users;
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