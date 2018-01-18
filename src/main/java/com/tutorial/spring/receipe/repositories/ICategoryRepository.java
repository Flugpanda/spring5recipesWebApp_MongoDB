package com.tutorial.spring.receipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.spring.receipe.model.Category;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long> {

}
