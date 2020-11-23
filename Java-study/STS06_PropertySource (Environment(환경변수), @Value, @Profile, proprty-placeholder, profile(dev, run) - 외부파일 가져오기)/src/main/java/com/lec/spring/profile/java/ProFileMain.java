package com.lec.spring.profile.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ProFileMain {
    public static void main(String[] args) {
        System.out.println("Main() 시작\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("개발환경 입력(dev / run) : ");
        String config = scanner.next();

        if (!config.equals("dev") && !config.equals("run"))
            config = "dev";

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles(config);

        annotationConfigApplicationContext.register(ProfileConfigDev.class, ProfileConfigRun.class);
        annotationConfigApplicationContext.refresh();

        ServerInfo serverInfo = annotationConfigApplicationContext.getBean("serverInfo", ServerInfo.class);
        System.out.println(serverInfo);

        annotationConfigApplicationContext.close();
        System.out.println("\nMain() 종료");
    }
}
