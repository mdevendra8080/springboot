package com.example;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class FooSimpleJdbcCall extends SimpleJdbcCall {

	public FooSimpleJdbcCall(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

}
