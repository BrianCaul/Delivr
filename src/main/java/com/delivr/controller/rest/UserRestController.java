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

import com.delivr.model.User;
import com.delivr.service.UserService;

@Controller
@SessionAttributes("user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	private static final String[] dateFormats = { "yyyy-MMM-dd", "yyyy-MM-dd",
			"yyyy/MM/dd", "yyyy/MMM/dd" };

	@RequestMapping(value = "/rest/users", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		List<User> users = null;
		Map<String, List<User>> results = new HashMap<String, List<User>>();

		try {
			users = userService.getAllUsers();
		} catch (Exception e) {
			String sMessage = "Error invoking users";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("users", users);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.DELETE)
	public ModelAndView deleteUser(@PathVariable String userid) {

		try {
			userService.deleteUser(userid);
		} catch (Exception e) {
			String sMessage = "Error invoking users";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD,
				"Sucessfully Deleted ID: " + userid);
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable String userid) {
		User user = new User();
		try {
			user = userService.findByUserId(userid);
		} catch (Exception e) {
			String sMessage = "Error invoking user";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, user);
	}

	@RequestMapping(value = "/rest/users", method = RequestMethod.POST)
	public ModelAndView createUser(@RequestParam("dob") String dob,
			@RequestParam("email") String emailAddress,
			@RequestParam("fname") String firstName,
			@RequestParam("lname") String lastName,
			@RequestParam("pass") String password,
			@RequestParam("uname") String userName) {

		User user = new User();
		try {
			Date dateofBirth = parseDate(dob, dateFormats);

			user.setDateOfBirth(dateofBirth);
			user.setEmailAddress(emailAddress);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setUserName(userName);
			user = userService.createUser(user);
		} catch (Exception e) {
			String sMessage = "Error creating user";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, user);
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.POST)
	public ModelAndView updateUser(@PathVariable String userid,
			@RequestParam("dob") String dob,
			@RequestParam("email") String emailAddress,
			@RequestParam("fname") String firstName,
			@RequestParam("lname") String lastName,
			@RequestParam("pass") String password,
			@RequestParam("uname") String userName) {

		User user = new User();
		try {
			Date dateofBirth = parseDate(dob, dateFormats);
			user = userService.findByUserId(userid);

			user.setDateOfBirth(dateofBirth);
			user.setEmailAddress(emailAddress);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setUserName(userName);
			user = userService.updateUser(user);
		} catch (Exception e) {
			String sMessage = "Error creating user";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, user);
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
