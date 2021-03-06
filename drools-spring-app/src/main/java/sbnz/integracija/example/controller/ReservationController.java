package sbnz.integracija.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.CarRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.CarService;
import sbnz.integracija.example.service.ReservationService;

import org.apache.commons.lang.time.DateUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("reservation")
public class ReservationController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/reserve", method = RequestMethod.POST, produces = "application/json")
	public Reservation addDiscount(@RequestParam("carId") String carId, @RequestParam("fromDate") String fromDate,
			@RequestParam("numberOfDays") int numberOfDays, @RequestParam("username") String username)
			throws ParseException {

		Reservation newRes = new Reservation();
		Car car = carRepository.findOneById(Integer.parseInt(carId));
		User user = userRepository.findOneByUsername(username);
		newRes.setCar(car);
		newRes.setPrice(car.getPrice() * numberOfDays);
		newRes.setStatus("REZERVISAN");
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
		Date tomorrow = new Date(date1.getTime() + (1000 * 60 * 60 * 24));
		newRes.setFromDate(tomorrow);
		newRes.setUntilDate(DateUtils.addDays(tomorrow, numberOfDays));
		newRes.setUser(user);

		newRes = reservationService.discountReservation(reservationRepository.findAll(), newRes);

		return newRes;
	}

	@RequestMapping(value = "/extend", method = RequestMethod.POST, produces = "application/json")
	public Reservation extendReservation(@RequestParam("idRes") String idRes, @RequestParam("fromDate") String fromDate,
			@RequestParam("numberOfDays") int numberOfDays) throws ParseException {

		Reservation r = reservationRepository.findOneById(Integer.parseInt(idRes));
		r.setPrice(r.getCarObject().getPrice() * numberOfDays);
		r.setStatus("PRODUZAVANJE");
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
		Date tomorrow = new Date(date1.getTime() + (1000 * 60 * 60 * 24));
		r.setFromDate(tomorrow);
		r.setUntilDate(DateUtils.addDays(date1, numberOfDays));
		System.out.println(fromDate);
		System.out.println(numberOfDays);

		r = reservationService.discountReservation(reservationRepository.findAll(), r);

		return r;

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> saveReservation(@RequestBody Reservation r) {

		Reservation newRes = new Reservation();
		newRes.setUser(userRepository.findOneByUsername(r.getUserame()));
		newRes.setCar(carRepository.findOneById(r.getCar()));
		Date fromD = new Date(r.getFromDate().getTime() - (1000 * 60 * 60 * 24));
		newRes.setFromDate(fromD);
		Date untilD = new Date(r.getUntilDate().getTime() - (1000 * 60 * 60 * 24));
		newRes.setUntilDate(untilD);
		newRes.setStatus("REZERVISAN");
		newRes.setPrice(r.getPrice());
		newRes.setDiscount(r.getDiscount());
		reservationRepository.save(newRes);
		
		carService.getNot(r);
		
		return new ResponseEntity<String>("Uspesno ste rezervisali vozilo!", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/all-active", method = RequestMethod.GET, produces = "application/json")
	public List<Reservation> getActiveReservations(@RequestParam("username") String username) {

		List<Reservation> r = reservationService.getUserReservations(username);

		return r;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET, produces = "application/json")
	public Reservation cancelDiscount(@RequestParam("reservationId") String reservationId) {
		Reservation r = reservationRepository.findOneById(Integer.parseInt(reservationId));

		r = reservationService.cancelReservation(reservationRepository.findAll(), r);

		return r;
	}

	@RequestMapping(value = "/cancel-agree", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> cancelDiscountAgree(@RequestParam("reservationId") String reservationId,
			@RequestParam("penalty") String penalty) {

		@SuppressWarnings("unused")
		int i = reservationRepository.updateStatus("OTKAZANO", Integer.parseInt(penalty),
				Integer.parseInt(reservationId));

		return new ResponseEntity<String>("Uspesno ste otkazali rezervaciju!", HttpStatus.OK);
	}
}