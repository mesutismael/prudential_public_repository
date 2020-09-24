package com.esmael.prudential.controller;


import com.esmael.prudential.helpers.ExcelHelper;
import com.esmael.prudential.model.Employee;
import com.esmael.prudential.model.ResponseMessage;
import com.esmael.prudential.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class EmployeeController {

    @Autowired
    EmployeService service;


    @GetMapping("/")
    public ModelAndView welcomePage(){

        return new ModelAndView("wc");
    }

    // employee List

    @GetMapping("/list")
    public ModelAndView employeeList(){

        ModelAndView model =new ModelAndView();
        List<Employee> employeeList = service.getAllEmployees();
        model.addObject("employeeList",employeeList);
        model.setViewName("employee_list");
        return model;
    }


    @GetMapping(value = "/listpage/{page}")
    public ModelAndView employeeListPage(@PathVariable("page") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Employee> employeePage = service.getPaginatedEmployees(pageable);
        ModelAndView modelAndView =new ModelAndView();
        int totalPages = employeePage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeEmployeeList", true);
        modelAndView.addObject("employeeList", employeePage.getContent());
        return modelAndView;
    }



    // add employee Page

    @GetMapping("/addEmployeePage")
    public ModelAndView addEmployeePage(){

        Employee employee = new Employee();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employeeForm",employee);
        modelAndView.setViewName("form");
        return modelAndView;
    }


    // add employee

    @PostMapping("/addEmployee")
    public ModelAndView addEmployee(@ModelAttribute("employeeForm") Employee employee){

        service.saveEmployee(employee);
        return new ModelAndView("redirect:/list");
    }

    // Delete List

    @GetMapping("/deleteEmployee/{Id}")
    public ModelAndView deleteCourse(@PathVariable("Id") int id){

        service.deleteEmployee(id);
        return new ModelAndView("redirect:/list");
    }

    // Update List

    @GetMapping("/updateEmployee/{Id}")
    public ModelAndView updateCourse(@PathVariable("Id") int id){

        ModelAndView model =new ModelAndView();
        Employee employee = service.getEmployeById(id);
        model.addObject("employeeForm", employee);
        model.setViewName("form");

        return model;
    }



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                service.saveExcel(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/download")
    public ResponseEntity<ResponseMessage> download() {
        String message = "File Download successful";
        List<Employee> employeeList = service.getAllEmployees();
             service.getExcelEmployees(employeeList);
            try {

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "File Download Failed";
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }


    }




    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

}

