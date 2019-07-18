package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Product;

public class ProductDAO implements DAO<Product>
{
	private static final String TABLE_NAME="Prodotto";

	private DBConnectionPool dbConnectionPool;

	public ProductDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Collection<Product> doRetrieveByName(String productName, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Product> products=new LinkedList<Product>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE Nome LIKE ?";

		if (productName!=null && !productName.equals(""))
		{
			try
			{
				connection=dbConnectionPool.getConnection();
				preparedStatement=connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, "%" + productName + "%");

				ResultSet rs=preparedStatement.executeQuery();

				while (rs.next())
				{
					Product productFromTable=new Product();

					productFromTable.setId(rs.getString("ID"));
					productFromTable.setName(rs.getString("Nome"));
					productFromTable.setPrice(rs.getFloat("Prezzo"));
					productFromTable.setDescription(rs.getString("Descrizione"));
					productFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
					productFromTable.setTag(rs.getString("Tag"));
					productFromTable.setArtistId(rs.getString("ID_Artista"));

					products.add(productFromTable);
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

			return products;
		}
		else
			try
			{
				throw new Exception();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return null;
	}

	public Product doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Product productFromTable=new Product();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try
		{
			int code=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));
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

		return productFromTable;
	}

	public Collection<Product> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Product> products=new LinkedList<Product>();

		String selectSQL="SELECT * FROM " + TABLE_NAME;

		if (order!=null && !order.equals(""))
			selectSQL+=" ORDER BY " + order;

		selectSQL+=" LIMIT " + pageInit + ", " + pageEnd;
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				Product productFromTable=new Product();

				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));

				products.add(productFromTable);
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

		return products;
	}

	public boolean doSave(Product product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (Nome, Prezzo, Descrizione, QuantitaRimanente, Tag, ID_Artista) VALUES (?, ?, ?, ?, ?, ?)";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setFloat(2, product.getPrice());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setInt(4, product.getRemaining());
			preparedStatement.setString(5, product.getTag());
			preparedStatement.setString(6, product.getArtistId());

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

	public boolean doUpdate(Product product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Nome = ?, Prezzo = ?, Descrizione = ?, QuantitaRimanente = ?, Tag = ?, ID_Artista = ? " +
				" WHERE ID = ?";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, product.getName());
			preparedStatement.setFloat(2, product.getPrice());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setInt(4, product.getRemaining());
			preparedStatement.setString(5, product.getTag());
			preparedStatement.setString(6, product.getArtistId());
			preparedStatement.setString(7, product.getId());

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

	public boolean doDelete(Product product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, Integer.parseInt(product.getId()));

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
