package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.dto.MeasurementDTO;
import lk.ijse.tailorshop.entity.Measurement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MeasurementBO extends SuperBO {
    public ArrayList<MeasurementDTO> getAllMeasurements() throws SQLException, ClassNotFoundException;
    public boolean saveMeasurements(MeasurementDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateMeasurements(MeasurementDTO dto) throws SQLException, ClassNotFoundException;

    public MeasurementDTO searchByIdMeasurements(String customerId) throws SQLException, ClassNotFoundException;
    public boolean deleteMeasurements(String customerId) throws SQLException, ClassNotFoundException ;
}
