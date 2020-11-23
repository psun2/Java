package com.lec.spring.environment;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@Data
public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean {
    private Environment env;
    private String adminId;
    private String adminPw;

    @Override
    public void setEnvironment(org.springframework.core.env.Environment environment) {
        System.out.println("setEnvironment() 호출");
        setEnv(environment);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet() 호출");
        setAdminId(env.getProperty("admin.id"));
        setAdminPw(env.getProperty("admin.pw"));
    }

    @Override
    public void destroy() throws Exception {

    }

}
