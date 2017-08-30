package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Customer c = new Customer();
		c.setId(rs.getLong("ID"));
		c.setName(rs.getString("NAME"));
		c.setEmail(rs.getString("EMAIL"));
		c.setDate(rs.getString("VDATE"));
		return c;
	}

}
