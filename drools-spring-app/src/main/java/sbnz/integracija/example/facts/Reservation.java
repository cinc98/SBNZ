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

	/*@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;*/

	private Date from;
	private Date until;
	private int status;
	private int price;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Date from, Date until, int status, int price) {
		super();
		this.from = from;
		this.until = until;
		this.status = status;
		this.price = price;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
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
