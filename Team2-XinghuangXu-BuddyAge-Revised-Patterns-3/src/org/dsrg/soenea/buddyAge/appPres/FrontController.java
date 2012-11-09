package org.dsrg.soenea.buddyAge.appPres;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.buddyAge.appPres.command.FrontCommand;
import org.dsrg.soenea.buddyAge.domLogic.Person;
import org.dsrg.soenea.buddyAge.domLogic.PersonOutputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = -416954672175724024L;

	public static void prepareDbRegistry(String db_id) {
		MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization(db_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(db_id, f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty(db_id + "mySqlTablePrefix");
		} catch (Exception e1) {
			e1.printStackTrace();
			tablePrefix = "";
		}
		if (tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(db_id, tablePrefix);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		prepareDbRegistry("");
		//Call Initialize
		setUpUow();
	}
	
	//Initialize the Unit of work
	public void setUpUow(){
		MapperFactory mapperFactory=new MapperFactory();
		mapperFactory.addMapping(Person.class, PersonOutputMapper.class);
		UoW.newCurrent();
		UoW.initMapperFactory(mapperFactory);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FrontCommand fc = null;
		try {
			fc = getFrontCommand(request);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		if (fc != null) {
			String dispatchTarget;
			try {
				UoW.newCurrent();
				dispatchTarget = fc.execute(request);
				UoW.getCurrent().commit();
			} catch (CommandException e) {
				throw new ServletException(e);
			}catch(Exception e){
				throw new ServletException(e);
			}
			
			if (dispatchTarget != null) {
				request.getRequestDispatcher(dispatchTarget).forward(request,
						response);
			}
//			try{
//				UoW.getCurrent().commit();
//			}
//			catch(Exception e){
//				throw new ServletException(e);
//			}
		}
	}

	private FrontCommand getFrontCommand(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException //
	{
		String command = request.getParameter("command");
		if (command == null || command.isEmpty())
			command = "ViewPerson";
		String fullyQualifiedCommand = "org.dsrg.soenea.buddyAge.appPres.command." + command;
		return (FrontCommand) Class.forName(fullyQualifiedCommand).newInstance();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		preProcessRequest(request, response);
		try {
			processRequest(request, response);
		} finally {
			postProcessRequest(request, response);
		}
	}

	protected void preProcessRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void postProcessRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			DbRegistry.closeDbConnectionIfNeeded();
		} catch (Exception e) {
		}
		ThreadLocalTracker.purgeThreadLocal();
	}

}
