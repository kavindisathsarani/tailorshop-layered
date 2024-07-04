package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.dto.MaterialDTO;
import lk.ijse.tailorshop.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialBO extends SuperBO {

    public ArrayList<MaterialDTO> getAllMaterial() throws SQLException, ClassNotFoundException;


    public boolean saveMaterial(MaterialDTO dto) throws SQLException, ClassNotFoundException;


    public boolean updateMaterial(MaterialDTO dto) throws SQLException, ClassNotFoundException;


    public MaterialDTO searchByIdMaterial(String materialId) throws SQLException, ClassNotFoundException;


    public boolean deleteMaterial(String materialId) throws SQLException, ClassNotFoundException;
}
