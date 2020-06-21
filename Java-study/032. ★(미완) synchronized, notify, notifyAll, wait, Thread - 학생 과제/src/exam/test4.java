package exam;

public class test4 {

	public static void main(String[] args) {

		main: while (true) {
			int a = 0;
			while (true) {

				try {
					Thread.sleep(1000);
					a++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(a);
				if (a > 10)
					break main;
			}
		}

	}

}
