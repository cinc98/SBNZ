package sbnz.integracija.example.model;

import java.util.ArrayList;
import java.util.List;

public class Search {
	private List<Characteristic> uslovi = new ArrayList<Characteristic>();
	private List<Characteristic> specificniUslovi = new ArrayList<Characteristic>();
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
	
	
	
}
