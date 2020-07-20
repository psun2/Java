package excep_p;

public class Excep1000_2 {

	public static void main(String[] args) {

		for (int i = 1; i <= 887; i++) {

			int buf = i;

			int cnt = 0;

			try {

				while (true) {
					int aa = 10 / buf;
					int one = buf % 10;
					buf /= 10;

					try {
						int a = 100 / (one % 3);
					} catch (Exception e) {
						try {
							int a = 100 / one;
							// System.out.print("3이다");
							cnt++;
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				}

			} catch (Exception ee) {

				String ttt = "";
				try {
					int a = 10 / cnt;
					for (int j = 0; j < cnt; j++) {
						ttt += "짝";
					}

				} catch (Exception e) {
					ttt += i;
				}

				System.out.println(ttt);
			}

		}

	}

}
