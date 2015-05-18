package com.delivr.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.delivr.model.Customer;
import com.delivr.model.Driver;
import com.delivr.model.User;
import com.delivr.service.CustomerService;
import com.delivr.service.DriverService;
import com.delivr.service.UserService;

@Controller
@SessionAttributes("customer")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/customers", method = RequestMethod.GET)
	public ModelAndView getAllCustomers() {
		List<Customer> customers = null;
		Map<String, List<Customer>> results = new HashMap<String, List<Customer>>();

		try {

			customers = customerService.getAllCustomers();
		} catch (Exception e) {
			String sMessage = "Error invoking customers";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("customers", customers);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	@RequestMapping(value = "/rest/{customerid}/packages", method = RequestMethod.GET)
	public ModelAndView getAllCustomerPackages(@PathVariable String customerid) {
		Customer customer = null;
		try {
			customer = customerService.getCustomerByID(customerid);
		} catch (Exception e) {
			String sMessage = "Error invoking packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, customer);
	}
	
	@RequestMapping(value = "/rest/customers/{userid}", method = RequestMethod.POST)
	public ModelAndView createDriver(@PathVariable String userid, @RequestParam("address") String address,
			@RequestParam("review") String review) {

		User user = userService.findByUserId(userid);
		Customer customer = new Customer(user, address, review);
		try {
			customer = customerService.createCustomer(customer);
		} catch (Exception e) {
			String sMessage = "Error creating customer";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, customer);
	}

	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 * @return
	 */
	private ModelAndView getErrorJSON(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}
}
