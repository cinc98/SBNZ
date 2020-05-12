package sbnz.integracija.example.facts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@SuppressWarnings("serial")
@Entity
public class Reservation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date untilDate;
	
	private int status;
	private double price;
	

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", user=" + user + ", car=" + car + ", fromDate=" + fromDate + ", untilDate="
				+ untilDate + ", status=" + status + ", price=" + price + "]";
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Date fromDate, Date untilDate, int status, int price) {
		super();
		this.fromDate = fromDate;
		this.untilDate = untilDate;
		this.status = status;
		this.price = price;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
