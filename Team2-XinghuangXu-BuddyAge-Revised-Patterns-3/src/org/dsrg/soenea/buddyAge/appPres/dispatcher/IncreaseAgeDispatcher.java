package org.dsrg.soenea.buddyAge.appPres.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

public class IncreaseAgeDispatcher extends PresentationDispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		redirectToDispatcher(new ViewPersonDispatcher());
	}
}
