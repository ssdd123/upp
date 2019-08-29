package UPP.NaucnaCentrala.model;

import javax.persistence.Entity;

@Entity
public class Registered extends User {

	public Registered() {
		super();
		this.setRole(Role.REGISTERED);
	}
}
