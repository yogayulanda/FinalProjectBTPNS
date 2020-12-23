package org.example.repository;

import org.example.model.Pdam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PdamRepository extends JpaRepository<Pdam, Long> {

    String salary = "SELECT DISTINCT e.emp_no, e.birth_date, e.first_name, e.last_name, e.gender, e.hire_date FROM employees AS e INNER JOIN salaries AS s ON e.emp_no=s.emp_no LIMIT 0,10";
    @Query(value=salary, nativeQuery = true)
    List<Pdam> findAll();

}
