package com.lec.spring.property.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertyConfig {

	@Value("${admin.id}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
	private String id;

	@Value("${admin.pw}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
	private String pw;

	@Value("${db.url}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
	private String url;

	@Value("${db.port}") // 외부 설정 파일이 있을경우 값을 autowired 하는 어노테이션
	private String port;

	@Bean
	// <context:property-placeholder location=".., .."/> 과 대응 합니다.
	public static PropertySourcesPlaceholderConfigurer property() {
		// static 키워드가 붙어야 제대로 동작합니다.
		// static 키워드가 없을시 null 
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

		Resource[] resources = new Resource[2];
		resources[0] = new ClassPathResource("admin.auth");
		resources[1] = new ClassPathResource("db.info");

		configurer.setLocations(resources);

		return configurer;
	}

	@Bean
	public DBConn dbConn() {
		// 생성자에서 @Value 사용 불가
		// 현재 메소드 내에서 @Value 사용 불가
		// 맴버 변수로 밖에 뺄수가 없습니다.
		return new DBConn(this.id, this.pw, this.url, this.port);
	}

}
