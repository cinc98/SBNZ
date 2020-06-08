package tests;

import java.util.Date;

import org.drools.core.ClockType;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import static org.junit.Assert.assertEquals;
import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;

public class CarServiceNotificationTest {
	
	private KieSession kSession;
	
	@Test
	public void getNotification() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
        
        Car c = new Car();
        c.setId(1);
        Car c1 = new Car();
        c1.setId(2);
        c1.setPrice(5000);
        c1.setModel("m1");
        Car c2 = new Car();
        c2.setId(3);
        c2.setPrice(100);
        c2.setModel("m1");
        
        Date d = new Date();
        User u = new User();
        u.setId(1);
       
        Reservation r1 = new Reservation(d,d,"ZAVRSEN",500);
        r1.setCar(c);
        r1.setUser(u);
        Reservation r2 = new Reservation(d,d,"ZAVRSEN",500);
        r2.setCar(c);
        r2.setUser(u);
        Reservation r3 = new Reservation(d,d,"ZAVRSEN",500);
        r3.setCar(c);
        r3.setUser(u);
        Reservation r4 = new Reservation(d,d,"ZAVRSEN",500);
        r4.setCar(c);
        r4.setUser(u);
        Reservation r5 = new Reservation(d,d,"ZAVRSEN",500);
        r5.setCar(c);
        r5.setUser(u);
        Reservation r6 = new Reservation(d,d,"ZAVRSEN",500);
        r6.setCar(c);
        r6.setUser(u);
        kSession.insert(c);
        kSession.insert(c1);
        kSession.insert(c2);
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.insert(r4);
        kSession.insert(r5);
        kSession.insert(r6);
        
        kSession.getAgenda().getAgendaGroup("notifikacije").setFocus();
        kSession.fireAllRules();
        assertEquals(1,c.getChangePrice());
        assertEquals(-1,c1.getChangePrice());
        assertEquals(0,c2.getChangePrice());
	}
	@Test
	public void getNotification2() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
        
        Car c = new Car();
        c.setId(1);        
        Date d = new Date();
        User u = new User();
        u.setId(1);
       
        Reservation r1 = new Reservation(d,d,"ZAVRSEN",500);
        r1.setCar(c);
        r1.setUser(u);
        Reservation r2 = new Reservation(d,d,"ZAVRSEN",500);
        r2.setCar(c);
        r2.setUser(u);
        kSession.insert(c);
        kSession.insert(r1);
        kSession.insert(r2);
        
        kSession.getAgenda().getAgendaGroup("cep").setFocus();
        kSession.fireAllRules();
        assertEquals(2,c.getChangePrice());
	}

}
