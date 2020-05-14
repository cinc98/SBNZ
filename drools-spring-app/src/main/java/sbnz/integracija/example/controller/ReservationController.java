package sbnz.integracija.example.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import sbnz.integracija.example.service.ReservationService;

import org.apache.commons.lang.time.DateUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/reserve", method = RequestMethod.POST, produces = "application/json")
	public Reservation addDiscount(@RequestParam("carId") String carId,
			@RequestParam("fromDate") Date fromDate, @RequestParam("numberOfDays") int numberOfDays,
			@RequestParam("username") String username) throws ParseException {

		Reservation newRes = new Reservation();
		Car car = carRepository.findOneById(Integer.parseInt(carId));
		User user = userRepository.findOneByUsername(username);
		newRes.setCar(car);
		newRes.setPrice(car.getPrice() * numberOfDays);
		newRes.setStatus("REZERVISAN");
		newRes.setFromDate(fromDate);
		newRes.setUntilDate(DateUtils.addDays(fromDate, numberOfDays));
		newRes.setUser(user);

		newRes = reservationService.discountReservation(reservationRepository.findAll(), newRes);

		return newRes;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET, produces = "application/json")
	public Reservation cancelDiscount(@RequestParam("reservationId") String reservationId) {
		Reservation r = reservationRepository.findOneById(Integer.parseInt(reservationId));

		r = reservationService.cancelReservation(reservationRepository.findAll(), r);

		return r;
	}
}
