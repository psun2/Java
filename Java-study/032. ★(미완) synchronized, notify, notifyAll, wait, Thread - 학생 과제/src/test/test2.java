package test;

class 엄마 {

	String 이름;
	int 지갑 = 1000;

	synchronized boolean 엄마목소리(자식 자식) {

//		if (자식.이름.equals("서울")) {
//			자식.notify();
//		}
//
//		try {
//			wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		boolean res = false;

		if (지갑 > 0) {

			res = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}
}

class 자식 extends Thread {

	String 이름;
	엄마 엄마;

	public 자식(String 이름, 엄마 엄마) {
		this.이름 = 이름;
		this.엄마 = 엄마;
//		this.엄마 = new 엄마(); // 깨워줄 사람이 없음
		엄마.이름 = this.이름;
//		System.out.println(엄마.이름);
	}

	@Override
	public void run() {

		while (true) {
			try {
				sleep(1000);
				if (엄마.엄마목소리(this)) {
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

public class test2 {

	public static void main(String[] args) {

		엄마 엄마 = new 엄마();

		자식 서울 = new 자식("서울", 엄마);
		자식 우유 = new 자식("우유", 엄마);

		서울.start();
		우유.start();

	}

}
