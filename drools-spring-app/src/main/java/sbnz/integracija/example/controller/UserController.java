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
import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.CarService;
import sbnz.integracija.example.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json")
	public List<User> checkIfRecommended(@RequestParam("first") String first) {

		return userService.check(first);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		return userService.registerUser(username, password);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUsers() {
		List<User> t = userRepository.findAll();
		List<User> ret = new ArrayList<User>();
		for (User u : t)
			if (!u.getUsername().equals("admin")) {
				ret.add(userService.setCategory(u, reservationRepository.findAll()));
			}
		return ret;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<User> setCategory() {

		return userService.getAllUsers();
	}

	@RequestMapping(value = "/new-recommendation", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> newRecommend(@RequestParam("first") String first,
			@RequestParam("second") String second) {
		return userService.newRecommendation(first, second);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> loginUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		return userService.login(username, password);
	}

	@RequestMapping(value = "/get-notifications", method = RequestMethod.GET, produces = "application/json")
	public List<Car> getNotifications() {
		List<Car> l = carService.getNot(null);
		List<Car> list= carService.getNotifications();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getChangePrice()==2) {
					list.get(i).setChangePrice(2);
			}
		}
		return list;
	}

}