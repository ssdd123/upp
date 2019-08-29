package UPP.NaucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleCoauthor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_article")
	private Article article;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_coauthor")
	private User coauthor;
	
	public ArticleCoauthor() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getCoauthor() {
		return coauthor;
	}

	public void setCoauthor(User coauthor) {
		this.coauthor = coauthor;
	}

}
