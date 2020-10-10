package spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.ui.ExamConsole;

public class Program { // https://mvnrepository.com/

	public static void main(String[] args) {

		/*
		 * 스프링에게 지시하는 방법으로 코드를 변경 
		 *  // Exam exam = new NewLecExam();
		 *  Exam exam = new NewLecExam(10, 10, 10, 10); 
		 *  ExamConsole console = new GridExamConsole();
		 *
		 * console.setExam(exam);
		 */

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/setting.xml");

		Exam exam = context.getBean(Exam.class);
		System.out.println(exam.toString());
		
		// ExamConsole console = (ExamConsole) context.getBean("console");
		ExamConsole console = context.getBean(ExamConsole.class);
		console.print();

//		ApplicationContext = new 
//				ClassPathXmlApplicationContext("path.xml");
//				FileSystemXmlApplicationContext("path.xml");
//				XmlWebApplicationContext("path.xml");
//				AnnotationConfigClassPathApplicationContext("scan 방법");
	}

}