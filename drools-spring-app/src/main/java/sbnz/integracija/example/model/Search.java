package sbnz.integracija.example.model;

import java.util.ArrayList;
import java.util.List;

public class Search {
	private List<Characteristic> uslovi = new ArrayList<Characteristic>();
	private List<Characteristic> specificniUslovi = new ArrayList<Characteristic>();
	private List<String> opelUslovi = new ArrayList<String>();
	private List<String> citroenUslovi = new ArrayList<String>();
	private List<String> fordUslovi = new ArrayList<String>();
	private List<String> bmwUslovi = new ArrayList<String>();
	private List<String> mercedesUslovi = new ArrayList<String>();
	private List<String> volvoUslovi = new ArrayList<String>();
	private List<String> ferariUslovi = new ArrayList<String>();
	private List<String> ferariSpecUslovi = new ArrayList<String>();
	private List<String> bugatiUslovi = new ArrayList<String>();
	private List<String> bugatiSpecUslovi = new ArrayList<String>();
	private List<String> bentliUslovi = new ArrayList<String>();
	private List<String> bentliSpecUslovi = new ArrayList<String>();

	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Search(List<Characteristic> uslovi, List<Characteristic> specificniUslovi) {
		super();
		this.uslovi = uslovi;
		this.specificniUslovi = specificniUslovi;
		opelUslovi.add("Germany");
		opelUslovi.add("Small Car");
		opelUslovi.add("Petrol");
		opelUslovi.add("Manual gearbox");
		opelUslovi.add("ABS");
		opelUslovi.add("Full leather");

		citroenUslovi.add("France");
		citroenUslovi.add("Saloon");
		citroenUslovi.add("Automatic transmission");
		citroenUslovi.add("Navigation system");

		fordUslovi.add("USA");
		fordUslovi.add("SUV");
		fordUslovi.add("LPG");
		fordUslovi.add("ESP");
		fordUslovi.add("Manual gearbox");

		bmwUslovi.add("Germany");
		bmwUslovi.add("SUV");
		bmwUslovi.add("Petrol");
		bmwUslovi.add("Automatic transmission");
		bmwUslovi.add("360 camera");

		mercedesUslovi.add("Germany");
		mercedesUslovi.add("Saloon");
		mercedesUslovi.add("Petrol");
		mercedesUslovi.add("Automatic transmission");
		mercedesUslovi.add("Sport seats");

		volvoUslovi.add("Sweden");
		volvoUslovi.add("SUV");
		volvoUslovi.add("Petrol");
		volvoUslovi.add("Automatic transmission");
		volvoUslovi.add("LED headlights");

		ferariUslovi.add("Italy");
		ferariUslovi.add("Coupe");

		ferariSpecUslovi.add("Night vision assist");
		ferariSpecUslovi.add("Heated streering wheel");
		ferariSpecUslovi.add("Bi-xenon headlights");

		bugatiUslovi.add("France");
		bugatiUslovi.add("Cabriolet");

		bugatiSpecUslovi.add("Sports suspension");
		bugatiSpecUslovi.add("Hands-free");
		bugatiSpecUslovi.add("Digital cockpit");

		bentliUslovi.add("United Kingdom");
		bentliUslovi.add("Saloon");

		bentliSpecUslovi.add("Voice control");
		bentliSpecUslovi.add("ISOFIX");
		bentliSpecUslovi.add("Start-stop system");

	}

	public List<Characteristic> getUslovi() {
		return uslovi;
	}

	public void setUslovi(List<Characteristic> uslovi) {
		this.uslovi = uslovi;
	}

	public List<Characteristic> getSpecificniUslovi() {
		return specificniUslovi;
	}

	public void setSpecificniUslovi(List<Characteristic> specificniUslovi) {
		this.specificniUslovi = specificniUslovi;
	}

	public List<String> getOpelUslovi() {
		return opelUslovi;
	}

	public void setOpelUslovi(List<String> opelUslovi) {
		this.opelUslovi = opelUslovi;
	}

	public List<String> getCitroenUslovi() {
		return citroenUslovi;
	}

	public void setCitroenUslovi(List<String> citroenUslovi) {
		this.citroenUslovi = citroenUslovi;
	}

	public List<String> getFordUslovi() {
		return fordUslovi;
	}

	public void setFordUslovi(List<String> fordUslovi) {
		this.fordUslovi = fordUslovi;
	}

	public List<String> getBmwUslovi() {
		return bmwUslovi;
	}

	public void setBmwUslovi(List<String> bmwUslovi) {
		this.bmwUslovi = bmwUslovi;
	}

	public List<String> getMercedesUslovi() {
		return mercedesUslovi;
	}

	public void setMercedesUslovi(List<String> mercedesUslovi) {
		this.mercedesUslovi = mercedesUslovi;
	}

	public List<String> getVolvoUslovi() {
		return volvoUslovi;
	}

	public void setVolvoUslovi(List<String> volvoUslovi) {
		this.volvoUslovi = volvoUslovi;
	}

	public List<String> getFerariUslovi() {
		return ferariUslovi;
	}

	public void setFerariUslovi(List<String> ferariUslovi) {
		this.ferariUslovi = ferariUslovi;
	}

	public List<String> getBugatiUslovi() {
		return bugatiUslovi;
	}

	public void setBugatiUslovi(List<String> bugatiUslovi) {
		this.bugatiUslovi = bugatiUslovi;
	}

	public List<String> getBentliUslovi() {
		return bentliUslovi;
	}

	public void setBentliUslovi(List<String> bentliUslovi) {
		this.bentliUslovi = bentliUslovi;
	}

	public List<String> getFerariSpecUslovi() {
		return ferariSpecUslovi;
	}

	public void setFerariSpecUslovi(List<String> ferariSpecUslovi) {
		this.ferariSpecUslovi = ferariSpecUslovi;
	}

	public List<String> getBugatiSpecUslovi() {
		return bugatiSpecUslovi;
	}

	public void setBugatiSpecUslovi(List<String> bugatiSpecUslovi) {
		this.bugatiSpecUslovi = bugatiSpecUslovi;
	}

	public List<String> getBentliSpecUslovi() {
		return bentliSpecUslovi;
	}

	public void setBentliSpecUslovi(List<String> bentliSpecUslovi) {
		this.bentliSpecUslovi = bentliSpecUslovi;
	}

}
