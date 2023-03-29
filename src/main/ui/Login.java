package ui;

import model.User;
import model.Users;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Login class (including UI) for the Car Auction application
 */
public class Login extends UiState {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private String username;
    private String password;
    private Clip clip;
    private MainMenu mainMenuUI;

    /**
     * Constructs a new Login UI state
     *
     */
    public Login() {
        super();
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        this.inputFields = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.toSetButtons = new ArrayList<>();
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
        playMusic("./data/ZA-ZA - LOVE & MONEY.wav");
        super.initWin();
        return loadPanel();
    }

    /**
     * Loads the login panel
     * @return JPanel with all the components
     */
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new Color(15, 23, 42));
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
        usernameTextField.setForeground(Color.WHITE);
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(148,163,184));
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setFont(robotoFont.deriveFont(11f));
        usernameLabel.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 70,
                200, 20);
        usernameTextField.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 50,
                200, 20);
        usernameTextField.setBorder(BorderFactory.createEmptyBorder());
        usernameTextField.setOpaque(false);
        JLabel usernameUnderline = new JLabel("_________________________________________________");
        usernameUnderline.setFont(new Font("Roboto", Font.PLAIN, 9));
        usernameUnderline.setForeground(new Color(148,163,184));
        usernameUnderline.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 40,
                300, 10);

        passwordTextField.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(148,163,184));
        passwordLabel.setLabelFor(passwordTextField);
        passwordLabel.setFont(robotoFont.deriveFont(11f));
        passwordLabel.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 20,
                200, 20);
        passwordTextField.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2,
                200, 20);
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());
        passwordTextField.setOpaque(false);
        JLabel passwordUnderline = new JLabel("_________________________________________________");
        passwordUnderline.setFont(robotoFont.deriveFont(9f));
        passwordUnderline.setForeground(new Color(148,163,184));
        passwordUnderline.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 10,
                300, 10);

        JLabel orLabel = new JLabel("OR");
        orLabel.setForeground(new Color(148,163,184));
        orLabel.setFont(robotoFont.deriveFont(8f));
        orLabel.setBounds((frame.getWidth() - 100) / 2 + 45, (frame.getHeight() - 40) / 2 + 40,
                20, 20);

        inputFields.add(passwordUnderline);
        inputFields.add(usernameUnderline);
        inputFields.add(orLabel);
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
        Border border = BorderFactory.createLineBorder(new Color(30,41,59), 2);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(robotoFont.deriveFont(10f));
        loginButton.setBounds((frame.getWidth() - 100) / 2 + 70, (frame.getHeight() - 40) / 2 + 40, 80,
                20);
        loginButton.addActionListener(e -> {
            username = usernameTextField.getText();
            password = passwordTextField.getText();
            loggedIn = currentUser.login(username, password, userMap);
            if (loggedIn) {
                for (User user : users.getUsers()) {
                    if (user.getUsername().equals(username)) {
                        currentUser = new User();
                        currentUser.createUser(user.getUsername(), user.getPassword(), user.getPassword());
                        System.out.println("Logged in as " + currentUser.getUsername());

                        cardLayout.show(cards, "mainMenu");
                        usernameTextField.setText("");
                        passwordTextField.setText("");
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect username or password you goofy goober",
                        "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginButton.setBorder(border);
        toSetButtons.add(loginButton);
        buttons.add(loginButton);

        JButton seePass = new JButton();
        ImageIcon seePassIcon = new ImageIcon("./data/view.png");
        Image seePassImage = seePassIcon.getImage();
        Image newSeePassImage = seePassImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon newSeePassIcon = new ImageIcon(newSeePassImage);
        seePass.setIcon(newSeePassIcon);
        seePass.setBounds((frame.getWidth() - 100) / 2 + 160, (frame.getHeight() - 20) / 2,
                25, 25);
        final boolean[] show = {false};
        seePass.addActionListener(e -> {
            if (show[0]) {
                passwordTextField.setEchoChar('•');
                setImageIcon(show[0], seePass);
                show[0] = false;
            } else {
                passwordTextField.setEchoChar((char) 0);
                setImageIcon(show[0], seePass);
                show[0] = true;
            }
        });
        seePass.setBorder(border);
        toSetButtons.add(seePass);
        buttons.add(seePass);

        JButton signUp = new JButton("Sign Up");
        signUp.setFont(robotoFont.deriveFont(10f));
        signUp.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 + 40, 80,
                20);
        signUp.addActionListener(e -> {
            cards.remove(createAccountPanel);
            createAccountUI = new CreateAccount();
            createAccountPanel = createAccountUI.initWin();
            cards.add(createAccountPanel, "createAccount");
            cardLayout.show(cards, "createAccount");
            });
        signUp.setBorder(border);
        toSetButtons.add(signUp);

        JButton toggleMusic = new JButton("Mute");
        toggleMusic.setFont(robotoFont.deriveFont(10f));
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
        toggleMusic.setBorder(border);
        toSetButtons.add(toggleMusic);

        super.setAttrButtons(toSetButtons);
        buttons.add(loginButton);
        buttons.add(signUp);
        buttons.add(toggleMusic);
        return buttons;
    }

    public void setImageIcon(boolean show, JButton seePass) {
        ImageIcon seePassIcon;
        if (show) {
            seePassIcon = new ImageIcon("./data/view.png");

        } else {
            seePassIcon = new ImageIcon("./data/hide.png");
        }
        Image seePassImage = seePassIcon.getImage();
        Image newSeePassImage = seePassImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon newSeePassIcon = new ImageIcon(newSeePassImage);
        seePass.setIcon(newSeePassIcon);
    }
}
