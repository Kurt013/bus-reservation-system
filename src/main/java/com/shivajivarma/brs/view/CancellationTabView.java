package com.shivajivarma.brs.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import com.shivajivarma.brs.utility.ValidationUtil;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;


public class CancellationTabView extends BaseView implements View{
	
	private JTextField ticketNumber;
	private JButton submitButton;
	private BufferedImage backgroundImage;

	public CancellationTabView() {
		super(); 

        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.RESERVE3));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }

		
		this.initializeLabels();
		this.initializeComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image scaled to fit the component size
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

	
	private void initializeLabels() {
		this.add(ViewComponentFactory.createJLabelMidNormal(Labels.ENTER_TICKET_ID, new int[]{50, 130, 200, 20}, Color.white));
		
		this.add(ViewComponentFactory.createJLabelNormal(Labels.REMINDER, new int[]{59, 293, 200, 20}, Color.white));
	}
	
	private void initializeComponents() {
		Color jaclinery = new Color(248, 188, 8);
		ticketNumber = ViewComponentFactory.createJTextFieldMidNormal(new int[]{420 , 125, 150, 40});
		submitButton = ViewComponentFactory.createJButtonNormal(Labels.CANCELTIX, new int[]{405, 230, 180, 35}, Color.white, jaclinery);
		
		submitButton.setVisible(true); 
		submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		addHoverEffect(submitButton, jaclinery, Color.white, Color.white, jaclinery);
		
		ticketNumber.setName(Labels.TICKET_NO);
		ticketNumber.setMargin(new Insets(5, 40, 5, 40));
		
		inputFields.add(ticketNumber);
		ticketNumber.setForeground(Color.red);
		
		this.add(ticketNumber);
		this.add(submitButton);
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
	
	public boolean validateFields(){
		ArrayList<String> errors = new ArrayList<String>();
		String message;
		
		message = ValidationUtil.validateField(ticketNumber, new String[]{"required","numeric"});
		errors.add(message);
		
		errors.removeAll(Collections.singleton(null));
		if(!errors.isEmpty()){
			message = "";
			for (String error : errors) {message = message + error + "\n";}
			Alert.errorMessage(message);
			return false;
		}
		return true;
	}

	public int getTicketNumber() {
		return Integer.parseInt(ticketNumber.getText());
	}

	public JButton getSubmitButton() {
		return submitButton;
	}
	
}
