package lk.ijse.dao.custom;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T>{

      boolean Save(T T) throws SQLException;

      boolean update(T T) throws SQLException ;
      T search(String Id) throws SQLException ;

      boolean delete(String roomId) throws SQLException ;

      List<T> getAll() throws SQLException;

      List<String> getIds() throws SQLException ;
      String getLastId() throws SQLException ;

}
