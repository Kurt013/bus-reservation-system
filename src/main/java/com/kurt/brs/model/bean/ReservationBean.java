package com.kurt.brs.model.bean;

import com.kurt.brs.model.entity.Reserve;

public class ReservationBean extends Reserve implements Bean {

	public static final String indentity = "Reservation";
	
	private String origin;
	private String destination;
	private String departuretime;
	private String arrivaltime;
	private int fare;
	private boolean discounted;
	
	public ReservationBean(){
	}
	
	public ReservationBean(int id, int busID, String dt, String tstamp, int seat, String origin, String destination, String departuretime, String arrivaltime, boolean discounted) {
		this.setId(id);
		this.setBusID(busID);
		this.setDt(dt);
		this.setTstamp(tstamp);
		this.setSeat(seat);
		this.origin = origin;
		this.destination = destination;
		this.departuretime = departuretime;
		this.arrivaltime = arrivaltime;
		this.discounted = discounted;
	}
	
	public ReservationBean(Reserve reserve){
		this.setId(reserve.getId());
		this.setBusID(reserve.getBusID());
		this.setDt(reserve.getDt());
		this.setTstamp(reserve.getTstamp());
		this.setSeat(reserve.getSeat());
		this.setDiscounted(reserve.getDiscounted());
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public boolean getDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

}
