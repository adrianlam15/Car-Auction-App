package ui;

import javax.swing.*;
import java.awt.*;

/**
 * MainMenu class (including UI) for the Car Auction application
 */
public class MainMenu extends UiState {

    /**
     * Constructs a new MainMenu UI state
     * @param cardLayout
     * @param cards
     * @param frame
     */
    public MainMenu(CardLayout cardLayout, JPanel cards, JFrame frame) {
        super(cardLayout, cards, frame);
    }
}
