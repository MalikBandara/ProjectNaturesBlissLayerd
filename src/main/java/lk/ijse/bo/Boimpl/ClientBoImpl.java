package lk.ijse.bo.Boimpl;

import lk.ijse.bo.ClientBO;
import lk.ijse.dao.ClientDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;

import lk.ijse.dto.ClientDTO;
import lk.ijse.entity.Client;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientBoImpl implements ClientBO {

    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.CLIENT);

    public  boolean saveClient(ClientDTO clientDTO) throws SQLException, SQLException {

        return  clientDAO.Save(new Client(clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getEmail(),
                clientDTO.getPhone(),
                clientDTO.getAddress(),
                clientDTO.getCheckIn(),
                clientDTO.getCheckOut()));
    }
    public  String getLatestClientId() throws SQLException {

        return clientDAO.getLastId();
    }

    public  boolean updateClient(ClientDTO clientDTO) throws SQLException {

        return  clientDAO.update(new Client(clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getEmail(),
                clientDTO.getPhone(),
                clientDTO.getAddress(),
                clientDTO.getCheckIn(),
                clientDTO.getCheckOut()));
    }

    public  boolean deleteClient(String clientId) throws SQLException {

        return  clientDAO.delete(clientId);
    }

    public ClientDTO searchClientById(String clientId) throws SQLException {

        Client search = clientDAO.search(clientId);
        return new ClientDTO(search.getId(),search.getName(),search.getEmail(),search.getPhone(),search.getAddress(),search.getCheckIn(),search.getCheckOut());
    }
    public  List<ClientDTO> getAllClients() throws SQLException {

        List<ClientDTO> clientList = new ArrayList<>();
        List<Client> all = clientDAO.getAll();
        for (Client client : all) {
            ClientDTO clientDTO  = new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getEmail(),
                    client.getPhone(),
                    client.getAddress(),
                    client.getCheckIn(),
                    client.getCheckOut()
            );
                 clientList.add(clientDTO);
        }
        return clientList;
    }


    public  List<String> getID() throws SQLException {

        return clientDAO.getIds();

    }

    public  String getLastClientId() throws SQLException {

        return clientDAO.getLastId();
    }
}
