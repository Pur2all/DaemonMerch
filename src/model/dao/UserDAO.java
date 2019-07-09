package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.User;
import model.UserType;

public class UserDAO implements DAO<User>
{
	private static final String TABLE_NAME="Utente";

	private DBConnectionPool dbConnectionPool;
	
	public UserDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;
		
		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}
	
	public boolean searchForUsernameAndPassword(String username, String password) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		String selectSQL="SELECT * FROM " + TABLE_NAME + "WHERE Username = ? AND Password = ?";
		
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			rs=preparedStatement.executeQuery();
		}
		finally
		{
			try 
			{
				if(preparedStatement!=null)
					preparedStatement.close();
			} 
			finally 
			{
				dbConnectionPool.releaseConnection(connection);
			}
		}
		
		return rs.first();
	}
	
	public User doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		User userFromTable=new User();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			int code=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next()) 
			{
				userFromTable.setId(rs.getString("ID"));
				userFromTable.setName(rs.getString("Nome"));
				userFromTable.setSurname(rs.getString("Cognome"));
				userFromTable.setUserType(UserType.valueOf(rs.getString("Tipo")));
				userFromTable.setBirthday(rs.getString("DataDiNascita"));
				userFromTable.setUsername(rs.getString("Username"));
				userFromTable.setPassword(rs.getString("Password"));
			}
		} 
		finally 
		{
			try 
			{
				if(preparedStatement!=null)
					preparedStatement.close();
			} 
			finally 
			{
				dbConnectionPool.releaseConnection(connection);
			}
		}
		
		return userFromTable;
	}

	public Collection<User> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<User> users=new LinkedList<User>();

		String selectSQL="SELECT * FROM " + TABLE_NAME;

		if (order!=null && !order.equals("")) 
			selectSQL+=" ORDER BY " + order;

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();
			
			while (rs.next())
			{
				User userFromTable=new User();
				
				userFromTable.setId(rs.getString("ID"));
				userFromTable.setName(rs.getString("Nome"));
				userFromTable.setSurname(rs.getString("Cognome"));
				userFromTable.setUserType(UserType.valueOf(rs.getString("Tipo")));
				userFromTable.setBirthday(rs.getString("DataDiNascita"));
				userFromTable.setUsername(rs.getString("Username"));
				userFromTable.setPassword(rs.getString("Password"));
				users.add(userFromTable);
			}
		} 
		finally 
		{
			try 
			{
				if (preparedStatement!=null)
					preparedStatement.close();
			} 
			finally 
			{
				dbConnectionPool.releaseConnection(connection);
			}
		}
		
		return users;
	}

	public void doSave(User user) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (Nome, Cognome, Tipo, DataDiNascita, Username, Password) VALUES (?, ?, ?, ?, ?, ?)";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getUserType().name());
			preparedStatement.setString(4, user.getBirthday());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.setString(6, user.getPassword());

			preparedStatement.executeUpdate();

			connection.commit();
		} 
		finally 
		{
			try 
			{
				if(preparedStatement!=null)
					preparedStatement.close();
			} 
			finally 
			{
				dbConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void doUpdate(User user) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Nome = ?, Cognome = ?, Tipo = ?, DataDiNascita = ?, Username = ?, Password = ? " +
				" WHERE ID = ?";
				
		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);			
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getUserType().name());
			preparedStatement.setString(4, user.getBirthday());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getId());
			
			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();	
			
			connection.commit();
		} 
		finally 
		{
			try 
			{
				if(preparedStatement!=null) 
					preparedStatement.close();
			} 
			finally 
			{
				dbConnectionPool.releaseConnection(connection);
			}			
		}
	}

	public boolean doDelete(User user) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, Integer.parseInt(user.getId()));

			result=preparedStatement.executeUpdate();
		} 
		finally
		{
			try 
			{
				if(preparedStatement!=null)
					preparedStatement.close();
			} 
			finally 
			{
				dbConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result!=0);
	}
}
