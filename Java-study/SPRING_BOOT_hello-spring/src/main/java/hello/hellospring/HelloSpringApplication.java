package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	// 톰캣 웹서버를 내장 하고 있어서 실행만으로도 톰캣이 실행되면서
	// localhost 8080포트에 접근이 가능합니다.

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
