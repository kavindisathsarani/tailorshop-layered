package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.MeasurementDAO;
import lk.ijse.tailorshop.entity.Measurement;

import java.sql.SQLException;
import java.util.ArrayList;

public class MeasurementDAOImpl implements MeasurementDAO {
    @Override
    public ArrayList<Measurement> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Measurement entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Measurement entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Measurement searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
