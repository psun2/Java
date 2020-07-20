package lang_p;

class SingleTTT {

	int a = 10;

	private static SingleTTT me = null;

	private SingleTTT() {
		System.out.println("생성자 실행");
		System.out.println("초기 첫번째 실행시에만 null 값이므로 생성자는 한번만 실행됩니다.");
		System.out.println("생성자는 1번만 실행되기 때문에 모든 인스턴스의 주소값이 같습니다.");
	}

	public static SingleTTT getInstance() {

		if (me == null)
			me = new SingleTTT();

		return me;
	}

}

public class SingleTonMain {

	public static void main(String[] args) {

		// TODO 싱글톤

//		SingleTTT st1 = new SingleTTT(); // 생성자가 private 라 생성이 안됩니다.

		SingleTTT str1 = SingleTTT.getInstance(); // Calendar 와 비슷한 구조
		SingleTTT str2 = SingleTTT.getInstance(); // Calendar 와 비슷한 구조
		SingleTTT str3 = SingleTTT.getInstance(); // Calendar 와 비슷한 구조

		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);

//		lang_p.SingleTTT@15db9742
//		lang_p.SingleTTT@15db9742
//		lang_p.SingleTTT@15db9742

		// 생성자는 1번만 돕니다.
		// why ? 첫번째에서 이미 생성이 되어 null 값이 삭제 됩니다.

		System.out.println(str1 == str2); // true
		System.out.println(str1 == str3); // true

		// 생성자는 1번만 실행되기 때문에 모든 인스턴스의 주소값이 같습니다.

		System.out.println(str1.a + ", " + str2.a + ", " + str3.a);

		str1.a = 1234;

		System.out.println(str1.a + ", " + str2.a + ", " + str3.a);

		// static 처럼 공유하면서 쓴다는 개념

	}

}
