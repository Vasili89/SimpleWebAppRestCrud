package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployee() {
        return employeeDao.getAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeDao.findById(id);
    }

    public void createEmployee(Employee employee) {
        employeeDao.create(employee);
    }

    public void updateEmployee(int id, Employee employee) {
        employeeDao.update(id, employee);
    }

    public void deleteEmployeeById(int id) {
        employeeDao.delete(id);
    }
}
