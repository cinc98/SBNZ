package sbnz.integracija.example.service;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.SampleApp;
import sbnz.integracija.example.model.Search;

@Service
public class SearchService {
	private static Logger log = LoggerFactory.getLogger(ReservationService.class);

	private KieContainer kieContainer = null;

	@Autowired
	public void ReservationService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	public Search searchCar(Search s) {
		KieSession kieSession = SampleApp.kieSessions.get("kieSession-searchCar");
        if(kieSession == null) {
        	kieSession = kieContainer.newKieSession();
        	SampleApp.kieSessions.put("kieSession-searchCar",kieSession);
        } 
		kieSession.insert(s);
		kieSession.getAgenda().getAgendaGroup("pretraga").setFocus();
		kieSession.fireAllRules();
		return s;
	}
}
