package com.esmael.prudential.service;

import com.esmael.prudential.model.Course;
import com.esmael.prudential.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ServiceImpl implements CourseService {

    @Autowired
    CourseRepository repository;


    @Override
    public List<Course> getAllCourse() {
        return (List<Course>) repository.findAll();
    }


    @Override
    public Course getCourseById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveCourse(Course course) {

        repository.save(course);
    }

    @Override
    public void saveCourse(MultipartFile courseFile) {
  /*      try {
            List<Course> tutorials = ExcelHelper.excelToObjects(courseFile.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }*/
    }

    @Override
    public void deleteCourse(int id) {

        repository.deleteById(id);
    }



}
