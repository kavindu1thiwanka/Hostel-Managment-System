package bo;


import bo.impl.RoomBOImpl;
import bo.impl.StudentBOImpl;

public class BOFactory {
//    private static BOFactory boFactory;

//    private BOFactory(){}
//
//    public static BOFactory getInstance(){
//        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
//    }
//
//    public <T extends bo.SuperBO> T getBO(bo.BOType boType){
//        switch (boType){
//            case STUDENT:
//                return (T) new StudentBOImpl();
//            case ROOM:
//                return (T) new RoomBOImpl();
//            default:
//                return null;
//        }
//    }

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();

            default:
                return null;
        }
    }

    public enum BoTypes {
        STUDENT, ROOM
    }
}
