package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Artist;
import model.CreditCard;

public class ArtistDAO implements DAO<Artist>
{
	private static final String TABLE_NAME="Artista";

	public Artist doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Artist artistFromTable=new Artist();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			int id=(int) key;
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next()) 
			{
				artistFromTable.setName(rs.getString("Nome"));
				artistFromTable.setLogo(rs.getBlob("Logo"));
				artistFromTable.setNumber(number);
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
		
		return artistFromTable;
	}

	public Collection<Artist> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Artist> artists=new LinkedList<Artist>();

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
				Artist artistFromTable=new Artist();
				
				artistFromTable.setCVV(rs.getString("CVV"));
				artistFromTable.setExpireDate(rs.getString("DataScadenza"));
				artistFromTable.setNumber(rs.getString("Numero"));
				artists.add(artistFromTable);
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
		
		return artists;
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
			preparedStatement.setString(4, creditCard.getNumber());
			
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
