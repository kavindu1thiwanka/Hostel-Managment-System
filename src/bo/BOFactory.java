package bo;


import bo.impl.LoginBOImpl;
import bo.impl.RoomBOImpl;
import bo.impl.StudentBOImpl;

public class BOFactory { private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends bo.SuperBO> T getBO(bo.BOType boType){
        switch (boType){
            case STUDENT:
                return (T) new StudentBOImpl();
            case ROOM:
                return (T) new RoomBOImpl();
            default:
                return null;
        }
    }

}
