package lk.ijse.bo.Boimpl;

import lk.ijse.bo.PackageBO;
import lk.ijse.dao.PackageDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;

import lk.ijse.dto.PackageDTO;

import lk.ijse.entity.Package;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageBoImpl implements PackageBO {

    PackageDAO packageDAO = (PackageDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.PACKAGE);
    public  boolean savePackage(PackageDTO Package) throws SQLException {

        return  packageDAO.Save(new Package(Package.getPackageId(),
                Package.getName(),
                Package.getType(),
                Package.getPrice()));
    }


    public  boolean updatePackage(PackageDTO PackageDTO) throws SQLException {

        return  packageDAO.update(new Package(PackageDTO.getPackageId(),
                PackageDTO.getName(),
                PackageDTO.getType(),
                PackageDTO.getPrice()));

    }

    public  boolean deletePackage(String packageId) throws SQLException {

        return  packageDAO.delete(packageId);
    }

    public  PackageDTO searchPackageById(String packageID) throws SQLException {

        Package search = packageDAO.search(packageID);
        return new PackageDTO(search.getPackageId(),search.getName(),search.getType(),search.getPrice());
    }

    public  List<PackageDTO> getAllpackages() throws SQLException {

        List<PackageDTO> puList = new ArrayList<>();
        List<Package> packages = packageDAO.getAll();
        for (Package packagess : packages) {
            PackageDTO paymentDTO = new PackageDTO(
                    packagess.getPackageId(),
                    packagess.getName(),
                    packagess.getType(),
                    packagess.getPrice());
            puList.add(paymentDTO);
        }
        return puList;
    }

    public  List<String> getIds() throws SQLException {

        return  packageDAO.getIds();
    }
    public  String getLastPackageId() throws SQLException {

        return packageDAO.getLastId();
    }
}
