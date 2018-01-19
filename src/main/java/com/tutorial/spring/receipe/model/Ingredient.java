package com.tutorial.spring.receipe.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * The ingredients that are used in a recipe
 * This class uses project lombock.
 * 
 * @author Bastian Bräunel
 *
 */
@Data
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;
	
	// FetchType.EAGER will tell hibernate to load the object every time from the database 
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeas;
	
	@ManyToOne
	private Recipe recipe;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (unitOfMeas == null) {
			if (other.unitOfMeas != null)
				return false;
		} else if (!unitOfMeas.equals(other.unitOfMeas))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((unitOfMeas == null) ? 0 : unitOfMeas.hashCode());
		return result;
	}

	
}
