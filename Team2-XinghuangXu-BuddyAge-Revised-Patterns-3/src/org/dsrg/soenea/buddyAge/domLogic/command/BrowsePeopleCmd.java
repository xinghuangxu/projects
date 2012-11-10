package org.dsrg.soenea.buddyAge.domLogic.command;


import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;


public class BrowsePeopleCmd extends DomainCommand {
	
	
	//public BrowsePeopleCmd(){}
	

	@Override
	public void execute() throws CommandException {
		try {
			helper.setRequestAttribute("people", PersonInputMapper.findAll());
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}
}
