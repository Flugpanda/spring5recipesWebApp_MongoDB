package com.tutorial.spring.receipe.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * The ingredients that are used in a recipe
 * 
 * @author Bastian Bräunel
 *
 */
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
	
	/**
	 * default constructor
	 */
	public Ingredient() {
	}
	
	/**
	 * Constructor to create a new Ingredient bean
	 * 
	 * @param decription		the name of the ingredient
	 * @param amount			the amount of the ingredient
	 * @param unitOfMeas		the measurement unit of the amount
	 */
	public Ingredient(String decription, BigDecimal amount, UnitOfMeasure unitOfMeas) {
		super();
		this.description = decription;
		this.amount = amount;
		this.unitOfMeas = unitOfMeas;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String decription) {
		this.description = decription;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public UnitOfMeasure getUnitOfMeas() {
		return unitOfMeas;
	}

	public void setUnitOfMeas(UnitOfMeasure unitOfMeas) {
		this.unitOfMeas = unitOfMeas;
	}

	@Override
	public String toString() {
		return "Ingedient [id=" + id + ", decription=" + description + ", amount=" + amount + "]";
	}
}
