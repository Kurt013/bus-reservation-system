package com.kurt.brs.model.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.kurt.brs.model.entity.TicketMaster;

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * The Service class, which contains business logic to update TicketMaster model.
 */
public class TicketMasterDAOImpl extends BaseDAO implements TicketMasterDAO {
	
	private static final String table = TicketMaster.indentity;
	
	public void save(TicketMaster ticketMaster){
		
		String sql = "insert into "+table+" (username,password) values(?,?)";
			 
		getJdbcTemplate().update(sql, 
				new Object[] { 
					ticketMaster.getUsername(),
					ticketMaster.getPassword()});
			
	}

	/**
	 * Following function retrieves Ticket Master data for given id.
	 */
    @Override
    public TicketMaster findById(int id) throws EmptyResultDataAccessException{
        String query = "select * from "+table+" where id = ?";
        
        //query single row with BeanPropertyRowMapper (TicketMaster.class)
        TicketMaster ticketMaster = (TicketMaster)getJdbcTemplate().queryForObject(query, 
        		new Object[] { id }, 
				new BeanPropertyRowMapper<TicketMaster>(TicketMaster.class));
       
        return ticketMaster;
    }
    
    /**
	 * Following function retrieves Ticket Master data for given username.
	 */
    @Override
    public TicketMaster findByUsername(String username) throws EmptyResultDataAccessException{
        String query = "select * from "+table+" where username = ?";
     
        //query single row with BeanPropertyRowMapper (TicketMaster.class)
        TicketMaster ticketMaster = (TicketMaster)getJdbcTemplate().queryForObject(query, 
    		   			new Object[] { username }, 
    		   			new BeanPropertyRowMapper<TicketMaster>(TicketMaster.class));
        return ticketMaster;
    }
 
}