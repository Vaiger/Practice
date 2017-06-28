package test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Order" database table.
 * 
 */
@Entity
@Table(name="\"Order\"")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pokupatel")
	private Integer idPokupatel;

	private Integer count2;

	@Column(name="id_tovara")
	private Integer idTovara;

	@Column(name="id_zakaza")
	private Integer idZakaza;

	private Boolean status;

	@Column(name="sum_zakaza")
	private Integer sumZakaza;

	public Order() {
	}

	public Integer getIdPokupatel() {
		return this.idPokupatel;
	}

	public void setIdPokupatel(Integer idPokupatel) {
		this.idPokupatel = idPokupatel;
	}

	public Integer getCount2() {
		return this.count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public Integer getIdTovara() {
		return this.idTovara;
	}

	public void setIdTovara(Integer idTovara) {
		this.idTovara = idTovara;
	}

	public Integer getIdZakaza() {
		return this.idZakaza;
	}

	public void setIdZakaza(Integer idZakaza) {
		this.idZakaza = idZakaza;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getSumZakaza() {
		return this.sumZakaza;
	}

	public void setSumZakaza(Integer sumZakaza) {
		this.sumZakaza = sumZakaza;
	}

}