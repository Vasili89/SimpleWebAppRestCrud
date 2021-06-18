package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

    Employee findByFirstNameAndLastName(String name1, String name2);

    List<Employee> findByFirstNameStartingWithIgnoreCase(String str);

    @Query("select e from Employee e where e.jobTitle = ?1")
    List<Employee> findByJob(String jobTitle);

}

