package by.naxa;

import by.naxa.dao.GenericDAO;
import by.naxa.model.Student;
import lombok.extern.slf4j.Slf4j;
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView students() {
		ModelAndView mav = new ModelAndView("list");

		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		List<Student> allStudents = studentDAO.findAll();
		mav.addObject("students", allStudents);

		return mav;
	}

}