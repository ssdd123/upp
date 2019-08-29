package UPP.NaucnaCentrala.camundaService;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleStatus;
import UPP.NaucnaCentrala.model.MagazineScientificFieldEditor;
import UPP.NaucnaCentrala.service.ArticleService;
import UPP.NaucnaCentrala.service.MagazineScientificFieldEditorService;

@Service
@Transactional
public class PickingFieldEditors implements JavaDelegate {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MagazineScientificFieldEditorService magazineScientificFieldEditorService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("\n\npicking field editors\n\n");
		
		HashMap<String, Object> articleeMap = (HashMap<String, Object>) execution.getVariable("newArticleMap");
		
		Article article= articleService.findById((Long)articleeMap.get("newArticleId"));
		
		List<MagazineScientificFieldEditor> editors = magazineScientificFieldEditorService
				.findByMagazineAndScintificField(article.getMagazine().getId(), article.getScientificField().getId());
		
		if(editors.size() == 0) {
			article.setScientificFieldEditor(article.getMagazine().getEditor());
		} else {
			int rnd = new Random().nextInt(editors.size());
			article.setScientificFieldEditor(editors.get(rnd).getEditor());
		}
		article.setArticleStatus(ArticleStatus.FOR_PICKING_REVIWERS);
		articleService.save(article);
	}
}
