package control.servlet.webapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.dao.DBConnectionPool;


public class ContextListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent servletContextEvent) 
	{
		ServletContext context=servletContextEvent.getServletContext();

		DBConnectionPool dbConnectionPool=new DBConnectionPool();
		
		context.setAttribute("DriverManager", dbConnectionPool);
		
		System.out.println("DriverManager creation...." + dbConnectionPool.toString());		
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) 
	{
		ServletContext context=servletContextEvent.getServletContext();
		
		DBConnectionPool dbConnectionPool=(DBConnectionPool) context.getAttribute("DriverManager");
		
		System.out.println("DriverManager deletion...." + dbConnectionPool.toString());		
	}
}
