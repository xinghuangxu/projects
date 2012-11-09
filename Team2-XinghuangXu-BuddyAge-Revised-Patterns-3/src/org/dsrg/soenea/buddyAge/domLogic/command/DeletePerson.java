package org.dsrg.soenea.buddyAge.domLogic.command;

import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;

public class DeletePerson extends DomainCommand {

	public DeletePerson(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		long id =0;
		Person person=null;
		id = Long.parseLong(helper.getString("id"));
		long version = Integer.parseInt(helper.getString("version"));
		try {
			person = PersonInputMapper.find(id, version);
			UoW.getCurrent().registerRemoved(person);
			helper.setRequestAttribute("people", PersonInputMapper.findAll());
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}
}
