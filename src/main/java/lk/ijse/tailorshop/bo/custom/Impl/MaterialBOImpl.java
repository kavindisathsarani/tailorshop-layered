package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.MaterialBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.dao.custom.MaterialDAO;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.dto.MaterialDTO;
import lk.ijse.tailorshop.entity.Employee;
import lk.ijse.tailorshop.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialBOImpl implements MaterialBO {
    MaterialDAO materialDAO= (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);


    @Override
    public ArrayList<MaterialDTO> getAllMaterial() throws SQLException, ClassNotFoundException {
        ArrayList<MaterialDTO> allMaterials= new ArrayList<>();

        ArrayList<Material> all = materialDAO.getAll();

        for (Material c : all) {
            allMaterials.add(new MaterialDTO(c.getMaterialId(),c.getDescription(),c.getQty(),
                    c.getUnitPrice(), c.getCustomerId()));
        }
        return allMaterials;
    }

    @Override
    public boolean saveMaterial(MaterialDTO dto) throws SQLException, ClassNotFoundException {
        return materialDAO.save(new Material(dto.getMaterialId(),dto.getDescription(),dto.getQty(),
                dto.getUnitPrice(), dto.getCustomerId()));
    }

    @Override
    public boolean updateMaterial(MaterialDTO dto) throws SQLException, ClassNotFoundException {
        return materialDAO.update(new Material(dto.getMaterialId(),
                dto.getDescription(), dto.getQty(), dto.getUnitPrice(), dto.getCustomerId()));

    }

    @Override
    public MaterialDTO searchByIdMaterial(String materialId) throws SQLException, ClassNotFoundException {
        Material c = materialDAO.searchById(materialId);
        return new MaterialDTO(c.getMaterialId(),c.getDescription(),c.getQty(),c.getUnitPrice(),c.getCustomerId());

    }

    @Override
    public boolean deleteMaterial(String materialId) throws SQLException, ClassNotFoundException {
        return materialDAO.delete(materialId);
    }
}
