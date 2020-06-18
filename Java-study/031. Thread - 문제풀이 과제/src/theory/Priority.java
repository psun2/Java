package theory;

public class Priority {

	public static void main(String[] args) {

		MyThread t1 = new MyThread("○");
		MyThread t2 = new MyThread("▲");
		MyThread t3 = new MyThread("□");

		t1.setPriority(1); // 쓰레드의 순서조작
		t2.setPriority(5);
		t3.setPriority(10);

		// setPriority 전
//		Thread[○,5,main]
//		Thread[□,5,main]
//		Thread[▲,5,main]

		// setPriority 후
//		Thread[○,1,main]
//		Thread[□,5,main]
//		Thread[▲,10,main]

		// 숫자만 바뀔뿐 별 의미가 없음.

		t1.start();
		t2.start();
		t3.start();

	}

}
