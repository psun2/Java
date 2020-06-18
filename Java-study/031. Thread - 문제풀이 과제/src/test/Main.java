package test;

import org.omg.CORBA.Current;

class Test extends Thread {

	String name;
	String current;
	String current1;
	String current2;
	String current3;

	public Test(String name) {
		super(name);
		this.name = name;
		this.setName(name);

	}

	@Override
	public void run() {

//		System.out.println("1 : " + this.getName());
//		System.out.println("10 : " + getName());
//		System.out.println("100 : " + currentThread().getName());
//
//		if (getName().equals(currentThread().getName()))
//			System.out.println("틀립");

//		for (int i = 0; i < 100; i++) {
//
////			System.out.println(this.getId() != currentThread().getId());
//			if (this.getId() == getId())
//				System.out.println("가틈\t" + i);
//
////			System.out.println("겟 아이디 : " + getId());
//			try {
//				sleep(1000); // 딜레이
//				System.out.println();
//			} catch (Exception e) {
//				e.getMessage();
//			}
//			System.out.println();
////			System.out.println("\t" + this.getId());
//		}
		String buf;
		String curr = getName();
		for (int i = 0; i < 10; i++) {
			System.out.print(getName());
			if (getName().equals(current1))
				System.out.println("t1");
			if (getName().equals(current2))
				System.out.println("t2");
			if (getName().equals(current3))
				System.out.println("t3");
		}
		System.out.println(current);


	}

	void temp(Test t1, Test t2, Test t3, String current) {
		this.current = current;
		this.current1 = t1.getName();
		this.current2 = t2.getName();
		this.current3 = t3.getName();

		t1.start();
		t2.start();
		t3.start();
	}

	void test(int a) {

//		start();

		System.out.println(currentThread() + "\t" + name);

		for (int i = 0; i < 100; i++) {

			System.out.println(this.getId());

//			System.out.println(a);
//			System.out.println();
//			System.out.println("\t" + Integer.parseInt(name));
//			System.out.println();
			if (a == Integer.parseInt(this.name))
				System.out.println("hi");

//			if (this.currentThread() != Thread.currentThread())
//				System.out.println("씨이발");

//			Object aa = (Object) Thread.currentThread();
//			System.out.println(aa.equals(this.getName()));
//			String aa2 = (String) aa;
//			System.out.println(aa);
//			System.out.println(Test.currentThread());

//			System.out.println(currentThread());

//			System.out.println("QKdQKd" + Thread.activeCount() + "\t");

//			System.out.println(Thread.currentThread());
//			System.out.println(this.getThreadGroup());
//			System.out.print(getName());

			try {
				sleep(100); // 딜레이
//				if (!getName().equals(this.name))
//					System.out.println("씨발");
			} catch (Exception e) {
				e.getMessage();
			}

		}

	}

}

public class Main {

	public static void main(String[] args) {

		Test t1 = new Test("1");
		Test t2 = new Test("2");
		Test t3 = new Test("3");

//		System.out.println(t1.getName());

//		t1.start((int) Thread.currentThread().getId());
//		t2.start((int) Thread.currentThread().getId());
//		t3.start((int) Thread.currentThread().getId());
		String current = Thread.currentThread().getName();

		t1.temp(t1, t2, t3, current);
//		t1.start();
//		t2.start();
//		t3.start();

//		System.out.println("하 : " + Thread.currentThread().getId());

	}

}
