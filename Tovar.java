package test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Tovar" database table.
 * 
 */
@Entity
@Table(name="\"Tovar\"")
@NamedQuery(name="Tovar.findAll", query="SELECT t FROM Tovar t")
public class Tovar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tovara")
	private Integer idTovara;

	private Integer cost;

	private Integer count;

	@Column(name="name_tovara")
	private String nameTovara;

	public Tovar() {
	}

	public Integer getIdTovara() {
		return this.idTovara;
	}

	public void setIdTovara(Integer idTovara) {
		this.idTovara = idTovara;
	}

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNameTovara() {
		return this.nameTovara;
	}

	public void setNameTovara(String nameTovara) {
		this.nameTovara = nameTovara;
	}

}