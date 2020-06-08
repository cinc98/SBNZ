package sbnz.integracija.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Recommendation;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.RecommendationRepository;
import sbnz.integracija.example.repository.UserRepository;

@Service
public class UserService {

	private final KieContainer kieContainer;

	@Autowired
	private RecommendationRepository recommendationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(KieContainer kieContainer) {
		 this.kieContainer = kieContainer;

	}
	
	public User setCategory(User u,List<Reservation> reservations) {
		KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        
		kieSession.insert(u);
		for (Reservation res : reservations)
			kieSession.insert(res);
		kieSession.getAgenda().getAgendaGroup("kategorije").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return u;
	}

	public ResponseEntity<String> newRecommendation(String first,String second){
		Recommendation ret = new Recommendation(first,second);
		recommendationRepository.save(ret);
		return new ResponseEntity<String>("Korisnik " + first + " je preporucio korisnika: " + second, HttpStatus.OK);
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
	}
	
	public List<User> check(String first) {
		User firstUser = userRepository.findOneByUsername(first);
		KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        
		kieSession.setGlobal("first", firstUser.getUsername());
		
		List<Recommendation> r = recommendationRepository.findAll();
		for (Recommendation res : r)
			kieSession.insert(res);
		List<User> users = userRepository.findAll();
		for(User u : users) {
			u.setRecommended(false);
			kieSession.insert(u);
		}
		kieSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return users;
	};

}