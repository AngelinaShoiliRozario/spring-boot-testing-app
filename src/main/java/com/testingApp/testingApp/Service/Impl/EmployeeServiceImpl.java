package com.testingApp.testingApp.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.testingApp.testingApp.Model.Employee;
import com.testingApp.testingApp.Repository.EmployeeRepository;
import com.testingApp.testingApp.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(existingEmployee.isPresent()){
            throw new RuntimeException("Employee already exist with given email:" + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee is Not present"));
        if(employee.getFirstName() != null){
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null){
            existingEmployee.setLastName(employee.getLastName());
        }
        if(employee.getEmail() != null){
            existingEmployee.setEmail(employee.getEmail());
        }
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

}
