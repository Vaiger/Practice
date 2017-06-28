package test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Buyer" database table.
 * 
 */
@Entity
@Table(name="\"Buyer\"")
@NamedQuery(name="Buyer.findAll", query="SELECT b FROM Buyer b")
public class Buyer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pokupatel")
	private Integer idPokupatel;

	private String fio;

	@Column(name="fiz_ur")
	private String fizUr;

	private float skidka;

	public Buyer() {
	}

	public Integer getIdPokupatel() {
		return this.idPokupatel;
	}

	public void setIdPokupatel(Integer idPokupatel) {
		this.idPokupatel = idPokupatel;
	}

	public String getFio() {
		return this.fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getFizUr() {
		return this.fizUr;
	}

	public void setFizUr(String fizUr) {
		this.fizUr = fizUr;
	}

	public float getSkidka() {
		return this.skidka;
	}

	public void setSkidka(float skidka) {
		this.skidka = skidka;
	}

}