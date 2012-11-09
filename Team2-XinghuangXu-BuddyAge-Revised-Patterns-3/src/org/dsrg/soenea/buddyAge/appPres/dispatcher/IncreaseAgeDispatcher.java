package org.dsrg.soenea.buddyAge.appPres.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

public class IncreaseAgeDispatcher extends PresentationDispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.execute();
		redirectToDispatcher(new ViewPersonDispatcher());
	}
}
