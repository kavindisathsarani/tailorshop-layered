package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.MeasurementBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.MaterialDAO;
import lk.ijse.tailorshop.dao.custom.MeasurementDAO;
import lk.ijse.tailorshop.dto.MaterialDTO;
import lk.ijse.tailorshop.dto.MeasurementDTO;
import lk.ijse.tailorshop.entity.Material;
import lk.ijse.tailorshop.entity.Measurement;

import java.sql.SQLException;
import java.util.ArrayList;

public class MeasurementBOImpl implements MeasurementBO {

    MeasurementDAO measurementDAO= (MeasurementDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEASUREMENT);

    @Override
    public ArrayList<MeasurementDTO> getAllMeasurements() throws SQLException, ClassNotFoundException {
        ArrayList<MeasurementDTO> allMeasurements= new ArrayList<>();

        ArrayList<Measurement> all = measurementDAO.getAll();

        for (Measurement c : all) {
            allMeasurements.add(new MeasurementDTO(c.getMeasurementId(),c.getNeckSize(),c.getArmhole(),
                    c.getSleeveLength(), c.getWrist(), c.getChest(),
                    c.getTorsoLength(),  c.getWaist(),  c.getHip(),
                    c.getCrotchLength(), c.getShoulderLength(), c.getThighCircumference(),
                    c.getWaistToHem(),  c.getEmployeeId(), c.getCustomerId()));
        }
        return allMeasurements;
    }

    @Override
    public boolean saveMeasurements(MeasurementDTO dto) throws SQLException, ClassNotFoundException {
        return measurementDAO.save(new Measurement(dto.getMeasurementId(),dto.getNeckSize(),dto.getArmhole(),
                dto.getSleeveLength(), dto.getWrist(), dto.getChest(), dto.getTorsoLength(),
                dto.getWaist(), dto.getHip(), dto.getCrotchLength(), dto.getShoulderLength(),
                dto.getThighCircumference(),dto.getWaistToHem(), dto.getEmployeeId(),dto.getCustomerId() ));
    }

    @Override
    public boolean updateMeasurements(MeasurementDTO dto) throws SQLException, ClassNotFoundException {
        return measurementDAO.update(new Measurement(dto.getMeasurementId(),dto.getNeckSize(),dto.getArmhole(),
                dto.getSleeveLength(), dto.getWrist(), dto.getChest(), dto.getTorsoLength(),
                dto.getWaist(), dto.getHip(), dto.getCrotchLength(), dto.getShoulderLength(),
                dto.getThighCircumference(),dto.getWaistToHem(), dto.getEmployeeId(),dto.getCustomerId()));

    }

    @Override
    public MeasurementDTO searchByIdMeasurements(String customerId) throws SQLException, ClassNotFoundException {
        Measurement c = measurementDAO.searchById(customerId);
        return new MeasurementDTO( c.getMeasurementId(),
                c.getNeckSize(),
                c.getArmhole(),
                c.getSleeveLength(),
                c.getWrist(),
                c.getChest(),
                c.getTorsoLength(),
                c.getWaist(),
                c.getHip(),
                c.getCrotchLength(),
                c.getShoulderLength(),
                c.getThighCircumference(),
                c.getWaistToHem(),
                c.getEmployeeId(),
                c.getCustomerId());

    }

    @Override
    public boolean deleteMeasurements(String customerId) throws SQLException, ClassNotFoundException {
        return measurementDAO.delete(customerId);

    }
}
