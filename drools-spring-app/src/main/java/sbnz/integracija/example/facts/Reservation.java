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
	private int price;
	

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", user=" + user + ", car=" + car + ", fromDate=" + fromDate + ", untilDate="
				+ untilDate + ", status=" + status + ", price=" + price + "]";
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Date from, Date until, int status, int price) {
		super();
		this.fromDate = from;
		this.untilDate = until;
		this.status = status;
		this.price = price;
	}

	public Date getFrom() {
		return fromDate;
	}

	public void setFrom(Date from) {
		this.fromDate = from;
	}

	public Date getUntil() {
		return untilDate;
	}

	public void setUntil(Date until) {
		this.untilDate = until;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
