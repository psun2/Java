package singleChat_GUI_TestInnerClass;

class Outer {

	public Outer() {
		// TODO Auto-generated constructor stub
		System.out.println("Outer 생성");
	}

	class Inner1 {

		public Inner1() {
			// TODO Auto-generated constructor stub
			System.out.println("inner1 생성");
		}
	}

	class Inner2 {

		public Inner2() {
			// TODO Auto-generated constructor stub
			System.out.println("inner2 생성");
		}
	}

}

public class TestInnerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Outer().new Inner1();
		new Outer().new Inner2();
		System.out.println(" outer 가 Socket 을 초기화 시키는데 서버에서 생성시\r\n"
				+ " new Client.new Receive() 되어 클라이언트를 계속 초기화 합니다.\r\n" + " 그래서 에러의 원인으로 보입니다.\r\n" + "");
	}

}
