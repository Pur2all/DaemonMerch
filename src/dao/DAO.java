package dao;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T>
{

	public T doRetrieveByKey(Object key) throws SQLException;

	public Collection<T> doRetrieveAll(String order) throws SQLException;

	public void doSave(T object) throws SQLException;

	public void doUpdate(T object) throws SQLException;

	public boolean doDelete(T object) throws SQLException;

}
