package com.testingApp.testingApp.Repository;

import com.testingApp.testingApp.Model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryUnitTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @DisplayName("Save Employee Test")
    @Rollback(value = false)
    public void saveEmployeeTest() {
        Employee employee = new Employee("John", "Doe", "john.doe@example.com");

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("Get Employee by ID Test")
    public void getEmployeeByIdTest() {
        Employee employee = employeeRepository.findById(1L).orElse(null);

        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee.getFirstName()).isEqualTo("John");
    }

    @Test
    @Order(3)
    @DisplayName("Get List of Employees Test")
    public void getListOfEmployeesTest() {
        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @DisplayName("Update Employee Test")
    @Rollback(value = false)
    public void updateEmployeeTest() {
        Employee employee = employeeRepository.findById(1L).orElse(null);

        employee.setEmail("john.updated@example.com");
        employeeRepository.save(employee);

        Employee updatedEmployee = employeeRepository.findById(1L).orElse(null);

        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("john.updated@example.com");
    }

    @Test
    @Order(5)
    @DisplayName("Delete Employee Test")
    @Rollback(value = false)
    public void deleteEmployeeTest() {
        Employee employee = employeeRepository.findById(1L).orElse(null);

        employeeRepository.delete(employee);

        Optional<Employee> optionalEmployee = employeeRepository.findById(1L);

        Assertions.assertThat(optionalEmployee).isEmpty();
    }
}
