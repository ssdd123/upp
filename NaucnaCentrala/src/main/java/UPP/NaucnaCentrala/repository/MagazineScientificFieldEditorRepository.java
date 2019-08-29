package UPP.NaucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.NaucnaCentrala.model.MagazineScientificFieldEditor;

public interface MagazineScientificFieldEditorRepository extends JpaRepository<MagazineScientificFieldEditor, Long>{
	
	List<MagazineScientificFieldEditor> findByMagazineScientificFieldMagazineIdAndMagazineScientificFieldScientificFieldId(Long magazineId, Long scientificFieldId);

}
