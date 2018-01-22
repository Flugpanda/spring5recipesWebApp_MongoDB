package com.tutorial.spring.receipe.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.service.RecipeService;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class RecipeControllerTest {

	private String expectedResult = "recipes";
	private String expectedAttributeName = "allRecipes";
	private RecipeController recipeController;

	@Mock
	private RecipeService service;
	@Mock
	private Model model;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(service);
	}

	/**
	 * behavior driven test utilizing an ArgumentCaptor
	 */
	@Test
	public void test() {
		// given
		Set<Recipe> recipes = new HashSet<>();
		Recipe recipeOne = new Recipe();
		Recipe recipeTwo = new Recipe();

		recipeOne.setId(1234l);
		recipeTwo.setId(9876l);

		recipes.add(recipeOne);
		recipes.add(recipeTwo);

		// configure Mockito to return the defined set when the method getRecipes is
		// called
		when(service.getRecipes()).thenReturn(recipes);
		// capture argument values for further assertions
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		// when
		String result = recipeController.findAllRecipies(model);

		// then
		assertEquals(expectedResult, result);
		// verify that the method addAttribute was called once and
		// capture the value the the method returns
		verify(model, times(1)).addAttribute(eq(expectedAttributeName), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(setInController, service.getRecipes());
	}
}
