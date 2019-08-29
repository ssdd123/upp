package UPP.NaucnaCentrala.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.UserDTO;
import UPP.NaucnaCentrala.model.Registered;
import UPP.NaucnaCentrala.model.User;

@Component
public class UserDTOToRegisteredConverter implements Converter<UserDTO, Registered> {

	@Override
	public Registered convert(UserDTO source) {
		if(source == null) {
			return null;
		}
		Registered user = new Registered();
		user.setName(source.getName());
		user.setCity(source.getCity());
		user.setCountry(source.getCountry());
		user.setEmail(source.getEmail());
		user.setPassword(source.getPassword());
		return user;
	}

}
