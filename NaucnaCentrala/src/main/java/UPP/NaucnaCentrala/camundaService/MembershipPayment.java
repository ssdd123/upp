package UPP.NaucnaCentrala.camundaService;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import UPP.NaucnaCentrala.model.Magazine;
import UPP.NaucnaCentrala.model.Membership;
import UPP.NaucnaCentrala.model.User;
import UPP.NaucnaCentrala.service.MagazineService;
import UPP.NaucnaCentrala.service.MembershipService;
import UPP.NaucnaCentrala.service.UserService;

@Service
@Transactional
public class MembershipPayment implements JavaDelegate {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private MembershipService membershipService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		HashMap<String, Object> magazineMap = (HashMap<String, Object>) execution.getVariable("magazineMap");
		User loggedInUser = userService.getCurrentUser();
		
		Magazine magazine = magazineService.getById((Long)magazineMap.get("magazineId"));
		
		Membership newMembership = new Membership();
		newMembership.setMagazine(magazine);
		newMembership.setUser(loggedInUser);
		
		membershipService.save(newMembership);
	}

}
