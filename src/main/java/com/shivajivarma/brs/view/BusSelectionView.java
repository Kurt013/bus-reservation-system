package com.shivajivarma.brs.view;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;



import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;

public class BusSelectionView extends BaseView implements View {

    private JButton submitButton, backButton;
    private JTable busTable;
    private DefaultTableModel tableModel;
	private BufferedImage backgroundImage;

    public BusSelectionView() {
        		super(); // Call superclass constructor

        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.RESERVE5));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }

		
		
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

    

    private void initializeComponents() {
		Color jaclinery = new Color(248, 188, 8);
		Color jaclinerr = new Color(220, 2, 11);
        submitButton = new RoundButton(Labels.SUBMIT, new int[]{520, 295, 100, 40}, Color.white, jaclinerr);
        backButton = new RoundButton(Labels.BACK, new int[]{370, 295, 100, 40}, Color.white, jaclinerr);
        
		submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		addHoverEffect(submitButton, jaclinerr, jaclinery, Color.white, Color.white);
		addHoverEffect(backButton, jaclinerr, jaclinery, Color.white, Color.white);

        add(submitButton);
        add(backButton);

        // Initialize the table
        String[] columnNames = {"", "B Id", "Terminal", "Destination", "Type", "Dept Tm", "Arr tm", "Availability", "Fare"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0: return Boolean.class;
                    case 1: return Integer.class;
                    case 2: return String.class;
                    case 3: return String.class;
                    case 4: return String.class;
                    case 5: return String.class;
                    case 6: return String.class;
                    case 7: return Integer.class;
                    case 8: return Integer.class;
                    default: return String.class;
                }
            }
        };
        busTable = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass(); // Ensure correct class type for columns
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Allow only the "Select" column to be editable
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);                

                // Center align text in all columns
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(JLabel.CENTER);
                }
                
                boolean isSelected = (Boolean) getValueAt(row, 0);
                if (isSelected) {
                    c.setBackground(Color.gray);
                    c.setForeground(Color.WHITE);
                } else {
                    if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
					c.setForeground(new Color(182, 4, 11));
                } else {
                    c.setBackground(new Color(240, 240, 240)); 
					c.setForeground(new Color(182, 4, 11));
                }
                }
                return c;
            }
        };

        // Set font for the table
        Font tableFont = new Font("Arial", Font.PLAIN, 14);
        busTable.setFont(tableFont);
		busTable.setShowGrid(false);
        busTable.setShowHorizontalLines(false);

        // Set row height for the table
        busTable.setRowHeight(35); // Adjust the height as needed

        // Set column widths
        TableColumnModel columnModel = busTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(100);
        columnModel.getColumn(8).setPreferredWidth(100);

        // Set header font and background
        JTableHeader header = busTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        

        // Set custom renderer for header cells to set font and foreground color
        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 14));
				label.setForeground(Color.white);
				label.setBackground(new Color(220, 2, 11));
                return label;
            }
        };
        header.setDefaultRenderer(headerRenderer);

        // Remove border from JScrollPane
        JScrollPane scrollPane = new JScrollPane(busTable);
        scrollPane.setBounds(50, 115, 900, 160); 
        scrollPane.setBorder(null); 
        add(scrollPane);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 0) {
                    int row = e.getFirstRow();
                    boolean isChecked = (Boolean) tableModel.getValueAt(row, 0);
                    if (isChecked) {
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            if (i != row) {
                                tableModel.setValueAt(false, i, 0);
                                busTable.repaint(); // Repaint the table when changing selection
                            }
                        }
                    }
                }
            }
        });
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
    public void addBus(int bid, String origin, String destination, String type, String arrtime, String depttime, int count, int fare) {
        Object[] row = {false, bid, origin, destination, type, depttime, arrtime, count, "Php "+fare+".00"};
        tableModel.addRow(row);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public boolean validateFields() {
        for (int i = 0; i < busTable.getRowCount(); i++) {
            if ((Boolean) busTable.getValueAt(i, 0)) {
                return true;
            }
        }
        return false;
    }

    public int getSelectedBusId() {
        for (int i = 0; i < busTable.getRowCount(); i++) {
            if ((Boolean) busTable.getValueAt(i, 0)) {
                return (Integer) busTable.getValueAt(i, 1);
            }
        }
        return -1; // Return an invalid value if no bus is selected
    }
}
