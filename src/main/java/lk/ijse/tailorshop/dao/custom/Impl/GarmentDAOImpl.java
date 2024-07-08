package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.GarmentDAO;
import lk.ijse.tailorshop.entity.Garment;
import lk.ijse.tailorshop.entity.Material;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GarmentDAOImpl implements GarmentDAO {

    public ArrayList<String> currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT garmentId FROM garment");
        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString("garmentId"));
        }
        return idList;

    }
    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {

        ArrayList<String> codeList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT garmentId FROM garment");
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    @Override
    public boolean updateQty(ArrayList<Garment> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(ArrayList<Garment> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS garment_count FROM garment");
        rst.next();
        return rst.getInt("garment_count");
    }

    @Override
    public ArrayList<Garment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Garment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO garment VALUES(?, ?, ?,?,?,?,?,?,?)", entity.getGarmentId(),entity.getName(),
                entity.getDescription(),entity.getCategory(),entity.getSize(),entity.getQtyOnHand(),entity.getMaterialCost(),
                entity.getTowage(),entity.getTotalPrice());

    }

    @Override
    public boolean update(Garment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Garment searchById(String garmentId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM garment WHERE garmentId = ?", garmentId + "");
        rst.next();
        return new Garment(garmentId + "",rst.getString("name"), rst.getString("description"),
                rst.getString("category"),rst.getString("size"),
                rst.getDouble("qtyOnHand"),rst.getDouble("materialCost"),
                rst.getDouble("towage"),rst.getDouble("totalPrice"));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
