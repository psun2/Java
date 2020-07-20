package thread_p;

class Timer extends Thread {

	boolean chk;

	public Timer() {
		this.chk = true;
	}

	@Override
	public void run() {

		for (int i = 20; i > 0; i--) {

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		chk = false;
//		System.out.println("마트 문 닫음 ㅅㄱ");

	}

}

class SamMart {

	String product = null;

	Timer timer = new Timer();

	synchronized void input(String prod) {

		while (product != null) {

//			System.out.println(" input ");
//
//			if(timer.chk) {
//				System.out.println("마트 입고 끝");
//				break;
//			}

			try {
				System.out.println(prod + " - 입고 대기");
				wait(); // 제품이 있다면 웨이트
				System.out.println(prod + " - 대기 해제");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		product = prod;
		System.out.println(prod + " <============ 입고 성공");

		notifyAll(); // 입고, 출고, 대기 모두 깨움

	}

	synchronized String sale(String buyer) {

		while (product == null) { // 오히려 null 이면 계속 대기

//			System.out.println(" sale ");
//			System.out.println(timer.chk);
//			if(timer.chk) {
//				System.out.println("마트 판매 끝");
//				break;
//			}

			try {
				System.out.println(buyer + " - 구매 대기");
				wait(); // 제품이 있다면 웨이트
				System.out.println(buyer + " - 구매 대기 해제");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String res = product;
		System.out.println(product + " <============ 구매 성공");
		product = null;
		notifyAll(); // 입고, 출고, 대기 모두 깨움

		return res;
	}
}

class SalerMoon extends Thread {

	SamMart mart;

	public SalerMoon(SamMart mart, String name) {
		super(name);
		this.mart = mart;
	}

	@Override
	public void run() {

		int no = 0;
		while (mart.timer.chk) {

			mart.input(getName() + no);
			no++;
			try {
				sleep(1000 * (int) (Math.random() * 5) + 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("세일러끝");
	}
}

class Hogu extends Thread {

	SamMart mart;

	public Hogu(SamMart mart, String name) {
		super(name);
		this.mart = mart;
	}

	@Override
	public void run() {

		int no = 0;

		while (mart.timer.chk) {

//			if (mart.timer.chk)
//				break;

			mart.sale(getName() + no);
			no++;
		}
		System.out.println("호구도 안삼");
	}

}

public class SamMarktMain {

	public static void main(String[] args) {

		SamMart mart = new SamMart();

		SalerMoon TV = new SalerMoon(mart, "TV");
		SalerMoon tong = new SalerMoon(mart, "세탁기");
		SalerMoon aircon = new SalerMoon(mart, "에오콘");

		Hogu hg1 = new Hogu(mart, "연지상인");
		Hogu hg2 = new Hogu(mart, "예솔상인");
		Hogu hg3 = new Hogu(mart, "휘진상인");
		Hogu hg4 = new Hogu(mart, "성언상인");

		mart.timer.start();
		TV.start();
		tong.start();
		aircon.start();

		hg1.start();
		hg2.start();
		hg3.start();
		hg4.start();

		System.out.println(Thread.currentThread().getName());

	}

}
