package by.naxa.services;

import by.naxa.dao.RateDAO;
import by.naxa.model.Rate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by phomal on 12.12.2014.
 */
@Component
public @Data class RateService {

	@Autowired
	private RateDAO rateDAO;

	public void addRate(Rate rate) {
		this.getRateDAO().insert(rate);
	}

	public List<Rate> fetchAllRates() {
		return this.getRateDAO().selectAll();
	}
}
