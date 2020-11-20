package com.lec.spring.aop01;

import com.lec.spring.beans.Logger;
import com.lec.spring.beans.Service;

public class MyService11 extends Service {

    @Override
    public void doAction() {
        new Logger().login(); // 공통기능

        System.out.println("MyService11 의 doAction() 호출"); // 핵심기능

        new Logger().logout(); // 공통기능
    }
}
