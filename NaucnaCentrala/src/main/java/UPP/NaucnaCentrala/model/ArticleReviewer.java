package UPP.NaucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleReviewer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_article")
	private Article article;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_reviewer")
	private Reviewer reviewer;
	
	private String commentForEditor;
	private String commentForAuthor;
	private String reviewChoice;
	
	public ArticleReviewer() {
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

	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	public String getCommentForEditor() {
		return commentForEditor;
	}

	public void setCommentForEditor(String commentForEditor) {
		this.commentForEditor = commentForEditor;
	}

	public String getCommentForAuthor() {
		return commentForAuthor;
	}

	public void setCommentForAuthor(String commentForAuthor) {
		this.commentForAuthor = commentForAuthor;
	}

	public String getReviewChoice() {
		return reviewChoice;
	}

	public void setReviewChoice(String reviewChoice) {
		this.reviewChoice = reviewChoice;
	}
	
}
