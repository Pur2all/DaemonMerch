package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Category;
import model.bean.PrintType;
import model.bean.Size;
import model.bean.Top;

public class TopDAO implements DAO<Top>
{
	private static final String TABLE_NAME="Top";

	private int pageInit, pageEnd;
	private DBConnectionPool dbConnectionPool;

	public TopDAO(DBConnectionPool aDBConnectionPool, int aPageInit, int aPageEnd)
	{
		pageInit=aPageInit;
		pageEnd=aPageEnd;
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Top doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool, pageInit, pageEnd);
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

	public Collection<Top> doRetrieveAll(String order) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Top> tops=new LinkedList<Top>();

		String selectSQL="SELECT * FROM " + TABLE_NAME + " INNER JOIN Prodotto";

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
				Top topFromTable=new Top();

				topFromTable.setId(rs.getString("ID"));
				topFromTable.setName(rs.getString("Nome"));
				topFromTable.setPrice(rs.getFloat("Prezzo"));
				topFromTable.setDescription(rs.getString("Descrizione"));
				topFromTable.setRemaining(rs.getInt("Quantit�Rimanente"));
				topFromTable.setTag(rs.getString("Tag"));
				topFromTable.setArtistId(rs.getString("ID_Artista"));
				topFromTable.setSize(Size.valueOf(rs.getString("Taglia")));
				topFromTable.setCategory(Category.valueOf(rs.getString("Categoria")));
				topFromTable.setPrintType(PrintType.valueOf(rs.getString("TipoStampa")));
				tops.add(topFromTable);
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

	public boolean doSave(Top top) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool, pageInit, pageEnd);
		productDAO.doSave(top);

		String insertSQL="INSERT INTO " + TABLE_NAME + " (Taglia, Categoria, TipoStampa) VALUES (?, ?, ?)";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, top.getSize().name());
			preparedStatement.setString(2, top.getCategory().name());
			preparedStatement.setString(3, top.getPrintType().name());

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

	public boolean doUpdate(Top top) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool, pageInit, pageEnd);
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
		ProductDAO productDAO=new ProductDAO(dbConnectionPool, pageInit, pageEnd);

		return productDAO.doDelete(top);
	}
}
