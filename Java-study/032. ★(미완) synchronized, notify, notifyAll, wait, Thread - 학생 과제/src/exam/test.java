package exam;

import java.util.ArrayList;

class MyThread extends Thread {

	AAA aaa;

	public MyThread(String name, AAA aaa) {
		super(name);
		this.aaa = aaa;
		// 여기서 AAA 를 생성하면 주소가 다 다르므로, 동기화가 진행 되지 않음.
	}

	@Override
	public void run() {
		aaa.show(getName());

	}

}

class MyThread2 extends Thread {

	public MyThread2(String name) {
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

class AAA {

	synchronized void show(String name) {

		try {
			Thread.sleep(1000);
			System.out.println(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class BBB {

	ArrayList<MyThread> therad;

	public BBB(MyThread t1, MyThread t2, MyThread t3) {
		therad = new ArrayList<MyThread>();
		therad.add(t1);
		therad.add(t2);
		therad.add(t3);
	}

	void start() {

		for (MyThread myThread : therad) {
			myThread.start();
		}

	}

}

public class test {

	public static void main(String[] args) {

		AAA aaa = new AAA();

//		ArrayList<MyThread> arr = new ArrayList<MyThread>();
//
//		arr.add(new MyThread("t1", aaa));
//		arr.add(new MyThread("t2", aaa));
//		arr.add(new MyThread("t3", aaa));
//
//		ArrayList<MyThread> arr2 = new ArrayList<MyThread>();
//
//		arr2.add(new MyThread("arr2 t1", aaa));
//		arr2.add(new MyThread("arr2 t2", aaa));
//		arr2.add(new MyThread("arr2 t3", aaa));
//
//		for (MyThread myThread : arr) {
//			myThread.start();
//		}
//
//		for (MyThread myThread : arr2) {
//			myThread.start();
//		}

//		BBB bbb1 = new BBB(new MyThread("bbb1", aaa), new MyThread("bbb2", aaa), new MyThread("bbb3", aaa));
//		BBB bbb2 = new BBB(new MyThread("cccc1", aaa), new MyThread("ccc2", aaa), new MyThread("ccc3", aaa));
//		BBB bbb3 = new BBB(new MyThread("ffff1", aaa), new MyThread("ffff2", aaa), new MyThread("ffff3", aaa));
//
//		BBB[] arr = { bbb1, bbb2, bbb3 };
//
//		for (BBB bbb : arr) {
//			bbb.start();
//		}
//		bbb1.start(); // Error 한번 start를 실행시킨 쓰레드는 다시 시작 할 수 없음

//		bbb1
//		ffff3
//		ffff2
//		ffff1
//		ccc3
//		cccc1
//		ccc2
//		bbb3
//		bbb2

		// 쓰레드 클래스 내부에서의 synchronized

//		ArrayList<MyThread2> arr = new ArrayList<MyThread2>();
//
//		arr.add(new MyThread2("한가인"));
//		arr.add(new MyThread2("두가인"));
//		arr.add(new MyThread2("삼가인"));

		MyThread2[] arr2 = { new MyThread2("한가인"), new MyThread2("두가인"), new MyThread2("삼가인"), new MyThread2("사가인"),
				new MyThread2("오가인"), new MyThread2("육가인"), new MyThread2("칠가인"), new MyThread2("팔가인"),
				new MyThread2("구가인"), new MyThread2("십가인") };

		for (MyThread2 myThread2 : arr2) {
			myThread2.start();
		}

//		쓰레드 클래스 내부에서도 synchronized 활용 불가능
	}

}
