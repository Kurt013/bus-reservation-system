package com.kurt.brs.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.bean.ReservationBean;
import com.kurt.brs.model.dao.ReservationDAO;
import com.kurt.brs.model.dao.ReserveDAO;
import com.kurt.brs.model.entity.Reserve;
import com.kurt.brs.utility.IOHelpers;

public class ReserveService implements Service {

	/**
	 * Session variable which holds account details of the customer until he logout.
	 */
	private Reserve reserve;
	public static ClassPathXmlApplicationContext dbApplicationContext;

	public ReserveService() {
		dbApplicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
	}

	public void setModel(Reserve reserve) {
		this.reserve = reserve;
	}

	public boolean cancelTicket() throws EmptyResultDataAccessException {
		ReserveDAO reserveDAO = dbApplicationContext.getBean("reserveDAO", ReserveDAO.class);
		reserveDAO.deleteById(reserve.getId());
		return true;
	}

	public List<ReservationBean> getReservationHistory() throws EmptyResultDataAccessException {

		ReservationDAO reservationDAO = dbApplicationContext.getBean("reservationDAO", ReservationDAO.class);

		List<ReservationBean> reservationList = reservationDAO.findAllReservations();
		return reservationList;
	}

	public List<Integer> getOccupiedSeatNumbers(int bid, String date) throws EmptyResultDataAccessException {

		ReserveDAO reserveDAO = dbApplicationContext.getBean("reserveDAO", ReserveDAO.class);

		List<Integer> occupiedSeatNumbers = reserveDAO.getSeatNumbersByBusAndDate(bid, date);
		return occupiedSeatNumbers;
	}

	public void printTickets(List<ReservationBean> tickets) {
		// Load ticket-head.html from classpath
		String html = loadResourceAsString("html/ticket-head.html");

		// Append ticket cards for each reservation
		for (ReservationBean reservationBean : tickets) {
			html += createTicketCard(reservationBean);
		}

		// Load ticket-end.html from classpath
		html += loadResourceAsString("html/ticket-end.html");

		// Print the generated HTML
		IOHelpers.printHTML(html, "tickets");
	}

	private String loadResourceAsString(String resourcePath) {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
			if (inputStream != null) {
				try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name()).useDelimiter("\\A")) {
					return scanner.hasNext() ? scanner.next() : "";
				}
			} else {
				throw new IOException("Resource not found: " + resourcePath);
			}
		} catch (IOException e) {
			System.err.println("Error loading resource: " + e.getMessage());
			return ""; // Handle error gracefully
		}
	}

	private static String createTicketCard(ReservationBean reservationBean) {
		int fare;
		boolean getDiscounted = reservationBean.getDiscounted();
		if (getDiscounted) {
			fare = (int) (Math.ceil(reservationBean.getFare() * 0.8));
		} else {
			fare = reservationBean.getFare();
		}

		String card = 
				"<div class=\"top\">" +
				"  <h1 class=\"title\"><i>Wait a second, the bus ticket is being printed...</i></h1>\n" +
				"  <div class=\"printer\"></div>\n" +
				"</div>\n" +
				"<div class=\"receipts-wrapper\">\n" +
				"  <div class=\"receipts\">\n" +
				"    <div class=\"receipt\">\n" +
				"      <img class=\"airliner-logo\" src=\"C:/xampp/htdocs/bus-reservation-system-oop/src/main/resources/images/logojacliner.jpg\" alt=\"Airliner Logo\">\n" +
				"      <div class=\"details\">\n" +
				"      <div class=\"item one\">\n" +
				"        <span>Date</span>\n" +
				"        <h3>" + reservationBean.getDt() + "</h3>\n" +
				"      </div>\n" +
				"      <div class=\"item\">\n" +
				"         <span>Ticket No.</span>\n" +
				"         <h3>" + reservationBean.getId() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Bus No.</span>\n" +
				"         <h3>" + reservationBean.getBusID() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Seat No.</span>\n" +
				"         <h3>" + reservationBean.getSeat() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Dept. Time</span>\n" +
				"         <h3>" + reservationBean.getDeparturetime() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Arr. Time</span>\n" +
				"         <h3>" + reservationBean.getArrivaltime() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Terminal</span>\n" +
				"         <h3>" + reservationBean.getOrigin() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Destination</span>\n" +
				"         <h3>" + reservationBean.getDestination() + "</h3>\n" +
				"       </div>\n" +
				"       <div class=\"item\">\n" +
				"         <span>Cost</span>\n" +
				"         <h3> Php " + fare + ".00 </h3>\n" +
				"       </div>\n" +
				"    </div>\n" +
				" </div>\n" +
				" <div class=\"receipt qr-code\">\n" +
				"   <div class=\"description\">\n" +
				"     <h2><b>Successfully Reserved!<b></h2>\n" +
				"     <p><i>Present it to the bus conductor on the date of your departure.</i></p>\n" +
				"   </div>\n" +
				" </div>\n" +
				"</div>\n" +
				"</div>\n";
		return card;
	}

	public int reserve(Reserve reserve) {

		ReserveDAO reserveDAO = dbApplicationContext.getBean("reserveDAO", ReserveDAO.class);

		return reserveDAO.save(reserve);
	}

	protected void finalize() {
		dbApplicationContext.close();
		dbApplicationContext = null;
	}

}
