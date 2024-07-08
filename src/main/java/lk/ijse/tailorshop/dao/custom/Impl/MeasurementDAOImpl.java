package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.MeasurementDAO;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.entity.Measurement;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeasurementDAOImpl implements MeasurementDAO {
    @Override
    public ArrayList<Measurement> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Measurement> allMeasurements = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM measurement");

        while (rst.next()) {
            Measurement measurement = new Measurement(rst.getString("measurementId"),
                    rst.getDouble("neckSize"), rst.getDouble("armhole"),
                    rst.getDouble("sleeveLength"), rst.getDouble("wrist"),
                    rst.getDouble("chest"), rst.getDouble("torsoLength"),
                    rst.getDouble("waist"), rst.getDouble("hip"), rst.getDouble("crotchLength"),
                    rst.getDouble("shoulderLength"), rst.getDouble("thighCircumference"), rst.getDouble("waistToHem"),
                    rst.getString("employeeId"), rst.getString("customerId"));

            allMeasurements.add(measurement);
        }
        return allMeasurements;
    }

    @Override
    public boolean save(Measurement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO measurement VALUES(?, ?, ?, ? ,? , ?,?,?,?,?,?,?,?,?,?)", entity.getMeasurementId(),
                entity.getNeckSize(), entity.getArmhole(), entity.getSleeveLength(), entity.getWrist(), entity.getChest(),
                entity.getTorsoLength(), entity.getWaist(), entity.getHip(), entity.getCrotchLength(),
                entity.getShoulderLength(), entity.getThighCircumference(),
                entity.getWaistToHem(), entity.getEmployeeId(), entity.getCustomerId());
    }

    @Override
    public boolean update(Measurement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE measurement SET neckSize = ?, armhole = ?, sleeveLength = ?, wrist = ?, chest = ?, " +
                        "torsoLength = ?, waist = ?, hip = ?, crotchLength = ?, shoulderLength = ?, thighCircumference = ?, waistToHem = ?, " +
                        "employeeId = ? , customerId = ? WHERE measurementId = ?",
                entity.getNeckSize(), entity.getArmhole(), entity.getSleeveLength(), entity.getWrist(), entity.getChest(),
                entity.getTorsoLength(), entity.getWaist(), entity.getHip(), entity.getCrotchLength(),
                entity.getShoulderLength(), entity.getThighCircumference(), entity.getWaistToHem(), entity.getEmployeeId(),
                entity.getCustomerId(),
                entity.getMeasurementId());

    }

    @Override
    public Measurement searchById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM measurement WHERE customerId = ?", customerId + "");
        if (rst.next()) {
            return new Measurement(
                    rst.getString("measurementId"),
                    rst.getDouble("neckSize"),
                    rst.getDouble("armhole"),
                    rst.getDouble("sleeveLength"),
                    rst.getDouble("wrist"),
                    rst.getDouble("chest"),
                    rst.getDouble("torsoLength"),
                    rst.getDouble("waist"),
                    rst.getDouble("hip"),
                    rst.getDouble("crotchLength"),
                    rst.getDouble("shoulderLength"),
                    rst.getDouble("thighCircumference"),
                    rst.getDouble("waistToHem"),
                    rst.getString("employeeId"),
                    rst.getString("customerId")
            );
        }
        return null;
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        //DELETE FROM measurement WHERE customerId = ?
        return SQLUtil.execute("DELETE FROM measurement WHERE customerId = ?", customerId);

    }

    @Override
    public ArrayList<String> currentId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(ArrayList<MaterialDetail> mdList) {
        return false;
    }

    @Override
    public boolean save(ArrayList<MaterialDetail> mdList) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
