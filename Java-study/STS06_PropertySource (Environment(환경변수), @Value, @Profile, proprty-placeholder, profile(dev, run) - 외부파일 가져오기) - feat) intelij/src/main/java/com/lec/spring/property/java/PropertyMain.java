package com.lec.spring.property.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PropertyMain {
    public static void main(String[] args) {
        System.out.println("Main() 시작\n");

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(PropertyConfig.class);
        annotationConfigApplicationContext.refresh();

        DBConn dbConn = annotationConfigApplicationContext.getBean("dbConn", DBConn.class);
        System.out.println(dbConn);
        // DBConn(id=system, pw=123456, url=db.news.net, port=3309)

        annotationConfigApplicationContext.close();
        System.out.println("\nMain() 종료");
    }
}
