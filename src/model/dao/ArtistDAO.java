package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Artist;
import model.bean.Image;

public class ArtistDAO implements DAO<Artist>
{
	private static final String TABLE_NAME="Artista";

	private DBConnectionPool dbConnectionPool;

	public ArtistDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Collection<Artist> doRetrieveAll() throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Artist> artists=new LinkedList<Artist>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " ORDER BY Nome";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next())
			{
				Artist artistFromTable=new Artist();

				artistFromTable.setName(rs.getString("Nome"));
				artistFromTable.setId(rs.getInt("ID"));

				Image newImage=new Image();
				newImage.setImage(rs.getBytes("Logo"));
				newImage.setImageName(rs.getString("Nome"));

				artistFromTable.setLogo(newImage);
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

	public Artist doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Artist artistFromTable=new Artist();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN Foto ON ID=ID_Artista WHERE ID = ?";

		try
		{
			int id=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs=preparedStatement.executeQuery();

			if(rs.next())
			{
				artistFromTable.setName(rs.getString("Nome"));

				Image newImage=new Image();
				newImage.setImage(rs.getBytes("Logo"));
				newImage.setImageName(rs.getString("Nome"));

				artistFromTable.setLogo(newImage);
				rs.last();
				Image[] artistFromTableImages=new Image[rs.getRow()];
				System.out.println("Number row: " + rs.getRow());
				int i=0;
				rs.beforeFirst();
				while(rs.next())
				{
					artistFromTableImages[i]=new Image();
					artistFromTableImages[i].setImageName(rs.getString("NomeFoto"));
					artistFromTableImages[i++].setImage(rs.getBytes("Foto"));
				}
				artistFromTable.setImages(artistFromTableImages);
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

	public Collection<Artist> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Artist> artists=new LinkedList<Artist>();

		String selectSQL="SELECT * FROM " + TABLE_NAME;

		if (order!=null && !order.equals(""))
			selectSQL+=" ORDER BY '" + order + "'";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				Artist artistFromTable=new Artist();

				artistFromTable.setName(rs.getString("Nome"));
				artistFromTable.setId(rs.getInt("ID"));

				Image newImage=new Image();
				newImage.setImage(rs.getBytes("Logo"));
				newImage.setImageName(rs.getString("Nome"));

				artistFromTable.setLogo(newImage);
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

	public synchronized boolean doSave(Artist artist) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (Nome, Logo) VALUES (?, ?)";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, artist.getName());
			preparedStatement.setBytes(2, artist.getLogo().getImage());

			rowsAffected=preparedStatement.executeUpdate();

			connection.commit();
			
			String selectSQL="SELECT * FROM " + TABLE_NAME;
			
			preparedStatement=connection.prepareStatement(selectSQL);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			rs.last();
			
			System.out.println(rs.getInt("ID"));
			ImageDAO imageDAO=new ImageDAO(dbConnectionPool, rs.getInt("ID"), TypeOfImage.ARTIST);
			
			for(int i=0; i<artist.getImages().length; i++)
				if(artist.getImages()[i]!=null)
					imageDAO.doSave(artist.getImages()[i]);
				
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

	public boolean doUpdate(Artist artist) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Nome = ?, Logo = ? " +
				" WHERE ID = ?";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, artist.getName());
			preparedStatement.setBytes(2, artist.getLogo().getImage());
			preparedStatement.setInt(3, artist.getId());

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
