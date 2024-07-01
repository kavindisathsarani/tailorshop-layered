package lk.ijse.tailorshop.bo;

import lk.ijse.tailorshop.bo.custom.Impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }


    public enum BOTypes{
        CUSTOMER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;
        }
    }

}
