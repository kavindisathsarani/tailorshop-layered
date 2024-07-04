package lk.ijse.tailorshop.bo;

import lk.ijse.tailorshop.bo.custom.Impl.CustomerBOImpl;
import lk.ijse.tailorshop.bo.custom.Impl.EmployeeBOImpl;
import lk.ijse.tailorshop.bo.custom.Impl.MaterialBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }


    public enum BOTypes{
        CUSTOMER,EMPLOYEE,MATERIAL
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
            default:
                return null;
        }
    }

}
