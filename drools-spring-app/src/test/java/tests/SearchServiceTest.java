package tests;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.SampleApp;
import sbnz.integracija.example.model.Characteristic;
import sbnz.integracija.example.model.Search;

public class SearchServiceTest {
	private KieSession kSession;
	@Test
	public void search() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
		
        Search s = new Search();
        List<Characteristic> l = new ArrayList<Characteristic>();
        l.add(new Characteristic("Germany"));
        l.add(new Characteristic("Petrol"));
        l.add(new Characteristic("ABS"));
        l.add(new Characteristic("Petrol"));
        s.setUslovi(l);
        kSession.insert(s);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("OPEL", s.getModel());
	}
	
	

}
