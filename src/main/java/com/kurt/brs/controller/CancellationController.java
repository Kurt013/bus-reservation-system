package com.kurt.brs.controller;

import java.awt.event.ActionEvent;

import com.kurt.brs.view.adapters.ActionAdapter;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.entity.Reserve;
import com.kurt.brs.model.service.ReserveService;
import com.kurt.brs.view.Alert;
import com.kurt.brs.view.CancellationTabView;
import com.kurt.brs.view.View;
import com.kurt.brs.utility.constants.Messages;

public class CancellationController implements Controller{
	
	private CancellationController _this;
	
	private CancellationTabView cancellationTab;
	private Reserve reserve;
	private ReserveService reserveService;
	
    public CancellationController(View cancellationTab) {
    	_this = this;
    	this.cancellationTab = (CancellationTabView) cancellationTab;
    	this.reserve = new Reserve();
    }
    
    public void control(Controller parentController){
    	
    	// reserve.setPassengerID(((HomeTabsMediator)parentController).getTicketMasterService().getModel().getId());
    	
    	cancellationTab.getSubmitButton().addActionListener(new ActionAdapter() {
			
			public void actionPerformed(ActionEvent ae) {
				if(cancellationTab.validateFields()){
					reserve.setId(cancellationTab.getTicketNumber());
					try{
						if(_this.cancelTicket()){
							Alert.successMessage(Messages.CANCEL_SUCCESS);
						}else{
							Alert.errorMessage(Messages.NO_ACCESS_TO_CANCEL);
						}
					}catch(EmptyResultDataAccessException e){
						Alert.errorMessage(Messages.UNKNOWN_TICKET_NO);
					}
				}
			}
			
		});
    	
    }
    
    private boolean cancelTicket(){
    	if(reserveService == null){
    		reserveService = new ReserveService();
    	}
    	reserveService.setModel(reserve);
    	return reserveService.cancelTicket();
    }
        
}