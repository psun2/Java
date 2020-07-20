package thread_p;

import java.util.ArrayList;

class SamMart2 extends Thread {

	String product = null;
	boolean chk = false;

	synchronized void input(String prod) {

		while (product != null) {

			try {
				if (chk)
					return;

				System.out.println(prod + "-입고 대기");
				wait();
				System.out.println(prod + "-입고 대기 해제");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		product = prod;
		System.out.println(prod + "<------------입고 성공");

		notifyAll(); // 입고/출고 대기 모두 깨움
	}

	synchronized String sale(String buyer) {

		while (product == null) {
			try {
				if (chk)
					return null;

				System.out.println(buyer + "-구매 대기");
				wait();
				System.out.println(buyer + "-구매 대기 해제");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String res = product;
		System.out.println(product + "<------------구매 성공");
		product = null;
		notifyAll(); // 입고/출고 대기 모두 깨움

		return res;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 20; i++) {
			try {
				sleep(1000);
				System.out.println(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chk = true;
		System.out.println("시장 패장");

	}
}

class SalerMoon2 extends Thread {

	SamMart2 mart;
	ArrayList<String> data;

	public SalerMoon2(SamMart2 mart, String name) {
		super(name);
		this.mart = mart;
		data = new ArrayList<String>();
	}

	@Override
	public void run() {
		int no = 0;

		while (!mart.chk) {
			mart.input(getName() + no);
			no++;
			data.add(getName() + no);
			try {
				sleep(1000 * (int) (Math.random() * 5 + 1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(getName() + " 팔기 종료:" + data);
	}
}

class Hogu2 extends Thread {

	SamMart2 mart;
	ArrayList<String> data;

	public Hogu2(SamMart2 mart, String name) {
		super(name);
		this.mart = mart;
		data = new ArrayList<String>();
	}

	@Override
	public void run() {
		int no = 0;

		while (!mart.chk) {
			data.add(mart.sale(getName() + no));
			no++;
			try {
				sleep(1000 * (int) (Math.random() * 5 + 1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(getName() + " 사기 종료:" + data);
	}
}

public class Sam2 {

	public static void main(String[] args) {
		SamMart2 mart = new SamMart2();

		SalerMoon2 TV = new SalerMoon2(mart, "TV");
		SalerMoon2 tong = new SalerMoon2(mart, "세탁기");
		SalerMoon2 aircon = new SalerMoon2(mart, "에어콘");

		Hogu2 hg1 = new Hogu2(mart, "개성상인");
		Hogu2 hg2 = new Hogu2(mart, "말성상인");
		Hogu2 hg3 = new Hogu2(mart, "미국상인");
		Hogu2 hg4 = new Hogu2(mart, "중국상인");

		mart.start();
		TV.start();
		tong.start();
		aircon.start();

		hg1.start();
		hg2.start();
		hg3.start();
		hg4.start();
	}

}
