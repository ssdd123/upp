package UPP.NaucnaCentrala.camundaService;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Disapproved implements JavaDelegate {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("\n\ndisapproved\n\n");
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("uppupp867@gmail.com");
		mail.setFrom("upp");
		mail.setSubject("Dispproved");			
		
		String poruka = "Article disapproved.";			
		
		mail.setText(poruka);
		javaMailSender.send(mail);
		
	}

}
