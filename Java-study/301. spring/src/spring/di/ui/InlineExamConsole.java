package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import spring.di.entity.Exam;

//@Component("console")
@Service("console")
public class InlineExamConsole implements ExamConsole {

	private Exam exam;

	public InlineExamConsole() {
		System.out.println("constructor");
	}

//	public InlineExamConsole(Exam exam) {
//		super();
//		this.exam = exam;
//		System.out.println("overloaded constructor");
//	}

	// 위치에 따라 불르는 생성자 및 메소드가 달라 집니다.
	@Autowired(required = false)
	public InlineExamConsole(@Qualifier("exam2") Exam exam) {
		super();
		this.exam = exam;
		System.out.println("overloaded constructor");
	}

	@Override
	public void print() {
		if (exam == null)
			System.out.printf("total is %d avg is %f\n", 0, 0.0);
		else
			System.out.printf("total is %d avg is %f\n", exam.total(), exam.avg());
	}

	// @Autowired // 위치에 따라 불르는 생성자 및 메소드가 달라 집니다.
	// @Qualifier("exam2") // 위치에 따라 불르는 생성자 및 메소드가 달라 집니다.
	// @Qualifier => @Autowired 의 기준이 됩니다.
	@Override
	public void setExam(Exam exam) {
		this.exam = exam;
		System.out.println("setter");
	}

}
