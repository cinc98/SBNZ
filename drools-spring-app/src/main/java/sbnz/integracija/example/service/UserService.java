package sbnz.integracija.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.UserRepository;

@Service
public class UserService {

	private final KieContainer kieContainer;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(KieContainer kieContainer) {
		 this.kieContainer = kieContainer;

	}
	public User setCategory(User u,List<Reservation> reservations) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(u);
		for (Reservation res : reservations)
			kieSession.insert(res);
		kieSession.getAgenda().getAgendaGroup("kategorije").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return u;
	}

	public ResponseEntity<String> registerUser(String username, String password) {

		User retUser = userRepository.findOneByUsername(username);
		if (retUser == null) {
			retUser = new User();
			Date d = new Date();
			retUser.setPassword(password);
			retUser.setUsername(username);
			retUser.setRegistrationDate(d);
			userRepository.save(retUser);
			return new ResponseEntity<String>("Korisnik " + username + " je uspesno registrovan!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Korisnicko ime " + username + " je zauzeto!", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<String> login(String username, String password) {

		User retUser = userRepository.findOneByUsername(username);
		if (retUser != null) {
			if (retUser.getPassword().equals(password))
				return new ResponseEntity<String>("Korisnik " + username + " je uspesno ulogovan!", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Pogresno korisnicko ime ili lozinka!", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<String>("Pogresno korisnicko ime ili lozinka!", HttpStatus.BAD_REQUEST);

	}
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		for(User u : userRepository.findAll()) {
			if(!u.getUsername().equals("admin")) {
				users.add(u);
			}
		}
		
		return users;
	};

}