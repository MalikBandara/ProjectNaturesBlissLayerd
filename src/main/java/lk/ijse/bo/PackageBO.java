package lk.ijse.bo;
import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.PackageDTO;
import java.sql.SQLException;
import java.util.List;

public interface PackageBO extends SuPerBO {
      boolean savePackage(PackageDTO Package) throws SQLException ;
      boolean updatePackage(PackageDTO Package) throws SQLException ;
      boolean deletePackage(String packageId) throws SQLException ;
      PackageDTO searchPackageById(String packageID) throws SQLException ;
     List<PackageDTO> getAllpackages() throws SQLException ;
      List<String> getIds() throws SQLException ;
      String getLastPackageId() throws SQLException ;
}
