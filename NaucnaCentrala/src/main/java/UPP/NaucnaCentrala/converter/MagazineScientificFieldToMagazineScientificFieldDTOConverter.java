package UPP.NaucnaCentrala.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.MagazineScientificFieldDTO;
import UPP.NaucnaCentrala.model.MagazineScientificField;

@Component
public class MagazineScientificFieldToMagazineScientificFieldDTOConverter 
			implements Converter<MagazineScientificField, MagazineScientificFieldDTO> {
	
	@Autowired
	private MagazineToMagazineDTOConverter magazineToMagazineDTOConverter;
	
	@Autowired
	private ScientificFieldToScientificFieldDTOConverter scientificFieldToScientificFieldDTOConverter;

	@Override
	public MagazineScientificFieldDTO convert(MagazineScientificField source) {
		if(source == null) {
			return null;
		}
		MagazineScientificFieldDTO ret = new MagazineScientificFieldDTO();
		ret.setId(source.getId());
		ret.setMagazineDTO(magazineToMagazineDTOConverter.convert(source.getMagazine()));
		ret.setScientificFieldDTO(scientificFieldToScientificFieldDTOConverter.convert(source.getScientificField()));
		
		return ret;
	}
	
	public List<MagazineScientificFieldDTO> convert(List<MagazineScientificField> source) {
		if(source == null) {
			return null;
		}
		
		List<MagazineScientificFieldDTO> ret = new ArrayList<MagazineScientificFieldDTO>();
		for(MagazineScientificField msf : source) {
			ret.add(convert(msf));
		}
		
		return ret;
	}

}
