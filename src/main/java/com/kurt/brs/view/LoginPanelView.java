package com.kurt.brs.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import com.kurt.brs.utility.ValidationUtil;
import com.kurt.brs.utility.ViewComponentFactory;
import com.kurt.brs.utility.constants.Labels;
import com.kurt.brs.utility.constants.ResourcePaths;

public class LoginPanelView extends BaseView implements View {

    // UI Components
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton, togglePasswordButton;
    private int x = 250, y = 125;
    private Image backgroundImage;
    private boolean passwordVisible = false;
    private ImageIcon showIcon, hideIcon;

    
    public LoginPanelView() {
        super(); 
        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.LOGIN));

            // Load eye icons for password toggle
            showIcon = new ImageIcon(ImageIO.read(getClass().getResource(ResourcePaths.EYE_OPEN)));
            hideIcon = new ImageIcon(ImageIO.read(getClass().getResource(ResourcePaths.EYE_CLOSED)));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }

        initializeLabels();
        initializeComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initializeLabels() {
        Color jacliner = new Color(246, 209, 0);
        add(ViewComponentFactory.createJLabelHeader(Labels.LOGIN, new int[] { x + 410 , y - 38, 120, 28 }, jacliner));
        add(ViewComponentFactory.createJLabelNormal(Labels.USERNAME, new int[] { x + 305, y + 25, 150, 20 }, Color.white));
        add(ViewComponentFactory.createJLabelNormal(Labels.PASSWORD, new int[] { x + 305, y + 105, 150, 20 }, Color.white));
    }

    private void initializeComponents() {
        Color jaclinery = new Color(248, 188, 8);
        Insets insets = new Insets(5, 10, 3, 10);

        username = ViewComponentFactory.createJTextFieldNormal(new int[] { x + 305, y + 55, 365, 30 });
        username.setMargin(insets);

        password = ViewComponentFactory.createJPasswordFieldNormal(new int[] { x + 305, y + 135, 323, 30 });
        password.setMargin(insets);
        password.setEchoChar('•');

        // Toggle button to show/hide password using eye icons
        togglePasswordButton = new JButton(hideIcon);
        togglePasswordButton.setBounds(x + 634, y + 135, 35, 30);
        togglePasswordButton.setOpaque(true);
        togglePasswordButton.setBackground(Color.YELLOW);
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.addActionListener(e -> togglePasswordVisibility());

        // loginButton = ViewComponentFactory.createJButtonNormal(Labels.LOGINB, new int[] { x + 385, y + 190, 200, 35 }, Color.white, jaclinery);
        loginButton = new RoundButton(Labels.LOGINB, new int[] { x + 385, y + 190, 200, 35 }, Color.white, jaclinery);

        loginButton.setBorderPainted(false);
        loginButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        
        addHoverEffect(loginButton, jaclinery, Color.white, Color.white, jaclinery);

        username.setName(Labels.USERNAME);
        password.setName(Labels.PASSWORD);

        inputFields.add(username);
        inputFields.add(password);

        add(username);
        add(password);
        add(loginButton);
        add(togglePasswordButton); 
    }

    private void togglePasswordVisibility() {
        if (passwordVisible) {
            // Hide password
            password.setEchoChar('•');
            togglePasswordButton.setIcon(hideIcon);
        } else {
            // Show password
            password.setEchoChar((char) 0);
            togglePasswordButton.setIcon(showIcon);
        }
        passwordVisible = !passwordVisible;
    }

    private void addHoverEffect(JButton button, Color originalColor, Color hoverColor, Color originalTextColor, Color hoverTextColor) {
        button.setBackground(originalColor);
        button.setForeground(originalTextColor);
        button.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setForeground(hoverTextColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
                button.setForeground(originalTextColor);
            }
        });
    }

    public boolean validateFields() {
        ArrayList<String> errors = new ArrayList<>();
        String message;

        message = ValidationUtil.validateField(username, new String[] { "required", "username" });
        errors.add(message);

        message = ValidationUtil.validateField(password, new String[] { "required", "noSpaces" });
        errors.add(message);

        errors.removeAll(Collections.singleton(null));
        if (!errors.isEmpty()) {
            message = "";
            for (String error : errors) {
                message = message + error + "\n";
            }
            return false;
        }
        return true;
    }

    public String getUsername() {
        return username.getText().toLowerCase();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

}
