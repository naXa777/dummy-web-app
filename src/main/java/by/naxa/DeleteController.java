package by.naxa;

import by.naxa.dao.GenericDAO;
import by.naxa.model.Student;
import lombok.extern.slf4j.Slf4j;
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

	@RequestMapping(value = "/del/{id:.+}", method = RequestMethod.POST)
	public ModelAndView deleteStudent(@PathVariable("id") String sid) {
		Long id = Long.parseLong(sid);

		// "Kill" the student
		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		Student sacrifice = studentDAO.find(id);
		studentDAO.delete(sacrifice);

		// OK. Back to the main page silently
		return new ModelAndView("redirect:/list");
	}

}
