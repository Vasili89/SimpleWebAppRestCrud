package com.mastery.java.task.dto;

import com.mastery.java.task.validation.MajorityAge;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employee", schema = "employeedb")
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "first_name")
    @NotEmpty(message = "First name should not be empty.")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name should not be empty.")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters.")
    private String lastName;

    @Column(name = "department_id")
    @Min(value = 1, message = "Department Id should be between 1 and 10.")
    @Max(value = 10, message = "Department Id should be between 1 and 10.")
    private Long departmentId;

    @Column(name = "job_title")
    @NotEmpty(message = "Job title should not be empty.")
    private String jobTitle;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    @MajorityAge(message = "Employee must be at least 18 years old.")
    private LocalDate dateOfBirth;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

}
