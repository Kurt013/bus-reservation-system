package com.kurt.brs.model.entity;



/**
 * The Bean class, which holds reserve table details.
 * 
 */
public class Reserve implements Entity {

	public static final String indentity = "Reserve";

	private int id;
	private int busID;
	private String dt;
	private String tstamp;
	private int seat;
	private boolean discounted;

	public Reserve() {
	}

	public Reserve(int id, int busID, String dt, String tstamp,
			int seat, boolean discounted) {
		this.id = id;
		this.busID = busID;
		this.dt = dt;
		this.tstamp = tstamp;
		this.seat = seat;
		this.discounted = discounted;
	}

	@Override
	public String toString() {
		return "Reserve::[ID=" + id + ",BID=" + busID
				+ ",Date=" + dt + ",Time Stamp=" + tstamp + ",Seat=" + seat
				+ ",Discounted=" + discounted + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusID() {
		return busID;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String string) {
		this.dt = string;
	}

	public String getTstamp() {
		return tstamp;
	}

	public void setTstamp(String tstamp) {
		this.tstamp = tstamp;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public boolean getDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

}
