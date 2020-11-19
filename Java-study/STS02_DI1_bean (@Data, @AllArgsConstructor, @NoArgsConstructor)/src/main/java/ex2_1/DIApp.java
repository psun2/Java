package ex2_1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DIApp {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println("Main 시작\n");

		String configLocation = "classpath:ex2_1.xml";
		AbstractApplicationContext context = new GenericXmlApplicationContext(configLocation);
		System.out.println("컨테이너 생성");
		
		System.out.println("생성된 빈의 갯수 : " + context.getBeanDefinitionCount() + " 개");
		
		for(String name : context.getBeanDefinitionNames()) {
			OperatorBean op = context.getBean(name, OperatorBean.class);
			System.out.println("결과 : " + op.doOperator());
		}
		// 결과 : 15
		// 결과 : 5
		
		
		context.close();

		System.out.println("\nMain 끝");
		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");

	}

}
