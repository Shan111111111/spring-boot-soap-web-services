package com.shantesh.spring.springbootsoapwebservices.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){

        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");

    }

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesXsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("coursePort");
        defaultWsdl11Definition.setTargetNamespace("http://shantesh.com/courses");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setSchema(coursesXsdSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema coursesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }
}
