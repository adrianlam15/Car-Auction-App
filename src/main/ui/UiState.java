package ui;

import javax.swing.*;
import java.awt.*;

public abstract class UiState {
    protected int width;
    protected int height;
    protected JFrame frame;
    protected CardLayout cardLayout;
    protected JPanel cards;
    protected JPanel panel;

    public UiState(CardLayout cardLayout, JPanel cards, JFrame frame) {
        this.cardLayout = cardLayout;
        this.cards = cards;
        this.frame = frame;
        panel = new JPanel();
    }

    protected JPanel initWin() {
        panel.setSize(width, height);
        panel.setVisible(true);
        return panel;
    }

    protected void switchWin(JPanel panel, String panelName) {
        cards.add(panel, panelName);
        cardLayout.show(cards, panelName);
    }
}