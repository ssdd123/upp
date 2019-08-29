package UPP.NaucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleStatus;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	List<Article> findByMagazineEditorIdAndArticleStatus(Long editorId, ArticleStatus articleStatus);
	List<Article> findByAuthorIdAndArticleStatus(Long authorId, ArticleStatus articleStatus);
	List<Article> findByReviewersReviewerIdAndArticleStatus(Long reviewerId, ArticleStatus articleStatus);
	List<Article> findBySecondReviewerIdAndArticleStatus(Long reviewerId, ArticleStatus articleStatus);
	List<Article> findByScientificFieldEditorIdAndArticleStatus(Long editorId, ArticleStatus articleStatus);

}
