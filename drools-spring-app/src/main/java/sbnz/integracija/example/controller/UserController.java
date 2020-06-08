package sbnz.integracija.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return carService.getNotifications();
	}

	@RequestMapping(value = "/add-rule", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> addRule(@RequestParam("minPrice") String minPrice,
			@RequestParam("maxPrice") String maxPrice, @RequestParam("depositValue") String depositValue) {

		File initialFile = new File(System.getProperty("user.dir").replace("drools-spring-app", "drools-spring-kjar")
				+ "\\src\\main\\resources\\sbnz\\integracija\\depozit.drt");

		InputStream template = null;
		try {
			template = new FileInputStream(initialFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Cant find template", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		DataProvider dataProvider = new ArrayDataProvider(
				new String[][] { new String[] { minPrice, maxPrice, depositValue } });

		DataProviderCompiler converter = new DataProviderCompiler();
		String drl = converter.compile(dataProvider, template);

		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(drl, ResourceType.DRL);

		Results results = kieHelper.verify();

		if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
			return new ResponseEntity<>("validation rule error", HttpStatus.INTERNAL_SERVER_ERROR);

		}

		FileWriter myWriter;
		try {
			myWriter = new FileWriter(System.getProperty("user.dir").replace("drools-spring-app", "drools-spring-kjar")
					+ "\\src\\main\\resources\\sbnz\\integracija\\" + depositValue + "_percentage_deposit_rule.drl");
			myWriter.write(drl);
			myWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>("Rule added",HttpStatus.OK);
	}

}