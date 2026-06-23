package com.kurt.brs.model.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.kurt.brs.model.bean.ReservationBean;

/**
 * CRUD operations for reservation view.
 */
public class ReservationDAOImpl extends BaseDAO implements ReservationDAO {

	public ReservationDAOImpl() {
		this.table = ReservationBean.indentity;
	}

	public List<ReservationBean> findAllReservations() throws EmptyResultDataAccessException {

		String query = "select * from " + table;

		List<ReservationBean> reservationBeans = getJdbcTemplate().query(query,
				new BeanPropertyRowMapper<ReservationBean>(ReservationBean.class));
		return reservationBeans;
	}
}
