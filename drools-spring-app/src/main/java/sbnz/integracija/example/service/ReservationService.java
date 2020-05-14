package sbnz.integracija.example.service;


import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Reservation;

@Service
public class ReservationService {
	private static Logger log = LoggerFactory.getLogger(ReservationService.class);

	private final KieContainer kieContainer;

	@Autowired
	public ReservationService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		
	}
	

	public Reservation discountReservation(List<Reservation> reservations, Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		for(Reservation res : reservations)
			kieSession.insert(res);
		kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return r;
	}
	
	public Reservation cancelReservation(List<Reservation> reservations, Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		for(Reservation res : reservations)
			kieSession.insert(res);
		kieSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return r;
	}

}
