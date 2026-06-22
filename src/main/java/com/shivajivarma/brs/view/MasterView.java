package com.shivajivarma.brs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

import com.shivajivarma.brs.controller.MasterController;
import com.shivajivarma.brs.utility.BorderLayoutPositionFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.Messages;

public class MasterView {

    private JFrame frame;

    private MasterController controller = new MasterController(this);

    public MasterView() {
        frame = new JFrame(Labels.TITLE);
        
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/jacliner-logo.png"));

        frame.setIconImage(icon.getImage());
        
        // Setting look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Alert.errorMessage(Messages.ERROR_LOOK_AND_FIELD);
        }

        // Window settings
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 1024, 740);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on screen
        frame.setLocationRelativeTo(null);

        // Give control to master controller
        controller.control(null);

        // Make the frame visible after all settings
        frame.setVisible(true);
    }

    public boolean insertPanel(View panel, String align) {
        if (align != null) {
            String position = BorderLayoutPositionFactory.create(align);
            if (position != null) {
                frame.add(position, (java.awt.Component) panel);
                frame.revalidate();
                frame.repaint();
                return true;
            } else {
                System.err.println("Invalid alignment: " + align);
                return false;
            }
        }
        return false;
    }

    public void clear() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }
}
