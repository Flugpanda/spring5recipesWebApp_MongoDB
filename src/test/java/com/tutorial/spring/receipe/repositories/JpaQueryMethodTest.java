package com.tutorial.spring.receipe.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tutorial.spring.receipe.model.Category;
import com.tutorial.spring.receipe.model.UnitOfMeasure;

/**
 * A simple test to try out the jpa query methods
 * 
 * @author Bastian Bräunel
 * 
 * @see https://stackoverflow.com/questions/23435937/how-to-test-spring-data-repositories
 * @SpringBootTest to load the ApplicationContext
 * @RunWith(SpringRunner.class) so the spring-specific annotations are picked up
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaQueryMethodTest {

	@Autowired
	public ICategoryRepository categoryRepository;
	@Autowired
	public IUnitOfMeasureRepository unitOfMeasureRepository;

	private Optional<UnitOfMeasure> mesurementUnit;
	private Optional<Category> category;

	@Test
	public void test() {
		
		category = categoryRepository.findByDescription("German");
		mesurementUnit = unitOfMeasureRepository.findByUnitDescription("Gram");

		assertNotNull("The category was not found.", category);
		assertNotNull("The mesurement unit was not found to.", mesurementUnit);

		System.out.println(category.toString());
		System.out.println(mesurementUnit.toString());
	}

}
