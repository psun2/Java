package thread_p;

public class JoinMain {

	public static void main(String[] args) {

		FirstTh t1 = new FirstTh("+");
		FirstTh t2 = new FirstTh("/");
		FirstTh t3 = new FirstTh("?");

		t1.start();
		t2.start();

		try {
			// 맨 처음엔 t1m t2 만 나오다가
			t1.join(3000); // 3초 뒤부터 t3이 나오기 시작합니다.
			// start 되지 못하도록 막는 개념 holding
			// key 를 줘서 막도록 합니다. join 은 효과적인 방법은 아닙니다.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t3.start();

	}

}
