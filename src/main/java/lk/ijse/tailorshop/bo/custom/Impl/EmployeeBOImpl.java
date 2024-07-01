package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.EmployeeBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees= new ArrayList<>();

        ArrayList<Employee> all = employeeDAO.getAll();

        for (Employee c : all) {
            allEmployees.add(new EmployeeDTO(c.getEmployeeId(),c.getName(),c.getAddress(),
                    c.getContactNumber(), c.getPosition()));
        }
        return allEmployees;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {

        return employeeDAO.save(new Employee(dto.getEmployeeId(),dto.getName(),dto.getAddress(),
                dto.getContactNumber(), dto.getPosition()));
    }


    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmployeeId(),
                dto.getName(), dto.getAddress(), dto.getContactNumber(), dto.getPosition()));

    }

    @Override
    public EmployeeDTO searchByIdEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        Employee c = employeeDAO.searchById(employeeId);
        return new EmployeeDTO(c.getEmployeeId(),c.getName(),c.getAddress(),c.getContactNumber(),c.getPosition());

    }

    @Override
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);
    }
}
