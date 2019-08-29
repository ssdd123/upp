package UPP.NaucnaCentrala.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.NaucnaCentrala.converter.UserToUserDTOConverter;
import UPP.NaucnaCentrala.dto.ArticleDTO;
import UPP.NaucnaCentrala.dto.ReviewDTO;
import UPP.NaucnaCentrala.dto.UserDTO;
import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleCoauthor;
import UPP.NaucnaCentrala.model.ArticleReviewer;
import UPP.NaucnaCentrala.model.ArticleStatus;
import UPP.NaucnaCentrala.model.NonRegistered;
import UPP.NaucnaCentrala.model.Reviewer;
import UPP.NaucnaCentrala.model.ReviewerScientificField;
import UPP.NaucnaCentrala.model.User;
import UPP.NaucnaCentrala.service.ArticleCoauthorService;
import UPP.NaucnaCentrala.service.ArticleReviewerService;
import UPP.NaucnaCentrala.service.ArticleService;
import UPP.NaucnaCentrala.service.ReviewerScientificFieldService;
import UPP.NaucnaCentrala.service.UserService;


@RestController
@RequestMapping(value = "/article")
public class ArticleController {
	
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	public static ProcessInstance processInstance;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ArticleCoauthorService articleCoauthorService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewerScientificFieldService reviewerScientificFieldService;
	
	@Autowired
	private UserToUserDTOConverter userToUserDTOConverter;
	
	@Autowired
	private ArticleReviewerService articleReviewerService;
	
