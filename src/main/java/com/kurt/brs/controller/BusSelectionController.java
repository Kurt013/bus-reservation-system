package com.kurt.brs.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import com.kurt.brs.view.adapters.ActionAdapter;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.Model;
import com.kurt.brs.model.entity.Bus;
import com.kurt.brs.model.entity.Route;
import com.kurt.brs.model.service.BusService;
import com.kurt.brs.view.BusSelectionView;
import com.kurt.brs.view.View;

public class BusSelectionController implements Controller{
	
	final private BusSelectionController _this;
	final private BusSelectionView busSelectionView;
	final private Route route;
	final private String date;

	private List<Bus> buses;
	private BusService busService;
	
    public BusSelectionController(View busSelectionView, Model route, String date) {
    	_this = this;
    	
    	this.busSelectionView = (BusSelectionView) busSelectionView;
    	this.route = (Route) route;
    	this.date = date;
    }
    
    public void control(Controller parentController){
    	MasterController masterController = (MasterController) parentController;
    	
    	this.populateBuses();
    	
    	busSelectionView.getBackButton().addActionListener(new ActionAdapter() {
			public void actionPerformed(ActionEvent ae) {
				masterController.applicationControl();
			}
		});
    	
    	busSelectionView.getSubmitButton().addActionListener(new ActionAdapter() {
			public void actionPerformed(ActionEvent ae) {
				if(busSelectionView.validateFields()){
					masterController.seatSelectionControl(route, date, _this.searchBus(busSelectionView.getSelectedBusId()));
				}
			}
		});
    	
    }
    
    private void populateBuses() {
		if (busService == null) {
			busService = new BusService();
		}
		
		try {
			buses = busService.findAvailableBuses(route, date);
			for (Bus bus : buses) {
				busSelectionView.addBus(bus.getId(), route.getOrigin(), route.getDestination(), bus.isAc()?"Deluxe":"Regular", bus.getArrivalTime(), bus.getDepartureTime(), bus.getAvailablityCount(), bus.getFare());
			}

		} catch (EmptyResultDataAccessException e) {
			System.out.print("No Buses");
		}
		
	}
    
    private Bus searchBus(int bid){
    	for (Bus bus : buses) {
			if(bus.getId() == bid){
				return bus;
			}
		}
    	return null;
    }
    
}