package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleStatus;
import UPP.NaucnaCentrala.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> findByEditorAndArticleStatus(Long editorId, ArticleStatus articleStatus) {
		return articleRepository.findByMagazineEditorIdAndArticleStatus(editorId, articleStatus);
	}
	
	public List<Article> findByAuthorAndArticleStatus(Long authorId, ArticleStatus articleStatus) {
		return articleRepository.findByAuthorIdAndArticleStatus(authorId, articleStatus);
	}
	
	public List<Article> findByReviewersAndArticleStatus(Long reviewerId, ArticleStatus articleStatus) {
		return articleRepository.findByReviewersReviewerIdAndArticleStatus(reviewerId, articleStatus);
	}
	
	public List<Article> findBySecondReviewerAndArticleStatus(Long reviewerId, ArticleStatus articleStatus) {
		return articleRepository.findBySecondReviewerIdAndArticleStatus(reviewerId, articleStatus);
	}
	
	public List<Article> findByScientificFieldEditorAndArticleStatus(Long editorId, ArticleStatus articleStatus) {
		return articleRepository.findByScientificFieldEditorIdAndArticleStatus(editorId, articleStatus);
	}
	
	public Article save(Article article) {
		return this.articleRepository.save(article);
	}
	
	public Article findById(Long id) {
		return articleRepository.getOne(id);
	}

}
