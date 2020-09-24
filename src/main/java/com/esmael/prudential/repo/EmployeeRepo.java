package com.esmael.prudential.repo;

import com.esmael.prudential.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee,Integer> {
    Page<Employee> findAll(Pageable pageable);
}
