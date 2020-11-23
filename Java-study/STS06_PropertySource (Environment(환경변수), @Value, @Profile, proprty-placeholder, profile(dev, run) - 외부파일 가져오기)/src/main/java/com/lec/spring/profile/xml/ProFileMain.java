package com.lec.spring.profile.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProFileMain {
    public static void main(String[] args) {
        System.out.println("Main() 시작\n");

        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();
        // 현재 profile 이 설정된 상태이기 때문에 환경변수에서 사용할 profile 을 활성화 시켜줄 수 있도록 합니다.
        // 만약 활성화가 되어 있지 않다면 NoSuchBeanDefinitionException 빈을 찾을 수 없다는 exception 이 발생합니다.
        genericXmlApplicationContext.getEnvironment().setActiveProfiles("dev");
        // genericXmlApplicationContext.getEnvironment().setActiveProfiles("run");

        genericXmlApplicationContext.load("classpath:profileSetting_dev.xml", "classpath:profileSetting_run.xml");
        genericXmlApplicationContext.refresh();

        ServerInfo serverInfo = genericXmlApplicationContext.getBean("serverInfo", ServerInfo.class);
        System.out.println(serverInfo); // ServerInfo(ipNum=213.186.229.29, portNum=80)

        // 로드가 끝난 상태이므로 환경변수는 변경되지 않습니다.
        // Error 는 나지 않지만 dev profile 이 출력됩니다.
        genericXmlApplicationContext.getEnvironment().setActiveProfiles("run");
        System.out.println(serverInfo); // ServerInfo(ipNum=213.186.229.29, portNum=80)

        genericXmlApplicationContext.close();
        System.out.println("\nMain() 종료");
    }
}
