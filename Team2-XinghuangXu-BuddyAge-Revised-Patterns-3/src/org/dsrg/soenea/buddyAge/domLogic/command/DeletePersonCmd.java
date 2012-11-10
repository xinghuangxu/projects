package org.dsrg.soenea.buddyAge.domLogic.command;

import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;

public class DeletePersonCmd extends DomainCommand {



	@Override
	public void execute() throws CommandException {
		long id =0;
		Person person=null;
		id = Long.parseLong(helper.getString("id"));
		long version = Integer.parseInt(helper.getString("version"));
		try {
			person = PersonInputMapper.find(id, version);
			UoW.getCurrent().registerRemoved(person);
			helper.setRequestAttribute("people", PersonInputMapper.findAll());
		} catch (MapperException e) {
			throw new CommandException(e);
		}
		
	}
}
