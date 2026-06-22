package com.shivajivarma.brs.view;

import com.shivajivarma.brs.utility.ViewComponentFactory;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

public class RoundButton extends JButton {

    private int arcWidth = 20; // Adjust the arc width for rounded corners
    private int arcHeight = 20; // Adjust the arc height for rounded corners

    public RoundButton(String name, int[] coordinates, Color textColor, Color bgColor) {
        super(name);
		this.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);

		this.setFont(ViewComponentFactory.FONT_BUTTON);
		this.setBackground (bgColor);
		this.setForeground(textColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create(); // Create a copy of Graphics

        // Draw the background shape
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setColor(getBackground());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground());
        } else {
            g2.setColor(getBackground());
        }

        // Draw a rounded rectangle based on current bounds
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

        g2.dispose(); // Dispose the Graphics2D object
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        // Check if the point is within the rounded rectangle bounds
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        return shape.contains(x, y);
    }
}
