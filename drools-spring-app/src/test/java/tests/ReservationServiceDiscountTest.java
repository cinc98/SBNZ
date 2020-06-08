package tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;

public class ReservationServiceDiscountTest {
	private KieSession kSession;
	
	@SuppressWarnings("deprecation")
	@Test
	public void getNotification() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        
        Date d = new Date();
        
        User u = new User();
        u.setId(1);
        Car c = new Car();
        c.setId(1);
        d.setMonth(10);
        Reservation r1 = new Reservation(d,d,"REZERVISAN",500);
        r1.setCar(c);
        r1.setUser(u);
        r1.setDiscount(0);
        d = new Date();
        d.setMonth(5);
        User u2 = new User();
        u2.setId(2);
        Reservation r2 = new Reservation(d,d,"REZERVISAN",500);
        r2.setCar(c);
        r2.setUser(u2);
        Car cc = new Car();
        cc.setId(100);
        Reservation r3 = new Reservation(d,d,"ZAVRSEN",500);
        r3.setCar(cc);
        r3.setUser(u2);
        d = new Date();
        User u3 = new User();
        u3.setId(3);
        
        Reservation r4 = new Reservation(d,d,"REZERVISAN",500);
        r4.setCar(c);
        r4.setUser(u3);
        r4.setDiscount(0);        
        Reservation r5 = new Reservation(d,d,"ZAVRSEN",500);
        r5.setCar(c);
        r5.setUser(u3);
        Reservation r6 = new Reservation(d,d,"PRODUZAVANJE",500);
        r6.setCar(c);
        r6.setUser(u3);
        
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.insert(r4);
        kSession.insert(r5);
        kSession.insert(r6);
        
        kSession.getAgenda().getAgendaGroup("popusti").setFocus();
        kSession.fireAllRules();
        assertEquals(20,r1.getDiscount());
        assertEquals(30,r2.getDiscount());
        assertEquals(70,r4.getDiscount());
        assertEquals(10,r6.getDiscount());
	}
}
