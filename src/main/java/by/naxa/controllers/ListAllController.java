package by.naxa.controllers;

import by.naxa.model.Student;
import by.naxa.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Web controller.
 * Shows all students as list.
 * Created by phomal on 11.12.2014.
 */
@Controller
@Slf4j
public class ListAllController {

	@Autowired
	StudentService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView students() {
		ModelAndView mav = new ModelAndView("list");

		List<Student> allStudents = service.fetchAllStudents();
		mav.addObject("students", allStudents);

		return mav;
	}

}
