package UPP.NaucnaCentrala.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import UPP.NaucnaCentrala.model.User;
import UPP.NaucnaCentrala.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRep;
	
	public User findById(Long id) {
		return this.userRep.findById(id).orElse(null);
	}
		
	public User findByEmailAndPassword(String email, String password) {
		return this.userRep.findByEmailAndPassword(email, password);
	}
	
	public User findByEmail(String email) {
		return this.userRep.findByEmail(email);
	}
	
	public User save(User user) {
		return this.userRep.save(user);
	}
	
	public void setCurrentUser(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            Long id = Long.parseLong(auth.getName());
            return userRep.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

}
