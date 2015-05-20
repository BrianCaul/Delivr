package com.delivr.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.delivr.model.Customer;
import com.delivr.model.Driver;
import com.delivr.model.User;
import com.delivr.model.Package;
import com.delivr.service.CustomerService;
import com.delivr.service.DriverService;
import com.delivr.service.PackageService;
import com.delivr.service.UserService;

@Controller
@SessionAttributes("package")
public class PackageRestController {

	
	@Autowired
	private PackageService packageService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DriverService driverService;

	@Autowired
	private View jsonView;
	
	private static final String[] dateFormats = { "yyyy-MMM-dd", "yyyy-MM-dd",
		"yyyy/MM/dd", "yyyy/MMM/dd" };

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/packages", method = RequestMethod.GET)
	public ModelAndView getAllPackages() {
		List<Package> packages = null;
		Map<String, List<Package>> results = new HashMap<String, List<Package>>();

		try {

			packages = packageService.getAllPackages();
		} catch (Exception e) {
			String sMessage = "Error invoking packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("packages", packages);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	@RequestMapping(value = "/rest/packages/pending", method = RequestMethod.GET)
	public ModelAndView getAllPendingPackages() {
		List<Package> packages = null;
		Map<String, List<Package>> results = new HashMap<String, List<Package>>();
		try {
			packages = packageService.getAllPendingPackages();
		} catch (Exception e) {
			String sMessage = "Error invoking pending packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("packages",packages);
		return new ModelAndView(jsonView, DATA_FIELD, results);
	}
	
	@RequestMapping(value = "/rest/packages/{customerid}/progress", method = RequestMethod.GET)
	public ModelAndView getInProgressPackages(@PathVariable String customerid) {
		List<Package> packages = null;
		Map<String, List<Package>> results = new HashMap<String, List<Package>>();
		try {
			packages = packageService.getAllProgressPackages(customerid);
		} catch (Exception e) {
			String sMessage = "Error invoking progress packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("packages",packages);
		return new ModelAndView(jsonView, DATA_FIELD, results);
	}
	
	@RequestMapping(value = "/rest/packages/{customerid}/pending", method = RequestMethod.GET)
	public ModelAndView getPendingPackages(@PathVariable String customerid) {
		List<Package> packages = null;
		Map<String, List<Package>> results = new HashMap<String, List<Package>>();
		try {
			packages = packageService.getAllPendingPackages(customerid);
		} catch (Exception e) {
			String sMessage = "Error invoking pending packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("packages",packages);
		return new ModelAndView(jsonView, DATA_FIELD, results);
	}
	
	@RequestMapping(value = "/rest/packages/{customerid}/complete", method = RequestMethod.GET)
	public ModelAndView getCompletePackages(@PathVariable String customerid) {
		List<Package> packages = null;
		Map<String, List<Package>> results = new HashMap<String, List<Package>>();
		try {
			packages = packageService.getAllCompletePackages(customerid);
		} catch (Exception e) {
			String sMessage = "Error invoking completed packages";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("packages",packages);
		return new ModelAndView(jsonView, DATA_FIELD, results);
	}
	
	@RequestMapping(value = "/rest/packages/{customerid}", method = RequestMethod.POST)
	public ModelAndView createPackage(@PathVariable String customerid, @RequestParam("name") String packageName,
			@RequestParam("description") String packageDescription,@RequestParam("weight") String packageWeight,@RequestParam("due") String datedue) {
	
		Package pack = new Package();
		Customer cus = customerService.getCustomerByID(customerid);
		pack.setCustomer(cus);
		
		Date dueDate = parseDate(datedue, dateFormats);
		pack.setDateDue(dueDate);
		
		pack.setStatus("Pending");
		pack.setPackageName(packageName);
		pack.setPackageDescription(packageDescription);
		pack.setPackageWeight(packageWeight);
		
		try {
			pack = packageService.createPackage(pack);
		} catch (Exception e) {
			String sMessage = "Error creating package";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, pack);
	}
	
	@RequestMapping(value = "/rest/packages/{packageid}/accept/{driverid}", method = RequestMethod.POST)
	public ModelAndView createPackage(@PathVariable String packageid,@PathVariable String driverid) {
	
		Package pack = packageService.getPackage(packageid);
		Driver driver  = driverService.getDriverByID(driverid);
		pack.setDriver(driver);
		Date recivedDate = new Date();
		pack.setDateReceived(recivedDate);
		
		pack.setStatus("In Progress");
		
		try {
			pack = packageService.updatePackage(pack);
		} catch (Exception e) {
			String sMessage = "Error accepting package";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, pack);
	}
	
	@RequestMapping(value = "/rest/packages/{packageid}/deliver", method = RequestMethod.POST)
	public ModelAndView createPackage(@PathVariable String packageid) {
	
		Package pack = packageService.getPackage(packageid);
		pack.setStatus("Complete");
		
		try {
			pack = packageService.updatePackage(pack);
		} catch (Exception e) {
			String sMessage = "Error completing package";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, pack);
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
	
	private static Date parseDate(String dateString, String[] formats) {
		Date date = null;

		for (int i = 0; i < formats.length; i++) {
			String format = formats[i];
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);

			try {
				// parse() will throw an exception if the given dateString
				// doesn't match
				// the current format
				date = dateFormat.parse(dateString);
				break;
			} catch (ParseException e) {
				// don't do anything. just let the loop continue.
				// we may miss on 99 format attempts, but match on one format,
				// but that's all we need.
			}
		}

		return date;
	}
}
