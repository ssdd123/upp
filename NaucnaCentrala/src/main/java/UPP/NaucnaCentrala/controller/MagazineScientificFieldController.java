package UPP.NaucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.NaucnaCentrala.converter.MagazineScientificFieldToMagazineScientificFieldDTOConverter;
import UPP.NaucnaCentrala.service.MagazineScientificFieldService;

@RestController
@RequestMapping(value = "/magazineScientificField")
public class MagazineScientificFieldController {
	
	@Autowired
	private MagazineScientificFieldService magazineScientificFieldService;
	
	@Autowired
	private MagazineScientificFieldToMagazineScientificFieldDTOConverter magazineScientificFieldToMagazineScientificFieldDTOConverter;

	@RequestMapping(
			value = "/getByMagazineId/{magazineId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getByMagazineId(@PathVariable Long magazineId) {
		return new ResponseEntity<>(magazineScientificFieldToMagazineScientificFieldDTOConverter
				.convert(magazineScientificFieldService.findByMagazineId(magazineId)), HttpStatus.OK);
	}
}
