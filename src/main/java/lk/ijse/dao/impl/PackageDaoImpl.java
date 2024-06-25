package lk.ijse.dao.impl;

import lk.ijse.dao.PackageDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.Booking;
import lk.ijse.dto.PackageDTO;
import lk.ijse.entity.Package;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageDaoImpl implements PackageDAO {
    public  boolean Save(Package enntity) throws SQLException {

        return  SQLUtil.execute("INSERT INTO Package VALUES (?,?,?,?)",enntity.getPackageId(),
                enntity.getName(),enntity.getType(),enntity.getPrice());
    }


    public  boolean update(Package entity) throws SQLException {

        return  SQLUtil.execute("UPDATE Package SET Name = ?,Type = ?,Price = ? WHERE Package_id = ?",entity.getName(),
                entity.getType(),entity.getPrice(),entity.getPackageId());

    }

    public  boolean delete(String packageId) throws SQLException {

        return SQLUtil.execute("DELETE FROM Package WHERE Package_id = ?",packageId);
    }

    public  Package search(String packageID) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Package WHERE Package_id = ?",packageID);
        if (resultSet.next()) {
            String packageId = resultSet.getString(1);
            String packageName = resultSet.getString(2);
            String description = resultSet.getString(3);
            double price = resultSet.getDouble(4);


            Package pkg = new Package(packageId, packageName, description, price);

            return pkg;
        } else {
            return null;
        }
    }

    public  List<Package> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Package");

        List<Package> puList = new ArrayList<>();
        while (resultSet.next()){
            String PackageID = resultSet.getString(1);
            String PackageName = resultSet.getString(2);
            String Type = resultSet.getString(3);
            double Price = Double.parseDouble(resultSet.getString(4));

            Package Package = new Package(PackageID, PackageName, Type, Price);
            puList.add(Package);
        }
        return puList;
    }

    public  List<String> getIds() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Package_id FROM Package");

        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;
    }




    public  String getLastId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Package_id FROM Package ORDER BY Package_id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}
