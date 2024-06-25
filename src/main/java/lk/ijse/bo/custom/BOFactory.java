package lk.ijse.bo.custom;

import lk.ijse.bo.Boimpl.*;

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
            case PACKAGE:
                return  new PackageBoImpl();
            case CLIENT:
                return new ClientBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            default:
                return null;
        }
    }
}
