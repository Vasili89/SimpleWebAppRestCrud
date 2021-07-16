package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee-async")
public class EmployeeControllerAsync {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public EmployeeControllerAsync(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        jmsTemplate.convertAndSend("employee-queue", employee);
        return employee;
    }

}
