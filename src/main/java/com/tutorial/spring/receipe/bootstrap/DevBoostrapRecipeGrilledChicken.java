package com.tutorial.spring.receipe.bootstrap;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.model.Category;
import com.tutorial.spring.receipe.model.Ingredient;
import com.tutorial.spring.receipe.model.Notes;
import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.model.UnitOfMeasure;
import com.tutorial.spring.receipe.model.enums.EDifficulty;
import com.tutorial.spring.receipe.repositories.ICategoryRepository;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;
import com.tutorial.spring.receipe.repositories.IUnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Bootstrap calls to add a full recipe
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
@Component
public class DevBoostrapRecipeGrilledChicken extends DevBoostrapRecipe
		implements ApplicationListener<ContextRefreshedEvent> {

	private Recipe chickenRecipe;

	/**
	 * Constructor to get the repositories injected
	 * 
	 * @param categoryRepository
	 * @param recipeRepository
	 * @param unitRepository
	 */
	public DevBoostrapRecipeGrilledChicken(ICategoryRepository categoryRepository, IRecipseRepository recipeRepository,
			IUnitOfMeasureRepository unitRepository) {
		super(categoryRepository, recipeRepository, unitRepository);
		log.debug(this.getClass().toString() + ": loading boostrap data.");
		chickenRecipe = new Recipe();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		// Find all units from the db
		Optional<UnitOfMeasure> piece = loadUnitFromDB("Piece");
		Optional<UnitOfMeasure> tablespoon = loadUnitFromDB("Tablespoon");
		Optional<UnitOfMeasure> teaspoon = loadUnitFromDB("Teaspoon");
		Optional<UnitOfMeasure> clove = loadUnitFromDB("Clove");

		// Find and set the category from the db
		Optional<Category> categorie = loadCategoryFromDB("Mexican");
		chickenRecipe.getCategories().add(categorie.get());
		
		// Setting the basic informations
		chickenRecipe.setDescription("Spicy Grilled Chicken Tacos");
		chickenRecipe.setPrepTime("20 minutes");
		chickenRecipe.setCooktime("15 minutes");
		chickenRecipe.setDifficulty(EDifficulty.MODERATE);
		chickenRecipe.setServings("4-6");
		chickenRecipe.setSource("Simply Recipes");
		chickenRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

		// define some ingredients
		Ingredient chili = new Ingredient();
		chili.setDescription("ancho chili powder");
		chili.setAmount(new BigDecimal("2"));
		chili.setUnitOfMeas(teaspoon.get());
		chili.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(chili);

		Ingredient oregano = new Ingredient();
		oregano.setDescription("dried oregano");
		oregano.setAmount(new BigDecimal("1"));
		oregano.setUnitOfMeas(teaspoon.get());
		oregano.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(oregano);

		Ingredient onions = new Ingredient();
		onions.setDescription("cup of minced red onion or thinly sliced green onion");
		onions.setAmount(new BigDecimal("2"));
		onions.setUnitOfMeas(tablespoon.get());
		onions.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(onions);

		Ingredient sugar = new Ingredient();
		sugar.setDescription("sugar");
		sugar.setAmount(new BigDecimal("1"));
		sugar.setUnitOfMeas(teaspoon.get());
		sugar.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(sugar);

		Ingredient salt = new Ingredient();
		salt.setDescription("teaspoon salt");
		salt.setAmount(new BigDecimal("0.5"));
		salt.setUnitOfMeas(teaspoon.get());
		salt.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(salt);

		Ingredient galic = new Ingredient();
		galic.setDescription("garlic, finely chopped");
		galic.setAmount(new BigDecimal("1"));
		galic.setUnitOfMeas(clove.get());
		galic.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(galic);

		Ingredient orange = new Ingredient();
		orange.setDescription("finely grated orange zest");
		orange.setAmount(new BigDecimal("1"));
		orange.setUnitOfMeas(tablespoon.get());
		orange.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(orange);

		Ingredient orangeJuice = new Ingredient();
		orangeJuice.setDescription("fresh-squeezed orange juice");
		orangeJuice.setAmount(new BigDecimal("3"));
		orangeJuice.setUnitOfMeas(tablespoon.get());
		orangeJuice.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(orangeJuice);

		Ingredient oliveOil = new Ingredient();
		oliveOil.setDescription("olive oil");
		oliveOil.setAmount(new BigDecimal("2"));
		oliveOil.setUnitOfMeas(tablespoon.get());
		oliveOil.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(oliveOil);

		Ingredient chicken = new Ingredient();
		chicken.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
		chicken.setAmount(new BigDecimal("4"));
		chicken.setUnitOfMeas(piece.get());
		chicken.setRecipe(chickenRecipe);
		chickenRecipe.getIngredients().add(chicken);

		// adding a note to the recipe
		Notes note = new Notes();
		note.setContent("We have a family motto and it is this: Everything goes better in a tortilla.\n" + "\n"
				+ "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n"
				+ "\n"
				+ "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n"
				+ "\n" + "\n"
				+ "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n"
				+ "\n"
				+ "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"
				+ "\n"
				+ "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n"
				+ "\n"
				+ "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n"
				+ "\n"
				+ "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n"
				+ "\n"
				+ "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!\n");
		chickenRecipe.setNotes(note);
		
		chickenRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" + "\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n"
				+ "\n" + "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"
				+ "\n" + "Spicy Grilled Chicken Tacos\n" + "\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
				+ "\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
				+ "\n" + "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" + "\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");

		// persist the recipe and all its properties
		chickenRecipe = recipeRepository.save(chickenRecipe);
	}
}
