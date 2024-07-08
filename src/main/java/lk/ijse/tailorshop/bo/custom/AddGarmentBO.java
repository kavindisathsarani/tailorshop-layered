package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.entity.AddGarment;
import lk.ijse.tailorshop.entity.Material;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AddGarmentBO extends SuperBO {

    public ArrayList<String> currentGarmentId() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getMaterialIds() throws SQLException, ClassNotFoundException;

    Material searchByIdMaterials(String materialId) throws SQLException, ClassNotFoundException;

    public  boolean addGarment(AddGarment ad) throws SQLException ;

    int getGarmentCount() throws SQLException, ClassNotFoundException;
}
