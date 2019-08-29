package UPP.NaucnaCentrala.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Editor extends User {
	
	public Editor() {
		super();
		this.setRole(Role.EDITOR);
	}
	
	@OneToMany(mappedBy = "editor", cascade = CascadeType.REMOVE)
	private List<Magazine> magazines;
	
	@OneToMany(mappedBy = "scientificFieldEditor", cascade = CascadeType.REMOVE)
    private List<Article> articleScientificFieldEditor;

	public List<Magazine> getMagazines() {
		return magazines;
	}

	public void setMagazines(List<Magazine> magazines) {
		this.magazines = magazines;
	}

	public List<Article> getArticleScientificFieldEditor() {
		return articleScientificFieldEditor;
	}

	public void setArticleScientificFieldEditor(List<Article> articleScientificFieldEditor) {
		this.articleScientificFieldEditor = articleScientificFieldEditor;
	}
	
	

}
