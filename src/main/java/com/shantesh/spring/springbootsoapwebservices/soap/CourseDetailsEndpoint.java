package com.shantesh.spring.springbootsoapwebservices.soap;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.shantesh.courses.CourseDetails;
import com.shantesh.courses.GetAllCourseDetailsRequest;
import com.shantesh.courses.GetAllCourseDetailsResponse;
import com.shantesh.courses.GetCourseDetailsRequest;
import com.shantesh.courses.GetCourseDetailsResponse;
import com.shantesh.spring.springbootsoapwebservices.soap.bean.Course;
import com.shantesh.spring.springbootsoapwebservices.soap.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {
    @Autowired
    CourseDetailsService courseDetailsService;


    @PayloadRoot(namespace = "http://shantesh.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest getCourseDetailsRequest){
    	Course course = courseDetailsService.findById(getCourseDetailsRequest.getId());

        return mapCourseDetails(course);
    }

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
 		return response;
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

    @PayloadRoot(namespace = "http://shantesh.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest getAllCourseDetailsRequest){

        List<Course> courses = courseDetailsService.findAll();

        return mapAllCourseDetails(courses);
    }
}
