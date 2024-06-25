package lk.ijse.bo.custom;

import lk.ijse.bo.Boimpl.GuestBOImpl;
import lk.ijse.bo.Boimpl.PaymentBoImpl;
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
            case PAYMENT:
                return new PaymentBoImpl();
            case GUEST:
                return new GuestBOImpl();
            default:
                return null;
        }
    }
}
