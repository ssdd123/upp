package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.MagazineScientificFieldEditor;
import UPP.NaucnaCentrala.repository.MagazineScientificFieldEditorRepository;

@Service
public class MagazineScientificFieldEditorService {

	@Autowired
	private MagazineScientificFieldEditorRepository magazineScientificFieldEditorRepository;
	
	public List<MagazineScientificFieldEditor> findByMagazineAndScintificField(Long magazineId, Long scientificFieldId) {
		return this.magazineScientificFieldEditorRepository.findByMagazineScientificFieldMagazineIdAndMagazineScientificFieldScientificFieldId(magazineId, scientificFieldId);
	}
	
}
