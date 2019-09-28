package by.vistal.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDao<T> {
    List<T> getAll() throws SQLException;
    T getByName(String name) throws SQLException;
    T getById(Integer id) throws SQLException;
    Boolean dellAll()throws SQLException;;
    Boolean dellByName(String name)throws SQLException;;
    Boolean dellById(Integer id)throws SQLException;;
    Boolean add(T date) throws SQLException;
    Boolean edit(T date) throws SQLException;
}
