package sbnz.integracija.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Car implements Serializable {
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
<<<<<<< HEAD
	private String image;
=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/model/Car.java
	private String image;
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/sbnz/integracija/example/facts/Car.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca

	@ManyToMany(mappedBy = "cars")
	Set<User> users = new HashSet<User>();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/model/Car.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

<<<<<<< HEAD
=======
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/sbnz/integracija/example/facts/Car.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", model=" + model + ", km=" + km + ", date=" + date + ", price="
				+ price + ", number=" + number + ", searched=" + searched + ", category=" + category + "]";
	}

}
