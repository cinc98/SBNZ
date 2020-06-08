package sbnz.integracija.example.service;

import java.util.List;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
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
		KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        
		for (Car c : retList) {
			kieSession.insert(c);
		}
		for (Reservation r : reservationRepository.findAll()) {
			kieSession.insert(r);
		}
		kieSession.getAgenda().getAgendaGroup("notifikacije").setFocus();
		kieSession.fireAllRules();
		//kieSession.getAgenda().getAgendaGroup("cep").setFocus();
		//kieSession.fireAllRules();
		return retList;
	}
	public List<Car> getNot(Reservation r) {
		List<Car> retList = carRepository.findAll();
		KieSession kieSession = SampleApp.kieSessions.get("kieSession-not");
		if(kieSession == null) {
	        	KieServices ks = KieServices.Factory.get();
	            KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
	            kconf.setOption(EventProcessingOption.STREAM);
	            KieBase kieBase = kieContainer.newKieBase(kconf);
	            KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
	            kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
	            kieSession = kieBase.newKieSession(kconfig1, null);
	        	SampleApp.kieSessions.put("kieSession-not",kieSession);
	        } 
		for (Car c : retList) {
			kieSession.insert(c);
		}
		if(r!=null)
			kieSession.insert(r);
		kieSession.getAgenda().getAgendaGroup("cep").setFocus();
		kieSession.fireAllRules();
		return retList;
	}

	public List<Car> getAll() {
		return carRepository.findAll();
	}

}