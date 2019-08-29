package UPP.NaucnaCentrala.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticleDTO implements Serializable {
	
	private Long id;
    private String title;
    private String keyWords;
    private String articleAbstract;
    private String articleStatus;
    private UserDTO author;
    private MagazineDTO magazineDTO;
    private ScientificFieldDTO scientificFieldDTO;
    private List<UserDTO> coauthors;
    
    public ArticleDTO() {
    	coauthors = new ArrayList<UserDTO>();
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

	public String getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
	}

	public UserDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserDTO author) {
		this.author = author;
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

	public List<UserDTO> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(List<UserDTO> coauthors) {
		this.coauthors = coauthors;
	}

}
