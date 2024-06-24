package lk.ijse.bo.Boimpl;

import lk.ijse.bo.PaymentBO;
import lk.ijse.dao.PaymentDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.PAYMENT);
    public  boolean savePayment(PaymentDTO paymentDTO) throws SQLException {

        return  paymentDAO.Save(new Payment(paymentDTO.getPayId(),
                paymentDTO.getAmount(),
                paymentDTO.getMethod(),
                paymentDTO.getPaidDate(),
                paymentDTO.getStatus()));

    }

    public  List<PaymentDTO> getAllPayment() throws SQLException {

        List<PaymentDTO> paymentDTOSList = new ArrayList<>();
        List<Payment> payments = paymentDAO.getAll();
        for (Payment payment : payments){
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPayId(),
                    payment.getAmount(),
                    payment.getMethod(),
                    payment.getPaidDate(),
                    payment.getStatus());
            paymentDTOSList.add(paymentDTO);
        }
        return paymentDTOSList;

    }


    public  boolean updatePayment(PaymentDTO paymentDTO) throws SQLException {

        return  paymentDAO.update(new Payment(paymentDTO.getPayId(),
                paymentDTO.getAmount(),
                paymentDTO.getMethod(),
                paymentDTO.getPaidDate(),
                paymentDTO.getStatus()));

    }

    public  boolean deletePayment(String payId) throws SQLException {

        return paymentDAO.delete(payId);
    }

    public  PaymentDTO searchPaymentById(String paymentId) throws SQLException {

        Payment search = paymentDAO.search(paymentId);
        return  new PaymentDTO(search.getPayId(),
                search.getAmount(),
                search.getMethod(),
                search.getPaidDate(),
                search.getStatus());
    }

    public  List<String> getId() throws SQLException {

        return paymentDAO.getIds();
    }


    public  String getLastPaymentId() throws SQLException {

        return paymentDAO.getLastId();
    }
}
