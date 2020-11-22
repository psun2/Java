package com.lec;

import com.lec.repository.user.UserDAO;
import com.lec.spring.config.UserConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Service {

    public static void main(String[] args) {

        Class<UserConfig> userConfigClass = UserConfig.class;
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(userConfigClass);
        annotationConfigApplicationContext.refresh();

//        System.out.println(annotationConfigApplicationContext.getBeanDefinitionCount());
//
//        for(String name : annotationConfigApplicationContext.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        UserDAO userDAO = annotationConfigApplicationContext.getBean("userDAO", UserDAO.class);
        System.out.println(userDAO + " userDAO 생성");

        System.out.print("userDTO 반환: ");
        userDAO.insert("박성언", "안녕하세요");

        annotationConfigApplicationContext.close();
    }

}
