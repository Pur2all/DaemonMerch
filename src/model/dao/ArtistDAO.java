package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Artist;

public class ArtistDAO implements DAO<Artist>
{
	private static final String TABLE_NAME="Artista";
	
	private DBConnectionPool dbConnectionPool;
	
	public ArtistDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;
		
		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Artist doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Artist artistFromTable=new Artist();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			int id=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next()) 
			{
				artistFromTable.setName(rs.getString("Nome"));
				artistFromTable.setLogo(rs.getBytes("Logo"));
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
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();
			
			while (rs.next())
			{
				Artist artistFromTable=new Artist();
				
				artistFromTable.setName(rs.getString("Nome"));
				artistFromTable.setLogo(rs.getBytes("Logo"));
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
				dbConnectionPool.releaseConnection(connection);
			}
		}
		
		return artists;
	}

	public void doSave(Artist artist) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (Nome, Logo) VALUES (?, ?)";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, artist.getName());
			preparedStatement.setBytes(2, artist.getLogo());

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

	public void doUpdate(Artist artist) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Nome = ?, Logo = ? " +
				" WHERE ID = ?";
				
		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);			
			
			preparedStatement.setString(1, artist.getName());
			preparedStatement.setBytes(2, artist.getLogo());
			preparedStatement.setInt(3, artist.getId());
			
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

	public boolean doDelete(Artist artist) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, artist.getId());

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
