package com.example;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import oracle.jdbc.OracleTypes;

public class CustomerStoredProcedure extends StoredProcedure {
	public static final String SPROC_NAME = "CUSTOMER_PKG.GET_CUSTOMER_DETAILS_BY_ID";
	
	public CustomerStoredProcedure(DataSource datasource){
		super(datasource, SPROC_NAME);
		declareParameter(new SqlParameter("v_id",Types.NUMERIC));
		declareParameter(new SqlOutParameter("result", OracleTypes.CURSOR,new CustomerMapper()));
				
		compile();
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> execute(Long id){
		Map<String,Object> custmerMap = super.execute(id);//, new SqlReturnResultSet("result", new CustomerMapper()));		
		List<Customer> customerList = (List<Customer>)custmerMap.get("result");
		return customerList;
	}
}
