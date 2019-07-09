package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.BillingAddress;

public class BillingAddressDAO implements DAO<BillingAddress>
{
	private static final String TABLE_NAME="IndirizzoDiFatturazione";

	private DBConnectionPool dbConnectionPool;
	
	public BillingAddressDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;
		
		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}
	
	public BillingAddress doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		BillingAddress billingAddressFromTable=new BillingAddress();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE Stato = ?, Via = ?, Paese = ?, Provincia = ?, NumeroCivico = ?";

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

	public Collection<BillingAddress> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<BillingAddress> billingAddresses=new LinkedList<BillingAddress>();

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

	public void doSave(BillingAddress billingAddress) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (Stato, Via, Paese, Provincia, NumeroCivico) VALUES (?, ?, ?, ?, ?)";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, billingAddress.getState());
			preparedStatement.setString(2, billingAddress.getStreet());
			preparedStatement.setString(3, billingAddress.getCity());
			preparedStatement.setString(4, billingAddress.getDistrict());
			preparedStatement.setInt(5, Integer.valueOf(billingAddress.getHouseNumber()));

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

	public void doUpdate(BillingAddress billingAddress) throws SQLException
	{
		//Method is empty because the entire billing address is a primary key and a primary key is immutable
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
}
