package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.aop.entity.Exam;
import spring.aop.entity.NewLecExam;

public class Program {

	public static void main(String[] args) {

		ApplicationContext context = //
				// new AnnotationConfigApplicationContext();
				new ClassPathXmlApplicationContext("spring/aop/setting.xml");

		Exam proxy = (Exam) context.getBean("proxy");

		System.out.printf("total is %d\n", proxy.total());
		System.out.printf("avg is %f\n", proxy.avg());

		// point cut : org.springframework.aop.support.NameMatchMethodPointcut
		// point cut 과 advice 연결: org.springframework.aop.support.DefaultPointcutAdvisor

		// spring xml 로 구현 (setting)
//		Exam exam = new NewLecExam(1, 1, 1, 1);
//		
//		//proxy 패키지 : org.springframework.aop.framework.ProxyFactoryBean 
//		
//		// Exam proxy = Proxy.newProxyInstance(loader, interfaces, h); // h: handler
//		Exam proxy = (Exam) Proxy.newProxyInstance(NewLecExam.class.getClassLoader(), new Class[] { Exam.class },
//				new InvocationHandler() {
//
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//						long start = System.currentTimeMillis();
//
//						Object result = method.invoke(exam, args);
//
//						long end = System.currentTimeMillis();
//
//						String message = (end - start) + "ms 시간이 걸렸습니다.";
//						System.out.println(message);
//
//						return result;
//					}
//				});

//		System.out.printf("total is %d\n", exam.total());
//		System.out.println();
//		System.out.printf("total is %d\n", proxy.total());
//		System.out.printf("avg is %f\n", proxy.avg());
	}

}
