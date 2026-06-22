package com.shivajivarma.brs;

import java.awt.EventQueue;


import com.shivajivarma.brs.view.MasterView;


public class AppLauncher {

	
	public static void main(String[] args) {

		// Use the event dispatch thread for Swing components
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MasterView();
			}
		});
	}

}
