package com.tutorial.spring.receipe.bootstrap;

import java.util.Optional;

import com.tutorial.spring.receipe.model.Category;
import com.tutorial.spring.receipe.model.UnitOfMeasure;
import com.tutorial.spring.receipe.repositories.ICategoryRepository;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;
import com.tutorial.spring.receipe.repositories.IUnitOfMeasureRepository;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class DevBoostrapRecipe {
	
	protected ICategoryRepository categoryRepository;
	protected IRecipseRepository recipeRepository;
	protected IUnitOfMeasureRepository unitRepository;
	
	/**
	 * 
	 * @param categoryRepository
	 * @param recipeRepository
	 * @param unitRepository
	 */
	public DevBoostrapRecipe(ICategoryRepository categoryRepository, IRecipseRepository recipeRepository,
			IUnitOfMeasureRepository unitRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitRepository = unitRepository;
	}

	/**
	 * This method will handle the loading of the units
	 * 
	 * @param description	the unit with this description
	 * @return				the object of the unit
	 * @throws Exception	throws exception whenever the unit with the given description could not be found 
	 */
	protected Optional<UnitOfMeasure> loadUnitFromDB(String description) throws IllegalArgumentException{
		Optional<UnitOfMeasure> tmp = unitRepository.findByUnitDescription(description);
		return tmp;
	}
	
	/**
	 * This method will handle the loading of the units
	 * 
	 * @param description	the unit with this description
	 * @return				the object of the unit
	 * @throws Exception	throws exception whenever the unit with the given description could not be found 
	 */
	protected Optional<Category> loadCategoryFromDB(String description) throws IllegalArgumentException{
		Optional<Category> tmp = categoryRepository.findByDescription(description);
		return tmp;
	}
	
}
