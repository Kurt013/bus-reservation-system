package com.kurt.brs.controller;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import com.kurt.brs.model.service.TicketMasterService;
import com.kurt.brs.view.CancellationTabView;
import com.kurt.brs.view.HomeTabsPanelView;
import com.kurt.brs.view.ReservationHistoryTabView;
import com.kurt.brs.view.ReservationTabView;
import com.kurt.brs.view.View;
import com.kurt.brs.view.adapters.ActionAdapter;
import com.kurt.brs.view.adapters.ChangeAdapter;

public class HomeTabsMediator implements Controller{
	
	private HomeTabsMediator _this;
	private MasterController masterController;
	
	private HomeTabsPanelView homeTabs;
	private CancellationController cancellationController;
	private ReservationHistoryController historyTabController;
	private ReservationController reservationController;
	private TicketMasterService ticketMasterService;
	
    public HomeTabsMediator(View homeTabs) {
    	_this = this;
    	this.homeTabs = (HomeTabsPanelView) homeTabs;
    }
    
    public void control(Controller parentController){
    	masterController = (MasterController) parentController;
    	ticketMasterService = (TicketMasterService) masterController.getTicketMasterService();
    	
    	View reservationTab = new ReservationTabView();
    	View cancelTab = new CancellationTabView();
    	View historyTab = new ReservationHistoryTabView();
    	
    	homeTabs.insertTab(reservationTab, "Reservation ");
    	homeTabs.insertTab(cancelTab, "Cancel Ticket ");
    	homeTabs.insertTab(historyTab, "History ");

    	
    	
    	reservationController = new ReservationController(reservationTab);
    	reservationController.control(this);
    	
    	cancellationController = new CancellationController(cancelTab);
    	cancellationController.control(this);
    	
    	historyTabController = new ReservationHistoryController(historyTab);
    	historyTabController.control(this);
    	
    	
    	
    	/**
		 *  On clicking logout button, control goes back login form.
		 */
    	homeTabs.getLogoutButton().addActionListener(new ActionAdapter() {
    		
    		@Override
			public void actionPerformed(ActionEvent ae) {
				masterController.loginControl();
			}
		});
    	
    	homeTabs.getTabs().addChangeListener(new ChangeAdapter() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				_this.updateTabs();
			}
		});
    	
    }
    
    public MasterController getMasterController() {
		return masterController;
	}

	public TicketMasterService getTicketMasterService() {
		return ticketMasterService;
	}
    
    public void updateTabs(){
    	historyTabController.populateReservationHistoryTable();
    }
        
}