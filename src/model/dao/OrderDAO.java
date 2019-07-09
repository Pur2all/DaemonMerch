package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.BillingAddress;
import model.Order;
import model.Product;
import model.State;

public class OrderDAO implements DAO<Order>
{
	private static final String TABLE_NAME="Ordine";
	
	private int userID;
	private DBConnectionPool dbConnectionPool;
	
	public OrderDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;
		
		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}
	
	public OrderDAO(int anUserID)
	{
		userID=anUserID;
	}

	public Order doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Order orderFromTable=new Order();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN Contiene INNER JOIN Prodotto WHERE " + TABLE_NAME + ".ID = ?";

		try 
		{
			int id=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs=preparedStatement.executeQuery();
			
			if(rs.next())
			{
				orderFromTable.setId(rs.getString("Ordine.ID"));
				orderFromTable.setDate(rs.getString("Data"));
				orderFromTable.setTotal(rs.getFloat("Totale"));
				orderFromTable.setState(State.valueOf(rs.getString("StatoOrdine")));
				BillingAddress aBillingAddress=new BillingAddress();
				aBillingAddress.setState(rs.getString("Stato"));
				aBillingAddress.setStreet(rs.getString("Via"));
				aBillingAddress.setCity(rs.getString("Paese"));
				aBillingAddress.setDistrict(rs.getString("Provincia"));
				aBillingAddress.setHouseNumber(String.valueOf(rs.getInt("NumeroCivico")));
				orderFromTable.setBillingAddress(aBillingAddress);
				while(rs.next()) 
				{
					Product aProduct=new Product();
					
					aProduct.setId(rs.getString("Prodotto.ID"));
					aProduct.setName(rs.getString("Nome"));
					aProduct.setPrice(rs.getFloat("Prezzo"));
					aProduct.setDescription(rs.getString("Descrizione"));
					aProduct.setRemaining(rs.getInt("Quantit‡Rimanente"));
					aProduct.setTag(rs.getString("Tag"));
					orderFromTable.addProducts(aProduct);
				}
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
		
		return orderFromTable;
	}

	public Collection<Order> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Order> orders=new LinkedList<Order>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + "INNER JOIN Contiene INNER JOIN Prodotto WHERE " + TABLE_NAME + ".ID = ?";

		if (order!=null && !order.equals("")) 
			selectSQL+=" ORDER BY Ordine.ID, " + order;
		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				Order orderFromTable=new Order();
				
				orderFromTable.setId(rs.getString("Ordine.ID"));
				orderFromTable.setDate(rs.getString("Data"));
				orderFromTable.setTotal(rs.getFloat("Totale"));
				orderFromTable.setState(State.valueOf(rs.getString("StatoOrdine")));
				BillingAddress aBillingAddress=new BillingAddress();
				aBillingAddress.setState(rs.getString("Stato"));
				aBillingAddress.setStreet(rs.getString("Via"));
				aBillingAddress.setCity(rs.getString("Paese"));
				aBillingAddress.setDistrict(rs.getString("Provincia"));
				aBillingAddress.setHouseNumber(String.valueOf(rs.getInt("NumeroCivico")));
				orderFromTable.setBillingAddress(aBillingAddress);
				while(rs.next() && rs.getString("Ordine.ID").equals(orderFromTable.getId()))
				{
					Product aProduct=new Product();
					
					aProduct.setId(rs.getString("Prodotto.ID"));
					aProduct.setName(rs.getString("Nome"));
					aProduct.setPrice(rs.getFloat("Prezzo"));
					aProduct.setDescription(rs.getString("Descrizione"));
					aProduct.setRemaining(rs.getInt("Quantit‡Rimanente"));
					aProduct.setTag(rs.getString("Tag"));
					orderFromTable.addProducts(aProduct);
				}
				if(!rs.getString("Ordine.ID").equals(orderFromTable.getId()))
					rs.previous();
				
				orders.add(orderFromTable);
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
		
		return orders;
	}

	public void doSave(Order order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (Data, Totale, StatoOrdine, ID_Utente, Stato, Via, Paese, Provincia, NumeroCivico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, order.getDate());
			preparedStatement.setFloat(2, order.getTotal());
			preparedStatement.setString(3, String.valueOf(order.getState()));
			preparedStatement.setInt(4, userID);
			preparedStatement.setString(5, order.getBillingAddress().getState());
			preparedStatement.setString(6, order.getBillingAddress().getStreet());
			preparedStatement.setString(7, order.getBillingAddress().getCity());
			preparedStatement.setString(8, order.getBillingAddress().getDistrict());
			preparedStatement.setInt(9, Integer.parseInt(order.getBillingAddress().getHouseNumber()));
			
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

	public void doUpdate(Order order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Data = ?, Totale = ?, StatoOrdine = ? " +
				" WHERE ID = ?";
				
		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);			
			
			preparedStatement.setString(1, order.getDate());
			preparedStatement.setFloat(2, order.getTotal());
			preparedStatement.setString(3, String.valueOf(order.getState()));
			preparedStatement.setString(4, order.getId());
			
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

	public boolean doDelete(Order order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, Integer.parseInt(order.getId()));

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
