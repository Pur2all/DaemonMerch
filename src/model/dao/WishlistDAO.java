package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Image;
import model.bean.WishlistProduct;

public class WishlistDAO implements DAO<WishlistProduct>
{
	private static final String TABLE_NAME="VorrebbeAcquistare";

	private DBConnectionPool dbConnectionPool;
	private int userId;

	public WishlistDAO(DBConnectionPool aDBConnectionPool, int anUserId)
	{
		userId=anUserId;
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Collection<WishlistProduct> doRetrieveByUserID() throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<WishlistProduct> products=new LinkedList<WishlistProduct>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN Prodotto ON Prodotto.ID=VorrebbeAcquistare.ID_Prodotto INNER JOIN Foto ON Prodotto.ID=Foto.ID_Prodotto WHERE ID_Utente = ? ";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, userId);

			ResultSet rs=preparedStatement.executeQuery();
			int start=0, end=0;
			while(rs.next())
			{
				start=rs.getRow();
				System.out.println("Start: " + start);

				WishlistProduct productFromTable=new WishlistProduct();

				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));
				productFromTable.setDateOfAddition("DataAggiunzione");
				productFromTable.setQuantity(rs.getInt("Quantita"));

				while(rs.next() && rs.getString("ID").equals(productFromTable.getId()));
				if(rs.getRow()==0)
				{
					rs.last();
					end=rs.getRow()+1;
				}
				else
					end=rs.getRow();
				System.out.println("End: " + end);
				Image[] productFromTableImages=new Image[end-start];
				int i=0;
				rs.absolute(start);
				System.out.println("CurrentRow: " + rs.getRow());
				while(start++<end)
				{
					productFromTableImages[i]=new Image();
					productFromTableImages[i].setImageName(rs.getString("NomeFoto"));
					productFromTableImages[i++].setImage(rs.getBytes("Foto"));
					rs.next();
				}
				productFromTable.setImages(productFromTableImages);

				products.add(productFromTable);
				if(rs.getRow()!=0)
					rs.previous();
			}
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		return products;
	}

	public WishlistProduct doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN Prodotto ON ID=ID_Prodotto INNER JOIN Foto ON ID=ID_Prodotto WHERE ID_Prodotto = ? AND ID_Utente = ?";
		
		WishlistProduct productFromTable=new WishlistProduct();
		
		try
		{
			int code=(int) key;
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next())
			{
				productFromTable.setId(rs.getString("ID"));
				productFromTable.setName(rs.getString("Nome"));
				productFromTable.setPrice(rs.getFloat("Prezzo"));
				productFromTable.setDescription(rs.getString("Descrizione"));
				productFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setArtistId(rs.getString("ID_Artista"));
				productFromTable.setDateOfAddition("DataAggiunzione");
				productFromTable.setQuantity(rs.getInt("Quantita"));

				rs.last();
				Image[] productFromTableImages=new Image[rs.getRow()];
				int i=0;
				rs.beforeFirst();
				while(rs.next())
				{
					productFromTableImages[i]=new Image();
					productFromTableImages[i].setImageName(rs.getString("NomeFoto"));
					productFromTableImages[i++].setImage(rs.getBytes("Foto"));
				}
				productFromTable.setImages(productFromTableImages);
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

	public Collection<WishlistProduct> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
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
				productFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				productFromTable.setTag(rs.getString("Tag"));
				productFromTable.setUserID(rs.getInt("ID_Utente"));
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

	public WishlistProduct doSave(WishlistProduct product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + TABLE_NAME + " (ID_Utente, ID_Prodotto, DataAggiunzione, Quantita) VALUES (?, ?, ?, ?)";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getUserID());
			preparedStatement.setString(2, product.getId());
			preparedStatement.setString(3, product.getDateOfAddition());
			preparedStatement.setInt(4, product.getQuantity());

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

		return product;
	}

	public boolean doUpdate(WishlistProduct product) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String updateSQL = "UPDATE " + TABLE_NAME +
				" SET  DataAggiunzione = ?, Quantita = ? " +
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

		String deleteSQL="DELETE FROM " + TABLE_NAME + " WHERE ID_Prodotto = ? AND ID_Utente = ?";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, Integer.parseInt(product.getId()));
			preparedStatement.setInt(2, product.getUserID());

			result=preparedStatement.executeUpdate();
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

		return (result!=0);
	}
}
