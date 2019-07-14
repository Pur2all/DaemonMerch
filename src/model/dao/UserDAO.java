package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.User;
import model.bean.UserType;

public class UserDAO implements DAO<User>
{
	private static final String TABLE_NAME="Utente";

	private DBConnectionPool dbConnectionPool;
	
	public UserDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;
		
		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}
	
	public User searchForUsernameAndPassword(String username, String password) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		User user=null;
		
		String selectSQL="SELECT * FROM " + TABLE_NAME + "WHERE Username = ? AND Password = ?";
		
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			rs=preparedStatement.executeQuery();
			
			if(rs.next())
				if(!rs.getString("Tipo").equals(UserType.DELETED.name()))
				{
					user=new User();
					user.setId(rs.getString("ID"));
					user.setName(rs.getString("Nome"));
					user.setSurname(rs.getString("Cognome"));
					user.setUserType(UserType.valueOf(rs.getString("Tipo")));
					user.setBirthday(rs.getString("DataDiNascita"));
					user.setUsername(rs.getString("Username"));
					user.setPassword(rs.getString("Password"));
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
		
		return user;
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

	public boolean doSave(User user) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (Nome, Cognome, Tipo, DataDiNascita, Username, Password) VALUES (?, ?, ?, ?, ?, ?)";
		int rowsAffected;
		
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

			rowsAffected=preparedStatement.executeUpdate();

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
		
		return rowsAffected>0;
	}

	public boolean doUpdate(User user) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Nome = ?, Cognome = ?, Tipo = ?, DataDiNascita = ?, Username = ?, Password = ? " +
				" WHERE ID = ?";
		int rowsAffected;
		
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
			rowsAffected=preparedStatement.executeUpdate();	
			
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
		
		return rowsAffected>0;
	}

	//This is a fake delete, because we want to store user info even if he has deleted his account, because we need them to keep informations about orders
	public boolean doDelete(User user) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET Tipo = ? " +
				" WHERE ID = ?";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, UserType.DELETED.name());
			preparedStatement.setInt(2, Integer.parseInt(user.getId()));

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
