package tests;

import java.util.Date;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import static org.junit.Assert.assertEquals;
import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.model.Reservation;

public class CarServiceNotificationTest {
	
	private KieSession kSession;
	
	@Test
	public void getNotification() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        
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
       
        Reservation r1 = new Reservation(d,d,"ZAVRSEN",500);
        r1.setCar(c);
        Reservation r2 = new Reservation(d,d,"ZAVRSEN",500);
        r2.setCar(c);
        Reservation r3 = new Reservation(d,d,"ZAVRSEN",500);
        r3.setCar(c);
        Reservation r4 = new Reservation(d,d,"ZAVRSEN",500);
        r4.setCar(c);
        Reservation r5 = new Reservation(d,d,"ZAVRSEN",500);
        r5.setCar(c);
        Reservation r6 = new Reservation(d,d,"ZAVRSEN",500);
        r6.setCar(c);
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

}
