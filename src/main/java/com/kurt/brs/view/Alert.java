package com.kurt.brs.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class Alert {

    // Custom icons (replace with your own icon paths)
    private static final ImageIcon SUCCESS_ICON = new ImageIcon("C:\\xampp\\htdocs\\bus-reservation-system-oop\\src\\main\\resources\\images\\success.png");
    private static final ImageIcon ERROR_ICON = new ImageIcon("C:\\xampp\\htdocs\\bus-reservation-system-oop\\src\\main\\resources\\images\\error.png");

    // Custom fonts and colors
    private static final Font MESSAGE_FONT = new Font("Arial", Font.PLAIN, 14); // Custom font for message
    private static final Color SUCCESS_COLOR = new Color(34, 139, 34); // Forest green for success
    private static final Color ERROR_COLOR = new Color(178, 34, 34); // Firebrick red for error

    public static void errorMessage(String mesg) {
        showMessage(mesg, "Error", ERROR_ICON, ERROR_COLOR);
    }

    public static void successMessage(String mesg) {
        showMessage(mesg, "Success", SUCCESS_ICON, SUCCESS_COLOR);
    }

    private static void showMessage(String mesg, String title, ImageIcon icon, Color color) {
        // Scale the icon to the desired size
        java.awt.Image scaledIcon = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIconImage = new ImageIcon(scaledIcon);

        // Use JTextArea for multiple lines support
        JTextArea messageTextArea = new JTextArea(mesg);
        messageTextArea.setFont(MESSAGE_FONT);
        messageTextArea.setForeground(color);
        messageTextArea.setBackground(null); // Make background transparent
        messageTextArea.setEditable(false);
        messageTextArea.setWrapStyleWord(true);

        messageTextArea.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0)); // Add padding around the text

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS)); // Align components horizontally
        panel.add(new JLabel(scaledIconImage)); // Align icon to the left
        panel.add(Box.createRigidArea(new Dimension(0, 0))); // Add space between icon and text
        panel.add(messageTextArea); // Add text area
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around the panel

        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setIconImage(null); // Ensure no default icon is shown
        dialog.setVisible(true);

    }
}
