package sbnz.integracija.example.service;


import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.Search;
import sbnz.integracija.example.repository.CarRepository;
import sbnz.integracija.example.repository.ReservationRepository;

@Service
public class SearchService {
	private static Logger log = LoggerFactory.getLogger(ReservationService.class);

	private KieContainer kieContainer = null;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	public void ReservationService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;

	}
	
	public Search searchCar(Search s) {
		KieSession kieSession = kieContainer.newKieSession();
		System.out.println("saljem: "+s.getUslovi().toString());
		kieSession.insert(s);
		kieSession.getAgenda().getAgendaGroup("pretraga").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return s;
	}
}
