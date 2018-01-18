package com.tutorial.spring.receipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.spring.receipe.model.UnitOfMeasure;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Repository
public interface IUnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

}
