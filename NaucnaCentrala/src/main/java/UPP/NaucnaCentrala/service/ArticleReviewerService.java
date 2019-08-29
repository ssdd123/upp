package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.ArticleReviewer;
import UPP.NaucnaCentrala.repository.ArticleReviewerRepository;

@Service
public class ArticleReviewerService {

	@Autowired
	private ArticleReviewerRepository articleReviewerRepository;
	
	public List<ArticleReviewer> getByArticle(Long articleId) {
		return this.articleReviewerRepository.findByArticleId(articleId);
	}

	public void save(ArticleReviewer ar) {
		articleReviewerRepository.save(ar);
	}
	
	public ArticleReviewer findByArticleIdAndReviewerId(Long articleId, Long reviewerId) {
		return this.articleReviewerRepository.findByArticleIdAndReviewerId(articleId, reviewerId);
	}
	
	public List<ArticleReviewer> findByArticleIdAndReviewChoiceIsNull(Long articleId) {
		return this.articleReviewerRepository.findByArticleIdAndReviewChoiceIsNull(articleId);
	}
	
	public List<ArticleReviewer> findByArticleIdAndReviewChoiceIsNotNull(Long articleId) {
		return this.articleReviewerRepository.findByArticleIdAndReviewChoiceIsNotNull(articleId);
	}
	
	public void delete(ArticleReviewer ar) {
		articleReviewerRepository.delete(ar);
	}
	
}
