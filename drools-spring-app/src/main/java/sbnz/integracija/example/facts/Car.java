package sbnz.integracija.example.facts;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Car implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String model;
	private int km;
	private Date date;
	private int price;
	private int number;
	private int searched;
	private String category;
	

	
	public Car(String name, String model, int km, Date date, int price, String category) {
		super();
		this.name = name;
		this.model = model;
		this.km = km;
		this.date = date;
		this.price = price;
		this.category = category;
	}
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSearched() {
		return searched;
	}
	public void setSearched(int searched) {
		this.searched = searched;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}
