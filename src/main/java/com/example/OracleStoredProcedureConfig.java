package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

import oracle.jdbc.OracleTypes;

@Configuration
public class OracleStoredProcedureConfig implements StoredProcedureConfig {

	@Autowired
	private DataSource datasource;
	
	
	@Override @Bean
	public FooSimpleJdbcCall customerSearch() {
		FooSimpleJdbcCall call = new FooSimpleJdbcCall(datasource);
		//FIXME externalize these strings to StoredProcMetaData
		System.out.println("inside FooSimpleJdbcCall");
		call.withCatalogName("CUSTOMER_PKG")
			 .withProcedureName("GET_CUSTOMER_DETAILS_BY_ID")
			 .declareParameters(new SqlParameter("v_id1", OracleTypes.NUMBER),
					 new SqlParameter("v_id2", OracleTypes.NUMBER),
					 new SqlOutParameter("result", OracleTypes.CURSOR, new CustomerMapper()))
			 .compile();
		return call;
	}

}
