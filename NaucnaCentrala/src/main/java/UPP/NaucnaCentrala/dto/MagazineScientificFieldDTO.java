package UPP.NaucnaCentrala.dto;

public class MagazineScientificFieldDTO {
	
	private Long id;
	private MagazineDTO magazineDTO;
	private ScientificFieldDTO scientificFieldDTO;
	
	public MagazineScientificFieldDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MagazineDTO getMagazineDTO() {
		return magazineDTO;
	}

	public void setMagazineDTO(MagazineDTO magazineDTO) {
		this.magazineDTO = magazineDTO;
	}

	public ScientificFieldDTO getScientificFieldDTO() {
		return scientificFieldDTO;
	}

	public void setScientificFieldDTO(ScientificFieldDTO scientificFieldDTO) {
		this.scientificFieldDTO = scientificFieldDTO;
	}

}
