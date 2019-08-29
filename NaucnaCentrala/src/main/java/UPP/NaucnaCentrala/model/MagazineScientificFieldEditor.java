package UPP.NaucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MagazineScientificFieldEditor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_magazine_scientific_field")
	private MagazineScientificField magazineScientificField;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_editor")
	private Editor editor;

	public MagazineScientificFieldEditor() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MagazineScientificField getMagazineScientificField() {
		return magazineScientificField;
	}

	public void setMagazineScientificField(MagazineScientificField magazineScientificField) {
		this.magazineScientificField = magazineScientificField;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	
}
