package UPP.NaucnaCentrala.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.NaucnaCentrala.dto.ReviewDTO;
import UPP.NaucnaCentrala.model.ArticleReviewer;

@Component
public class ArticleReviewerToReviewDTOConverter implements Converter<ArticleReviewer, ReviewDTO> {

	@Autowired
	private UserToUserDTOConverter userToUserDTOConverter; 
	
	@Override
	public ReviewDTO convert(ArticleReviewer source) {
		if(source == null) {
			return null;
		}
		ReviewDTO ret = new ReviewDTO();
		ret.setCommentForAuthor(source.getCommentForAuthor());
		ret.setCommentForEditor(source.getCommentForEditor());
		ret.setReviewChoice(source.getReviewChoice());
		ret.setUserDTO(userToUserDTOConverter.convert(source.getReviewer()));
		
		return ret;
	}
	
	public List<ReviewDTO> convert(List<ArticleReviewer> source) {
		if(source == null) {
			return null;
		}
		List<ReviewDTO> ret = new ArrayList<ReviewDTO>();
		for(ArticleReviewer ar : source) {
			ret.add(convert(ar));
		}
		
		return ret;
	}

}
