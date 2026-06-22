package com.shivajivarma.brs.view;

import java.awt.Color;
import java.awt.Component;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import com.shivajivarma.brs.utility.DateUtil;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;

public class ReservationTabView extends BaseView implements View {

    private JButton submitButton;
    private JComboBox<String> origin, destination, month;
    private JComboBox<Integer> date, year;
    private int x = 200, y = 40;
    private BufferedImage backgroundImage;
	public static final Font FONT_HOME;

	static {
		
		FONT_HOME = loadFont("/fonts/Inter-SemiBold.ttf", Font.PLAIN, 17);
	}

	private static Font loadFont(String path, int style, int size) {
		try (InputStream is = ViewComponentFactory.class.getResourceAsStream(path)) {
			if (is == null) {
				throw new IOException("Font resource not found: " + path);
			}
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			return font.deriveFont(style, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			// Fallback to a default font in case of an error
			return new Font("Serif", style, size);
		}
	}
	

    public ReservationTabView() {
        super(); 

        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.RESERVE2));
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
        this.add(ViewComponentFactory.createJLabelNormal(Labels.ORIGIN, new int[]{x+220, y, 70, 30}, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.DESTINATION, new int[]{x + 500, y, 200, 30}, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.DATE, new int[]{x + 220, y + 120, 240, 30}, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.REMINDER2, new int[]{x + 237, y + 280, 240, 30}, Color.white));
    }

    private void initializeComponents() {

        Color jaclinery = new Color(248, 188, 8);

        origin = createCustomJComboBox(new int[]{x+220, y + 40, 200, 30});
        destination = createCustomJComboBox(new int[]{x + 500, y + 40, 200, 30});
        date = createCustomJComboBox(new int[]{x + 480, y + 160, 100, 30});
        month = createCustomJComboBox(new int[]{x + 310, y + 160, 150, 30});
        year = createCustomJComboBox(new int[]{x + 220, y + 160, 70, 30});
    
        month.addItem("Select a month");
        for (int i = 1; i <= 12; i++) {
            month.addItem(Month.of(i).toString());
        }
    
        year.addItem(DateUtil.currentYear());
        year.addItem(DateUtil.currentYear() + 1);
    
        submitButton = ViewComponentFactory.createJButtonNormal(Labels.SUBMIT, new int[]{x + 550, y + 220, 150, 35}, Color.white, jaclinery);
        
        
        submitButton.setVisible(false);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        addHoverEffect(submitButton, jaclinery, Color.white, Color.white, jaclinery);
    
        this.add(origin);
        this.add(destination);
        this.add(date);
        this.add(month);
        this.add(year);
        this.add(submitButton);
    }
    

    private <T> JComboBox<T> createCustomJComboBox(int[] bounds) {
        
		JComboBox<T> comboBox = new JComboBox<>();
        comboBox.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        
        // Custom renderer to change text color and background color
        comboBox.setRenderer(new CustomComboBoxRenderer());
		comboBox.setFont(FONT_HOME);
		comboBox.setForeground(new Color(182, 4, 11));
		
        
        // Mouse listener for hover effect
        comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                comboBox.setBackground(new Color(230, 230, 230)); // Light blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                comboBox.setBackground(Color.WHITE); // Default background color
            }
        });

        return comboBox;
    }

    private class CustomComboBoxRenderer extends BasicComboBoxRenderer {
        @Override
        public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                setBackground(new Color(182, 4, 11)); // Background color when selected
                setForeground(Color.WHITE); // Text color when selected
            } else {

                setBackground(Color.WHITE); // Default background color
                setForeground(new Color(182, 4, 11));// Default text color
            }

            return this;
        }
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

    public void addOrigin(String origin) {
        this.origin.addItem(origin);
    }

    public void addDestination(String destination) {
        this.destination.addItem(destination);
    }

    public void addDate(Integer date) {
        this.date.addItem(date);
    }

    public void clearDestinations() {
        destination.removeAllItems();
    }

    public void clearDate() {
        date.removeAllItems();
    }

    public int getMonthSelectedIndex() {
        return month.getSelectedIndex();
    }

    public String getSelectedMonth() {
        return month.getSelectedItem().toString();
    }

    public int getDateSelectedIndex() {
        return date.getSelectedIndex();
    }

    public int getSelectedDate() {
        return Integer.parseInt(date.getSelectedItem().toString());
    }

    public int getYearSeleted() {
        return Integer.parseInt(year.getSelectedItem().toString());
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JComboBox<String> getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination.getSelectedItem().toString();
    }

    public JComboBox<String> getMonth() {
        return month;
    }

    public JComboBox<Integer> getYear() {
        return year;
    }
}
