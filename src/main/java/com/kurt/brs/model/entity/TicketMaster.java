package com.kurt.brs.model.entity;

/**
 * The Bean class, which holds ticket master properties.
 */
public class TicketMaster implements Entity{
	
	public static final String indentity = "Ticket_Master";
	
	private int id;
	private String username;
	private String password;

	public TicketMaster() {
	}

	public TicketMaster(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "TicketMaster::[ID=" + id + ",Username=" + username
				+ ",Password=" + password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
