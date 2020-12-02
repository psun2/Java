package com.lec.rest.setting;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig { // 출처: http://blog.leekyoungil.com/?p=390

    // 2020 - 12 - 02
    // TomcatEmdeddedServletContainerFactory 가 없어지고
    // TomcatConnectorCustomizer 인터페이스로 통합된 것 같습니다.
    // @Configuration 으로 Tomcat만 설정한다면
    // 클래스 단에서 implements 를 받아 override 하여 사용 하셔도 무방합니다.

    @Bean
    public TomcatConnectorCustomizer tomcatConnectorCustomizer() {
        return new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setParseBodyMethods("POST,PUT,DELETE");
            }
        };
    }
}
