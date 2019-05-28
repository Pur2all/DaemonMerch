package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.CreditCard;

public class CreditCardDAO implements DAO<CreditCard>
{
	private static final String TABLE_NAME="CartaDiCredito";

	public CreditCard doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		CreditCard creditCardFromTable=new CreditCard();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE Number = ?";

		try 
		{
			String number=(String) key;
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, number);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next()) 
			{
				creditCardFromTable.setCVV(rs.getString("ID"));
				creditCardFromTable.setExpireDate(rs.getString("DataScadenza"));
				creditCardFromTable.setNumber(String.valueOf(number));
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
				DBConnectionPool.releaseConnection(connection);
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
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();
			
			while (rs.next())
			{
				CreditCard creditCardFromTable=new CreditCard();
				
				creditCardFromTable.setCVV(rs.getString("CVV"));
				creditCardFromTable.setExpireDate(rs.getString("DataScadenza"));
				creditCardFromTable.setNumber(rs.getString("Number"));
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
				DBConnectionPool.releaseConnection(connection);
			}
		}
		
		return users;
	}

	public void doSave(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (CVV, DataScadenza, Numero) VALUES (?, ?, ?)";

		try 
		{
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, creditCard.getCVV());
			preparedStatement.setString(2, creditCard.getExpireDate());
			preparedStatement.setString(3, creditCard.getNumber());

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
				DBConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void doUpdate(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" CVV = ?, DataScadenza = ?, Numero = ? " +
				" WHERE Numero = ?";
				
		try 
		{
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);			
			
			preparedStatement.setString(1, creditCard.getCVV());
			preparedStatement.setString(2, creditCard.getExpireDate());
			preparedStatement.setString(3, creditCard.getNumber());
			
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
				DBConnectionPool.releaseConnection(connection);
			}			
		}
	}

	public boolean doDelete(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE Number = ?";

		try 
		{
			connection=DBConnectionPool.getConnection();
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
				DBConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result!=0);
	}
}
