package lk.ijse.GameCafe.model;

import lk.ijse.GameCafe.db.DbConnection;
import lk.ijse.GameCafe.dto.CustomerDto;
import lk.ijse.GameCafe.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {


    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, dto.getEmpId());
        ps.setString(2, dto.getEmpName());
        ps.setString(3, dto.getEmpContactNum());
        ps.setString(4, dto.getEmpSalary());
        ps.setString(5, dto.getEmpAddress());

        int i = ps.executeUpdate();
        return i > 0;
    }


    public List<EmployeeDto> getAllEmployees() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        List<EmployeeDto> list = new ArrayList<>();

        while (resultSet.next()) {
            EmployeeDto dto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

            list.add(dto);

        }
        return list;
    }

    public EmployeeDto SearchModel(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE cus_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);

        ResultSet resultSet = ps.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empContactNum = resultSet.getString(3);
            String empSalary = resultSet.getString(4);
            String empAddress = resultSet.getString(5);

            dto = new EmployeeDto( empId, empName, empContactNum, empSalary, empAddress);
        }
        return dto;
    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE employee SET  emp_name=?,emp_contact_num=?,emp_salary=?, emp_address=? WHERE emp_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, dto.getEmpName());
        ps.setString(2, dto.getEmpContactNum());
        ps.setString(3, dto.getEmpSalary());
        ps.setString(4, dto.getEmpAddress());
        ps.setString(5, dto.getEmpId());

        return ps.executeUpdate()>0;

    }

    public boolean deleteEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM employee WHERE emp_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,id);

        return ps.executeUpdate()>0;
    }
}


