package ui;

import model.User;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Login class (including UI) for the Car Auction application
 */
public class Login extends UiState {
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;
    private String username;
    private String password;
    private Clip clip;

    public Login() {
        super();
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        this.inputFields = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.toSetButtons = new ArrayList<>();
    }

    // REQUIRES: musicLocation is a valid file path
    // MODIFIES: this
    // EFFECTS: plays music
    public void playMusic(String musicLocation) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicLocation)
                    .getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // MODIFIES: this, UiState
    // EFFECTS: initializes the login window
    protected JPanel initWin() {
        playMusic("./data/ZA-ZA - LOVE & MONEY.wav");
        super.initWin();
        return loadPanel();
    }

    // MODIFIES: this, UiState
    // EFFECTS: loads the login panel
    protected JPanel loadPanel() {
        panel.setLayout(null);
        panel.setBackground(new Color(15, 23, 42));
        getInputFields().forEach(inputField -> panel.add(inputField));
        getJButtons().forEach(button -> panel.add(button));
        try {
            getImages().forEach(image -> panel.add(image, BorderLayout.CENTER));
        } catch (IOException e) {
            System.out.println("Error importing images");
        }
        return panel;
    }

    // REQUIRES: images are valid file paths
    // EFFECTS: loads the images for the login panel and returns them as an ArrayList
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

    // MODIFIES: this, UiState
    // EFFECTS: loads the input fields for the login panel and returns them as an ArrayList
    private ArrayList<JComponent> getInputFields() {
        usernameTextField.setForeground(Color.WHITE);
        JLabel usernameLabel = addLabel("Username", usernameTextField,
                (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 70);
        setInputFields(usernameTextField, (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 50);

        JLabel passwordLabel = addLabel("Password", passwordTextField,
                (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 - 20);
        setInputFields(passwordTextField, (frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2);

        JLabel orLabel = new JLabel("OR");
        orLabel.setForeground(new Color(148,163,184));
        orLabel.setFont(robotoFont.deriveFont(8f));
        orLabel.setBounds((frame.getWidth() - 100) / 2 + 45, (frame.getHeight() - 40) / 2 + 40,
                20, 20);
        inputFields.add(createUnderline((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 - 40
        ));
        inputFields.add(createUnderline((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 20) / 2 + 10
        ));

        inputFields.add(orLabel);
        inputFields.add(usernameTextField);
        inputFields.add(passwordTextField);
        inputFields.add(usernameLabel);
        inputFields.add(passwordLabel);
        return inputFields;
    }

    // REQUIRES: x and y are valid coordinates, labelField is not null
    // EFFECTS: adds a label to the UI
    private JLabel addLabel(String text, JTextField labelField, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(148,163,184));
        label.setLabelFor(labelField);
        label.setFont(robotoFont.deriveFont(11f));
        setFixedBounds(label, x, y);
        return label;
    }

    // REQUIRES: x and y are valid coordinates, field is not null
    // EFFECTS: sets the input fields for the Login UI
    private void setInputFields(JTextField field, int x, int y) {
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setOpaque(false);
        field.setForeground(Color.WHITE);
        field.setFont(robotoFont.deriveFont(11f));
        setFixedBounds(field, x, y);
    }

    // REQUIRES: x and y are valid coordinates, component is not null
    // EFFECTS: sets the bounds for the input fields
    private void setFixedBounds(JComponent component, int x, int y) {
        component.setBounds(x, y, 200, 20);
    }

    // REQUIRES: x and y are valid coordinates
    // EFFECTS: creates an underline for the input fields
    private JLabel createUnderline(int x, int y) {
        JLabel underline = new JLabel("_________________________________________________");
        underline.setFont(robotoFont.deriveFont(9f));
        underline.setForeground(new Color(148,163,184));
        underline.setBounds(x, y, 300, 10);
        return underline;
    }

    // MODIFIES: this, UiState
    // EFFECTS: loads the buttons for the login panel and returns them as an ArrayList
    private ArrayList<JComponent> getJButtons() {
        JButton loginButton = new JButton("Login");
        loginButton.setBounds((frame.getWidth() - 100) / 2 + 70, (frame.getHeight() - 40) / 2 + 40, 80,
                20);
        setListener(loginButton, "login");

        JButton seePass = new JButton();
        ImageIcon seePassIcon = new ImageIcon("./data/view.png");
        Image seePassImage = seePassIcon.getImage();
        Image newSeePassImage = seePassImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon newSeePassIcon = new ImageIcon(newSeePassImage);
        seePass.setIcon(newSeePassIcon);
        seePass.setBounds((frame.getWidth() - 100) / 2 + 160, (frame.getHeight() - 20) / 2,
                25, 25);
        setListener(seePass, "seePass");

        JButton signUp = new JButton("Sign Up");
        signUp.setBounds((frame.getWidth() - 100) / 2 - 50, (frame.getHeight() - 40) / 2 + 40, 80,
                20);
        setListener(signUp, "signUp");

        JButton toggleMusic = new JButton("Mute");
        toggleMusic.setBounds((frame.getWidth() - 80) / 2, (frame.getHeight() - 40) / 2 + 100, 20,
                20);
        setListener(toggleMusic, "music");

        super.setAttrButtons(toSetButtons);
        return buttons;
    }

    // MODIFIES: this, UiState
    // EFFECTS: sets the listener for the buttons
    @SuppressWarnings("methodlength")
    private void setListener(JButton button, String type) {
        Border border = BorderFactory.createLineBorder(new Color(30,41,59), 2);
        button.setFont(robotoFont.deriveFont(10f));
        button.setBorder(border);
        if (type.equals("music")) {
            button.addActionListener(e -> {
                changeMusicOption(button);
            });
        } else if (type.equals("signUp")) {
            button.addActionListener(e -> {
                cards.remove(loginPanel);
                createAccountUI = new CreateAccount();
                createAccountPanel = createAccountUI.initWin();
                cards.add(createAccountPanel, "createAccount");
                cardLayout.show(cards, "createAccount");
            });
        } else if (type.equals("login")) {
            button.addActionListener(e -> {
                handleLogin();
            });
            button.setBorder(border);
        } else {
            final boolean[] show = {false};
            button.addActionListener(e -> {
                handleCaseChange(show, button);
            });
        }
        toSetButtons.add(button);
        buttons.add(button);
    }

    // MODIFIES: this
    // EFFECTS: sets the image icon for the see password button, and switch between show and hide password
    private void handleCaseChange(boolean[] show, JButton button) {
        if (show[0]) {
            passwordTextField.setEchoChar('•');
            setImageIcon(show[0], button);
            show[0] = false;
        } else {
            passwordTextField.setEchoChar((char) 0);
            setImageIcon(show[0], button);
            show[0] = true;
        }
    }

    // REQUIRES: button is not null
    // MODIFIES: this
    // EFFECTS: unmutes or mutes the music
    private void changeMusicOption(JButton button) {
        if (button.getText().equals("Mute")) {
            clip.stop();
            button.setText("Unmute");
        } else {
            clip.start();
            button.setText("Mute");
        }
    }

    // MODIFIES: this, User, UiState
    // EFFECTS: handles the login process
    private void handleLogin() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();
        loggedIn = currentUser.login(username, password, userMap);
        if (loggedIn) {
            for (User user : users.getUsers()) {
                if (user.getUsername().equals(username)) {
                    if (!loaded) {
                        currentUser = new User();
                        currentUser.createUser(user.getUsername(), user.getPassword(), user.getPassword());
                    } else {
                        currentUser = user;
                    }
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
    }

    // REQUIRES: seePass is not null, show is not null
    // EFFECTS: sets the image icon for the see password button
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
