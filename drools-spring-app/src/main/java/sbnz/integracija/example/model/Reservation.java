package sbnz.integracija.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Reservation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String status;
	private int discount;
	private int penaltyPercentage;
	private double price;
<<<<<<< HEAD
=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/model/Reservation.java
=======
	
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/sbnz/integracija/example/facts/Reservation.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date untilDate;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Date fromDate, Date untilDate, String status, int price) {
		super();
		this.fromDate = fromDate;
		this.untilDate = untilDate;
		this.status = status;
		this.price = price;
	}

	public String getStatus() {
		return status;
	}
<<<<<<< HEAD
=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/model/Reservation.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
	
	public Car getCarObject() {
		return this.car;
	}
<<<<<<< HEAD
=======
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/sbnz/integracija/example/facts/Reservation.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date from) {
		this.fromDate = from;
	}

	public Date getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(Date until) {
		this.untilDate = until;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUser() {
		return user.getId();
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/model/Reservation.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
	public String getUserame() {
		return user.getUsername();
	}

<<<<<<< HEAD
=======
=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/sbnz/integracija/example/facts/Reservation.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
	public void setUser(User user) {
		this.user = user;
	}

	public int getCar() {
		return car.getId();
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPenaltyPercentage() {
		return penaltyPercentage;
	}

	public void setPenaltyPercentage(int penaltyPercentage) {
		this.penaltyPercentage = penaltyPercentage;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", user=" + user + ", car=" + car + ", fromDate=" + fromDate + ", untilDate="
				+ untilDate + ", status=" + status + ", price=" + price + "]";
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/model/Reservation.java
=======

>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/sbnz/integracija/example/facts/Reservation.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
}
