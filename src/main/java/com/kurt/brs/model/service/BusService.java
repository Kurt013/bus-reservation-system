package com.kurt.brs.model.service;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.dao.BusDAO;
import com.kurt.brs.model.entity.Bus;
import com.kurt.brs.model.entity.Route;


public class BusService implements Service {

	public static ClassPathXmlApplicationContext dbApplicationContext;

	public BusService() {
		dbApplicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
	}

	public List<Bus> findAvailableBuses(Route route,String date) throws EmptyResultDataAccessException {

		BusDAO busDAO = dbApplicationContext.getBean("busDAO",BusDAO.class);

		return busDAO.findByRouteAndDate(route, date);
	}

	protected void finalize() {
		dbApplicationContext.close();
		dbApplicationContext = null;
	}

}
