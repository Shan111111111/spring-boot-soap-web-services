package com.shantesh.spring.springbootsoapwebservices.soap;

import com.shantesh.courses.CourseDetails;
import com.shantesh.courses.GetCourseDetailsRequest;
import com.shantesh.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @PayloadRoot(namespace = "http://shantesh.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest getCourseDetailsRequest){

        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(getCourseDetailsRequest.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("That would be a wonderful course!");

        response.setCourseDetails(courseDetails);

        return response;
    }
}
