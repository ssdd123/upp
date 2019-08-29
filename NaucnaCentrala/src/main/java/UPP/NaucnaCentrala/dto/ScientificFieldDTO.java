package UPP.NaucnaCentrala.dto;

import java.io.Serializable;

public class ScientificFieldDTO implements Serializable {
	
	private Long id;
    private String name;
    
    public ScientificFieldDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
