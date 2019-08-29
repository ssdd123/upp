package UPP.NaucnaCentrala.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.UserDTO;
import UPP.NaucnaCentrala.model.Role;
import UPP.NaucnaCentrala.model.User;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO>{

	@Override
	public UserDTO convert(User source) {
		if(source == null) {
			return null;
		}
		UserDTO ret = new UserDTO();
		ret.setId(source.getId());
		ret.setCity(source.getCity());
		ret.setCountry(source.getCountry());
		ret.setName(source.getName());
		ret.setEmail(source.getEmail());
		ret.setRole(roleConverter(source.getRole()));
		
		return ret;
	}
	
	public List<UserDTO> convert(List<User> source) {
		if(source == null) {
			return null;
		}
		List<UserDTO> ret = new ArrayList<UserDTO>();
		for(User u : source) {
			ret.add(convert(u));
		}
		return ret;
	}

	private String roleConverter(Role role) {
		if(role.equals(Role.EDITOR)) {
			return "editor";
		} else if(role.equals(Role.REGISTERED)) {
			return "registered";
		} else if(role.equals(Role.REVIEWER)) {
			return "reviewer";
		} else if(role.equals(Role.NON_REGISTERED)) {
			return "non_registered";
		} else {
			return "undefined";
		}
	}

}
