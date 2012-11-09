package org.dsrg.soenea.buddyAge.appPres.command;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;

public class ViewPerson extends FrontCommand {

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

}
