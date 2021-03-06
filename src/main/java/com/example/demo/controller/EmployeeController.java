package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAll() {
        return employeeService.getAll();

    }

    @PostMapping("/")
    public String add(@RequestBody Employee employee) {
        return employeeService.add(employee);

    }

    @PutMapping("/")
    public String update(@RequestBody Employee employee) {
        return employeeService.update(employee);

    }

    @DeleteMapping("/")
    public String delete(@PathParam("id") String id) {
        return employeeService.delete(id);

    }
}
