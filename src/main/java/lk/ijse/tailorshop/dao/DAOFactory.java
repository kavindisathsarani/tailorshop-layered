package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.tailorshop.dao.custom.Impl.EmployeeDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            default:
                return null;
        }
    }
}