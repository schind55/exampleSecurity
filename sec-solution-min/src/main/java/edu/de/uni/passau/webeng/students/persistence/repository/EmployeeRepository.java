package edu.de.uni.passau.webeng.students.persistence.repository;

import edu.de.uni.passau.webeng.students.persistence.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
