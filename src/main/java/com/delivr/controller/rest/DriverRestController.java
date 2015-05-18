package com.delivr.controller.rest;

import java.util.Date;
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

import com.delivr.model.Driver;
import com.delivr.model.User;
import com.delivr.service.DriverService;
import com.delivr.service.UserService;

@Controller
@SessionAttributes("driver")
public class DriverRestController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	// JSON MAPPING as it returns the jsonView
	@RequestMapping(value = "/rest/drivers", method = RequestMethod.GET)
	public ModelAndView getAllDrivers() {
		List<Driver> drivers = null;
		Map<String, List<Driver>> results = new HashMap<String, List<Driver>>();

		try {
			drivers = driverService.getAllDrivers();
		} catch (Exception e) {
			String sMessage = "Error invoking drivers";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("drivers", drivers);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	// JSON MAPPING as it returns the jsonView
	@RequestMapping(value = "/rest/{driverid}/packages", method = RequestMethod.GET)
	public ModelAndView getAllDriverPackages(@PathVariable String driverid) {
		Driver driver = null;
		try {
			driver = driverService.getDriverByID(driverid);
		} catch (Exception e) {
			String sMessage = "Error invoking packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, driver);
	}
	
	
	@RequestMapping(value = "/rest/drivers/{userid}", method = RequestMethod.POST)
	public ModelAndView createDriver(@PathVariable String userid, @RequestParam("address") String address,
			@RequestParam("review") String review,
			@RequestParam("type") String type) {

		User user = userService.findByUserId(userid);
		Driver driver = new Driver(user, address, review, type);
		try {
			driver = driverService.createDriver(driver);
		} catch (Exception e) {
			String sMessage = "Error creating driver";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, driver);
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
