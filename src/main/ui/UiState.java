package ui;

import javax.swing.*;
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
}