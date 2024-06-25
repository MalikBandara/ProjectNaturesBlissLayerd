package lk.ijse.dao.custom;


import lk.ijse.dao.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public CrudDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ROOM :
                return new RoomDaoImpl();
            case VEHICLE:
                return new VehicleDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            case GUEST:
                return new GuestDaoImpl();
            case PACKAGE:
                return new PackageDaoImpl();
            case CLIENT:
                return new ClientDaoImpl();
            default:
                return null;

        }
    }
}
