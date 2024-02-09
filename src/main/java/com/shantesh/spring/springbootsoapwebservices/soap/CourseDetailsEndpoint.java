package com.shantesh.spring.springbootsoapwebservices.soap;

import java.util.Iterator;
import java.util.List;

import com.shantesh.courses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.shantesh.spring.springbootsoapwebservices.soap.bean.Course;
import com.shantesh.spring.springbootsoapwebservices.soap.service.CourseDetailsService;
import com.shantesh.spring.springbootsoapwebservices.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {
    @Autowired
    CourseDetailsService courseDetailsService;


    @PayloadRoot(namespace = "http://shantesh.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest getCourseDetailsRequest){
    	Course course = courseDetailsService.findById(getCourseDetailsRequest.getId());

		if (course == null){
			throw new CourseNotFoundException("invalid id -" + getCourseDetailsRequest.getId());
		}
        return mapCourseDetails(course);
    }

	@PayloadRoot(namespace = "http://shantesh.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest getAllCourseDetailsRequest){

		List<Course> courses = courseDetailsService.findAll();

		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://shantesh.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest deleteCourseDetailsRequest){

		Status status = courseDetailsService.delete(deleteCourseDetailsRequest.getId());
		DeleteCourseDetailsResponse deleteResponse = new DeleteCourseDetailsResponse();
		deleteResponse.setStatus(mastatus(status));
		return deleteResponse;
	}

	private com.shantesh.courses.Status mastatus(Status status) {
		if (status == Status.FAILURE) {
            return com.shantesh.courses.Status.FAILURE;
        } else return com.shantesh.courses.Status.SUCCESS;
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




}
