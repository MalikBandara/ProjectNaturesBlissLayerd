package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;

import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.entity.Payment;

import java.sql.SQLException;


public interface PaymentDAO extends CrudDAO<Payment> {
     boolean PaymentIdStatusForBooking(String payid) throws SQLException ;

}
