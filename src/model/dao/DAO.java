package model.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T>
{

	public T doRetrieveByKey(Object key) throws SQLException;

	public Collection<T> doRetrieveAll(String order, int pageInit, int pageEnd) throws SQLException;

	public boolean doSave(T object) throws SQLException;

	public boolean doUpdate(T object) throws SQLException;

	public boolean doDelete(T object) throws SQLException;

}
