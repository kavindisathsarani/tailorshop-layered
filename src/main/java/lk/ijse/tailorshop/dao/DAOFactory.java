package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.dao.custom.Impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
