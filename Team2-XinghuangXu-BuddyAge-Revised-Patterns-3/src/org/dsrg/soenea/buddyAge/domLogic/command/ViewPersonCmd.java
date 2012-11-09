package org.dsrg.soenea.buddyAge.domLogic.command;


import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;

public class ViewPersonCmd extends DomainCommand {

	public ViewPersonCmd(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		Person person;
		try {
			long id = Long.parseLong(helper.getString("id"));
			person = PersonInputMapper.find(id);
			helper.setRequestAttribute("person", person);	
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}

}
