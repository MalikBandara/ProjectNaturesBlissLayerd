package lk.ijse.bo.Boimpl;


import lk.ijse.bo.GuestBO;
import lk.ijse.dao.GuestDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;

import lk.ijse.dto.BookingDTO;
import lk.ijse.dto.GuestDTO;
import lk.ijse.entity.Guest;

import java.sql.SQLException;

import java.util.List;

public class GuestBOImpl implements GuestBO {

    GuestDAO guestDAO = (GuestDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.GUEST);
    public  boolean saveGuest(GuestDTO guestDTO) throws SQLException {

        return  guestDAO.Save(new Guest(guestDTO.getIdentityDetails(),
                guestDTO.getName(),
                guestDTO.getPassword(),
                guestDTO.getEmail()));
    }

    public  String getTouristEmailFromId(String id) throws SQLException {

        return guestDAO.getTouristEmailFromId(id);
    }

    public  GuestDTO getGuest(String id) throws SQLException {

        Guest guest = guestDAO.getGuest(id);
        return new GuestDTO(guest.getIdentityDetails(),guest.getName(),guest.getPassword(),guest.getEmail());
    }


    public  String getGuestEmailFormID(String id) throws SQLException {

        return guestDAO.getGuestEmailFormID(id);
    }

    public  boolean changePassword(String id, String password) throws SQLException {

        return guestDAO.changePassword(id,password);
    }




    public  List<String> getID() throws SQLException {

        return guestDAO.getIds();

    }

    public  boolean updateGuest(List<BookingDTO> bookingList) {

        return true;
    }

    public  GuestDTO searchGuesttById(String identity) throws SQLException {

        Guest guest = guestDAO.searchGuesttById(identity);
        return new GuestDTO(guest.getIdentityDetails(),guest.getName(),guest.getPassword(),guest.getEmail());

    }

    @Override
    public int getGuestCount() throws SQLException {
        return guestDAO.getGuestCount();
    }


}
