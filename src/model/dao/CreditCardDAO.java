package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.CreditCard;

public class CreditCardDAO implements DAO<CreditCard>
{
	private static final String TABLE_NAME="CartaDiCredito";

	private DBConnectionPool dbConnectionPool;
	
	public CreditCardDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;
		
		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}
	
	public void saveUserCreditCardRelation(CreditCard creditCard, int userID) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO PagaCon (ID, CVV, DataScadenza, Numero) VALUES (?, ?, ?, ?)";
		
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, creditCard.getCVV());
			preparedStatement.setString(3, creditCard.getExpireDate());
			preparedStatement.setString(4, creditCard.getNumber());
			
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
	
	public CreditCard doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		CreditCard creditCardFromTable=new CreditCard();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE Numero = ?";

		try 
		{
			String number=(String) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, number);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next()) 
			{
				creditCardFromTable.setCVV(rs.getString("CVV"));
				creditCardFromTable.setExpireDate(rs.getString("DataScadenza"));
				creditCardFromTable.setNumber(number);
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
		
		return creditCardFromTable;
	}

	public Collection<CreditCard> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<CreditCard> users=new LinkedList<CreditCard>();

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
				CreditCard creditCardFromTable=new CreditCard();
				
				creditCardFromTable.setCVV(rs.getString("CVV"));
				creditCardFromTable.setExpireDate(rs.getString("DataScadenza"));
				creditCardFromTable.setNumber(rs.getString("Numero"));
				users.add(creditCardFromTable);
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
		
		return users;
	}

	public boolean doSave(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (CVV, DataScadenza, Numero) VALUES (?, ?, ?)";
		int rowsAffected;
		
		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, creditCard.getCVV());
			preparedStatement.setString(2, creditCard.getExpireDate());
			preparedStatement.setString(3, creditCard.getNumber());

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
		
		return rowsAffected>0 ? true : false;
	}

	public boolean doUpdate(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" CVV = ?, DataScadenza = ?, Numero = ? " +
				" WHERE Numero = ?";
		int rowsAffected;
		
		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);			
			
			preparedStatement.setString(1, creditCard.getCVV());
			preparedStatement.setString(2, creditCard.getExpireDate());
			preparedStatement.setString(3, creditCard.getNumber());
			preparedStatement.setString(4, creditCard.getNumber());
			
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
		
		return rowsAffected>0 ? true : false;
	}

	public boolean doDelete(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE Number = ?";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, Integer.parseInt(creditCard.getNumber()));

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
