package sbnz.integracija.example.service;


import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
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
        	KieServices ks = KieServices.Factory.get();
            KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
            kconf.setOption(EventProcessingOption.STREAM);
            KieBase kieBase = kieContainer.newKieBase(kconf);
            KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
            kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
            kieSession = kieBase.newKieSession(kconfig1, null);
        	SampleApp.kieSessions.put("kieSession-searchCar",kieSession);
        } 
		kieSession.insert(s);
		kieSession.getAgenda().getAgendaGroup("pretraga").setFocus();
		kieSession.fireAllRules();
		return s;
	}
}
