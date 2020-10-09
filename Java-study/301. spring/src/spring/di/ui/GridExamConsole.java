package spring.di.ui;

import spring.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

	private Exam exam;

	public GridExamConsole(Exam exam) {
		super();
		this.exam = exam;
	}

	@Override
	public void print() {
		System.out.println("┌─────────────────┐");
		System.out.println("│  total  │  avg  │");
		System.out.println("├─────────────────┤");
		 System.out.printf("│   %3d   │ %3.2f │\n", exam.total(), exam.avg());
		System.out.println("└─────────────────┘");
	}

}
