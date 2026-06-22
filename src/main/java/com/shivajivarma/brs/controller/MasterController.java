package com.shivajivarma.brs.controller;

import com.shivajivarma.brs.model.Model;
import com.shivajivarma.brs.model.entity.Bus;
import com.shivajivarma.brs.model.entity.TicketMaster;
import com.shivajivarma.brs.model.service.TicketMasterService;
import com.shivajivarma.brs.model.service.Service;
import com.shivajivarma.brs.view.BannerViewPanel;
import com.shivajivarma.brs.view.BusSelectionView;
import com.shivajivarma.brs.view.HomeTabsPanelView;
import com.shivajivarma.brs.view.LoginPanelView;
import com.shivajivarma.brs.view.MasterView;
import com.shivajivarma.brs.view.SeatSelectionView;
import com.shivajivarma.brs.view.View;


public class MasterController implements Controller{
	
	private MasterView masterView;
	private View bannerView;
	private Service ticketMasterService;
    
	public MasterController(MasterView masterView) {
    	this.masterView = masterView;
    }
    
      
    public void control(Controller parentController){
    	this.bannerView = new BannerViewPanel();
    	this.loginControl();
    	// this.autoLoginControl();
    }
    
    public void loginControl(){
    	View loginForm = new LoginPanelView();
    	
    	masterView.clear();
    	masterView.insertPanel(bannerView, "north");
    	masterView.insertPanel(loginForm, "center");
    	
    	Controller loginController = new LoginController(loginForm);
    	loginController.control(this);
    }
    
    public void autoLoginControl(){
        	TicketMaster ticketMaster = new TicketMaster();
        	ticketMaster.setUsername("ticket_master");
        	ticketMaster.setPassword("jacliner12345678");
        	ticketMasterService = new TicketMasterService();
        	((TicketMasterService)ticketMasterService).setModel(ticketMaster);
        	((TicketMasterService)ticketMasterService).login();
        	this.setTicketMasterService(ticketMasterService);
    		this.applicationControl();
    }
    
    
    public void applicationControl(){
    	View homeTabs = new HomeTabsPanelView();
    	
    	masterView.clear();
    	masterView.insertPanel(bannerView, "north");
    	masterView.insertPanel(homeTabs, "center");
    	
    	HomeTabsMediator homeTabsMediator = new HomeTabsMediator(homeTabs);
    	homeTabsMediator.control(this);   	
    }
    
    public void busSelectionControl(Model route, String date){
    	View busSelection = new BusSelectionView();
    	
    	masterView.clear();
    	masterView.insertPanel(busSelection, "center");
    	
    	BusSelectionController busSelectionController = new BusSelectionController(busSelection,route, date);
    	busSelectionController.control(this);   	
    }
    
    public void seatSelectionControl(Model route, String date, Bus bus){
    	View seatSelection = new SeatSelectionView();
    	
    	masterView.clear();
    	masterView.insertPanel(seatSelection, "center");
    	
    	SeatSelectionController seatSelectionController = new SeatSelectionController(seatSelection, route, date, bus);
    	seatSelectionController.control(this);   	
    }
    
    public Service getTicketMasterService() {
		return ticketMasterService;
	}

	public void setTicketMasterService(Service ticketMasterService) {
		this.ticketMasterService = ticketMasterService;
	}
    
}