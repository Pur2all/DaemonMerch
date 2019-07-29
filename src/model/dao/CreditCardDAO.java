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
	private int userID;

	public CreditCardDAO(DBConnectionPool aDBConnectionPool, int anUserID)
	{
		userID=anUserID;
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Collection<CreditCard> doRetrieveAll() throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<CreditCard> creditCards=new LinkedList<CreditCard>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN PagaCon WHERE ID = ?";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, userID);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				CreditCard creditCardFromTable=new CreditCard();

				creditCardFromTable.setCvv(rs.getString("CVV"));
				creditCardFromTable.setExpireDate(rs.getString("DataScadenza"));
				creditCardFromTable.setNumber(rs.getString("Numero"));
				creditCards.add(creditCardFromTable);
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

		return creditCards;
	}

	public CreditCard doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		CreditCard creditCardFromTable=new CreditCard();

		String selectSQL="SELECT * FROM " + TABLE_NAME + "INNER JOIN PagaCon WHERE Numero = ? AND ID = ?";

		try
		{
			String number=(String) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, number);
			preparedStatement.setInt(2, userID);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next())
			{
				creditCardFromTable.setCvv(rs.getString("CVV"));
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

	public Collection<CreditCard> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<CreditCard> users=new LinkedList<CreditCard>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN PagaCon WHERE ID = ?";

		if (order!=null && !order.equals(""))
			selectSQL+=" ORDER BY " + order;
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, userID);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				CreditCard creditCardFromTable=new CreditCard();

				creditCardFromTable.setCvv(rs.getString("CVV"));
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

	public CreditCard doSave(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (CVV, DataScadenza, Numero) VALUES (?, ?, ?)";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, creditCard.getCvv());
			preparedStatement.setString(2, creditCard.getExpireDate());
			preparedStatement.setString(3, creditCard.getNumber());

			preparedStatement.executeUpdate();

			connection.commit();
			saveUserCreditCardRelation(creditCard);
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

		return creditCard;
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

			preparedStatement.setString(1, creditCard.getCvv());
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

		return rowsAffected>0;
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

	private void saveUserCreditCardRelation(CreditCard creditCard) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO PagaCon (ID, Numero) VALUES (?, ?)";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, creditCard.getNumber());

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
}
