package sbnz.integracija.example.service;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Reservation;
<<<<<<< HEAD
import sbnz.integracija.example.repository.CarRepository;
import sbnz.integracija.example.repository.ReservationRepository;
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930

@Service
public class ReservationService {
	private static Logger log = LoggerFactory.getLogger(ReservationService.class);

	private final KieContainer kieContainer;
<<<<<<< HEAD
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private CarRepository carRepository;
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930

	@Autowired
	public ReservationService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;

	}

	public Reservation discountReservation(List<Reservation> reservations, Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		for (Reservation res : reservations)
			kieSession.insert(res);
		kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return r;
	}

	public Reservation cancelReservation(List<Reservation> reservations, Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		FactHandle handle = kieSession.getFactHandle(r);
		
		r.setStatus("OTKAZIVANJE");
		kieSession.update(handle, r);
		for (Reservation res : reservations) {
			if (res.getId() != r.getId()) {
				kieSession.insert(res);

			}
				
		}

		kieSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return r;
	}
<<<<<<< HEAD
	
	public List<Reservation> getUserReservations(String username) {
		List<Reservation> retList = new ArrayList<Reservation>();
		for(Reservation r : reservationRepository.findAll()) {
			if(r.getStatus().equals("REZERVISAN") && r.getUserame().equals(username)) {
				r.setCar(carRepository.findOneById(r.getCar()));
				retList.add(r);
			}
		}
		
		return retList;
	}
	
	
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930

}
