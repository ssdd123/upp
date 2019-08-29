package UPP.NaucnaCentrala.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.ScientificFieldDTO;
import UPP.NaucnaCentrala.model.ScientificField;

@Component
public class ScientificFieldToScientificFieldDTOConverter implements Converter<ScientificField, ScientificFieldDTO> {

	@Override
	public ScientificFieldDTO convert(ScientificField source) {
		if(source == null) {
			return null;
		}
		ScientificFieldDTO ret = new ScientificFieldDTO();
		ret.setId(source.getId());
		ret.setName(source.getName());
		
		return ret;
	}
	
	public List<ScientificFieldDTO> convert(List<ScientificField> source) {
		if(source == null) {
			return null;
		}
		ArrayList<ScientificFieldDTO> ret = new ArrayList<ScientificFieldDTO>();
		for(ScientificField scientificField : source) {
			ret.add(convert(scientificField));
		}
		
		return ret;
	}

}
