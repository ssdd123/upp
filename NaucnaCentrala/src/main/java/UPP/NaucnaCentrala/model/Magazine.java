package UPP.NaucnaCentrala.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Magazine {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String title;

    private String issn;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_editor")
    private Editor editor;
    
    @OneToMany(mappedBy = "magazine", cascade = CascadeType.REMOVE)
    private List<Article> articles;
    
    @OneToMany(mappedBy = "magazine", cascade = CascadeType.REMOVE)
    private List<MagazineScientificField> scientificFields;
    
    private boolean openAccess;
    
    @OneToMany(mappedBy = "magazine", cascade = CascadeType.REMOVE)
    private List<Membership> memberships;

    public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public boolean isOpenAccess() {
		return openAccess;
	}

	public void setOpenAccess(boolean openAccess) {
		this.openAccess = openAccess;
	}

	public Magazine() {
		articles = new ArrayList<Article>();
		scientificFields = new ArrayList<MagazineScientificField>();
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

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<MagazineScientificField> getScientificFields() {
		return scientificFields;
	}

	public void setScientificFields(List<MagazineScientificField> scientificFields) {
		this.scientificFields = scientificFields;
	}

}
