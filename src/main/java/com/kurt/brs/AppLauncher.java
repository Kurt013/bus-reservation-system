package com.kurt.brs;

import java.awt.EventQueue;


import com.kurt.brs.view.MasterView;


public class AppLauncher {

	public static void main(String[] args) {
		EventQueue.invokeLater(MasterView::new);
	}

}
