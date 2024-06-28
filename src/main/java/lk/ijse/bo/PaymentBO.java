package lk.ijse.bo;


import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.PaymentDTO;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuPerBO {
    public  boolean savePayment(PaymentDTO payment) throws SQLException ;
    public List<PaymentDTO> getAllPayment() throws SQLException;
    public  boolean updatePayment(PaymentDTO payment) throws SQLException ;
    public  boolean deletePayment(String payId) throws SQLException ;

    public  PaymentDTO searchPaymentById(String paymentId) throws SQLException;
    public  List<String> getId() throws SQLException ;
    public  String getLastPaymentId() throws SQLException ;
}
