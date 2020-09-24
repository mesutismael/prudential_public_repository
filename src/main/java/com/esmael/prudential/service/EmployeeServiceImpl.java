package com.esmael.prudential.service;

import com.esmael.prudential.helpers.ExcelHelper;
import com.esmael.prudential.model.Employee;
import com.esmael.prudential.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeService {

    @Autowired
    EmployeeRepo repository;


    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) repository.findAll();
    }


    @Override
    public Employee getEmployeById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveEmployee(Employee employee) {

        repository.save(employee);
    }

    @Override
    public void saveExcel(MultipartFile employeeFile) {
        try {
            List<Employee> employees = ExcelHelper.excelToObjects(employeeFile.getInputStream());
            repository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) {

        repository.deleteById(id);
    }

    @Override
    public void getExcelEmployees(List<Employee> employees) {
        try {
            ExcelHelper.ObjectsToExcel(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Employee> getPaginatedEmployees(Pageable pageable) {
        return repository.findAll(pageable);
    }


}
