package UPP.NaucnaCentrala.camundaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.dto.ArticleDTO;
import UPP.NaucnaCentrala.dto.UserDTO;
import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleCoauthor;
import UPP.NaucnaCentrala.model.ArticleStatus;
import UPP.NaucnaCentrala.model.Magazine;
import UPP.NaucnaCentrala.model.NonRegistered;
import UPP.NaucnaCentrala.model.Registered;
import UPP.NaucnaCentrala.model.User;
import UPP.NaucnaCentrala.service.ArticleCoauthorService;
import UPP.NaucnaCentrala.service.ArticleService;
import UPP.NaucnaCentrala.service.MagazineService;
import UPP.NaucnaCentrala.service.ScientificFieldService;
import UPP.NaucnaCentrala.service.UserService;

@Service
@Transactional
public class SavingSubmittedArticle implements JavaDelegate {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private ScientificFieldService scientificFieldService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		HashMap<String, Object> articleMap = (HashMap<String, Object>) execution.getVariable("newArticleMap");
		HashMap<String, Object> magazineMap = (HashMap<String, Object>) execution.getVariable("magazineMap");
		
		Magazine magazine = magazineService.getById((Long)magazineMap.get("magazineId"));
		
		Article article = articleService.findById((Long) articleMap.get("newArticleId"));
		if(article != null) {
			article.setArticleAbstract((String) articleMap.get("newArticleAbstract"));
			article.setAuthor((Registered)userService.getCurrentUser());
			article.setKeyWords((String) articleMap.get("newArticleKeywords"));
			article.setScientificField(scientificFieldService.findById((Long) articleMap.get("newArticleScientificFieldId")));
			article.setTitle((String) articleMap.get("newArticleTitle"));
			article.setMagazine(magazine);
			
			articleService.save(article);
		}
	}

}
