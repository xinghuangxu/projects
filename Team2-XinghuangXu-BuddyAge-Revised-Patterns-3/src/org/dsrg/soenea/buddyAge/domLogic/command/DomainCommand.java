package org.dsrg.soenea.buddyAge.domLogic.command;


import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;

public abstract class DomainCommand {

	protected Helper helper;
	
	
	public void init(Helper helper){
		this.helper=helper;
	}
	public DomainCommand(){}

	public abstract void execute() throws CommandException ;
	
	

}
