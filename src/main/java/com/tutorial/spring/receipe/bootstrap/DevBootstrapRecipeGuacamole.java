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
public class DevBootstrapRecipeGuacamole implements ApplicationListener<ContextRefreshedEvent> {

	private ICategoryRepository categoryRepository;
	private IRecipseRepository recipeRepository;
	private IUnitOfMeasureRepository unitRepository;
	private Recipe guacamoleRecipe;

	/**
	 * 
	 * @param categoryRepository
	 * @param recipeRepository
	 * @param unitRepository
	 */
	public DevBootstrapRecipeGuacamole(ICategoryRepository categoryRepository, IRecipseRepository recipeRepository,
			IUnitOfMeasureRepository unitRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitRepository = unitRepository;

		guacamoleRecipe = new Recipe();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Setting the basic informations
		guacamoleRecipe.setDescription("Perfect Guacamole");
		guacamoleRecipe.setPrepTime("0 minutes");
		guacamoleRecipe.setCooktime("10 minutes");
		guacamoleRecipe.setDifficulty(EDifficulty.EASY);
		guacamoleRecipe.setServings("2-4");
		guacamoleRecipe.setSource("Simply Recipes");
		guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");

		// Find and set the category from the db
		Optional<Category> categorie = categoryRepository.findByDescription("Mexican");
		guacamoleRecipe.getCategories().add(categorie.get());

		// define some ingredients
		Ingredient avocados = new Ingredient();
		avocados.setDescription("ripe avocados");
		avocados.setAmount(new BigDecimal("2.00"));
		avocados.setUnitOfMeas(unitRepository.findByUnitDescription("Piece").get());
		avocados.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(avocados);

		Ingredient salt = new Ingredient();
		salt.setDescription("Kosher salt");
		salt.setAmount(new BigDecimal("0.5"));
		salt.setUnitOfMeas(unitRepository.findByUnitDescription("Teaspoon").get());
		salt.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(salt);

		Ingredient limeJuice = new Ingredient();
		limeJuice.setDescription("lime juice");
		limeJuice.setAmount(new BigDecimal("1"));
		limeJuice.setUnitOfMeas(unitRepository.findByUnitDescription("Tablespoon").get());
		limeJuice.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(limeJuice);

		Ingredient onions = new Ingredient();
		onions.setDescription("cup of minced red onion or thinly sliced green onion");
		onions.setAmount(new BigDecimal("2"));
		onions.setUnitOfMeas(unitRepository.findByUnitDescription("Tablespoon").get());
		onions.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(onions);

		Ingredient serranoChiles = new Ingredient();
		serranoChiles.setDescription("serrano chiles, stems and seeds removed, minced");
		serranoChiles.setAmount(new BigDecimal("2"));
		serranoChiles.setUnitOfMeas(unitRepository.findByUnitDescription("Piece").get());
		serranoChiles.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(serranoChiles);

		Ingredient cilantro = new Ingredient();
		cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
		cilantro.setAmount(new BigDecimal("2"));
		cilantro.setUnitOfMeas(unitRepository.findByUnitDescription("Tablespoon").get());
		cilantro.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(cilantro);

		Ingredient pepper = new Ingredient();
		pepper.setDescription("of freshly grated black pepper");
		pepper.setAmount(new BigDecimal("1"));
		pepper.setUnitOfMeas(unitRepository.findByUnitDescription("Dash").get());
		pepper.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(pepper);

		Ingredient tomato = new Ingredient();
		tomato.setDescription("tomato, seeds and pulp removed, chopped");
		tomato.setAmount(new BigDecimal("2"));
		tomato.setUnitOfMeas(unitRepository.findByUnitDescription("Piece").get());
		tomato.setRecipe(guacamoleRecipe);
		guacamoleRecipe.getIngredients().add(tomato);

		// adding a note to the recipe
		Notes note = new Notes();
		note.setRecipe(guacamoleRecipe);
		guacamoleRecipe.setNote(note);
		note.setContent(
				"Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n"
						+ "\n"
						+ "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity to balance the richness of the avocado. Then comes chopped cilantro, chiles, onion, and tomato, if you want.\n"
						+ "\n"
						+ "The trick to making perfect guacamole is using good, ripe avocados. Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n");

		// persist the recipe and all its properties
		guacamoleRecipe = recipeRepository.save(guacamoleRecipe);
	}
}
