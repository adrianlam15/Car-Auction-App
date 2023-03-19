package ui;

import model.User;
import model.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Login class (including UI) for the Car Auction application
 */
public class Login {
    private int width;
    private int height;
    private JFrame frame = new JFrame();
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private boolean loggedIn = false;
    private String username;
    private String password;
    private Users users;
    private HashMap<String, String> userMap;
    private User currentUser = new User();

    public Login(int width, int height, Users users, HashMap<String, String> userMap) {
        this.width = width;
        this.height = height;
        this.users = users;
        this.userMap = userMap;
        initWin();
    }

    private void initWin() {
        frame.setSize(width, height);
        frame.add(loadLoginPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel loadLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new java.awt.Color(15, 23, 42));
        for (JComponent inputField : getInputFields()) {
            loginPanel.add(inputField);
        }
        for (JComponent buttons : getJButtons()) {
            loginPanel.add(buttons);
        }
        return loginPanel;
    }

    private ArrayList<JComponent> getInputFields() {
        Font labelFont = new Font("Roboto", Font.PLAIN, 14);

        ArrayList<JComponent> inputFields = new ArrayList<>();
        usernameTextField = new JTextField();
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setFont(labelFont);
        usernameLabel.setBounds((frame.getWidth() - 100) / 2 - 75, (frame.getHeight() - 40) / 2 - 53,
                100, 20);
        usernameTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 40) / 2 - 50,
                100, 20);
        usernameTextField.setBorder(BorderFactory.createEmptyBorder());

        passwordTextField = new JPasswordField();
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setLabelFor(passwordTextField);
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds((frame.getWidth() - 100) / 2 - 75, (frame.getHeight() - 20) / 2 - 33,
                100, 20);
        passwordTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 20) / 2 - 30,
                100, 20);
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());

        inputFields.add(usernameTextField);
        inputFields.add(passwordTextField);
        inputFields.add(usernameLabel);
        inputFields.add(passwordLabel);
        return inputFields;
    }

    private ArrayList<JComponent> getJButtons() {
        ArrayList<JComponent> buttons = new ArrayList<>();
        ArrayList<JButton> toSetButtons = new ArrayList<>();
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(buttonFont.deriveFont(10f));
        loginButton.setBounds((frame.getWidth() - 80) / 2, (frame.getHeight() - 40) / 2 + 10, 80, 20);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameTextField.getText();
                password = passwordTextField.getText();
                loggedIn = currentUser.login(username, password, userMap);
                if (loggedIn) {
                    for (User user : users.getUsers()) {
                        if (user.getUsername().equals(username)) {
                            currentUser = user;
                            loggedIn = true;
                        }
                    }
                    /**
                     * TODO: add a new MainMenu class that represents UI for the main menu (state transition)
                     */
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect username or password you goofy goober",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        toSetButtons.add(loginButton);

        JButton createAcc = new JButton("<html>Create an<br>account</html>");
        createAcc.setFont(buttonFont);
        createAcc.setBounds((frame.getWidth() - 50) / 2 - 25, (frame.getHeight() - 40) / 2 + 80, 100,
                40);
        toSetButtons.add(createAcc);

        setAttrButtons(toSetButtons);
        buttons.add(loginButton);
        buttons.add(createAcc);
        return buttons;
    }

    private void setAttrButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setBackground(new Color(99, 102, 241));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(148, 163, 184));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(new Color(99, 102, 241));
                }
            });
        }
    }

    public boolean getLoginState() {
        return loggedIn;
    }

}
