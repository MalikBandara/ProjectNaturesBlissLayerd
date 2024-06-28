package lk.ijse.bo;


import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.ClientDTO;


import java.sql.SQLException;

import java.util.List;

public interface ClientBO extends SuPerBO {
    public  boolean saveClient(ClientDTO client) throws SQLException, SQLException ;
    public  String getLatestClientId() throws SQLException ;
    public  boolean updateClient(ClientDTO client) throws SQLException ;
    public  boolean deleteClient(String clientId) throws SQLException ;
    public  ClientDTO searchClientById(String clientId) throws SQLException ;
    public List<ClientDTO> getAllClients() throws SQLException ;
    public  List<String> getID() throws SQLException ;
    public  String getLastClientId() throws SQLException ;
}
