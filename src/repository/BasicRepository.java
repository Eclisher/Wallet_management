package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BasicRepository <T>{
    List<T> findAll(Map<String, Pair> filters) throws SQLException;
    List<T> saveAll(List<T> toSave, String meta);
    T save(T toSave, String meta) throws SQLException;
}
