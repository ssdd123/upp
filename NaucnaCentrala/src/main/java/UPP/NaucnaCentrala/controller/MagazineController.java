package UPP.NaucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.NaucnaCentrala.converter.MagazineToMagazineDTOConverter;
import UPP.NaucnaCentrala.service.MagazineService;

@RestController
@RequestMapping(value = "/magazine")
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private MagazineToMagazineDTOConverter magazineToMagazineDTOConverter;
	
	@RequestMapping(
			value = "/getAll",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(magazineToMagazineDTOConverter.convert(magazineService.getAll()), HttpStatus.OK);
	}

}
