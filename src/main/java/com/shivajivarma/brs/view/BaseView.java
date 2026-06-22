package com.shivajivarma.brs.view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;


public abstract class BaseView extends JPanel{
	
	ArrayList<JTextField> inputFields;
	
	public BaseView() {
		this.setLayout(null);
		this.inputFields = new ArrayList<JTextField>();
	}
	
	/**
	 * Clears all input and repaints the panel.
	 */
	public void refresh(){
		inputFields.forEach(field -> field.setText(null));
		this.revalidate();
	}
}