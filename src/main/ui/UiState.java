package ui;

import model.Car;
import model.Cars;
import model.User;
import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * UiState class (including UI) for the Car Auction application
 */
public abstract class UiState {
    protected int width;
    protected int height;
    protected JFrame frame;
    protected CardLayout cardLayout;
    protected JPanel cards;
    protected JPanel panel;
    protected User currentUser;
    protected Cars listedCars;

    /**
     * Constructs a new UiState
     * @param cardLayout
     * @param cards
     * @param frame
     */
    public UiState(CardLayout cardLayout, JPanel cards, JFrame frame) {
        this.cardLayout = cardLayout;
        this.cards = cards;
        this.frame = frame;
        this.listedCars = new Cars();
        panel = new JPanel();
    }

    /**
     * Initializes the UI state
     * @return the JPanel of the UI state
     */
    protected JPanel initWin() {
        panel.setSize(width, height);
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
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(30,41,59));
                    button.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(new Color(99,102,241));
                    button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                }
            });
        }
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addCars(Car car) {
        this.listedCars.addCar(car);
    }

    public void setListedCars(Cars listedCars) {
        this.listedCars = listedCars;
    }

    protected void setUsers(Users users) {
        this.listedCars = listedCars;
    }
}