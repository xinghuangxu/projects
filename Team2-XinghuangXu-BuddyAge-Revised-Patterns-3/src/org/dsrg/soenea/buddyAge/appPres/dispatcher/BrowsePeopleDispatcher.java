package org.dsrg.soenea.buddyAge.appPres.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;




public  class BrowsePeopleDispatcher extends PresentationDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		super.execute();
		forward("/WEB-INF/JSP/BrowsePeople.jsp");
	}
	
	

}
