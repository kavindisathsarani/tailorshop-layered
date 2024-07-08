package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.entity.Employee;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");

        while (rst.next()) {
            Employee employee = new Employee(rst.getString("employeeId"), rst.getString("name"),
                    rst.getString("address"), rst.getInt("contactNumber"),
                    rst.getString("position"));

            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee(employeeId,name,address,contactNumber,position) VALUES(?,?,?,?,?)", entity.getEmployeeId(),
                entity.getName(),entity.getAddress(),entity.getContactNumber(),
                entity.getPosition());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name = ?,address = ?, contactNumber = ?, position = ? WHERE employeeId = ?",
                entity.getName(), entity.getAddress(),entity.getContactNumber(), entity.getPosition(),entity.getEmployeeId());
    }

    @Override
    public Employee searchById(String employeeId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE employeeId=?", employeeId + "");
        rst.next();
        return new Employee(employeeId + "", rst.getString("name"),
                rst.getString("address"),rst.getInt("contactNumber"),rst.getString("position"));

    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employeeId=?", employeeId);
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
    public boolean updateQty(ArrayList<Employee> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(ArrayList<Employee> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public int getCount() {
        return 0;
    }
}
