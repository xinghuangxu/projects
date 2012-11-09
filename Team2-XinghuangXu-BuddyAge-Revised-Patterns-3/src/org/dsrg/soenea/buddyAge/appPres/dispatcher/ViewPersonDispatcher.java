package org.dsrg.soenea.buddyAge.appPres.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

public class ViewPersonDispatcher extends PresentationDispatcher{
	
	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.execute();
		forward("/WEB-INF/JSP/ViewPerson.jsp");
	}

}
