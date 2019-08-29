package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.ArticleCoauthor;
import UPP.NaucnaCentrala.repository.ArticleCoauthorRepository;

@Service
public class ArticleCoauthorService {
	
	@Autowired
	private ArticleCoauthorRepository articleCoauthorRepository;
	
	public ArticleCoauthor save(ArticleCoauthor ac) {
		return this.articleCoauthorRepository.save(ac);
	}

}
