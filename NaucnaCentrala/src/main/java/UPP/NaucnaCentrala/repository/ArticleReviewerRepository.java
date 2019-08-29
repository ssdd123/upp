package UPP.NaucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.NaucnaCentrala.model.ArticleReviewer;

public interface ArticleReviewerRepository extends JpaRepository<ArticleReviewer, Long>{
	
	List<ArticleReviewer> findByArticleId(Long articleId);
	ArticleReviewer findByArticleIdAndReviewerId(Long articleId, Long reviewerId);
	List<ArticleReviewer> findByArticleIdAndReviewChoiceIsNull(Long articleId);
	List<ArticleReviewer> findByArticleIdAndReviewChoiceIsNotNull(Long articleId);

}
