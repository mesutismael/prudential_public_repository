package com.esmael.prudential.service;

import com.esmael.prudential.model.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {


    List<Course> getAllCourse();

    Course getCourseById(int id);

    void saveCourse(Course course);

    void saveCourse(MultipartFile courseFile);

    void deleteCourse(int id);


   }
