package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.BillingAddress;

public class BillingAddressDAO implements DAO<BillingAddress>
{
	private static final String TABLE_NAME="IndirizzoDiFatturazione";

	private DBConnectionPool dbConnectionPool;
	private int userID;

	public BillingAddressDAO(DBConnectionPool aDBConnectionPool, int anUserID)
	{
		userID=anUserID;
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public BillingAddress doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		BillingAddress billingAddressFromTable=new BillingAddress();

		String selectSQL="SELECT * FROM " + TABLE_NAME + "INNER JOIN Ha WHERE Stato = ?, Via = ?, Paese = ?, Provincia = ?, NumeroCivico = ? AND ID = ?";

		try
		{
			BillingAddress billingAddress=(BillingAddress) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, billingAddress.getState());
			preparedStatement.setString(2, billingAddress.getStreet());
			preparedStatement.setString(3, billingAddress.getCity());
			preparedStatement.setString(4, billingAddress.getDistrict());
			preparedStatement.setInt(5, Integer.valueOf(billingAddress.getHouseNumber()));
			preparedStatement.setInt(6, userID);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				billingAddressFromTable.setState(rs.getString("State"));
				billingAddressFromTable.setStreet(rs.getString("Via"));
				billingAddressFromTable.setCity(rs.getString("Paese"));
				billingAddressFromTable.setDistrict(rs.getString("Provincia"));
				billingAddressFromTable.setHouseNumber(String.valueOf(rs.getInt("NumeroCivico")));
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

		return billingAddressFromTable;
	}

	public Collection<BillingAddress> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<BillingAddress> billingAddresses=new LinkedList<BillingAddress>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + "INNER JOIN Ha WHERE ID = ?";

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
				BillingAddress billingAddressFromTable=new BillingAddress();

				billingAddressFromTable.setState(rs.getString("State"));
				billingAddressFromTable.setStreet(rs.getString("Via"));
				billingAddressFromTable.setCity(rs.getString("Paese"));
				billingAddressFromTable.setDistrict(rs.getString("Provincia"));
				billingAddressFromTable.setHouseNumber(String.valueOf(rs.getInt("NumeroCivico")));
				billingAddresses.add(billingAddressFromTable);
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

		return billingAddresses;
	}

	public boolean doSave(BillingAddress billingAddress) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (Stato, Via, Paese, Provincia, NumeroCivico) VALUES (?, ?, ?, ?, ?)";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, billingAddress.getState());
			preparedStatement.setString(2, billingAddress.getStreet());
			preparedStatement.setString(3, billingAddress.getCity());
			preparedStatement.setString(4, billingAddress.getDistrict());
			preparedStatement.setInt(5, Integer.valueOf(billingAddress.getHouseNumber()));

			rowsAffected=preparedStatement.executeUpdate();

			connection.commit();
			saveUserBillingAddressRelation(billingAddress);
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

	public boolean doUpdate(BillingAddress billingAddress) throws SQLException
	{
		//Method is empty because the entire billing address is a primary key and a primary key is immutable
		return false;
	}

	public boolean doDelete(BillingAddress billingAddress) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE Stato = ?, Via = ?, Paese = ?, Provincia = ?, NumeroCivico = ?";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, billingAddress.getState());
			preparedStatement.setString(2, billingAddress.getStreet());
			preparedStatement.setString(3, billingAddress.getCity());
			preparedStatement.setString(4, billingAddress.getDistrict());
			preparedStatement.setInt(5, Integer.valueOf(billingAddress.getHouseNumber()));

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

	private void saveUserBillingAddressRelation(BillingAddress aBillingAddress) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO Ha (ID, Stato, Via, Paese, Provincia, NumeroCivico) VALUES (?, ?, ?, ?, ?, ?)";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, aBillingAddress.getState());
			preparedStatement.setString(3, aBillingAddress.getStreet());
			preparedStatement.setString(4, aBillingAddress.getCity());
			preparedStatement.setString(5, aBillingAddress.getDistrict());
			preparedStatement.setString(6, aBillingAddress.getHouseNumber());

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
