package com.testingApp.testingApp.Repository;

import com.testingApp.testingApp.Model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test") //will use the application-test.properties
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryUnitTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @DisplayName("Save Employee Test")
    @Rollback(value = false)
    public void saveEmployeeTest() {
        //Actions
        Employee employee = new Employee("John", "Doe", "john.doe@example.com");
        employeeRepository.save(employee);
        //Verify
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
        System.out.println("Employee saved with ID: " + employee.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Get Employee by ID Test")
    public void getEmployeeByIdTest() {
        //Actions
        Employee employee = employeeRepository.findById(1L).orElse(null);
        //Verify
        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee.getFirstName()).isEqualTo("John");
    }

    @Test
    @Order(3)
    @DisplayName("Get List of Employees Test")
    public void getListOfEmployeesTest() {
        //Actions
        List<Employee> employees = employeeRepository.findAll();
        //Verify
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @DisplayName("Update Employee Test")
    @Rollback(value = false)
    public void updateEmployeeTest() {
        //Actions
        Employee employee = employeeRepository.findById(1L).orElse(null);
        employee.setEmail("john.updated@example.com");
        employeeRepository.save(employee);
        Employee updatedEmployee = employeeRepository.findById(1L).orElse(null);
        //Verify
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("john.updated@example.com");
    }

    @Test
    @Order(5)
    @DisplayName("Delete Employee Test")
    @Rollback(value = false)
    public void deleteEmployeeTest() {
        //Actions
        Employee employee = employeeRepository.findById(1L).orElse(null);
        employeeRepository.delete(employee);
        Optional<Employee> optionalEmployee = employeeRepository.findById(1L);
        //Verify
        Assertions.assertThat(optionalEmployee).isEmpty();
    }
}
