package by.naxa.dao;

import by.naxa.model.Rate;
import by.naxa.model.Student;

import java.io.Serializable;

/**
 * TODO: Use Spring JPA Repositories for this abstraction.
 * Rate data access object interface.
 * Created by phomal on 16.12.2014.
 */
public interface RateDAO {

	void save(Rate entity);

	Rate findOne(Serializable pk);

	Iterable<Rate> findRatesOf(Student student);

	Long count();

	void delete(Rate entity);

	boolean exists(Serializable pk);

	void deleteRatesOf(Student student);
}
