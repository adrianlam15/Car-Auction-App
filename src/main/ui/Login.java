package ui;

import javax.swing.*;
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
        frame.setLayout(null);
        frame.setSize(width, height);
        frame.getContentPane().setBackground(new java.awt.Color(15, 23, 42));
        for (JComponent comp : loadJComp()) {
            frame.add(comp);
        }
        frame.setVisible(true);
    }

    private List<JComponent> loadJComp() {
        List<JComponent> comps = new ArrayList<>();
        comps.addAll(loadButtons());
        comps.addAll(loadInputFields());
        return comps;
    }

    private Collection<? extends JComponent> loadInputFields() {
        List<JComponent> inputFields = new ArrayList<>();


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
