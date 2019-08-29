package UPP.NaucnaCentrala.camundaService;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import UPP.NaucnaCentrala.service.UserService;

@Service
@Transactional
public class RegistrationCheckingUser implements JavaDelegate {
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		HashMap<String, Object> userMap = (HashMap<String, Object>) execution.getVariable("userMap");
		
		System.out.println("\nChecking user " + userMap.get("email").toString() + "...\n");
		
		if(userService.findByEmailAndPassword(userMap.get("email").toString(), userMap.get("password").toString()) != null) {
			System.out.println("\nUser " + userMap.get("email").toString() + " is invalid\n");
			execution.setVariable("userIsValid", false);
		} else {
			System.out.println("\nUser " + userMap.get("email").toString() + " is valid\n");
			execution.setVariable("userIsValid", true);
		}
	}

}
