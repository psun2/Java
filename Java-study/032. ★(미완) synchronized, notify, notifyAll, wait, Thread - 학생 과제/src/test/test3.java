package test;

class 엄마2 {

	String 이름;
	int 지갑 = 1000;

	synchronized boolean 엄마목소리(자식2 자식, String kind) {

//		System.out.println(kind);
//		System.out.println(kind == null);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (kind != null)
			notifyAll();

		try {
			if (kind == null)
				wait();
		} catch (InterruptedException e) {
		}

		boolean res = true;

//		System.out.println("노티파이 : " + kind);

		return res;
	}
}

class 자식2 extends Thread {

	String 이름;
	엄마2 엄마;

	public 자식2(String 이름, 엄마2 엄마) {
		this.이름 = 이름;
		this.엄마 = 엄마;
	}

	@Override
	public void run() {

		while (true) {

			String res = "영어"; // 국영수를 입력 받음....
								// 영어 문제 고고

			try {
				sleep(1000);
				if (엄마.엄마목소리(this, res)) {
					엄마.지갑 -= 50;
					System.out.println(this.이름 + "\t" + 엄마.지갑);
				} else {
					System.out.println(this.이름 + "\t" + 엄마.지갑);
					break;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

public class test3 {

	public static void main(String[] args) {

		엄마2 엄마 = new 엄마2();

		자식2 국어 = new 자식2("국어", 엄마);
		자식2 영어 = new 자식2("영어", 엄마);
		자식2 수학 = new 자식2("수학", 엄마);

		국어.start();
		영어.start();
		수학.start();

	}

}
