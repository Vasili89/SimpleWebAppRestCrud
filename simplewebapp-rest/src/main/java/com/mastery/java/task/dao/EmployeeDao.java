package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.List;
import java.util.Locale;

@Repository
public class EmployeeDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        String sql = "SELECT * FROM employeedb.employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Employee findById(Integer id){
        String sql = "SELECT * FROM employeedb.employee WHERE employee_id = ?";
        return  jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id);

    }

    public void create(Employee employee) {
        String sql ="INSERT INTO employeedb.employee(" +
                "first_name, last_name, department_id, job_title, gender, date_of_birth)" +
                " VALUES(?, ?, ?, ?, ?, ?)";
        Object[] params = {employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                        employee.getJobTitle(), employee.getGender().name().toLowerCase(Locale.ROOT), employee.getDateOfBirth()};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.OTHER, Types.VARCHAR};
        jdbcTemplate.update(sql, params, types);
    }

    public void update(Integer id, Employee employee) {
        String sql = "UPDATE employeedb.employee set first_name = ?," +
                " last_name = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";
        Object[] params = {employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender().name().toLowerCase(Locale.ROOT),
                employee.getDateOfBirth(), id};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.VARCHAR,
                Types.OTHER, Types.VARCHAR, Types.BIGINT};
        jdbcTemplate.update(sql, params, types);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM employeedb.employee WHERE employee_id = ?";
        jdbcTemplate.update(sql, id);
    }

}

