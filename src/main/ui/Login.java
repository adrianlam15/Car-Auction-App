package ui;

import model.User;
import model.Users;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sound.sampled.Clip;

/**
 * Login class (including UI) for the Car Auction application
 */
public class Login extends UiState {
    private boolean loggedIn = false;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private ArrayList<JComponent> inputFields;
    private ArrayList<JComponent> buttons;
    private ArrayList<JButton> toSetButtons;
    private String username;
    private String password;
    private Users users;
    private HashMap<String, String> userMap;
    private User currentUser = new User();
    private Clip clip;

    /**
     * Constructs a new Login UI state
     * @param cardLayout
     * @param cards
     * @param users
     * @param userMap
     * @param frame
     */
    public Login(CardLayout cardLayout, JPanel cards,
                 Users users, HashMap<String, String> userMap, JFrame frame) {
        super(cardLayout, cards, frame);
        this.users = users;
        this.userMap = userMap;
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        this.inputFields = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.toSetButtons = new ArrayList<>();
        JPanel mainPanel = new MainMenu(cardLayout, cards, frame).initWin();
        cards.add(mainPanel, "mainMenu");
        JPanel createAccountPanel = new CreateAccount(users, cardLayout, cards, frame, userMap).initWin();
        cards.add(createAccountPanel, "createAccount");
        initWin();
        playMusic("./data/ZA-ZA - LOVE & MONEY.wav");
    }

    /**
     * Plays music
     * @param musicLocation
     */
    public void playMusic(String musicLocation) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicLocation).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes the login window
     * @return JPanel of the login window
     */
    protected JPanel initWin() {
        super.initWin();
        return loadLoginPanel();
    }

    /**
     * Loads the login panel
     * @return JPanel with all the components
     */
    private JPanel loadLoginPanel() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getInputFields().forEach(inputField -> panel.add(inputField));
        getJButtons().forEach(button -> panel.add(button));
        try {
            getImages().forEach(image -> panel.add(image, BorderLayout.CENTER));
        } catch (IOException e) {

        }
        return panel;
    }

    /**
     * Makes images to be used on login panel
     * @return
     * @throws IOException
     */
    private ArrayList<JComponent> getImages() throws IOException {
        ArrayList<JComponent> images = new ArrayList<>();
        ImageIcon mainImageIcon = new ImageIcon("./data/mainImage.jfif");
        Image mainImage = mainImageIcon.getImage();

        int newWidth = mainImage.getWidth(null) / 4;
        int newHeight = mainImage.getHeight(null) / 4;
        Image scaledImage = mainImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds((frame.getWidth() - 100) / 2, 50, 100, 100);
        images.add(imageLabel);

        ImageIcon featuredDriverIcon = new ImageIcon("./data/featureDriver.png");
        Image featuredDriverImage = featuredDriverIcon.getImage();
        int newWidth2 = featuredDriverImage.getWidth(null) / 8;
        int newHeight2 = featuredDriverImage.getHeight(null) / 8;
        Image scaledImage2 = featuredDriverImage.getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        JLabel imageLabel2 = new JLabel(scaledIcon2);
        imageLabel2.setBounds(50, 50, 100, 100);
        images.add(imageLabel2);
        return images;
    }

    /**
     * Makes input fields to be used on login panel
     * @return ArrayList of JComponents (input fields)
     */
    private ArrayList<JComponent> getInputFields() {
        Font labelFont = new Font("Roboto", Font.PLAIN, 14);

        usernameTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 40) / 2 - 50,
                100, 20);
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

        inputFields.add(usernameTextField);
        inputFields.add(passwordTextField);
        inputFields.add(usernameLabel);
        inputFields.add(passwordLabel);
        return inputFields;
    }

    /**
     * Makes buttons to be used on login panel
     * @return ArrayList of JComponents (buttons)
     */
    private ArrayList<JComponent> getJButtons() {
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(buttonFont.deriveFont(10f));
        loginButton.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 40) / 2 + 10, 100, 20);
        loginButton.addActionListener(e -> {
            username = usernameTextField.getText();
            password = passwordTextField.getText();
            loggedIn = currentUser.login(username, password, userMap);
            if (loggedIn) {
                for (User user : users.getUsers()) {
                    if (user.getUsername().equals(username)) {
                        currentUser = user;
                        loggedIn = true;
                        break;
                    }
                }
                cardLayout.show(cards, "mainMenu");
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect username or password you goofy goober",
                        "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        toSetButtons.add(loginButton);
        buttons.add(loginButton);

        JButton seePass = new JButton("Show");
        seePass.setFont(buttonFont.deriveFont(10f));
        seePass.setBounds((frame.getWidth() - 100) / 2 + 110, (frame.getHeight() - 20) / 2 - 30,
                20, 20);
        seePass.addActionListener(e -> {
            if (seePass.getText().equals("Show")) {
                passwordTextField.setEchoChar((char) 0);
                seePass.setText("Hide");
            } else {
                passwordTextField.setEchoChar('•');
                seePass.setText("Show");
            }
        });
        toSetButtons.add(seePass);
        buttons.add(seePass);

        JButton createAcc = new JButton("<html>Create an<br>account</html>");
        createAcc.setFont(buttonFont.deriveFont(10f));
        createAcc.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 40) / 2 + 50, 100,
                40);
        createAcc.addActionListener(e -> {
            cardLayout.show(cards, "createAccount");
            });
        toSetButtons.add(createAcc);

        JButton toggleMusic = new JButton("Mute");
        toggleMusic.setFont(buttonFont.deriveFont(10f));
        toggleMusic.setBounds((frame.getWidth() - 80) / 2, (frame.getHeight() - 40) / 2 + 100, 20,
                20);
        toggleMusic.addActionListener(e -> {
            if (toggleMusic.getText().equals("Mute")) {
                clip.stop();
                toggleMusic.setText("Unmute");
            } else {
                clip.start();
                toggleMusic.setText("Mute");
            }
        });
        toSetButtons.add(toggleMusic);

        super.setAttrButtons(toSetButtons);
        buttons.add(loginButton);
        buttons.add(createAcc);
        buttons.add(toggleMusic);
        return buttons;
    }

    public boolean getLoginState() {
        return loggedIn;
    }
}
