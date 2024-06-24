package lk.ijse.dao.custom;


import lk.ijse.dao.impl.RoomDaoImpl;

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
            default:
                return null;

        }
    }
}
