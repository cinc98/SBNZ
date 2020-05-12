package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sbnz.integracija.example.facts.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();

	User findOneByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update User p set p.medal = :medal where p.username = :username")
	int updateUserMedal(@Param("medal") String medal, @Param("username") String string);
}
