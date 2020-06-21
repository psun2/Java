package exam;

class Student extends Thread {

	public Student(String name) {
		super(name);

	}

	@Override
	public void run() { // 학생수가 많아 순차적으로 하는 동기화 필요

		test();

	}

	synchronized void test() { // 한명씩 시험을 start (문제도 동기화 필요)

		try {
			sleep(1000);
			System.out.println(getName() + " 문제풀이 start");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class MyThread3 extends Thread {

	public MyThread3(String name) {
		super(name);
	}

	@Override
	public void run() {

		test();

	}

	synchronized void test() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getName());

	}

}

public class test3 {

	public static void main(String[] args) {

		Student[] students = { new Student("한가인"), new Student("두가인"), new Student("삼가인"), new Student("사가인"),
				new Student("오가인"), new Student("육가인"), new Student("칠가인"), new Student("팔가인"), new Student("구가인"),
				new Student("십가인") }; // 학생

		MyThread3[] test = { new MyThread3("한가인"), new MyThread3("두가인"), new MyThread3("삼가인"), new MyThread3("사가인"),
				new MyThread3("오가인"), new MyThread3("육가인"), new MyThread3("칠가인"), new MyThread3("팔가인"),
				new MyThread3("구가인"), new MyThread3("십가인") };

		for (Student student : students) {
			student.start();
		}

		for (MyThread3 myThread3 : test) {
			myThread3.start();
		}

	}

}
