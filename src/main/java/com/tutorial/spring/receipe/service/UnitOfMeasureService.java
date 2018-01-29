package com.tutorial.spring.receipe.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tutorial.spring.receipe.commands.UnitOfMeasureCommand;
import com.tutorial.spring.receipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.tutorial.spring.receipe.repositories.IUnitOfMeasureRepository;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Service
public class UnitOfMeasureService implements IUnitOfMeasureService {

	private IUnitOfMeasureRepository unitRepository;
	private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	

	public UnitOfMeasureService(IUnitOfMeasureRepository unitRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.unitRepository = unitRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	/**
	 * Load all {@link UnitOfMeasure} from the database and return a set of detached UnitOfMeasureCommand objects
	 */
	@Override
	public Set<UnitOfMeasureCommand> findAllUnits() {
		Set<UnitOfMeasureCommand> commands = new HashSet<>();
		
		unitRepository.findAll().forEach(element -> commands.add(unitOfMeasureToUnitOfMeasureCommand.convert(element)));
		
		return commands;
	}

}
