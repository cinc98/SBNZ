package sbnz.integracija.example.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.SampleApp;
import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.repository.CarRepository;
import sbnz.integracija.example.repository.ReservationRepository;

@Service
public class CarService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CarRepository carRepository;

	private final KieContainer kieContainer;

	@Autowired
	public CarService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;

	}

	public List<Car> getNotifications() {
		List<Car> retList = carRepository.findAll();
		KieSession kieSession = kieContainer.newKieSession();
		SampleApp.kieSessions.put("kieSession-reservations", kieSession);
		for (Car c : retList) {
			kieSession.insert(c);
		}
		for (Reservation r : reservationRepository.findAll()) {
			kieSession.insert(r);
		}
		kieSession.getAgenda().getAgendaGroup("notifikacije").setFocus();
		kieSession.fireAllRules();
		return retList;
	}

	public List<Car> getAll() {
		return carRepository.findAll();
	}

}