package lk.ijse.dao.impl;

import lk.ijse.dao.PaymentDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDAO {
    public  boolean Save(Payment entity) throws SQLException {
        return  SQLUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?)",entity.getPayId(),
                entity.getAmount(),entity.getMethod(),entity.getPaidDate(),entity.getStatus());
    }

    public  List<Payment> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");

        List<Payment> puList = new ArrayList<>();
        while (resultSet.next()){
            String PaymentID = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String method = resultSet.getString(3);
            String paidDate = resultSet.getString(4);
            String status = resultSet.getString(5);
            Payment payment = new Payment(PaymentID, amount, method, paidDate, status);
            puList.add(payment);
        }
        return puList;
    }

    public  boolean update(Payment entity) throws SQLException {
        return  SQLUtil.execute("UPDATE payment SET amount = ?, method = ?, paidDate = ?, status = ? WHERE payId = ?",
                entity.getAmount(),
                entity.getMethod(),
                entity.getPaidDate(),
                entity.getStatus(),
                entity.getPayId());

    }

    public  boolean delete(String payId) throws SQLException {
        return SQLUtil.execute("DELETE FROM payment WHERE payId = ?",payId);
    }

    public Payment search(String paymentId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE payId = ?",paymentId);
        if (resultSet.next()) {
            String payid = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String method = resultSet.getString(3);
            String paidDate = resultSet.getString(4); // Directly retrieve as double
            String Status = resultSet.getString(5); // Directly retrieve as double

            Payment pkg = new Payment(payid, amount, method, paidDate ,Status);

            return pkg;
        } else {
            return null;
        }
    }

    public  List<String> getIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT PayId FROM payment");
        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;
    }


    public  String getLastId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT payId FROM payment ORDER BY payId DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}


