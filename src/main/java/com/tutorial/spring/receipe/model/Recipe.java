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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tutorial.spring.receipe.model.enums.EDifficulty;

/**
 * The model for the recipe object with all the jpa goodness
 * 
 * @author Bastian Bräunel
 *
 */
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
	private Set<Ingredient> ingredients;

	// Define the many to many relationship and the join table
	@ManyToMany
	@JoinTable(name = "recipe_category", 
				joinColumns = @JoinColumn(name = "recipe_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	/**
	 * default constructor
	 */
	public Recipe() {
		ingredients = new HashSet<>();
	}

	/**
	 * Constructor for a new recipe bean
	 * 
	 * @param description
	 *            the short description of the recipe
	 * @param prepTime
	 *            the preparation time
	 * @param cooktime
	 *            the actual cooking time
	 * @param servings
	 *            the amount of servings you will get by using the amount of
	 *            ingredients
	 * @param source
	 *            the source of the original recipe
	 * @param url
	 *            the url where the recipe can be found online
	 * @param directions
	 *            the cooking instructions
	 * @param difficulty
	 *            the difficulty level of the recipe
	 */
	public Recipe(String description, String prepTime, String cooktime, String servings, String source, String url,
			String directions, EDifficulty difficulty) {
		this.description = description;
		this.prepTime = prepTime;
		this.cooktime = cooktime;
		this.servings = servings;
		this.source = source;
		this.url = url;
		this.directions = directions;
		this.difficulty = difficulty;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCooktime() {
		return cooktime;
	}

	public void setCooktime(String cooktime) {
		this.cooktime = cooktime;
	}

	public String getServings() {
		return servings;
	}

	public void setServings(String servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public EDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(EDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", description=" + description + ", prepTime=" + prepTime + ", cooktime=" + cooktime
				+ ", servings=" + servings + ", source=" + source + ", url=" + url + ", directions=" + directions
				+ ", note=" + note + "]";
	}
}
