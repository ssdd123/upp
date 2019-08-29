package UPP.NaucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.ScientificField;
import UPP.NaucnaCentrala.repository.ScientificFieldRepository;

@Service
public class ScientificFieldService {
	
	@Autowired
	private ScientificFieldRepository scientificFieldRepository;
	
	public ScientificField findById(Long id) {
		return this.scientificFieldRepository.getOne(id);
	}

}
