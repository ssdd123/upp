package UPP.NaucnaCentrala.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.NaucnaCentrala.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

}
