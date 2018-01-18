package com.tutorial.spring.receipe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	/**
	 * The name of the property of the other side of the many to many relationship
	 * defined at the Recipe class
	 */
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

	/**
	 * default constructor
	 */
	public Category() {
		recipes = new HashSet<>();
	}
	
	/**
	 * Create a category bean
	 * 
	 * @param description	the description of the category
	 */
	public Category(String description) {
		this.description = description;
		recipes = new HashSet<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDecription() {
		return description;
	}

	public void setDecription(String description) {
		this.description = description;
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", description=" + description + "]";
	}
	
}
