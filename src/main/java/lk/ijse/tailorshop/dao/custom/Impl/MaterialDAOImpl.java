package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.MaterialDAO;
import lk.ijse.tailorshop.entity.Material;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public ArrayList<Material> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Material> allMaterials = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM material");

        while (rst.next()) {
            Material material = new Material(rst.getString("materialId"), rst.getString("description"),
                    rst.getDouble("qty"), rst.getDouble("unitPrice"),
                    rst.getString("customerId"));

            allMaterials.add(material);
        }
        return allMaterials;
    }


    @Override
    public boolean save(Material entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO material(materialId,description,qty,unitPrice,customerId) VALUES(?,?,?,?,?)", entity.getMaterialId(),
                entity.getDescription(),entity.getQty(),entity.getUnitPrice(),
                entity.getCustomerId());
    }

    @Override
    public boolean update(Material entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE material SET description = ?,qty = ?, unitPrice = ?, customerId = ? WHERE materialId = ?",
                entity.getDescription(), entity.getQty(),entity.getUnitPrice(), entity.getCustomerId(),entity.getMaterialId());

    }

    @Override
    public Material searchById(String materialId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM material WHERE materialId=?", materialId + "");
        rst.next();
        return new Material(materialId + "", rst.getString("description"),
                rst.getDouble("qty"),rst.getDouble("unitPrice"),rst.getString("customerId"));

    }

    @Override
    public boolean delete(String materialId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM material WHERE materialId=?", materialId);
    }

    @Override
    public ArrayList<String> currentId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> codeList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT materialId FROM material");
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;

    }

    @Override
    public boolean updateQty(ArrayList<Material> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(ArrayList<Material> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }



    private boolean updateQty(MaterialDetail md) throws SQLException, ClassNotFoundException {

        return false;
    }



    @Override
    public int getCount() {
        return 0;
    }

}
