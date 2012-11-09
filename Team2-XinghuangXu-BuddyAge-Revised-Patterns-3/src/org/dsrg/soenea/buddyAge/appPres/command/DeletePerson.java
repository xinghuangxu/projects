package org.dsrg.soenea.buddyAge.appPres.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.buddyAge.domLogic.PersonOutputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.dsrg.soenea.uow.UoW;

public class DeletePerson extends FrontCommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long id = 0;
		Person person = null;
		try {
			id = Long.parseLong(request.getParameter("id"));
			long version = Integer.parseInt(request.getParameter("version"));
			person = PersonInputMapper.find(id, version);
			UoW.getCurrent().registerRemoved(person);
			request.setAttribute("people", PersonInputMapper.findAll());
			return "/WEB-INF/JSP/BrowsePeople.jsp";
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
		} catch(Exception e){
			throw new CommandException(e);
		}
	}
}
