package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class DBConnectionPool
{
	private static List<Connection> freeDbConnections;

	static
	{
		freeDbConnections=new LinkedList<Connection>();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException exception)
		{
			exception.printStackTrace();
		}
	}

	private synchronized Connection createDBConnection() throws SQLException
	{
		Connection newConnection=null;
		String ip="localhost";
		String port="3306";
		String db="daemonmerch";
		String username="root";
		String password="admin";

		newConnection=DriverManager.getConnection("jdbc:mysql://"+ ip + ":" +
				port + "/" + db + "?serverTimezone=UTC", username, password);

		System.out.println("Create a new DB connection");
		newConnection.setAutoCommit(true);

		return newConnection;
	}

	public synchronized Connection getConnection() throws SQLException
	{
		Connection connection;

		if (!freeDbConnections.isEmpty())
		{
			connection=(Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try
			{
				if (connection.isClosed())
					connection=getConnection();
			}
			catch (SQLException e) 
			{
				connection.close();
				connection=getConnection();
			}
		}
		else
			connection=createDBConnection();

		return connection;
	}

	public synchronized void releaseConnection(Connection connection) throws SQLException
	{
		if(connection != null)
			freeDbConnections.add(connection);
	}
}
