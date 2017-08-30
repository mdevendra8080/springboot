package com.example;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class CustomerDao {
		
	private CustomerStoredProcedure customerStoredProcedure;

	public CustomerDao(DataSource dataSource) {
		System.out.println("DataSource::::"+dataSource);
		customerStoredProcedure = new CustomerStoredProcedure(dataSource);
	}

	public List<Customer> findById(Long id) {
		return customerStoredProcedure.execute(id);
	}
}
