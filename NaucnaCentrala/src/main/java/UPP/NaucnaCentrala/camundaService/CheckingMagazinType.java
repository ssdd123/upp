package UPP.NaucnaCentrala.camundaService;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import UPP.NaucnaCentrala.model.Magazine;
import UPP.NaucnaCentrala.service.MagazineService;

@Service
@Transactional
public class CheckingMagazinType implements JavaDelegate {
	
	@Autowired
	private MagazineService magazineService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		HashMap<String, Object> magazineMap = (HashMap<String, Object>) execution.getVariable("magazineMap");
		
		Magazine magazine = magazineService.getById((Long)magazineMap.get("magazineId"));
		if(magazine != null) {
			if(magazine.isOpenAccess()) {
				execution.setVariable("open_access", true);
				System.out.println("\nOPEN ACCESS.\n");
			} else {
				execution.setVariable("open_access", false);
				System.out.println("\nNIJE OPEN ACCESS.\n");
			}
		}
		
	}

}
