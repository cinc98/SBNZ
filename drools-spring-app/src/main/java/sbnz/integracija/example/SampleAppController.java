package sbnz.integracija.example;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.CarRepository;
import repository.ReservationRepository;
import repository.UserRepository;
import sbnz.integracija.example.facts.Car;
import sbnz.integracija.example.facts.Item;
import sbnz.integracija.example.facts.Reservation;
import sbnz.integracija.example.facts.User;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class SampleAppController {
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);
	
	private final SampleAppService sampleService;
	@Autowired
	private UserRepository repo ;
	@Autowired
	private CarRepository carRepo ;
	@Autowired
	public SampleAppController(SampleAppService sampleService) {
		this.sampleService = sampleService;
	}
	@RequestMapping(value = "/add/user", method = RequestMethod.POST)
	public @ResponseBody String addNewUser(@RequestParam String username
		      , @RequestParam String password) {
		Date date = new Date();		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setMedal("SILVER");
		u.setRegistrationDate(date);
		repo.save(u);
		return "saved";
	}
	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
	public User getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name,
			@RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) {
		
		/*Car c = new Car();
		System.out.println(c);
		carRepo.save(c);
		User u = new User();
		
		u.getCars().add(c);
		c.getUsers().add(u);
		u.setUsername("a");
		System.out.println(u);
		repo.save(u);
		
		System.out.println(repo.getSearches(1));*/
		User u = new User();
		u.setUsername("a");
		
		//Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);
		System.out.println(u);
		//log.debug("Item request received for: " + newItem);
		User i2 = sampleService.getClassifiedItem(u);

		return i2;
	}
	
	
	
}
