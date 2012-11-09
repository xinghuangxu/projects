package org.dsrg.soenea.buddyAge.domLogic.command;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;

public class ViewPerson extends DomainCommand {

	public ViewPerson(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			Person person = PersonInputMapper.find(id);
			request.setAttribute("person", person);
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
