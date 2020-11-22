package com.lec;

import com.lec.repository.user.UserDAO;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Service {

    public static void main(String[] args) {

        String configLocation = "userSetting.xml";
        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext(configLocation);

        UserDAO userDAO = genericXmlApplicationContext.getBean("userDAO", UserDAO.class);
        System.out.println(userDAO + " userDAO 생성");

        System.out.print("userDTO 반환: ");
        userDAO.insert("박성언", "안녕하세요");

        genericXmlApplicationContext.close();
    }

}
