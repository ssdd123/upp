package UPP.NaucnaCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.NaucnaCentrala.converter.ArticleReviewerToReviewDTOConverter;
import UPP.NaucnaCentrala.converter.ArticleToArticleDTOConverter;
import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleReviewer;
import UPP.NaucnaCentrala.model.ArticleStatus;
import UPP.NaucnaCentrala.model.Role;
import UPP.NaucnaCentrala.model.User;
import UPP.NaucnaCentrala.service.ArticleReviewerService;
import UPP.NaucnaCentrala.service.ArticleService;
import UPP.NaucnaCentrala.service.UserService;

@RestController
@RequestMapping(value = "/articleData")
public class ArticleDataController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleToArticleDTOConverter articleToArticleDTOConverter;
	
	@Autowired
	private ArticleReviewerService articleReviewerService;
	
	@Autowired
	private ArticleReviewerToReviewDTOConverter articleReviewerToReviewDTOConverter;
	
	/**
	 * FOR EDITOR
	 * Returns articles which are submitted and need review by editor
	 * 
	 */
	@RequestMapping(
			value = "/submittedForEditor",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> submittedForEditor() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.EDITOR)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByEditorAndArticleStatus(loggedIn.getId(), ArticleStatus.SUBMITTED_FOR_EDITOR);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	/**
	 * FOR EDITOR
	 * Returns articles for picking reviewers
	 * 
	 */
	@RequestMapping(
			value = "/forPickingReviewers",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> forPickingReviewers() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.EDITOR)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByScientificFieldEditorAndArticleStatus(loggedIn.getId(), ArticleStatus.FOR_PICKING_REVIWERS);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	
	/**
	 * FOR REGISTERED
	 * Returns articles which are reviewed by editor and need reformatting
	 * 
	 */
	@RequestMapping(
			value = "/needsFormatting",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> needsFormatting() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.REGISTERED)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByAuthorAndArticleStatus(loggedIn.getId(), ArticleStatus.NEEDS_FORMATTING);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	
	/**
	 * FOR REVIEWER
	 * Returns articles which need reviewing by reviewer(first)
	 * 
	 */
	@RequestMapping(
			value = "/forFirstReview",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> forFirstReview() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.REVIEWER)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByReviewersAndArticleStatus(loggedIn.getId(), ArticleStatus.FOR_FIRST_REVIEW);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	
	
	
	/**
	 * FOR EDITOR
	 * Returns articles which are reviewed by reviewer(first)
	 * 
	 */
	@RequestMapping(
			value = "/reviewedForEditorFirst",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> reviewedByEditorFirst() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.EDITOR)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByEditorAndArticleStatus(loggedIn.getId(), ArticleStatus.REVIEWED_FOR_EDITOR_FIRST);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	
	/**
	 * FOR EDITOR
	 * Returns articles which are corrected
	 * 
	 */
	@RequestMapping(
			value = "/correctedForEditor",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> correctedForEditor() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.EDITOR)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByEditorAndArticleStatus(loggedIn.getId(), ArticleStatus.CORRECTED_FOR_EDITOR);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	
	/**
	 * FOR REGISTERED
	 * Returns articles which are reviewed by editor and reviewer and need correction
	 * 
	 */
	@RequestMapping(
			value = "/needsCorrection",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> needCorrection() {
		User loggedIn = userService.getCurrentUser();
		if(loggedIn == null || !loggedIn.getRole().equals(Role.REGISTERED)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Article> articles = articleService.findByAuthorAndArticleStatus(loggedIn.getId(), ArticleStatus.NEEDS_CORRECTION);
		
        return new ResponseEntity<>(articleToArticleDTOConverter.convert(articles), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getArticleReviews/{articleId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getArticleReviews(@PathVariable Long articleId) {
		List<ArticleReviewer> ars = articleReviewerService.getByArticle(articleId);
		
        return new ResponseEntity<>(articleReviewerToReviewDTOConverter.convert(ars), HttpStatus.OK);
	}

}
