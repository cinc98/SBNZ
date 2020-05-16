package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Characteristic;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.Search;
import sbnz.integracija.example.service.SearchService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("searchCar")
public class SearchController {
	
	@Autowired
	private SearchService service;
	
	@RequestMapping(value = "/s", method = RequestMethod.GET, produces = "application/json")
	public Search cancelDiscount(@RequestParam("zemlja") String zemlja,@RequestParam("karoserija") String karoserija,
			@RequestParam("gorivo") String gorivo,@RequestParam("menjac") String menjac,
			@RequestParam("oprema") String oprema,@RequestParam("sedista") String sedista,
			@RequestParam("s1") String s1,@RequestParam("s2") String s2,
			@RequestParam("s3") String s3) {
		List<Characteristic> uslovi = new ArrayList<Characteristic>();
		List<Characteristic> spec = new ArrayList<Characteristic>();
		
		uslovi.add(new Characteristic(zemlja));
		uslovi.add(new Characteristic(karoserija));
		uslovi.add(new Characteristic(gorivo));
		uslovi.add(new Characteristic(menjac));
		uslovi.add(new Characteristic(oprema));
		uslovi.add(new Characteristic(sedista));
		spec.add(new Characteristic(s1));
		spec.add(new Characteristic(s2));
		Search s = new Search(uslovi,spec);
		
		s.getSpecificniUslovi().add(new Characteristic(s3));
		s = service.searchCar(s);

		return s;
	}
}
