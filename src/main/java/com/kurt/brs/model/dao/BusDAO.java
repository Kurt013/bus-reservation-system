package com.kurt.brs.model.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.entity.Bus;
import com.kurt.brs.model.entity.Route;

/**
 * CRUD operations for bus table.
 */
public interface BusDAO {
  
  //Read
  public List<Bus> findByRouteAndDate(Route route, String date) throws EmptyResultDataAccessException;

}