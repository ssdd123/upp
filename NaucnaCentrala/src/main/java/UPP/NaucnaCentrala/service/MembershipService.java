package UPP.NaucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.Membership;
import UPP.NaucnaCentrala.repository.MembershipRepository;

@Service
public class MembershipService {

	@Autowired
	private MembershipRepository membershipRepository;
	
	public Membership save(Membership membership) {
		return membershipRepository.save(membership);
	}
}
