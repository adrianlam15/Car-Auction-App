package ui;

import model.Cars;
import model.User;
import model.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * UiState class (including UI) for the Car Auction application
 */
public abstract class UiState {
    protected static JFrame frame;
    protected static CardLayout cardLayout;
    protected JPanel panel;
    protected static JPanel cards;
    protected static User currentUser = new User();
    protected static Cars listedCars;
    protected static Users users;
    protected static Font robotoFont;
    protected static HashMap<String, String> userMap;
    protected ArrayList<JButton> toSetButtons;
    protected static CreateListing createListingUI;
    protected static ViewListings viewListingsUI;
    protected static ViewYourListings viewYourListingsUI;
    protected static ViewBids viewBidsUI;
    protected static ViewWon viewWonUI;
    protected static JPanel createListingPanel;
    protected static JPanel viewListingsPanel;
    protected static JPanel viewYourListingsPanel;
    protected static JPanel viewBidsPanel;
    protected static JPanel viewWonPanel;
    protected static HashMap<String, Boolean> activeClass = new HashMap<>();

    /**
     * Constructs a new UiState
     */
    public UiState() {
        toSetButtons = new ArrayList<>();
        panel = new JPanel();
    }

    /**
     * Initializes the UI state
     * @return the JPanel of the UI state
     */
    protected JPanel initWin() {
        panel.setVisible(true);
        return panel;
    }

    /**
     * Sets the attributes of the buttons
     * @param buttons
     */
    protected void setAttrButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setBackground(new Color(99, 102, 241));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(30,41,59));
                    button.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                    button.setForeground(new Color(148,163,184));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(new Color(99,102,241));
                    button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                    button.setForeground(Color.WHITE);
                }
            });
        }
    }

    protected static void loadFont() {
        try {
            File fontFile = new File("./data/Roboto/Roboto-Regular.ttf");
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(robotoFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract JPanel loadPanel();

    protected static void updateListingPanel() {
        viewListingsPanel = viewListingsUI.loadPanel();
    }
}