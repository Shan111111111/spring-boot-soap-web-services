package com.shantesh.spring.springbootsoapwebservices.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://shantesh.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String s) {
        super(s);

    }
}
