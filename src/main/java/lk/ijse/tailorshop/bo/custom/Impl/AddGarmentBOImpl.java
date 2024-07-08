package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.AddGarmentBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.GarmentDAO;
import lk.ijse.tailorshop.dao.custom.MaterialDAO;
import lk.ijse.tailorshop.dao.custom.MaterialDetailDAO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.AddGarmentDTO;
import lk.ijse.tailorshop.entity.AddGarment;
import lk.ijse.tailorshop.entity.Material;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddGarmentBOImpl implements AddGarmentBO {
    MaterialDAO materialDAO= (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);
   MaterialDetailDAO materialDetailDAO= (MaterialDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIALDETAIL);
    GarmentDAO garmentDAO= (GarmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GARMENT);



    @Override
    public ArrayList<String> currentGarmentId() throws SQLException, ClassNotFoundException {
        return garmentDAO.currentId();
    }

    @Override
    public ArrayList<String> getMaterialIds() throws SQLException, ClassNotFoundException {
        return materialDAO.getIds();
    }

    @Override
    public Material searchByIdMaterials(String materialId) throws SQLException, ClassNotFoundException {
        return materialDAO.searchById(materialId);
    }

    @Override
    public boolean addGarment(AddGarmentDTO ad) throws SQLException {
        Connection connection = DbConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = garmentDAO.save(ad.getGarment());
            if (isOrderSaved) {
                boolean isOrderDetailSaved = materialDetailDAO.save(ad.getMdList());
                if (isOrderDetailSaved) {
                    boolean isItemQtyUpdate = materialDetailDAO.updateQty(ad.getMdList());
                    if (isItemQtyUpdate) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public int getGarmentCount() throws SQLException, ClassNotFoundException {
            return garmentDAO.getCount();
    }

}


