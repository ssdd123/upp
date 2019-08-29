package UPP.NaucnaCentrala.model;

import javax.persistence.Entity;

@Entity
public class NonRegistered extends User {

	public NonRegistered() {
		super();
		this.setRole(Role.NON_REGISTERED);
	}
	
}
