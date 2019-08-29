package UPP.NaucnaCentrala.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Article {
		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    private String title;
    private String keyWords;
    private String articleAbstract;
    
    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_author")
    private Registered author;
    
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<ArticleCoauthor> coauthors;
    
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<ArticleReviewer> reviewers;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_second_reviewer")
    private Reviewer secondReviewer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scientific_field")
    private ScientificField scientificField;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scientific_field_editor")
    private Editor scientificFieldEditor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_magazine")
    private Magazine magazine;
    
    private String content;
    
    public Article() {
		this.reviewers = new ArrayList<ArticleReviewer>();
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

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public ArticleStatus getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(ArticleStatus articleStatus) {
		this.articleStatus = articleStatus;
	}

	public Registered getAuthor() {
		return author;
	}

	public void setAuthor(Registered author) {
		this.author = author;
	}

	public ScientificField getScientificField() {
		return scientificField;
	}

	public void setScientificField(ScientificField scientificField) {
		this.scientificField = scientificField;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public List<ArticleCoauthor> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(List<ArticleCoauthor> coauthors) {
		this.coauthors = coauthors;
	}

	public List<ArticleReviewer> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<ArticleReviewer> reviewers) {
		this.reviewers = reviewers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Reviewer getSecondReviewer() {
		return secondReviewer;
	}

	public void setSecondReviewer(Reviewer secondReviewer) {
		this.secondReviewer = secondReviewer;
	}

	public Editor getScientificFieldEditor() {
		return scientificFieldEditor;
	}

	public void setScientificFieldEditor(Editor scientificFieldEditor) {
		this.scientificFieldEditor = scientificFieldEditor;
	}

}
