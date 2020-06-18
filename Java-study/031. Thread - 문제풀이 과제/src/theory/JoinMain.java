package theory;

public class JoinMain {

	public static void main(String[] args) {

		MyThread t1 = new MyThread("○");
		MyThread t2 = new MyThread("▲");
		MyThread t3 = new MyThread("□");

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
//		○▲▲○▲○▲○○▲▲○○▲▲○▲○▲○○▲▲○○▲○▲▲○○▲▲○○▲○▲○▲○▲○▲○▲▲○○▲▲○▲○○▲□▲○□○▲○▲□□○▲○▲□▲□○○▲□○□▲▲□○▲□○▲□○□○▲□○▲○□▲○□▲▲○□▲○□○▲□○□▲□○▲○▲□○▲□□□□□□□□□□□□□□□□□□□□□□□□□□□□□

//		쓰레드 1과 2만 번갈아 나오다가 1이 시작한 3초 뒤부터 쓰레드 3이 출력되기 시작합니다.
	}

}
