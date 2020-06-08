package tests;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.model.Characteristic;
import sbnz.integracija.example.model.Search;

public class SearchServiceTest {
	private KieSession kSession;
	@Test
	public void searchFirst() {
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
		
		l.clear();
		l.add(new Characteristic("France"));
        l.add(new Characteristic("Saloon"));
        l.add(new Characteristic("Automatic transmission"));
        s.setUslovi(l);
        Search citroen = new Search();
        citroen.setUslovi(l);
        kSession.insert(citroen);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("CITROEN", citroen.getModel());
		
		l.clear();
		l.add(new Characteristic("USA"));
        l.add(new Characteristic("SUV"));
        l.add(new Characteristic("LPG"));
        s.setUslovi(l);
        Search ford = new Search();
        ford.setUslovi(l);
        kSession.insert(ford);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("FORD", ford.getModel());
	}
	@Test
	public void searchSecond() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        
        Search s = new Search();
        List<Characteristic> l = new ArrayList<Characteristic>();
        l.add(new Characteristic("Germany"));
        l.add(new Characteristic("SUV"));
        l.add(new Characteristic("Petrol"));
        l.add(new Characteristic("Automatic transmission"));
        l.add(new Characteristic("360 camera"));
        s.setUslovi(l);
        kSession.insert(s);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("BMW", s.getModel());
		
		l.clear();
		l.add(new Characteristic("Germany"));
        l.add(new Characteristic("Saloon"));
        l.add(new Characteristic("Petrol"));
        l.add(new Characteristic("Automatic transmission"));
        l.add(new Characteristic("Sport seats"));
        s.setUslovi(l);
        Search m = new Search();
        m.setUslovi(l);
        kSession.insert(m);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("MERCEDES", m.getModel());
		
		l.clear();
		l.add(new Characteristic("Sweden"));
        l.add(new Characteristic("SUV"));
        l.add(new Characteristic("Petrol"));
        l.add(new Characteristic("Automatic transmission"));
        l.add(new Characteristic("LED headlights"));
        s.setUslovi(l);
        Search v = new Search();
        v.setUslovi(l);
        kSession.insert(v);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("VOLVO", v.getModel());
	}
	@Test
	public void searchThird() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession();
        
        Search s = new Search();
        List<Characteristic> l = new ArrayList<Characteristic>();
        l.add(new Characteristic("Italy"));
        l.add(new Characteristic("Coupe"));
        s.setUslovi(l);
        List<Characteristic> spec = new ArrayList<Characteristic>();
        spec.add(new Characteristic("Night vision assist"));
        s.setSpecificniUslovi(spec);
        kSession.insert(s);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("FERARI", s.getModel());
		
		l.clear();
		spec.clear();
		Search b = new Search();
        l.add(new Characteristic("Cabriolet"));
        b.setUslovi(l);
        spec.add(new Characteristic("Hands-free"));
        b.setSpecificniUslovi(spec);
        kSession.insert(b);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("BUGATI", b.getModel());
		
		l.clear();
		spec.clear();
		Search ben = new Search();
        l.add(new Characteristic("Saloon"));
        ben.setUslovi(l);
        spec.add(new Characteristic("ISOFIX"));
        ben.setSpecificniUslovi(spec);
        kSession.insert(ben);
        kSession.getAgenda().getAgendaGroup("pretraga").setFocus();
        kSession.fireAllRules();
		assertEquals("BENTLI", ben.getModel());
		
	}
	

}
