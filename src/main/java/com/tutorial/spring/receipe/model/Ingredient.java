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
	
	private String decription;
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
		this.decription = decription;
		this.amount = amount;
		this.unitOfMeas = unitOfMeas;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
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

	@Override
	public String toString() {
		return "Ingedient [id=" + id + ", decription=" + decription + ", amount=" + amount + "]";
	}
}
