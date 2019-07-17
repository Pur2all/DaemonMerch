package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.WishlistProduct;

public class WishlistDAO implements DAO<WishlistProduct>
{
	private static final String TABLE_NAME="VorrebbeAcquistare";

	private int pageInit, pageEnd;
	private DBConnectionPool dbConnectionPool;

	public WishlistDAO(DBConnectionPool aDBConnectionPool, int aPageInit, int aPageEnd)
	{
		pageInit=aPageInit;
		pageEnd=aPageEnd;
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Collection<WishlistProduct> doRetrieveByUserID(int userID) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<WishlistProduct> products=new LinkedList<WishlistProduct>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + "INNER JOIN PRODOTTO WHERE ID_User = ? LIMIT " + pageInit + ", " + pageEnd;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, userID);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				WishlistProduct productFromTable=new WishlistProduct();

				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("Quantit�Rimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));
				productFromTable.setDateOfAddition("DataAggiunzione");
				productFromTable.setQuantity(rs.getInt("Quantit�"));
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

	public WishlistProduct doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		WishlistProduct productFromTable=new WishlistProduct();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN PRODOTTO WHERE ID_Prodotto = ?";

		try
		{
			int id=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("Quantit�Rimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));
				productFromTable.setDateOfAddition("DataAggiunzione");
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

	public Collection<WishlistProduct> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<WishlistProduct> products=new LinkedList<WishlistProduct>();

		String selectSQL="SELECT * FROM " + TABLE_NAME;

		if (order!=null && !order.equals(""))
			selectSQL+=" ORDER BY " + order;

		selectSQL+="LIMIT " + pageInit + ", " + pageEnd;
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next())
			{
				WishlistProduct productFromTable=new WishlistProduct();

				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("Quantit�Rimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));
				productFromTable.setDateOfAddition("DataAggiunzione");
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

	public boolean doSave(WishlistProduct product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (ID_Utente, ID_Prodotto, DataAggiunzione, Quantit�) VALUES (?, ?, ?, ?)";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getUserID());
			preparedStatement.setString(2, product.getId());
			preparedStatement.setString(3, product.getDateOfAddition());
			preparedStatement.setInt(4, product.getQuantity());

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

	public boolean doUpdate(WishlistProduct product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String updateSQL = "UPDATE " + TABLE_NAME +
				" SET  DataAggiunzione = ?, Quantit� = ? " +
				" WHERE ID_Prodotto = ? AND ID_Utente = ?";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, product.getDateOfAddition());
			preparedStatement.setInt(2, product.getQuantity());
			preparedStatement.setString(3, product.getId());
			preparedStatement.setInt(4, product.getUserID());

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

	public boolean doDelete(WishlistProduct product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		int result=0;

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE ID_Prodotto = ?";

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
