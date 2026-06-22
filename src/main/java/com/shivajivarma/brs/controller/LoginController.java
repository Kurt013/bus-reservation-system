package com.shivajivarma.brs.controller;

import java.awt.event.ActionEvent;

import com.shivajivarma.brs.view.adapters.ActionAdapter;
import org.springframework.dao.EmptyResultDataAccessException;

import com.shivajivarma.brs.model.entity.TicketMaster;
import com.shivajivarma.brs.model.service.TicketMasterService;
import com.shivajivarma.brs.view.Alert;
import com.shivajivarma.brs.view.LoginPanelView;
import com.shivajivarma.brs.view.View;
import com.shivajivarma.brs.utility.constants.Messages;

public class LoginController implements Controller{
	
	private LoginController _this;
	private TicketMasterService ticketMasterService;
	private LoginPanelView loginView;
	private TicketMaster ticketMaster;
	
    public LoginController(View loginView) {
    	_this = this;
    	this.loginView = (LoginPanelView) loginView;
    	this.ticketMaster = new TicketMaster();
    }
    
    public void control(Controller parentController){
    	MasterController masterController = (MasterController) parentController;

    	loginView.getLoginButton().addActionListener(new ActionAdapter() {
			public void actionPerformed(ActionEvent ae) {
				if(loginView.validateFields()){
					ticketMaster.setUsername(loginView.getUsername());
					ticketMaster.setPassword(loginView.getPassword());
					try{
						if(_this.login()){
							masterController.setTicketMasterService(ticketMasterService);
							masterController.applicationControl();
						}else{
							loginView.refresh();
							Alert.errorMessage(Messages.ERROR_WRONG_PASSWORD);
						}
					}catch(EmptyResultDataAccessException e){
						loginView.refresh();
						Alert.errorMessage(Messages.ERROR_NO_USERNAME);
					}
				}
			}
		});
    	
    	
    }
    
    private boolean login()throws EmptyResultDataAccessException{
    	if(ticketMasterService == null){
    		ticketMasterService = new TicketMasterService();
    	}
    	ticketMasterService.setModel(ticketMaster);
    	return ticketMasterService.login();
    }    
    
}