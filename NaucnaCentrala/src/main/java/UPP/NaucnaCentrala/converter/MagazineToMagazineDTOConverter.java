package UPP.NaucnaCentrala.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.MagazineDTO;
import UPP.NaucnaCentrala.model.Magazine;
import UPP.NaucnaCentrala.model.MagazineScientificField;
import UPP.NaucnaCentrala.model.ScientificField;

@Component
public class MagazineToMagazineDTOConverter implements Converter<Magazine, MagazineDTO> {
	
	@Autowired
	private UserToUserDTOConverter userToUserDTOConverter;
	
	@Autowired
	private ScientificFieldToScientificFieldDTOConverter scientificFieldToScientificFieldDTOConverter;

	@Override
	public MagazineDTO convert(Magazine source) {
		if(source == null) {
			return null;
		}
		ArrayList<ScientificField> scientificFields = new ArrayList<ScientificField>();
		for(MagazineScientificField msf : source.getScientificFields()) {
			scientificFields.add(msf.getScientificField());
		}
		
		MagazineDTO ret = new MagazineDTO();
		ret.setId(source.getId());
		ret.setIssn(source.getIssn());
		ret.setTitle(source.getTitle());
		ret.setEditor(userToUserDTOConverter.convert(source.getEditor()));
		ret.setScientificFieldDTOs(scientificFieldToScientificFieldDTOConverter.convert(scientificFields));
		
		return ret;
	}
	
	public List<MagazineDTO> convert(List<Magazine> source) {
		if(source == null) {
			return null;
		}
		ArrayList<MagazineDTO> ret = new ArrayList<MagazineDTO>();
		for(Magazine magazine : source) {
			ret.add(convert(magazine));
		}
		
		return ret;
	}

}
