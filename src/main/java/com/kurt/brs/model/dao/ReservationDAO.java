package com.kurt.brs.model.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.kurt.brs.model.bean.ReservationBean;

//CRUD operations
public interface ReservationDAO {

	public List<ReservationBean> findAllReservations() throws EmptyResultDataAccessException;

}