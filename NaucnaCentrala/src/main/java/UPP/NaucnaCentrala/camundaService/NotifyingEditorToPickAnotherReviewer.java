package UPP.NaucnaCentrala.camundaService;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleReviewer;
import UPP.NaucnaCentrala.model.ArticleStatus;
import UPP.NaucnaCentrala.service.ArticleReviewerService;
import UPP.NaucnaCentrala.service.ArticleService;

@Service
@Transactional
public class NotifyingEditorToPickAnotherReviewer implements JavaDelegate {
	
	@Autowired
	private ArticleReviewerService articleReviewerService;

	@Autowired
	private ArticleService articleService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		HashMap<String, Object> articleeMap = (HashMap<String, Object>) execution.getVariable("newArticleMap");
		Long articleId = (Long)articleeMap.get("newArticleId");
		
		List<ArticleReviewer> ars = articleReviewerService.findByArticleIdAndReviewChoiceIsNull(articleId);
		
		for(ArticleReviewer ar : ars) {
			articleReviewerService.delete(ar);
			System.out.println("removed " + ar.getReviewer().getName() + " " + ar.getArticle().getTitle());
		}
		
		Article article = articleService.findById(articleId);
		article.setArticleStatus(ArticleStatus.FOR_PICKING_REVIWERS);
		articleService.save(article);
	}

}
