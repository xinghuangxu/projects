package org.dsrg.soenea.buddyAge.appPres.command;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.domain.command.CommandException;

public abstract class FrontCommand {
	public abstract String execute(HttpServletRequest req) throws CommandException;
}
