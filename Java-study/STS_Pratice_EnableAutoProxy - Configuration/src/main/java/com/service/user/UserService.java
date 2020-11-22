package com.service.user;

import com.config.user.UserConfig;
import com.repository.user.UserDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserService {

    public static void main(String[] args) {
        System.out.println("[service 시작]");

        Class<UserConfig> userConfigClass = UserConfig.class;

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(userConfigClass);
        annotationConfigApplicationContext.refresh();
        System.out.println("[IoC 컨테이너 생성]");

        UserDAO bean = annotationConfigApplicationContext.getBean(UserDAO.class);
        bean.insert("박성언", "123456");

        annotationConfigApplicationContext.close();

        System.out.println("[service 끝]");
    }

}
