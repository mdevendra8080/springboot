package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceFacade {
	@Autowired
	private DataSource datasource;

	@Autowired
	CustomerDao customerDao;

	public String save(Customer custData) {

		String sql = "INSERT INTO CUSTOMER VALUES(?,?,?,?)";
		boolean status = false;
		try {
			Connection con = datasource.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, custData.getId());
			st.setString(2, custData.getName());
			st.setString(3, custData.getEmail());
			st.setString(4, custData.getDate());
			st.execute();
			status = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = false;
			System.out.print(e);
			e.printStackTrace();

		}

		return (status == true ? custData.getName() + " CUSTOMER SAVED" : "FAILED Customer");
	}

	public List<Customer> findById(Long id1, Long id2) {
		return customerDao.findCustomer(id1, id2);
	}
}