package lk.ijse.culinaryacademy.bo;

import lk.ijse.culinaryacademy.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){

        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,DASHBOARD,PAYMENT,PLACEPAYMENT,STUDENT,COURSE
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBOImpl ();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACEPAYMENT:
                return new PlacePaymentBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case COURSE:
                return new CourseBOImpl();
            default:
                return null;
        }
    }
}
