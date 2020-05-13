package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.service.ReservationService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping(value = "/add-discount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> addDiscount() {

		for (Reservation r : reservationRepository.findAll())
			reservationService.discountReservation(r);

		return new ResponseEntity<>("Discount added!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> cancelDiscount() {

		for (Reservation r : reservationRepository.findAll())
			reservationService.cancelReservation(r);

		return new ResponseEntity<>("Discount added!", HttpStatus.OK);
	}
}
