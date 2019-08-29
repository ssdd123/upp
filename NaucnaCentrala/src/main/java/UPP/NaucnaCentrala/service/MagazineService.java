package UPP.NaucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.Magazine;
import UPP.NaucnaCentrala.repository.MagazinRepository;

@Service
public class MagazineService {
	
	@Autowired 
	private MagazinRepository magazinRepository;
	
	public Magazine getById(Long id) {
		return magazinRepository.getOne(id);
	}
	
	public List<Magazine> getAll() {
		return magazinRepository.findAll();
	}

}
