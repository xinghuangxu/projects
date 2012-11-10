package org.dsrg.soenea.buddyAge.domLogic.command;


import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;

public class ViewPersonCmd extends DomainCommand {


	@Override
	public void execute() throws CommandException {
		Person person;
		try {
			long id = Long.parseLong(helper.getString("id"));
			person = PersonInputMapper.find(id);
			helper.setRequestAttribute("person", person);	
		} catch (MapperException e) {
			throw new CommandException(e);
		}
		
	}

}
