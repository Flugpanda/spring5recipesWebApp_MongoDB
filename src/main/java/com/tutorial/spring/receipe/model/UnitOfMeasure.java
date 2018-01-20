package com.tutorial.spring.receipe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The unit of measurement for the ingredients 
 * 
 * @author Bastian Bräunel
 *
 */
@Entity
public class UnitOfMeasure {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String unitDescription;
		
	/**
	 * default constructor
	 */
	public UnitOfMeasure() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUom() {
		return unitDescription;
	}

	public void setUom(String uom) {
		this.unitDescription = uom;
	}

	@Override
	public String toString() {
		return "UnitOfMeasure [id=" + id + ", unitDescription=" + unitDescription + "]";
	}
	
}