	@RequestMapping(
			value = "/checkMagazineType/{magazineId}",
			method = RequestMethod.POST
	)
	public void checkMagazineType(@PathVariable Long magazineId) {
		processInstance = runtimeService.startProcessInstanceByKey("SubmittingProcess");
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		
		HashMap<String, Object> magazineMap = new HashMap<String, Object>();
		magazineMap.put("magazineId", magazineId);
		
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "magazineMap", magazineMap);
		formService.submitTaskForm(task.getId(), magazineMap);
	}
	
	@RequestMapping(
			value = "/submitNewArticle",
			method = RequestMethod.POST
	)
	public void submitNewArticle(@RequestBody ArticleDTO articleDTO) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		
		HashMap<String, Object> newArticleMap = new HashMap<String, Object>();
		
		Article article = new Article();
		article.setArticleStatus(ArticleStatus.SUBMITTED_FOR_EDITOR);
		articleService.save(article);
		for(UserDTO c : articleDTO.getCoauthors()) {
			ArticleCoauthor ac = new ArticleCoauthor();
			ac.setArticle(article);
			User registeredCoauthor = userService.findByEmail(c.getEmail());
			if(registeredCoauthor == null) {
				NonRegistered nonRegistered = new NonRegistered();
				nonRegistered.setCity(c.getCity());
				nonRegistered.setCountry(c.getCountry());
				nonRegistered.setEmail(c.getEmail());
				nonRegistered.setName(c.getName());
				nonRegistered = (NonRegistered) userService.save(nonRegistered);
				ac.setCoauthor(nonRegistered);
			} else {
				ac.setCoauthor(registeredCoauthor);
			}
			articleCoauthorService.save(ac);
		}
		
		newArticleMap.put("newArticleTitle", articleDTO.getTitle());
		newArticleMap.put("newArticleKeywords", articleDTO.getKeyWords());
		newArticleMap.put("newArticleAbstract", articleDTO.getArticleAbstract());
		newArticleMap.put("newArticleScientificFieldId", articleDTO.getScientificFieldDTO().getId());
		newArticleMap.put("newArticleId", article.getId());
		
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "newArticleMap", newArticleMap);
		formService.submitTaskForm(task.getId(), newArticleMap);
	}
	
	@RequestMapping(
			value = "/approveForPickingFieldEditor/{articleId}",
			method = RequestMethod.PUT
	)
	public void approveForPickingFieldEditor(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.FOR_PICKING_FIELD_EDITOR);
			articleService.save(article);
			runtimeService.setVariable(task.getProcessInstanceId(), "approveForPickingFieldEditor", true);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsFormatting", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "disapproved", false);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/articleNeedsFormatting/{articleId}",
			method = RequestMethod.PUT
	)
	public void articleNeedsFormatting(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.NEEDS_FORMATTING);
			articleService.save(article);
			runtimeService.setVariable(task.getProcessInstanceId(), "approveForPickingFieldEditor", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsFormatting", true);
			runtimeService.setVariable(task.getProcessInstanceId(), "disapproved", false);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/articleDisaproved/{articleId}",
			method = RequestMethod.PUT
	)
	public void articleDisaproved(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.DISAPPROVED);
			articleService.save(article);
			runtimeService.setVariable(task.getProcessInstanceId(), "approveForPickingFieldEditor", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsFormatting", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsCorrection", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsReviewing", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "disapproved", true);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/addReviewer/{articleId}/{reviewerId}",
			method = RequestMethod.PUT
	)
	public void addReviewer(@PathVariable Long articleId, @PathVariable Long reviewerId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		Reviewer reviewer = (Reviewer) userService.findById(reviewerId);
		if(article != null && reviewer != null) {
			ArticleReviewer ar = new ArticleReviewer();
			ar.setArticle(article);
			ar.setReviewer(reviewer);
			articleReviewerService.save(ar);
		}
		List<ArticleReviewer> ars = articleReviewerService.getByArticle(articleId);
		if(ars.size() < 2) {
			runtimeService.setVariable(task.getProcessInstanceId(), "hasEnoughReviewers", false);
		} else {
			runtimeService.setVariable(task.getProcessInstanceId(), "hasEnoughReviewers", true);
			runtimeService.setVariable(task.getProcessInstanceId(), "numberOfReviewers", ars.size());
			article.setArticleStatus(ArticleStatus.FOR_FIRST_REVIEW);
			articleService.save(article);
		}
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/addReviewFirst/{articleId}",
			method = RequestMethod.POST
	)
	public void addReviewFirst(@PathVariable Long articleId, @RequestBody ReviewDTO reviewDTO) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		User loggedIn = userService.getCurrentUser();
		ArticleReviewer ar = articleReviewerService.findByArticleIdAndReviewerId(articleId, loggedIn.getId());
		ar.setCommentForAuthor(reviewDTO.getCommentForAuthor());
		ar.setCommentForEditor(reviewDTO.getCommentForEditor());
		ar.setReviewChoice(reviewDTO.getReviewChoice());
		articleReviewerService.save(ar);
		
		List<ArticleReviewer> ars = articleReviewerService.findByArticleIdAndReviewChoiceIsNotNull(articleId);
		if(ars.size() >= 2) {
			Article article = articleService.findById(articleId);
			article.setArticleStatus(ArticleStatus.REVIEWED_FOR_EDITOR_FIRST);
			articleService.save(article);
		}
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/articleAproved/{articleId}",
			method = RequestMethod.PUT
	)
	public void articleAproved(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.APPROVED);
			articleService.save(article);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsCorrection", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "disapproved", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsReviewing", false);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsFormatting", false);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/articleNeedsCorrection/{articleId}",
			method = RequestMethod.PUT
	)
	public void articleNeedsCorrection(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.NEEDS_CORRECTION);
			articleService.save(article);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsCorrection", true);
			runtimeService.setVariable(task.getProcessInstanceId(), "disapproved", false);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/corrected/{articleId}",
			method = RequestMethod.PUT
	)
	public void corrected(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.CORRECTED_FOR_EDITOR);
			articleService.save(article);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/format/{articleId}",
			method = RequestMethod.PUT
	)
	public void format(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.SUBMITTED_FOR_EDITOR);
			articleService.save(article);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	
	@RequestMapping(
			value = "/sendCorrectedToReviewer/{articleId}",
			method = RequestMethod.PUT
	)
	public void sendCorrectedToReviewer(@PathVariable Long articleId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Article article = articleService.findById(articleId);
		if(article != null) {
			article.setArticleStatus(ArticleStatus.FOR_PICKING_FIELD_EDITOR);
			articleService.save(article);
			runtimeService.setVariable(task.getProcessInstanceId(), "needsReviewing", true);
			runtimeService.setVariable(task.getProcessInstanceId(), "disapproved", false);
		}
		
		formService.submitTaskForm(task.getId(), null);
	}
	

	
	@RequestMapping(
			value = "/getReviewersForScientificField/{scientificFieldId}/{articleId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getReviewersForScientificField(@PathVariable Long scientificFieldId, @PathVariable Long articleId) {
		List<User> reviewers = new ArrayList<User>();
		List<ReviewerScientificField> rev = reviewerScientificFieldService.getByScientificField(scientificFieldId);
		for(ReviewerScientificField rsf : rev) {
			reviewers.add(rsf.getReviewer());
		}
		List<ArticleReviewer> articleReviewers = articleReviewerService.getByArticle(articleId);
		for(ArticleReviewer ar : articleReviewers) {
			for(int i = reviewers.size() - 1; i >= 0; i--) {
				if(reviewers.get(i).getId().equals(ar.getReviewer().getId())) {
					reviewers.remove(i);
				}
			}
		}
		
        return new ResponseEntity<>(userToUserDTOConverter.convert(reviewers), HttpStatus.OK);
	}
	

}
