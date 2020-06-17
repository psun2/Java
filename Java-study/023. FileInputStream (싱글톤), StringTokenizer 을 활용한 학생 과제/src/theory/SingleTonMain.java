package theory;

import java.util.Calendar;

class testSingleTon {

	int a = 10;

	private static testSingleTon me = null;
	// static 변수여야 인스턴스를 생성전에 접근이 가능하게 됩니다.

	private testSingleTon() { // 싱글톤은 생성자를 private로 접근을 제어 합니다.
		System.out.println("생성자 실행");
		System.out.println("초기 첫번째 실행시에만 null 값이므로 생성자는 한번만 실행됩니다.");
		System.out.println("생성자는 1번만 실행되기 때문에 모든 인스턴스의 주소값이 같습니다.");
	}

	public static testSingleTon getInstance() {
		// static 변수여야 인스턴스를 생성전에 접근이 가능하게 됩니다.
		// 보통 생성자는 public 을 많이 쓰는데 생성자가 잠겨 있으므로,
		// 생성을 할 수 있는 메소드를 public 설정 하는 것을 권장합니다

		if (me == null)
			me = new testSingleTon();

		return me;

	}

}

public class SingleTonMain {

	public static void main(String[] args) {

//		testSingleTon st1 = new testSingleTon();
		// 생성자가 private 로 접근이 불가 합니다.

		testSingleTon st1 = testSingleTon.getInstance();
		// 생김새가 Calendar 와 비슷 하구나 ?
//		Calendar today = new Calendar();
		// 싱글톤이므로 접근 불가
		Calendar today = Calendar.getInstance();

		testSingleTon st2 = testSingleTon.getInstance();
		testSingleTon st3 = testSingleTon.getInstance();
		testSingleTon st4 = testSingleTon.getInstance();

		System.out.println(st1);
		System.out.println(st2);
		System.out.println(st3);
		System.out.println(st4);

//		theory.testSingleTon@42a57993
//		theory.testSingleTon@42a57993
//		theory.testSingleTon@42a57993
//		theory.testSingleTon@42a57993

		// 내부적으로 한번만 생성되기때문에 생성한 인스턴스의 주소가 모두 같습니다.

		// 생성자는 1번만 돕니다.
		// why ? 첫번째에서 이미 생성이 되어 null 값이 삭제 됩니다.

		System.out.println(st1 == st2); // true
		System.out.println(st1 == st3); // true
		System.out.println(st1 == st4); // true

		// 생성자는 1번만 실행되기 때문에 모든 인스턴스의 주소값이 같습니다.

		System.out.println(st1.a + ", " + st2.a + ", " + st3.a + ", " + st4.a);
		// 10, 10, 10, 10

		st1.a = 1234;

		System.out.println(st1.a + ", " + st2.a + ", " + st3.a + ", " + st4.a);
		// 1234, 1234, 1234, 1234

		// static 처럼 공유하면서 쓴다는 개념 과 비슷합니다.

	}

}
