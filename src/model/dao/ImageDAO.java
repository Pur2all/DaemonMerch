package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
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

	public Image doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Image image=null;

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE NomeFoto = ?";

		try
		{
			int id=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next())
			{
				image=new Image();

				image.setImageName(rs.getString("NomeFoto"));
				image.setImage(rs.getBlob("Foto"));
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

	public Collection<Image> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Image> artists=new LinkedList<Image>();

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
				Image image=new Image();

				image.setImageName(rs.getString("NomeFoto"));
				image.setImage(rs.getBlob("Foto"));

				artists.add(image);
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
			preparedStatement.setBlob(1, image.getImage());
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
		
		return rowsAffected>0 ? true : false;
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

			preparedStatement.setBlob(1, image.getImage());
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
		
		return rowsAffected>0 ? true : false;
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
