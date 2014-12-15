package by.naxa.controllers;

import by.naxa.model.Student;
import by.naxa.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Deletion controller.
 * Created by phomal on 11.12.2014.
 */
@Controller
@Slf4j
public class DeleteController {

	@Autowired
	StudentService service;

	@RequestMapping(value = "/del/{id:.+}", method = RequestMethod.POST)
	public ModelAndView deleteStudent(@PathVariable("id") String sid) {
		Long id = Long.parseLong(sid);

		// "Kill" the student
		Student sacrifice = service.findStudentById(id);
		service.deleteStudent(sacrifice);

		// OK. Back to the main page silently
		return new ModelAndView("redirect:/list");
	}

}
