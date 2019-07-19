package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Image;

public class ImageDAO implements DAO<Image>
{
	private static final String TABLE_NAME="Foto";

	private DBConnectionPool dbConnectionPool;
	private int id;
	private TypeOfImage typeOfImage;

	public ImageDAO(DBConnectionPool aDBConnectionPool, int anID, TypeOfImage aTypeOfImage)
	{
		id=anID;
		typeOfImage=aTypeOfImage;
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Image doRetrieveByKeyInArtist(String key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Image image=null;

		String selectSQL="SELECT * FROM Artista WHERE Nome = ?";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, key);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next())
			{
				image=new Image();

				image.setImageName(rs.getString("Nome"));
				image.setImage(rs.getBytes("Logo"));
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

		return image;
	}
	
	public Image doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Image image=null;

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE NomeFoto = ?";

		try
		{
			String imageName=(String) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, imageName);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next())
			{
				image=new Image();

				image.setImageName(rs.getString("NomeFoto"));
				image.setImage(rs.getBytes("Foto"));
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

		return image;
	}

	public Collection<Image> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Image> images=new LinkedList<Image>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE ID_Artista = ? AND ID_Prodotto = ?";

		if (order!=null && !order.equals(""))
			selectSQL+=" ORDER BY " + order;
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			//TODO Controlla sta roba, ha senso ciò che fai? Cioè meglio il metodo a parte o sta roba ti serve a qualcosa? Pensa come il te del passato
			preparedStatement.setInt(1, typeOfImage==TypeOfImage.ARTIST ? id : Types.NULL);
			preparedStatement.setInt(2, typeOfImage==TypeOfImage.PRODUCT ? id : Types.NULL);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				Image image=new Image();

				image.setImageName(rs.getString("NomeFoto"));
				image.setImage(rs.getBytes("Foto"));

				images.add(image);
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

		return images;
	}

	public boolean doSave(Image image) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (Foto, NomeFoto, ID_Prodotto, ID_Artista) VALUES (?, ?, ?, ?)";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setBytes(1, image.getImage());
			preparedStatement.setString(2, image.getImageName());
			preparedStatement.setInt(3, typeOfImage==TypeOfImage.PRODUCT ? id : Types.NULL);
			preparedStatement.setInt(4, typeOfImage==TypeOfImage.ARTIST ? id : Types.NULL);

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

	public boolean doUpdate(Image image) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Foto = ? WHERE NomeFoto = ?";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);

			preparedStatement.setBytes(1, image.getImage());
			preparedStatement.setString(2, image.getImageName());

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

	public boolean doDelete(Image image) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE NomeFoto = ?";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, image.getImageName());

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
