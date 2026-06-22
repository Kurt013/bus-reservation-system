package com.shivajivarma.brs.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.shivajivarma.brs.view.adapters.ActionAdapter;
import org.springframework.dao.EmptyResultDataAccessException;

import com.shivajivarma.brs.model.bean.ReservationBean;
import com.shivajivarma.brs.model.service.ReserveService;
import com.shivajivarma.brs.view.ReservationHistoryTabView;
import com.shivajivarma.brs.view.View;


public class ReservationHistoryController implements Controller {

	private ReservationHistoryTabView historyTab;
	private ReserveService reserveService;
	private List<ReservationBean> reservationsList;

	public ReservationHistoryController(View historyTab) {
		this.historyTab = (ReservationHistoryTabView) historyTab;
	}

	public void control(Controller parentController) {

		/*
		 * Populate table
		 */
		this.populateReservationHistoryTable();

		historyTab.getPrintButton().addActionListener(new ActionAdapter() {
			public void actionPerformed(ActionEvent ae) {
				List<ReservationBean> tickets = new ArrayList<ReservationBean>();
				
				int i=0;
				for (ReservationBean reservationBean : reservationsList) {	
					if(historyTab.isRowSelected(i)){						
						tickets.add(reservationBean);
					}
					i++;
				}
				reserveService.printTickets(tickets);
			}

		});
	}

	protected void populateReservationHistoryTable() {
		if (reserveService == null) {
			reserveService = new ReserveService();
		}
		
		historyTab.clearTable();
		try {
			reservationsList = reserveService
					.getReservationHistory();

			for (ReservationBean reservationBean : reservationsList) {
				historyTab
						.addTableRow(String.valueOf(reservationBean.getId()),
								String.valueOf(reservationBean.getBusID()),
								reservationBean.getDt(),
								reservationBean.getOrigin(),
								reservationBean.getDestination(),
								reservationBean.getDeparturetime(),
								reservationBean.getArrivaltime(),
								String.valueOf(reservationBean.getSeat()
								));
			}

		} catch (EmptyResultDataAccessException e) {
			System.out.print("No reservation history");
		}
	}

}