package com.shivajivarma.brs.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

import com.shivajivarma.brs.view.adapters.ActionAdapter;
import org.springframework.dao.EmptyResultDataAccessException;

import com.shivajivarma.brs.model.Model;
import com.shivajivarma.brs.model.bean.ReservationBean;
import com.shivajivarma.brs.model.entity.Bus;
import com.shivajivarma.brs.model.entity.Reserve;
import com.shivajivarma.brs.model.entity.Route;
import com.shivajivarma.brs.model.service.ReserveService;
import com.shivajivarma.brs.view.Alert;
import com.shivajivarma.brs.view.SeatSelectionView;
import com.shivajivarma.brs.view.View;
import com.shivajivarma.brs.utility.DateUtil;
import com.shivajivarma.brs.utility.constants.Messages;


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