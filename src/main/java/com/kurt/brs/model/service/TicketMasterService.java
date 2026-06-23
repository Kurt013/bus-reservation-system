package com.kurt.brs.model.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.dao.TicketMasterDAO;
import com.kurt.brs.model.entity.TicketMaster;


public class TicketMasterService implements Service{
	
	/**
	 * Session variable which holds account details of the customer until he logout.
	 */
	private TicketMaster ticketMaster;
	public static ClassPathXmlApplicationContext dbApplicationContext;
	
	public TicketMasterService(){
		dbApplicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
	}
	
	public void setModel(TicketMaster ticketMaster){
		this.ticketMaster = ticketMaster;
	}
	
	public TicketMaster getModel(){
		return ticketMaster;
	}
	
	public boolean isUsernameAvailable(){
		TicketMasterDAO ticketMasterDAO = dbApplicationContext.getBean("ticketmasterDAO", TicketMasterDAO.class);
		try{
			ticketMasterDAO.findByUsername(ticketMaster.getUsername());
		}catch(EmptyResultDataAccessException e){
			return true;
		}
		return false;
	}
	
	// public void register(){
	// 	PassengerDAO passengerDAO = dbApplicationContext.getBean("passengerDAO", PassengerDAO.class);
	// 	passengerDAO.save(passenger);
	// }
	
	public boolean login() throws EmptyResultDataAccessException{
		TicketMasterDAO ticketMasterDAO = dbApplicationContext.getBean("ticketmasterDAO", TicketMasterDAO.class);
		
		TicketMaster dbRecord = ticketMasterDAO.findByUsername(ticketMaster.getUsername());
		if(ticketMaster.getPassword().equals(dbRecord.getPassword())){
			setModel(dbRecord);
			return true;
		}
		return false;
	}
	
	protected void finalize(){
		dbApplicationContext.close();
		dbApplicationContext = null;
	}
}
