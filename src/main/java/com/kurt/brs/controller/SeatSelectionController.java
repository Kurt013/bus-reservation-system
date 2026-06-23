package com.kurt.brs.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

import com.kurt.brs.view.adapters.ActionAdapter;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.Model;
import com.kurt.brs.model.bean.ReservationBean;
import com.kurt.brs.model.entity.Bus;
import com.kurt.brs.model.entity.Reserve;
import com.kurt.brs.model.entity.Route;
import com.kurt.brs.model.service.ReserveService;
import com.kurt.brs.view.Alert;
import com.kurt.brs.view.SeatSelectionView;
import com.kurt.brs.view.View;
import com.kurt.brs.utility.DateUtil;
import com.kurt.brs.utility.constants.Messages;


public class SeatSelectionController implements Controller{
	
	private SeatSelectionController _this;
	
	private SeatSelectionView seatSelectionView;
	private Route route;
	private String date;
	private Bus bus;
	private ReserveService reserveService;
	private List<ReservationBean> tickets = new ArrayList<ReservationBean>();
	
	
    public SeatSelectionController(View seatSelectionView, Model route, String date, Bus bus) {
    	_this = this;
    	this.seatSelectionView = (SeatSelectionView) seatSelectionView;
    	this.route = (Route) route;
    	this.date = date;
    	this.bus = bus;
    }
    
    public void control(Controller parentController){
    	MasterController masterController = (MasterController) parentController;
    	
    	
    	this.populateSeats();
    	
    	seatSelectionView.getBackButton().addActionListener(new ActionAdapter() {
			public void actionPerformed(ActionEvent ae) {
				masterController.busSelectionControl(route, date);
			}


		});

    	
    	seatSelectionView.getBookButton().addActionListener(new ActionAdapter() {
				public void actionPerformed(ActionEvent ae) {
					for (int i = 0; i < seatSelectionView.getSeats().size(); i++) {
						JCheckBox seat = seatSelectionView.getSeats().get(i);
						if (seat.isSelected()) {	
							_this.reserve(Integer.parseInt(seat.getActionCommand()), seatSelectionView.getDisc().get(i));
						}
					}
					if(!tickets.isEmpty()){
						reserveService.printTickets(tickets);
						masterController.applicationControl();
					}else{
						Alert.errorMessage(Messages.NO_SEAT_SELECTED);
					}
				}
			});
				
			}
    
    private void populateSeats() {
    	if (reserveService == null) {
    		reserveService = new ReserveService();
    	}
		try {
			List<Integer> occupiedSeatNumbers = reserveService.getOccupiedSeatNumbers(bus.getId(), date);
			
			for (Integer occupiedSeat : occupiedSeatNumbers) {
				seatSelectionView.disableSeat(occupiedSeat);
			}	

		} catch (EmptyResultDataAccessException e) {
			System.out.print("No Seats");
		}		
	}
    
    private void reserve(int seatNumber, boolean isDiscounted) {
    	if (reserveService == null) {
    		reserveService = new ReserveService();
    	}
		try{
			Reserve reserve = new Reserve();
			reserve.setBusID(bus.getId());
			reserve.setDt(date);
			reserve.setTstamp(DateUtil.getTimeStamp());
			reserve.setSeat(seatNumber);
			reserve.setDiscounted(isDiscounted);				
			int ticketNum = reserveService.reserve(reserve);
			
			reserve.setId(ticketNum);
			
			ReservationBean reservationBean = new ReservationBean(reserve);
			reservationBean.setOrigin(route.getOrigin());
			reservationBean.setDestination(route.getDestination());
			reservationBean.setArrivaltime(bus.getArrivalTime());
			reservationBean.setDeparturetime(bus.getDepartureTime());
			reservationBean.setDiscounted(reserve.getDiscounted());
			// Set fare in ReservationBean			
			reservationBean.setFare(bus.getFare());

			tickets.add(reservationBean);
		} catch (EmptyResultDataAccessException e) {
			System.out.print("Reservation Failed");
		}		
	}
    
    
}