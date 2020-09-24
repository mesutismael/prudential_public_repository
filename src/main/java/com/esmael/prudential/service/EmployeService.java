package com.esmael.prudential.service;

import com.esmael.prudential.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeService {


    List<Employee> getAllEmployees();

    Employee getEmployeById(int id);

    void saveEmployee(Employee employee);

    void saveExcel(MultipartFile employeeFile);

    void deleteEmployee(int id);

    void getExcelEmployees(List<Employee> employees);

    Page<Employee> getPaginatedEmployees(Pageable pageable);


}
