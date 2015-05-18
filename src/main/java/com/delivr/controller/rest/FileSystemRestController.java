package com.delivr.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.delivr.model.FileSystem;
import com.delivr.service.FileSystemService;

@Controller
@SessionAttributes("filesystem")
public class FileSystemRestController {

	@Autowired
	private FileSystemService fileSystemService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";


	@RequestMapping(value = "/rest/files", method = RequestMethod.GET)
	public ModelAndView getAllFiles() {
		List<FileSystem> files = null;
		Map<String, List<FileSystem>> results = new HashMap<String, List<FileSystem>>();

		try {
			files = fileSystemService.getAllFiles();
		} catch (Exception e) {
			String sMessage = "Error invoking files";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("files", files);

		return new ModelAndView(jsonView, DATA_FIELD, results);
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
