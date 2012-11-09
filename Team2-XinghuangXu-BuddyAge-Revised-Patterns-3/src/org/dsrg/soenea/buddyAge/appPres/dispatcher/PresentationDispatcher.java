package org.dsrg.soenea.buddyAge.appPres.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;

public abstract class PresentationDispatcher extends Dispatcher {
	
	protected DomainCommand getDomainCommand()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException 
	{
		String command = myHelper.getString("command");
		if (command == null || command.isEmpty())
			command = "ViewPerson";
		String fullyQualifiedCommand = "org.dsrg.soenea.buddyAge.appPres.command." + command+"Command";
		return (DomainCommand) Class.forName(fullyQualifiedCommand).newInstance();
	}
	
	@Override
	public void execute() throws ServletException, IOException {
		
		try {
			DomainCommand domainCommand=getDomainCommand();
			domainCommand.execute();
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
