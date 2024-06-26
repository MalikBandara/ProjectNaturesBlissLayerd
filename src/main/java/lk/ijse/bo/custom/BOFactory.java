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
    public SuPerBO getBOTYpes(BOTypes boTypes){
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
            case ADMIN:
                return new AdminBoImpl();
            case RESERVATION:
                return new ReservationBoImpl();
            case BOOKING:
                return new BookingBoImpl();
            default:
                return null;
        }
    }
}
