package org.dsrg.soenea.buddyAge.appPres.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.buddyAge.domLogic.command.DomainCommandFactory;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;

public abstract class PresentationDispatcher extends Dispatcher {
	
	protected DomainCommand getDomainCommand()
			throws Exception
	{
		String command = myHelper.getString("command");
		if (command == null || command.isEmpty())
			command = "ViewPerson";
		String fullyQualifiedCommand = "org.dsrg.soenea.buddyAge.domLogic.command." + command+"Cmd";
		return DomainCommandFactory.getInstance(fullyQualifiedCommand);
	}
	
	@Override
	public void execute() throws ServletException, IOException {
		try {
			DomainCommand domainCommand=getDomainCommand();
			domainCommand.execute();
		} catch (CommandException e) {
			throw new ServletException(e);
		}  catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
