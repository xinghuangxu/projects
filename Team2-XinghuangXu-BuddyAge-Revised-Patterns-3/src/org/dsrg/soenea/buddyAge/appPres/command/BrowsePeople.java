package org.dsrg.soenea.buddyAge.appPres.command;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.buddyAge.domLogic.PersonInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;

public class BrowsePeople extends FrontCommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try {
			request.setAttribute("people", PersonInputMapper.findAll());
			return "/WEB-INF/JSP/BrowsePeople.jsp";
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}
}
