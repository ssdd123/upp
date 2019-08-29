package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.ReviewerScientificField;
import UPP.NaucnaCentrala.repository.ReviewerScientificFieldRepository;

@Service
public class ReviewerScientificFieldService {
	
	@Autowired
	private ReviewerScientificFieldRepository reviewerScientificFieldRepository;
	
	public List<ReviewerScientificField> getByScientificField(Long scientificFieldId) {
		return this.reviewerScientificFieldRepository.findByScientificFieldId(scientificFieldId);
	}

}
