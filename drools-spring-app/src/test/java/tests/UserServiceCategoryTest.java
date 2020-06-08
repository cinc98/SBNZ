package tests;

import static org.junit.Assert.assertEquals;

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

import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;

public class UserServiceCategoryTest {
	private KieSession kSession;
	
	@Test
	public void bronzeMedal() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
		
        User u = new User();
        u.setId(1);
        u.setUsername("bronze");
        Date d = new Date();
		u.setPassword("123");
		u.setRegistrationDate(d);
        kSession.insert(u);
        kSession.getAgenda().getAgendaGroup("kategorije").setFocus();
        kSession.fireAllRules();
        assertEquals("BRONZE", u.getMedal());
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void silverMedal() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
		
        User u = new User();
        u.setId(100);
        u.setUsername("silver");
		u.setPassword("123");

		Date d = new Date();
        d.setMonth(0);
        d.setDate(2);
        d.setYear(119);
		u.setRegistrationDate(d);
		
        Reservation r1 = new Reservation(d,d,"ZAVRSEN",500);
        r1.setUser(u);
        Reservation r2 = new Reservation(d,d,"ZAVRSEN",500);
        r2.setUser(u);
        
        kSession.insert(u);
        kSession.insert(r1);
        kSession.insert(r2);
        
        
        kSession.getAgenda().getAgendaGroup("kategorije").setFocus();
        kSession.fireAllRules();  
        kSession.dispose();      
		assertEquals("SILVER", u.getMedal());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void goldMedal() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
		
        User u = new User();
        u.setId(100);
        u.setUsername("silver");
		u.setPassword("123");

		Date d = new Date();
        d.setMonth(0);
        d.setDate(2);
        d.setYear(119);
		u.setRegistrationDate(d);
		
		d = new Date();
		
        Reservation r1 = new Reservation(d,d,"ZAVRSEN",1000);
        r1.setUser(u);
        Reservation r2 = new Reservation(d,d,"ZAVRSEN",5000);
        r2.setUser(u);
        Reservation r3 = new Reservation(d,d,"ZAVRSEN",500);
        r3.setUser(u);
        Reservation r4 = new Reservation(d,d,"ZAVRSEN",500);
        r4.setUser(u);
        Reservation r5 = new Reservation(d,d,"ZAVRSEN",500);
        r5.setUser(u);
        
        kSession.insert(u);
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.insert(r4);
        kSession.insert(r5);
        
        
        kSession.getAgenda().getAgendaGroup("kategorije").setFocus();
        kSession.fireAllRules();  
        kSession.dispose();      
		assertEquals("GOLD", u.getMedal());
		
	}
}
