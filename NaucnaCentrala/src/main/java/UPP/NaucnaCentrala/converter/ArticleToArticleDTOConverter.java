package UPP.NaucnaCentrala.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.ArticleDTO;
import UPP.NaucnaCentrala.dto.UserDTO;
import UPP.NaucnaCentrala.model.Article;
import UPP.NaucnaCentrala.model.ArticleCoauthor;
import UPP.NaucnaCentrala.model.ArticleStatus;

@Component
public class ArticleToArticleDTOConverter implements Converter<Article, ArticleDTO> {
	
	@Autowired
	private UserToUserDTOConverter userToUserDTOConverter;
	
	@Autowired
	private MagazineToMagazineDTOConverter magazineToMagazineDTOConverter;
	
	@Autowired
	private ScientificFieldToScientificFieldDTOConverter scientificFieldToScientificFieldDTOConverter;

	@Override
	public ArticleDTO convert(Article source) {
		if(source == null) {
			return null;
		}
		ArticleDTO ret = new ArticleDTO();
		ret.setId(source.getId());
		ret.setArticleAbstract(source.getArticleAbstract());
		ret.setKeyWords(source.getKeyWords());
		ret.setAuthor(userToUserDTOConverter.convert(source.getAuthor()));
		ret.setMagazineDTO(magazineToMagazineDTOConverter.convert(source.getMagazine()));
		ret.setTitle(source.getTitle());
		ret.setScientificFieldDTO(scientificFieldToScientificFieldDTOConverter.convert(source.getScientificField()));
		ret.setArticleStatus(articleStatusConvrter(source.getArticleStatus()));
		
		List<UserDTO> coauthors = new ArrayList<UserDTO>();
		for(ArticleCoauthor ac : source.getCoauthors()) {
			UserDTO coauthor = userToUserDTOConverter.convert(ac.getCoauthor());
			coauthors.add(coauthor);
		}
		ret.setCoauthors(coauthors);
		
		return ret;
	}
	
	public List<ArticleDTO> convert(List<Article> source) {
		if(source == null) {
			return null;
		}
		ArrayList<ArticleDTO> ret = new ArrayList<ArticleDTO>();
		for(Article article : source) {
			ret.add(convert(article));
		}
		return ret;
	}

	private String articleStatusConvrter(ArticleStatus articleStatus) {
		if(articleStatus.equals(ArticleStatus.SUBMITTED_FOR_EDITOR)) {
			return "submittedForEditor";
		} else if(articleStatus.equals(ArticleStatus.NEEDS_FORMATTING)) {
			return "needsFormatting";
		} else if(articleStatus.equals(ArticleStatus.FOR_FIRST_REVIEW)) {
			return "forFirstReview";
		} else if(articleStatus.equals(ArticleStatus.FOR_SECOND_REVIEW)) {
			return "forSecondReview";
		} else if(articleStatus.equals(ArticleStatus.REVIEWED_FOR_EDITOR_FIRST)) {
			return "reviewedForEditorFirst";
		} else if(articleStatus.equals(ArticleStatus.CORRECTED_FOR_EDITOR)) {
			return "correctedForEditor";
		} else if(articleStatus.equals(ArticleStatus.NEEDS_CORRECTION)) {
			return "needsCorrection";
		} else if(articleStatus.equals(ArticleStatus.APPROVED)) {
			return "approved";
		} else if(articleStatus.equals(ArticleStatus.DISAPPROVED)) {
			return "unapproved";
		} else if(articleStatus.equals(ArticleStatus.FOR_PICKING_FIELD_EDITOR)) {
			return "forPickingFieldEditor";
		} else {
			return "undefined";
		}
	}

}
