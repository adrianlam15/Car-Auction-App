package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Login {
    private int width;
    private int height;
    private JFrame frame = new JFrame();

    public Login(int width, int height) {
        this.width = width;
        this.height = height;
        initWin();
    }

    private void initWin() {
        frame.setSize(width, height);
        //frame.getContentPane().setBackground(new java.awt.Color(15, 23, 42));
        frame.add(loadJPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel loadJPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new java.awt.Color(15, 23, 42));
        for (JComponent inputField : getInputFields()) {
            mainPanel.add(inputField);
        }
        return mainPanel;
    }

    private ArrayList<JComponent> getInputFields() {
        Font labelFont = new Font("Roboto", Font.PLAIN, 14);

        ArrayList<JComponent> inputFields = new ArrayList<>();
        JTextField usernameTextField = new JTextField();
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setFont(labelFont);
        usernameTextField.setBorder(BorderFactory.create);

        JPasswordField passwordTextField = new JPasswordField();
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setLabelFor(passwordTextField);
        passwordLabel.setFont(labelFont);

        usernameTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 40) / 2 - 50,
                100, 20);
        usernameLabel.setBounds((frame.getWidth() - 100) / 2 - 75, (frame.getHeight() - 40) / 2 - 53,
                100, 20);
        passwordTextField.setBounds((frame.getWidth() - 100) / 2, (frame.getHeight() - 20) / 2 - 30,
                100, 20);
        passwordLabel.setBounds((frame.getWidth() - 100) / 2 - 75, (frame.getHeight() - 20) / 2 - 33,
                100, 20);
        inputFields.add(usernameTextField);
        inputFields.add(passwordTextField);
        inputFields.add(usernameLabel);
        inputFields.add(passwordLabel);
        return inputFields;
    }

    private Collection<? extends JComponent> loadButtons() {
        List<JComponent> buttons = new ArrayList<>();
        JButton createAcc = new JButton("Create an account");
        createAcc.setSize(100, 100);
        buttons.add(createAcc);
        return buttons;
    }
}
