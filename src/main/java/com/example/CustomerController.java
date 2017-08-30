package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerServiceFacade service;

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody String save(@RequestBody Customer customer) {
		return service.save(customer);
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/get}") public
	 * @ResponseBody String get() { return "IN CUSTOMER"; }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Customer> create(@RequestParam(required=true,name="id")  Long id) {
		// return accountServiceFacde.create(account);

		return service.findById(id); //"Datasource :::DEVENDRA";
	}
}