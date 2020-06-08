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

public class PenaltyPercentageTest {
private KieSession kSession;
	
	@SuppressWarnings("deprecation")
	@Test
	public void getNotification() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        Date d = new Date();
        d.setDate(8);
        User u = new User();
        u.setId(1);
        Car c = new Car();
        c.setId(1);
        Reservation r1 = new Reservation(d,d,"OTKAZIVANJE",500);
        r1.setCar(c);
        r1.setUser(u);
        User u1= new User();
        u1.setId(2);
        Date d1 = new Date();
        d1.setDate(12);
        Reservation r2 = new Reservation(d1,d1,"OTKAZIVANJE",500);
        r2.setCar(c);
        r2.setUser(u1);
        
        
        Date d2 = new Date();
        d2.setYear(130);
        Reservation r3 = new Reservation(d2,d2,"OTKAZIVANJE",500);
        r3.setCar(c);
        r3.setUser(u1);
        kSession.insert(r1);
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        kSession.fireAllRules();
        
        kSession =  kContainer.newKieSession();
        kSession.insert(r2);
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        kSession.fireAllRules();
        
        kSession =  kContainer.newKieSession();
        kSession.insert(r3);
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        kSession.fireAllRules();

        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        kSession.fireAllRules();
        assertEquals(50,r1.getPenaltyPercentage());
        assertEquals(30,r2.getPenaltyPercentage());
        assertEquals(0,r3.getPenaltyPercentage());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void getNotification2() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        Date d = new Date();
        d.setDate(8);
        Date d1 = new Date();
        d1.setDate(10);
        User u = new User();
        u.setId(1);
        Car c = new Car();
        c.setId(1);
        Reservation r1 = new Reservation(d,d1,"REZERVISAN",500);
        r1.setCar(c);
        r1.setUser(u);
        
        Date d2 = new Date();
        d2.setDate(1);
        Date d3 = new Date();
        d3.setDate(3);
        @SuppressWarnings("unused")
		User u1 = new User();
        u.setId(2);
       
        Reservation r2 = new Reservation(d2,d3,"REZERVISAN",500);
        r2.setCar(c);
        r2.setUser(u);
        
        Date d4 = new Date();
        d4.setDate(5);
        Date d5 = new Date();
        d5.setDate(6);
        
        Reservation r3 = new Reservation(d4,d5,"OTKAZIVANJE",500);
        r3.setCar(c);
        r3.setUser(u);
       
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        kSession.fireAllRules();

        assertEquals(55,r3.getPenaltyPercentage());
     
	}
	@SuppressWarnings("deprecation")
	@Test
	public void getNotification3() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        Date d = new Date();
        d.setYear(120);
        Date d1 = new Date();
        d1.setYear(119);
        User u = new User();
        u.setId(1);
        Car c = new Car();
        c.setId(1);
        Reservation r1 = new Reservation(d,d,"ZAVRSEN",500);
        r1.setCar(c);
        r1.setUser(u);
        Reservation r2 = new Reservation(d1,d1,"ZAVRSEN",500);
        r2.setCar(c);
        r2.setUser(u);
        
        Date d2 = new Date();
        d2.setYear(118);
        Date d3 = new Date();
        d3.setYear(117);
        @SuppressWarnings("unused")
		User u1 = new User();
        u.setId(2);
       
        Reservation r3 = new Reservation(d2,d2,"ZAVRSEN",500);
        r3.setCar(c);
        r3.setUser(u);
        Reservation r4 = new Reservation(d3,d3,"ZAVRSEN",500);
        r4.setCar(c);
        r4.setUser(u);
        
        Date d4 = new Date();
        d4.setDate(5);
        Date d5 = new Date();
        d5.setDate(6);
        
        Reservation r5 = new Reservation(d4,d5,"OTKAZIVANJE",500);
        r5.setCar(c);
        r5.setUser(u);
        
        Date d6 = new Date();
        d6.setYear(116);
        Date d7 = new Date();
        d3.setYear(115);
        
       
        Reservation r6 = new Reservation(d6,d6,"ZAVRSEN",500);
        r6.setCar(c);
        r6.setUser(u);
        Reservation r7 = new Reservation(d7,d7,"ZAVRSEN",500);
        r7.setCar(c);
        r7.setUser(u);
       
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.insert(r4);
        kSession.insert(r5);
        kSession.insert(r6);
        kSession.insert(r7);
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        kSession.fireAllRules();

        assertEquals(50,r5.getPenaltyPercentage());
     
	}
}
