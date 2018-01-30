package com.tutorial.spring.receipe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tutorial.spring.receipe.model.enums.EDifficulty;

import lombok.Data;

/**
 * The model for the recipe object with all the jpa goodness
 * 
 * @author Bastian Bräunel
 *
 */
@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private String prepTime;
	private String cooktime;
	private String servings;
	private String source;
	private String url;
	
	@Lob
	private Byte[] image;
	@Lob
	private String directions;

	// using cascade makes it unnecessary to write a own jpa data repository for the notes
	@OneToOne(cascade = CascadeType.ALL)
	private Notes note;

	/**
	 * EnumType.STRING changes the persistence behavior of hibernate to store a
	 * actual string in the database instead of the index value. This makes it
	 * easier to change the enum on the fly, without having to take care of the
	 * index values that are already persisted. 0 1 2 -> 0 1 2 3 One, Two, Three ->
	 * One, Two, Banana, Three
	 */
	@Enumerated(value = EnumType.STRING)
	private EDifficulty difficulty;

	/**
	 * mappedBy = "recipe" is the target property of the ingredient class spelling
	 * must be accurate, because hibernate will look for the name of the variable
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	// Define the many to many relationship and the join table
	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	/**
	 * A convenience method to do the bidirectional mapping for the ingredients and
	 * the recipes
	 * 
	 * @param ingredient
	 *            the ingredient to ad
	 * @return the recipe
	 */
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}

	/**
	 * Add the note to the recipe and do the bidirectional mapping
	 * 
	 * @param note
	 *            the note to add
	 */
	public void setNotes(Notes note) {
		this.note = note;
		note.setRecipe(this);
	}
}
