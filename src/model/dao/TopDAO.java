package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Category;
import model.PrintType;
import model.Size;
import model.Top;

public class TopDAO implements DAO<Top>
{
	private static final String TABLE_NAME="Top";
	
	public Top doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO();
		Top topFromTable=(Top) productDAO.doRetrieveByKey(key);

		String selectSQL="SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try 
		{
			int code=(int) key;
			connection=DBConnectionPool.getConnection();
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
				DBConnectionPool.releaseConnection(connection);
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

		try 
		{
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();
			
			while (rs.next())
			{
				Top topFromTable=new Top();
				
				topFromTable.setId(rs.getString("ID"));
				topFromTable.setName(rs.getString("Nome"));
				topFromTable.setPrice(rs.getFloat("Prezzo"));
				topFromTable.setDescription(rs.getString("Descrizione"));
				topFromTable.setRemaining(rs.getInt("Quantit‡Rimanente"));
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
				DBConnectionPool.releaseConnection(connection);
			}
		}
		
		return tops;
	}

	public void doSave(Top top) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		ProductDAO productDAO=new ProductDAO();
		productDAO.doSave(top);
		
		String insertSQL="INSERT INTO " + TABLE_NAME + " (Taglia, Categoria, TipoStampa) VALUES (?, ?, ?)";

		try 
		{
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, top.getSize().name());
			preparedStatement.setString(2, top.getCategory().name());
			preparedStatement.setString(3, top.getPrintType().name());

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

	public void doUpdate(Top top) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;		

		ProductDAO productDAO=new ProductDAO();
		productDAO.doUpdate(top);
		
		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Taglia = ?, Categoria = ?, TipoStampa = ? WHERE ID = ?";
				
		try 
		{
			connection=DBConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);			
			
			preparedStatement.setString(1, top.getSize().name());
			preparedStatement.setString(2, top.getCategory().name());
			preparedStatement.setString(3, top.getPrintType().name());
			preparedStatement.setString(4, top.getId());
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

	public boolean doDelete(Top top) throws SQLException
	{
		ProductDAO productDAO=new ProductDAO();
		
		return productDAO.doDelete(top);
	}
}
