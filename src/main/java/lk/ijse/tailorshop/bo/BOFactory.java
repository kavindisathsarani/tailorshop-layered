package lk.ijse.tailorshop.bo;

import lk.ijse.tailorshop.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }


    public enum BOTypes{
        CUSTOMER,EMPLOYEE,MATERIAL,MEASUREMENT,ADDGARMENT,PLACEORDER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case MEASUREMENT:
                return new MeasurementBOImpl();
            case ADDGARMENT:
                return new AddGarmentBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }

}
