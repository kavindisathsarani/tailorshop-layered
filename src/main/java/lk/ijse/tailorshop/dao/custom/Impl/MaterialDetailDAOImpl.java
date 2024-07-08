package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.MaterialDAO;
import lk.ijse.tailorshop.dao.custom.MaterialDetailDAO;
import lk.ijse.tailorshop.entity.Material;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDetailDAOImpl implements MaterialDetailDAO {


    @Override
    public ArrayList<MaterialDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(MaterialDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO materialDetail VALUES(?, ?, ?)", entity.getGarmentId(),
                entity.getMaterialId(),entity.getQty());

    }

    @Override
    public boolean update(MaterialDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MaterialDetail searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> currentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(ArrayList<MaterialDetail> mdList) throws SQLException, ClassNotFoundException {
        for (MaterialDetail md : mdList) {
            if(!updateQty(md)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(MaterialDetail md) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE material SET qty = qty - ? WHERE materialId = ?";
        // Execute update statement with parameters
        // Replace SQLUtil.execute() with your actual method to execute SQL statements
        return SQLUtil.execute(sql, md.getQty(), md.getMaterialId());
    }
//
    @Override
    public boolean save(ArrayList<MaterialDetail> mdList) throws SQLException, ClassNotFoundException {
        for (MaterialDetail md : mdList) {
            if(!save(md)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
