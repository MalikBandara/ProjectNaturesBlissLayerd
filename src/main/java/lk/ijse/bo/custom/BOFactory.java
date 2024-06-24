package lk.ijse.bo.custom;

import lk.ijse.bo.Boimpl.RoomBoImpl;
import lk.ijse.bo.Boimpl.VehicleBoImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public SpuerBO getBOTYpes(BOTypes boTypes){
        switch (boTypes){
            case ROOM :
                return new RoomBoImpl();
            case VEHICLE:
                return new VehicleBoImpl();
            default:
                return null;
        }
    }
}
