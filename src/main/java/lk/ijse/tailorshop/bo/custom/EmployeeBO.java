package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException ;

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;


    public EmployeeDTO searchByIdEmployee(String employeeId) throws SQLException, ClassNotFoundException;


    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException ;
}
