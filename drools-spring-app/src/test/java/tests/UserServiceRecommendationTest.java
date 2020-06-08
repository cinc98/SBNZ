package tests;

import static org.junit.Assert.assertEquals;

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

import sbnz.integracija.example.model.Recommendation;
import sbnz.integracija.example.model.User;

public class UserServiceRecommendationTest {
	private KieSession kSession;
	
	@Test
	public void checkTrue() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
        kSession.setGlobal("first", "Cinc");
        
        User u = new User();
        u.setId(100);
        u.setUsername("Miki");
		u.setPassword("123");

		Recommendation r1 = new Recommendation("Cinc","Stefan");
		Recommendation r2 = new Recommendation("Stefan","Miki");
        
        
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(u);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
        kSession.fireAllRules();  
        kSession.dispose();      
		assertEquals(true, u.getRecommended());
		
	}
	@Test
	public void checkFalse() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kSession = kieBase.newKieSession(kconfig1, null);
        kSession.setGlobal("first", "Miki");
        
        User u = new User();
        u.setId(100);
        u.setUsername("Stefan");
		u.setPassword("123");
		u.setRecommended(false);
		Recommendation r1 = new Recommendation("Cinc","Stefan");
		Recommendation r2 = new Recommendation("Stefan","Miki");
        
        
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(u);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
        kSession.fireAllRules();  
        kSession.dispose();      
		assertEquals(false, u.getRecommended());
		
	}
}
