package exam;

public class TryCacth {

	static int show(int su) {
		if (su == 1) {
			if (su > 0) {

				System.out.println("시작");
				try {
					Thread.sleep(3000);
					System.out.println("try");
					return 1;
				} catch (Exception e) {
					System.out.println("error");
					System.out.println("끝");
					return 2;
				}

			}

		} else {
			return -2;
		}
		return (Integer) null;
	}

	public static void main(String[] args) {
		int aa = show(1);
		System.out.println(aa);
	}
}
