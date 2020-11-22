package com.service.user;

import com.repository.user.UserDAO;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserService {

    public static void main(String[] args) {
        System.out.println("[service 시작]");

        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();
        genericXmlApplicationContext.load("userSetting.xml");
        genericXmlApplicationContext.refresh();
        System.out.println("[IoC 컨테이너 생성]");

        UserDAO bean = genericXmlApplicationContext.getBean(UserDAO.class);
        bean.insert("박성언", "123456");

        genericXmlApplicationContext.close();

        System.out.println("[service 끝]");
    }

}
