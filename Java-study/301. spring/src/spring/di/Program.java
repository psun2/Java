package spring.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.entity.NewLecExam;
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

		// ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/settingXML.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/settingAnnotation.xml");

		// Exam exam = context.getBean(Exam.class);
		// System.out.println(exam.toString());
		
		ExamConsole console = (ExamConsole) context.getBean("console"); // 이름을 이용하여 가져오는 방법
		// ExamConsole console = context.getBean(ExamConsole.class); // 자료형을 통해 가져오는 방법
		console.print();
		
		// 기본적인 자바 코드
		// List<Exam> exams = new ArrayList<>();
		// exams.add(new NewLecExam(1,1,1,1));
		
		// Context를 통해 xml 로 가져오는 방법
		// ArrayList 인 경우엔 여러 List 들이 존재 할 수 있으므로 클래스보단 이름을 직접적으로 가져오는 편이 안전하고 바람직합니다.
		// List<Exam> exams = (List<Exam>) context.getBean("exams"); // 이름을 통해 가져오는 방법
		// exams.add(new NewLecExam(1,1,1,1));
		
		// for(Exam e : exams) {
		// 	System.out.println(e);
		// }
		
	}

}