package com.kurt.brs.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.kurt.brs.model.entity.Reserve;


public class ReserveDAOImpl extends BaseDAO implements ReserveDAO {
		
	
	public ReserveDAOImpl(){
		this.table = Reserve.indentity;
	}
	
	@Override
	public int save(Reserve reserve){
		
		String query = "INSERT INTO "+table+" (busid,dt,tstamp,seat,discounted) VALUES(?,STR_TO_DATE(?,'%d-%M-%Y'),STR_TO_DATE(?,'%d/%M/%Y'),?,?)";
		
		getJdbcTemplate().update(query, 
				new Object[] { 
				reserve.getBusID(),
				reserve.getDt(),
				reserve.getTstamp(),
				reserve.getSeat(),
				reserve.getDiscounted()});
		
		query = "select max(id) from "+table;
		
		return Integer.parseInt(getJdbcTemplate().queryForObject(query, 
				new Object[] {}, String.class));
	}
	
	public int findNewId(){
		String query = "select TID_AUTO.currval from DUAL";
		
		return (Integer) getJdbcTemplate().queryForObject(query, 
        		new BeanPropertyRowMapper<Integer>(Integer.class));
	}

	
	@Override
    public Reserve findById(int id) throws EmptyResultDataAccessException{
        String query = "select * from "+table+" where id = ?";
        
        //query single row with BeanPropertyRowMapper (Passenger.class)
        Reserve reserve = (Reserve) getJdbcTemplate().queryForObject(query, 
        		new Object[] { id }, 
				new BeanPropertyRowMapper<Reserve>(Reserve.class));
       
        return reserve;
    }

	@Override
	public List<Integer> getSeatNumbersByBusAndDate(int busid, String date) throws EmptyResultDataAccessException{
		String query = "select SEAT from RESERVE where busid=? and dt=STR_TO_DATE(?,'%d-%M-%Y')";
			
	 	List<Integer> seatNumbers = new ArrayList<Integer>();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query,new Object[] {busid,date});
		for (Map<String, Object> row : rows) {
			seatNumbers.add((Integer) row.get("SEAT"));
		}
			
		return seatNumbers;
	 }
}
