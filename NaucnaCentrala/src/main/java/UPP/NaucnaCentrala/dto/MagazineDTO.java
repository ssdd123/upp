package UPP.NaucnaCentrala.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MagazineDTO implements Serializable {
	
	private Long id;
    private String title;
    private String issn;
    private UserDTO editor;
    private List<ScientificFieldDTO> scientificFieldDTOs;
    
    public MagazineDTO() {
		scientificFieldDTOs = new ArrayList<ScientificFieldDTO>();
	}

	public List<ScientificFieldDTO> getScientificFieldDTOs() {
		return scientificFieldDTOs;
	}

	public void setScientificFieldDTOs(List<ScientificFieldDTO> scientificFieldDTOs) {
		this.scientificFieldDTOs = scientificFieldDTOs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public UserDTO getEditor() {
		return editor;
	}

	public void setEditor(UserDTO editor) {
		this.editor = editor;
	}
    
}
