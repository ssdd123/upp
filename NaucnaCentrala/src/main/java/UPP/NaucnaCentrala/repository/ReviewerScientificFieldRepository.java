package UPP.NaucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.NaucnaCentrala.model.ReviewerScientificField;

public interface ReviewerScientificFieldRepository extends JpaRepository<ReviewerScientificField, Long>{

	List<ReviewerScientificField> findByScientificFieldId(Long scientificField);
	
}
