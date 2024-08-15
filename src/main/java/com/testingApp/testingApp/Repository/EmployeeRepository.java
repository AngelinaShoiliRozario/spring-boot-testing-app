package com.testingApp.testingApp.Repository;

import com.testingApp.testingApp.Model.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
