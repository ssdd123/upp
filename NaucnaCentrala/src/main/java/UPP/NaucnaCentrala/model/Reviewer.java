package UPP.NaucnaCentrala.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Reviewer extends User {
	
	public Reviewer() {
		super();
		this.setRole(Role.REVIEWER);
	}
	
	@OneToMany(mappedBy = "reviewer", cascade = CascadeType.REMOVE)
    private List<ArticleReviewer> articles;
	
	@OneToMany(mappedBy = "scientificField", cascade = CascadeType.REMOVE)
    private List<ReviewerScientificField> scientificFields;

	public List<ArticleReviewer> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleReviewer> articles) {
		this.articles = articles;
	}

	public List<ReviewerScientificField> getScientificFields() {
		return scientificFields;
	}

	public void setScientificFields(List<ReviewerScientificField> scientificFields) {
		this.scientificFields = scientificFields;
	}

}
