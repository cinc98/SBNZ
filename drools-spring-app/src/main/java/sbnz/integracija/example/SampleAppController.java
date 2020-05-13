package sbnz.integracija.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private UserRepository repo;
	@Autowired
	private CarRepository carRepo;
	@Autowired
	private ReservationRepository resRepo;

	@Autowired
	public SampleAppController(SampleAppService sampleService) {
		this.sampleService = sampleService;
	}

	@RequestMapping(value = "/add/user", method = RequestMethod.POST)
	public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password) {
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
	public Reservation getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name,
			@RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) throws ParseException {

		/*
		 * Car c = new Car(); System.out.println(c); carRepo.save(c); User u = new
		 * User();
		 * 
		 * u.getCars().add(c); c.getUsers().add(u); u.setUsername("a");
		 * System.out.println(u); repo.save(u);
		 * 
		 * System.out.println(repo.getSearches(1));
		 */

		Reservation newRes = new Reservation();
		String sDate1 = "31/12/2021";
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		newRes.setFromDate(date1);
		newRes.setStatus(20);
		newRes.setPrice(5000);
		
		User o = new User();
		o.setId((int)cost);
		
		Car c = new Car();
		c.setId((int) salePrice);
		
		newRes.setUser(o);
		newRes.setCar(c);
		List<Reservation> reservations = resRepo.findAll();
		// Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);
		// log.debug("Item request received for: " + newItem);
		Reservation i2 = sampleService.getClassifiedItem(reservations,newRes);
		
		
		return i2;
	}

}
