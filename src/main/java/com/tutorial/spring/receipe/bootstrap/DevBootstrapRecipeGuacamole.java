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

/**
 * Bootstrap calls to add a full recipe
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class DevBootstrapRecipeGuacamole extends DevBoostrapRecipe  implements ApplicationListener<ContextRefreshedEvent> {

	private Recipe guacamoleRecipe;

	public DevBootstrapRecipeGuacamole(ICategoryRepository categoryRepository, IRecipseRepository recipeRepository,
			IUnitOfMeasureRepository unitRepository) {

		super(categoryRepository, recipeRepository, unitRepository);
		guacamoleRecipe = new Recipe();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		// Find all units from the db
		Optional<UnitOfMeasure> piece = loadUnitFromDB("Piece");
		Optional<UnitOfMeasure> tablespoon = loadUnitFromDB("Tablespoon");
		Optional<UnitOfMeasure> teaspoon = loadUnitFromDB("Teaspoon");
		Optional<UnitOfMeasure> dash = loadUnitFromDB("Dash");

		// Find and set the category from the db
		Optional<Category> categorie = loadCategoryFromDB("Mexican");
		guacamoleRecipe.getCategories().add(categorie.get());
		
		// Setting the basic informations
		guacamoleRecipe.setDescription("Perfect Guacamole");
		guacamoleRecipe.setPrepTime("0 minutes");
		guacamoleRecipe.setCooktime("10 minutes");
		guacamoleRecipe.setDifficulty(EDifficulty.EASY);
		guacamoleRecipe.setServings("2-4");
		guacamoleRecipe.setSource("Simply Recipes");
		guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");

		// define some ingredients
		Ingredient avocados = new Ingredient();
		avocados.setDescription("ripe avocados");
		avocados.setAmount(new BigDecimal("2.00"));
		avocados.setUnitOfMeas(piece.get());
		avocados.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(avocados);

		Ingredient salt = new Ingredient();
		salt.setDescription("Kosher salt");
		salt.setAmount(new BigDecimal("0.5"));
		salt.setUnitOfMeas(tablespoon.get());
		salt.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(salt);

		Ingredient limeJuice = new Ingredient();
		limeJuice.setDescription("lime juice");
		limeJuice.setAmount(new BigDecimal("1"));
		limeJuice.setUnitOfMeas(tablespoon.get());
		limeJuice.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(limeJuice);

		Ingredient onions = new Ingredient();
		onions.setDescription("cup of minced red onion or thinly sliced green onion");
		onions.setAmount(new BigDecimal("2"));
		onions.setUnitOfMeas(tablespoon.get());
		onions.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(onions);

		Ingredient serranoChiles = new Ingredient();
		serranoChiles.setDescription("serrano chiles, stems and seeds removed, minced");
		serranoChiles.setAmount(new BigDecimal("2"));
		serranoChiles.setUnitOfMeas(piece.get());
		serranoChiles.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(serranoChiles);

		Ingredient cilantro = new Ingredient();
		cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
		cilantro.setAmount(new BigDecimal("2"));
		cilantro.setUnitOfMeas(tablespoon.get());
		cilantro.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(cilantro);

		Ingredient pepper = new Ingredient();
		pepper.setDescription("of freshly grated black pepper");
		pepper.setAmount(new BigDecimal("1"));
		pepper.setUnitOfMeas(dash.get());
		pepper.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(pepper);

		Ingredient tomato = new Ingredient();
		tomato.setDescription("tomato, seeds and pulp removed, chopped");
		tomato.setAmount(new BigDecimal("2"));
		tomato.setUnitOfMeas(piece.get());
		tomato.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(tomato);

		// adding a note to the recipe
		Notes note = new Notes();
		note.setContent(
				"Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n"
						+ "\n"
						+ "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity to balance the richness of the avocado. Then comes chopped cilantro, chiles, onion, and tomato, if you want.\n"
						+ "\n"
						+ "The trick to making perfect guacamole is using good, ripe avocados. Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n");
		guacamoleRecipe.setNote(note);
		
		// adding the directs of coocking
		guacamoleRecipe.setDirections(
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
						+ "\n" + "\n" + "\n"
						+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"
						+ "\n" + "\n" + "\n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
						+ "\n"
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
						+ "\n"
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
						+ "\n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
						+ "\n"
						+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n"
						+ "\n" + "Variations\n" + "\n"
						+ "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"
						+ "\n"
						+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n"
						+ "\n"
						+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
						+ "\n"
						+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n"
						+ "\n" + "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n" + "\n"
						);

		// persist the recipe and all its properties
		guacamoleRecipe = recipeRepository.save(guacamoleRecipe);
	}
}
