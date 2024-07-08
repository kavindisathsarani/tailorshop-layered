package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE,MATERIAL,MEASUREMENT,MATERIALDETAIL,GARMENT,OREDER,ORDERDETAIL
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case MEASUREMENT:
                return new MeasurementDAOImpl();
            case MATERIALDETAIL:
                return new MaterialDetailDAOImpl();
            case GARMENT:
                return new GarmentDAOImpl();
            case OREDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
