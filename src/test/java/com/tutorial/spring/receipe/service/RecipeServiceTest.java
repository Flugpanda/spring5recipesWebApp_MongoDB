package com.tutorial.spring.receipe.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

/**
 * A unit test for the recipe service using Mockito
 * 
 * @author Bastian Bräunel
 *
 */
public class RecipeServiceTest {

	private RecipeService recipeService;
	private HashSet<Recipe> mockedRecipes;
	private Recipe mockRecipe;
	
	@Mock
	private IRecipseRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception {
		// tell Mockito to mock all annotated objects in this class/test
		MockitoAnnotations.initMocks(this);
		
		// creating a new RecipeService and using the mock of the repository
		recipeService = new RecipeService(recipeRepository);
				
		// create a recipe object that shall be used by Mockito
		mockRecipe = new Recipe();
		mockRecipe.setId(1234l);
		mockRecipe.setDescription("My mocked recipe");
		
		// create a set the and add the recipe
		mockedRecipes = new HashSet<>();
		mockedRecipes.add(mockRecipe);
		
		Optional<Recipe> optionalRecipe = Optional.of(mockRecipe);
		
		// configure Mockito to return the mocked set whenever the method getRecipes is called on the recipeService
		when(recipeService.getRecipes()).thenReturn(mockedRecipes);	
		when(recipeRepository.findById(Mockito.anyLong())).thenReturn(optionalRecipe);
	}

	@Test
	public void getRecipes() throws Exception {
		// run the method to test
		Set<Recipe> foundRecipes = recipeService.getRecipes();
		
		assertEquals(mockedRecipes.size(), foundRecipes.size());
		assertTrue(foundRecipes.contains(mockRecipe));
		
		// make sure that the method recipeRepository.findAll was called exactly once and only once
		// Verify the interaction with the mocked object, so that we make sure the recipeRepository was called like this
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void getById() throws Exception {
		Long idToFind = new Long(1234l);
		Recipe foundRecipe = recipeService.findById(idToFind);
		
		// assert a correct object
		foundRecipe = recipeService.findById(idToFind);
		assertEquals(foundRecipe.getId(), idToFind);
	}
}
