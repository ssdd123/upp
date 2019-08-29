package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.MagazineScientificField;
import UPP.NaucnaCentrala.repository.MagazineScientificFieldRepository;

@Service
public class MagazineScientificFieldService {

	@Autowired
	private MagazineScientificFieldRepository magazineScientificFieldRepository;
	
	public List<MagazineScientificField> findByMagazineId(Long magazineId) {
		return this.magazineScientificFieldRepository.findByMagazineId(magazineId);
	}
	
}
