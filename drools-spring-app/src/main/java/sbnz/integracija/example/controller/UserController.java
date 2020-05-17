package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("user")
public class UserController {


	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;


	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		return userService.registerUser(username, password);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
	public List<User> setCategory() {
		List<User> t = userRepository.findAll();
		List<User> ret = new ArrayList<User>();
		for (User u : t)
			ret.add(userService.setCategory(u, reservationRepository.findAll()));
		return ret;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> loginUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		return userService.login(username, password);
	}

}