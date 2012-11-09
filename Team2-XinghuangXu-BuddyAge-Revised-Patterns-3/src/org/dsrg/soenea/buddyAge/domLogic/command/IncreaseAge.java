package org.dsrg.soenea.buddyAge.domLogic.command;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.buddyAge.domLogic.PersonOutputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.dsrg.soenea.uow.UoW;

public class IncreaseAge extends DomainCommand {

	public IncreaseAge(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long id = 0;
		Person person = null;
		try {
			id = Long.parseLong(request.getParameter("id"));
			long version = Integer.parseInt(request.getParameter("version"));
			person = PersonInputMapper.find(id, version);
			request.setAttribute("person", person);
			person.setAge(person.getAge() + 1);
			UoW.getCurrent().registerDirty(person);
			// DbRegistry.getDbConnection().createStatement().execute("COMMIT");
			return "/WEB-INF/JSP/ViewPerson.jsp";
		} catch (LostUpdateException e) {
			try {
				person = PersonInputMapper.find(id);
			} catch (MapperException e1) {
				throw new CommandException(e);
			}
			request.setAttribute("person", person);
			request.setAttribute("warning", e.getMessage());
			return "/WEB-INF/JSP/ViewPerson.jsp";
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}
}
