package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.bean.Image;
import model.bean.Patch;
import model.bean.PatchType;
import model.bean.Product;

public class PatchDAO implements DAO<Patch>
{
	private static final String TABLE_NAME="Patch";

	private DBConnectionPool dbConnectionPool;

	public PatchDAO(DBConnectionPool aDBConnectionPool)
	{
		dbConnectionPool=aDBConnectionPool;

		System.out.println("DBConnectionPool " + this.getClass().getSimpleName() + " creation..");
	}

	public Patch doRetrieveByKey(Object key) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool);
		Patch patchFromTable=(Patch) productDAO.doRetrieveByKey(key);

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
				patchFromTable.setMeasures(rs.getString("Misure"));
				patchFromTable.setPatchType(PatchType.valueOf(rs.getString("Tipo")));
				patchFromTable.setMaterial(rs.getString("Tessuto"));
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

		return patchFromTable;
	}

	//TODO metti sta roba anche negli altri metodi di retrieve, idem nel TopDAO
	public Collection<Patch> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		Collection<Patch> patches=new LinkedList<Patch>();

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

				Patch patchFromTable=new Patch();

				patchFromTable.setId(rs.getString("ID"));
				patchFromTable.setName(rs.getString("Nome"));
				patchFromTable.setPrice(rs.getFloat("Prezzo"));
				patchFromTable.setDescription(rs.getString("Descrizione"));
				patchFromTable.setRemaining(rs.getInt("QuantitaRimanente"));
				patchFromTable.setTag(rs.getString("Tag"));
				patchFromTable.setArtistId(rs.getString("ID_Artista"));
				patchFromTable.setMeasures(rs.getString("Misure"));
				patchFromTable.setPatchType(PatchType.valueOf(rs.getString("Tipo")));
				patchFromTable.setMaterial(rs.getString("Tessuto"));

				while(rs.next() && rs.getString("ID").equals(patchFromTable.getId()));
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
				patchFromTable.setImages(productFromTableImages);

				patches.add(patchFromTable);
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

		return patches;
	}

	public Patch doSave(Patch patch) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool);
		int id=Integer.parseInt(productDAO.doSave(patch).getId());

		String insertSQL="INSERT INTO " + TABLE_NAME + " (ID, Misure, Tipo, Tessuto) VALUES (?, ?, ?, ?)";

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, patch.getMeasures());
			preparedStatement.setString(3, patch.getPatchType().name());
			preparedStatement.setString(4, patch.getMaterial());

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
		
		Patch newPatch=new Patch();
		
		newPatch.setId(String.valueOf(id));
		
		return newPatch;
	}

	public boolean doUpdate(Patch patch) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ProductDAO productDAO=new ProductDAO(dbConnectionPool);
		productDAO.doUpdate(patch);

		String updateSQL = "UPDATE " + TABLE_NAME + " SET" +
				" Misure = ?, Tipo = ?, Tessuto = ? WHERE ID = ?";
		int rowsAffected;

		try
		{
			connection=dbConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, patch.getMeasures());
			preparedStatement.setString(2, patch.getPatchType().name());
			preparedStatement.setString(3, patch.getMaterial());
			preparedStatement.setString(4, patch.getId());
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

	public boolean doDelete(Patch patch) throws SQLException
	{
		ProductDAO productDAO=new ProductDAO(dbConnectionPool);

		return productDAO.doDelete(patch);
	}
}
