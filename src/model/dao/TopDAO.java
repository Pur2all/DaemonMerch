package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Category;
import model.bean.Image;
import model.bean.PrintType;
import model.bean.Size;
import model.bean.Top;

public class TopDAO implements DAO<Top>
{
	private static final String TABLE_NAME="Top";

	private DBConnectionPool dbConnectionPool;

	public TopDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Collection<Top> doRetrieveByName(String productName, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Top> products=new LinkedList<Top>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " NATURAL JOIN Prodotto INNER JOIN Foto ON ID=ID_Prodotto WHERE Nome LIKE ?";

		if (productName!=null && !productName.equals(""))
		{
			try
			{
				connection=dbConnectionPool.getConnection();
				preparedStatement=connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, "%" + productName + "%");

				ResultSet rs=preparedStatement.executeQuery();
				int start=0, end=0;
				while(rs.next())
				{
					start=rs.getRow();
					System.out.println("Start: " + start);

					Top topFromTable=new Top();

					topFromTable.setId(rs.getString("ID"));
					topFromTable.setName(rs.getString("Nome"));
					topFromTable.setPrice(rs.getFloat("Prezzo"));
					topFromTable.setDescription(rs.getString("Descrizione"));
					topFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
					topFromTable.setTag(rs.getString("Tag"));
					topFromTable.setArtistId(rs.getString("ID_Artista"));
					topFromTable.setSize(Size.valueOf(rs.getString("Taglia")));
					topFromTable.setCategory(Category.valueOf(rs.getString("Categoria")));
					topFromTable.setPrintType(PrintType.valueOf(rs.getString("TipoStampa")));

					while(rs.next() && rs.getString("ID").equals(topFromTable.getId()));
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
					topFromTable.setImages(productFromTableImages);

					products.add(topFromTable);
					if(rs.getRow()!=0)
						rs.previous();
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
	
	public Collection<Top> doRetrieveByArtistID(int artistId, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Top> products=new LinkedList<Top>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " NATURAL JOIN Prodotto INNER JOIN Foto ON ID=ID_Prodotto WHERE Prodotto.ID_Artista=?";

		if(artistId>0)
		{
			try
			{
				connection=dbConnectionPool.getConnection();
				preparedStatement=connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, artistId);

				ResultSet rs=preparedStatement.executeQuery();
				int start=0, end=0;
				while(rs.next())
				{
					start=rs.getRow();
					System.out.println("Start: " + start);

					Top topFromTable=new Top();

					topFromTable.setId(rs.getString("ID"));
					topFromTable.setName(rs.getString("Nome"));
					topFromTable.setPrice(rs.getFloat("Prezzo"));
					topFromTable.setDescription(rs.getString("Descrizione"));
					topFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
					topFromTable.setTag(rs.getString("Tag"));
					topFromTable.setArtistId(rs.getString("ID_Artista"));
					topFromTable.setSize(Size.valueOf(rs.getString("Taglia")));
					topFromTable.setCategory(Category.valueOf(rs.getString("Categoria")));
					topFromTable.setPrintType(PrintType.valueOf(rs.getString("TipoStampa")));

					while(rs.next() && rs.getString("ID").equals(topFromTable.getId()));
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
					topFromTable.setImages(productFromTableImages);

					products.add(topFromTable);
					if(rs.getRow()!=0)
						rs.previous();
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
	
	public Collection<Top> doRetrieveByTag(String tag) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		Collection<Top> products=new LinkedList<Top>();
	
		String selectSQL="SELECT * FROM " + TABLE_NAME + " NATURAL JOIN Prodotto INNER JOIN Foto ON ID=ID_Prodotto WHERE Tag= ?";
	
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, tag);
	
			ResultSet rs=preparedStatement.executeQuery();
			int start=0, end=0;
			while(rs.next())
			{
				start=rs.getRow();
				System.out.println("Start: " + start);
	
				Top topFromTable=new Top();
	
				topFromTable.setId(rs.getString("ID"));
				topFromTable.setName(rs.getString("Nome"));
				topFromTable.setPrice(rs.getFloat("Prezzo"));
				topFromTable.setDescription(rs.getString("Descrizione"));
				topFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				topFromTable.setTag(rs.getString("Tag"));
				topFromTable.setArtistId(rs.getString("ID_Artista"));
				topFromTable.setSize(Size.valueOf(rs.getString("Taglia")));
				topFromTable.setCategory(Category.valueOf(rs.getString("Categoria")));
				topFromTable.setPrintType(PrintType.valueOf(rs.getString("TipoStampa")));
	
				while(rs.next() && rs.getString("ID").equals(topFromTable.getId()));
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
				topFromTable.setImages(productFromTableImages);
	
				products.add(topFromTable);
				if(rs.getRow()!=0)
					rs.previous();
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
	
	public Top doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool);
		Top topFromTable=(Top) productDAO.doRetrieveByKey(key);

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
				topFromTable.setSize(Size.valueOf(rs.getString("Taglia")));
				topFromTable.setCategory(Category.valueOf(rs.getString("Categoria")));
				topFromTable.setPrintType(PrintType.valueOf(rs.getString("TipoStampa")));
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

		return topFromTable;
	}

	public Collection<Top> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Top> tops=new LinkedList<Top>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " NATURAL JOIN Prodotto INNER JOIN Foto ON ID=ID_Prodotto";

		if (order!=null && !order.equals(""))
			selectSQL+=" ORDER BY " + order;

		selectSQL+=" LIMIT " + pageInit + ", " + pageEnd;
		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();
			int start=0, end=0;
			while(rs.next())
			{
				start=rs.getRow();
				System.out.println("Start: " + start);

				Top topFromTable=new Top();

				topFromTable.setId(rs.getString("ID"));
				topFromTable.setName(rs.getString("Nome"));
				topFromTable.setPrice(rs.getFloat("Prezzo"));
				topFromTable.setDescription(rs.getString("Descrizione"));
				topFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				topFromTable.setTag(rs.getString("Tag"));
				topFromTable.setArtistId(rs.getString("ID_Artista"));
				topFromTable.setCategory(Category.valueOf(rs.getString("Categoria")));
				topFromTable.setPrintType(PrintType.valueOf(rs.getString("TipoStampa")));
				topFromTable.setSize(Size.valueOf(rs.getString("Taglia")));

				while(rs.next() && rs.getString("ID").equals(topFromTable.getId()));
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
				topFromTable.setImages(productFromTableImages);

				tops.add(topFromTable);
				if(rs.getRow()!=0)
					rs.previous();
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

		return tops;
	}

	public Top doSave(Top top) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool);
		int id=Integer.parseInt(productDAO.doSave(top).getId());

		String insertSQL="INSERT INTO " + TABLE_NAME + " (ID, Taglia, Categoria, TipoStampa) VALUES (?, ?, ?, ?)";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, top.getSize().name());
			preparedStatement.setString(3, top.getCategory().name());
			preparedStatement.setString(4, top.getPrintType().name());

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

		Top newTop=new Top();

		newTop.setId(String.valueOf(id));

		return newTop;
	}

	public boolean doUpdate(Top top) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool);
		productDAO.doUpdate(top);

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Taglia = ?, Categoria = ?, TipoStampa = ? WHERE ID = ?";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, top.getSize().name());
			preparedStatement.setString(2, top.getCategory().name());
			preparedStatement.setString(3, top.getPrintType().name());
			preparedStatement.setString(4, top.getId());

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

	public boolean doDelete(Top top) throws SQLException
	{
		ProductDAO productDAO=new ProductDAO(dbConnectionPool);

		return productDAO.doDelete(top);
	}
}
