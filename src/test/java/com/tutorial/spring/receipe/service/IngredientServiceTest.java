package com.tutorial.spring.receipe.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tutorial.spring.receipe.commands.IngredientsCommand;
import com.tutorial.spring.receipe.converters.IngredientsCommandToIngredients;
import com.tutorial.spring.receipe.converters.IngredientsToIngredientsCommand;
import com.tutorial.spring.receipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.tutorial.spring.receipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.tutorial.spring.receipe.model.Ingredient;
import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.model.UnitOfMeasure;
import com.tutorial.spring.receipe.repositories.IIngredientReoisitory;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;
import com.tutorial.spring.receipe.repositories.IUnitOfMeasureRepository;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class IngredientServiceTest {

	private IngredientService ingredientService;
	private Recipe mockRecipe;
	private HashSet<Ingredient> mockIngredients;
	
	private Long ingredientOneId = 10l;
	private Long ingredientTwoId = 11l;
	private Long mockedRecipeId = 40l;
	
	@Mock
	private IRecipseRepository recipeRepository;
	@Mock
	private IUnitOfMeasureRepository unitOfMeasureRepository;
	@Mock
	private IIngredientReoisitory ingredientReoisitory;
	@Mock
	private IngredientsToIngredientsCommand ingredientsToCommand;
	@Mock
	private IngredientsCommandToIngredients commandToIngredients;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		ingredientsToCommand = new IngredientsToIngredientsCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		commandToIngredients = new IngredientsCommandToIngredients(new UnitOfMeasureCommandToUnitOfMeasure());
		
		ingredientService = new IngredientService(recipeRepository, unitOfMeasureRepository, ingredientsToCommand,ingredientReoisitory, commandToIngredients);
		
		UnitOfMeasure teaspoon = new UnitOfMeasure();
		UnitOfMeasure dash = new UnitOfMeasure();
		
		teaspoon.setId(20l);
		teaspoon.setUom("Teaspoon");
		
		dash.setId(22l);;
		dash.setUom("Dash");
		
		mockRecipe = new Recipe();
		mockRecipe.setId(40l);
		mockRecipe.setDescription("Salt and Pepper");
						
		Ingredient ingredientOne = new Ingredient();
		ingredientOne.setId(ingredientOneId);
		ingredientOne.setDescription("Salt");
		ingredientOne.setUnitOfMeas(dash);
		ingredientOne.setAmount(new BigDecimal(2));
		ingredientOne.setRecipe(mockRecipe);
		
		Ingredient ingredientTwo = new Ingredient();
		ingredientTwo.setId(ingredientTwoId);
		ingredientTwo.setDescription("Pepper");
		ingredientTwo.setUnitOfMeas(teaspoon);
		ingredientTwo.setAmount(new BigDecimal(1));
		ingredientTwo.setRecipe(mockRecipe);
		
		mockIngredients = new HashSet<>();
		mockIngredients.add(ingredientOne);
		mockIngredients.add(ingredientTwo);
		
		mockRecipe.setIngredients(mockIngredients);
		
		when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRecipe));
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {
		
		IngredientsCommand command = ingredientService.findByRecipeIdAndIngredientId(mockedRecipeId, ingredientOneId);
		
		assertNotNull(command);
		assertEquals(ingredientOneId, command.getId());
		assertEquals(mockedRecipeId, command.getRecipeId());
		verify(recipeRepository, times(1)).findById(Mockito.anyLong());
	}
}
