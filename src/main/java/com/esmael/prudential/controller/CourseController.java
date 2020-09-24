/*
package com.app.controller;


import ExcelHelper;
import Course;
import ResponseMessage;
import CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService service;


      @GetMapping("/")
      public ModelAndView welcomePage(){

         return new ModelAndView("wc");
      }

      // Course List

    @GetMapping("/list")
    public ModelAndView courseList(){

          ModelAndView model =new ModelAndView();
        List<Course> courseList = service.getAllCourse();
          model.addObject("courseLists",courseList);
          model.setViewName("course_list");
        return model;
    }


    // add course Page

    @GetMapping("/addCoursePage")
    public ModelAndView addCoursePage(){

          Course course = new Course();
          ModelAndView modelAndView = new ModelAndView();
          modelAndView.addObject("courseForm",course);
          modelAndView.setViewName("form");
        return modelAndView;
    }


    // add Course

    @PostMapping("/addCourse")
    public ModelAndView addCourse(@ModelAttribute("courseForm") Course course){

          service.saveCourse(course);
        return new ModelAndView("redirect:/list");
    }

    // Delete List

    @GetMapping("/deleteCourse/{c_id}")
    public ModelAndView deleteCourse(@PathVariable("c_id") int id){

          service.deleteCourse(id);
        return new ModelAndView("redirect:/list");
    }

    // Update List

    @GetMapping("/updateCourse/{c_id}")
    public ModelAndView updateCourse(@PathVariable("c_id") int id){

          ModelAndView model =new ModelAndView();
          Course course = service.getCourseById(id);
          model.addObject("courseForm", course);
          model.setViewName("form");

        return model;
    }



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                service.saveCourse(file);

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




}
*/
