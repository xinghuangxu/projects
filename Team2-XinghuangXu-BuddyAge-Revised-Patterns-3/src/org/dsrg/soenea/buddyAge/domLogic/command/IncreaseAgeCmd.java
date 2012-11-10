package org.dsrg.soenea.buddyAge.domLogic.command;


import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.dsrg.soenea.uow.UoW;

public class IncreaseAgeCmd extends DomainCommand {




	@Override
	public void execute() throws CommandException {
		long id = 0;
		Person person = null;
		try {
			id = Long.parseLong(helper.getString("id"));
			long version = Integer.parseInt(helper.getString("version"));
			person = PersonInputMapper.find(id, version);
			helper.setRequestAttribute("person", person);
			person.setAge(person.getAge() + 1);
			UoW.getCurrent().registerDirty(person);
			// DbRegistry.getDbConnection().createStatement().execute("COMMIT");
		} catch (LostUpdateException e) {
			try {
				person = PersonInputMapper.find(id);
			} catch (MapperException e1) {
				throw new CommandException(e);
			}
			helper.setRequestAttribute("person", person);
			helper.setRequestAttribute("warning", e.getMessage());
		} catch (MapperException e) {
			throw new CommandException(e);
		}
		
	}
}
