package sbnz.integracija.example;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.facts.Item;
import sbnz.integracija.example.facts.Reservation;
import sbnz.integracija.example.facts.User;

@Service
public class SampleAppService {

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;

	@Autowired
	public SampleAppService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		
	}
	


	public Reservation getClassifiedItem(Reservation i) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(i);
		kieSession.fireAllRules();
		kieSession.dispose();
		return i;
	}
	public Reservation getClassifiedItem(List<Reservation> reservations,Reservation res) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(res);
		for(Reservation r : reservations)
			kieSession.insert(r);

		kieSession.getAgenda().getAgendaGroup("popusti").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return res;
	}
}
