package com.tutorial.spring.receipe.service;

import java.util.Set;

import com.tutorial.spring.receipe.commands.UnitOfMeasureCommand;

public interface IUnitOfMeasureService {

	public Set<UnitOfMeasureCommand> findAllUnits();
}
