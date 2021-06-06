package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployee() {
        logger.info("In EmployeeService getAll");
        return employeeDao.findAll();
    }

    public Employee getEmployeeById(Long id) {
        logger.info("In EmployeeService getEmployeeById {}", id);
        return employeeDao.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee with id " + id + " not found."));
    }

    public void createEmployee(Employee employee) {
        logger.info("In EmployeeService createEmployee {}", employee);
        employeeDao.save(employee);
    }

    public void updateEmployee(Long id, Employee employee) {
        Employee employeeForUpdate = employeeDao.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee with id " + id + " not found."));
        if (employee.getFirstName() != null) employeeForUpdate.setFirstName(employee.getFirstName());
        if (employee.getLastName() != null) employeeForUpdate.setLastName(employee.getLastName());
        if (employee.getDepartmentId() != null) employeeForUpdate.setDepartmentId(employee.getDepartmentId());
        if (employee.getJobTitle() != null) employeeForUpdate.setJobTitle(employee.getJobTitle());
        if (employee.getDateOfBirth() != null) employeeForUpdate.setDateOfBirth(employee.getDateOfBirth());
        employeeForUpdate.setEmployeeId(id);
        this.validAndSave(employeeForUpdate);
    }

    public void deleteEmployeeById(Long id) {
        boolean exists = employeeDao.existsById(id);
        if (!exists) {
            logger.error("In EmployeeService deleteEmployeeById {}. Employee not found.", id);
            throw new EmployeeNotFoundException("Employee with id " + id + " not found.");
        } else {
            logger.info("In EmployeeService deleteEmployeeById {}", id);
            employeeDao.deleteById(id);
        }
    }

    public void validAndSave(@Valid Employee employee) {
        employeeDao.save(employee);
        logger.info("In EmployeeService updateEmployee with id {}", employee.getEmployeeId());
    }

}
