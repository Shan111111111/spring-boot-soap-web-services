package com.shantesh.spring.springbootsoapwebservices.soap.service;


import com.shantesh.spring.springbootsoapwebservices.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    public enum Status {
        SUCCESS, FAILURE
    }

    private  static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "spoken english", "you will get better in english");
        courses.add(course1);
        Course course2 = new Course(2, "spoken kannada", "kannadam siri gelge");
        courses.add(course2);
        Course course3 = new Course(3, "spoken spanish", "efrada tsu mi");
        courses.add(course3);
    }

    public  Course findById(int id){
        for (Course course:courses){
            if (course.getId() == id){
                return course;
            }
        } return null;
    }

    public  List<Course> findAll(){
        return  courses;
    }


    public Status delete(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;

    }
}
