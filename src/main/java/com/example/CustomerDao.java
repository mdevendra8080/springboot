package com.example;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao {

	//private CustomerStoredProcedure customerStoredProcedure;
	//private DataSource dataSource;
	@Autowired
	private FooSimpleJdbcCall customerSearch;

//	public CustomerDao(DataSource dataSource) {
//		this.dataSource = dataSource;
//		System.out.println("DataSource::::" + dataSource);
//		customerStoredProcedure = new CustomerStoredProcedure(dataSource);
//	}

//	public List<Customer> findById(Long id) {
//		return customerStoredProcedure.execute(id);
//	}

	public List<Customer> findCustomer(Long id1, Long id2) {

		final Map<String, Object> params = new HashMap<>();
		params.put("v_id1", id1);
		params.put("v_id2", id2);
		Map<String, Object> results = customerSearch.execute(params);
		@SuppressWarnings("unchecked")
		List<Customer> customerList = (List<Customer>) results.get("result");

		try {
			JAXBContext jc = JAXBContext.newInstance(JSONWrapper.class, Customer.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshal(marshaller, customerList, "result");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerList;
	}

	
	
	/**
	 * Wrap List in Wrapper, then leverage JAXBElement to supply root element
	 * information.
	 */
	private static void marshal(Marshaller marshaller, List<?> list, String name) throws JAXBException {
		QName qName = new QName(name);
		JSONWrapper wrapper = new JSONWrapper(list);
		JAXBElement<JSONWrapper> jaxbElement = new JAXBElement<JSONWrapper>(qName, JSONWrapper.class, wrapper);
		File f = new File("D://test//customerList.json");
		marshaller.marshal(jaxbElement, f);
	}
}
