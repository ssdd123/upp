package UPP.NaucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.NaucnaCentrala.model.MagazineScientificField;

public interface MagazineScientificFieldRepository extends JpaRepository<MagazineScientificField, Long> {

	List<MagazineScientificField> findByMagazineId(Long magazineId);
}
