package UPP.NaucnaCentrala.camundaService;

import java.util.HashMap;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import UPP.NaucnaCentrala.model.Registered;
import UPP.NaucnaCentrala.service.UserService;

@Service
@Transactional
public class RegistrationSavingUser implements JavaDelegate {
	
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		HashMap<String, Object> podaci = (HashMap<String, Object>) execution.getVariable("userMap");
		
		Registered registered = new Registered();
		registered.setEmail(podaci.get("email").toString());
		registered.setName(podaci.get("name").toString());
		registered.setPassword(podaci.get("password").toString());
		registered.setCity(podaci.get("city").toString());
		registered.setCountry(podaci.get("country").toString());
		
		User camundaUser = identityService.newUser(registered.getEmail());
		camundaUser.setEmail(registered.getEmail());
		camundaUser.setPassword(registered.getPassword());
		camundaUser.setFirstName(registered.getName());
		camundaUser.setLastName(registered.getName());
		
		if(userService.save(registered) != null) 
			identityService.saveUser(camundaUser);
	}

}
