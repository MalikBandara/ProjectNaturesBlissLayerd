package lk.ijse.bo;

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
            default:
                return null;
        }
    }
}
