package excep_p;

import java.util.Arrays;

public class Excep1000 {

	public static void main(String[] args) {

		for (int i = 1; i <= 1000; i++) { // 1 ~ 1000 까지 반복

			int buf = i; // i 를 빼서 계속 update

			int cnt = 0; // 3, 6, 9 의 갯수 누적

			for (int j = 0; j < 4; j++) { // 4 의 의미 1일때 1의 자리 2일때 10의 자리 3일때 100의 자리 4일때 1000의 자리

				int one = buf % 10; // 10으로 나눈 나머지 1, 2, 3, 4, 5, 6, 7, 8, 9, 0

				buf /= 10; // 받은 i 를 buf 로 업데이트
				// why? buf update ? ? ? ?? ''? ? ? ? ? ?? ? ? ? ?
				// 4번 업데이트 되면서 10으로 나누면 1000의 자리까지 일의자리, 십의자리, 일의 자리를 짤라서 계속 반복합니다.
				// but 1000 의 자리가 아닌 일의자리 십의 자리 백의자리가 비 효율적으로 반복문이 실행됩니다.

				try {
					int a = 100 / (one % 3); // 3, 6, 9, 0 이 catch
				} catch (Exception e) {
					try {
						int a = 100 / one;
						// System.out.print("3이다");
						// 3, 6, 9 가 아닐시 아직 아무 작업도 하지 않습니다.
						cnt++; // 3,6, 9 일때 ++;
					} catch (Exception e2) {
						// TODO: handle exception
					}

				}
			} // 안쪽 for문 끝
				// 큰 for 문에서 가지고 있는 것 => buf, cnt
				// ⏬ 작은 for문이 끝나고 cnt 를 가지고 아래 try catch 진입
			String ttt = "";
			try {
				int a = 10 / cnt; // 3, 6, 9 일때만 값이 있으므로 try가 됨
				// 3, 6, 9 가 아닐시 cnt 는 0 이기 때문에 catch 진입
				for (int j = 0; j < cnt; j++) {
					ttt += "짝"; // count 갯수에 따라 짝을 더해줌
				}

			} catch (Exception e) {
				ttt += i;
			}

			System.out.println(ttt);

		}

		System.out.println();
		System.out.println(1 % 10); // 1 => one
		System.out.println(2 % 10); // 2 => one
		System.out.println(3 % 10); // 3 => one
		System.out.println(4 % 10); // 4 => one
		System.out.println(5 % 10); // 5 => one
		System.out.println(6 % 10); // 6 => one
		System.out.println(7 % 10); // 7 => one
		System.out.println(8 % 10); // 8 => one
		System.out.println(9 % 10); // 9 => one
		System.out.println(10 % 10); // 0 => one
		System.out.println(999 % 10); // 9 => one

		System.out.println();
		System.out.println((1 % 10) / 10); // 0 => buf
		System.out.println((2 % 10) / 10); // 0 => buf
		System.out.println((3 % 10) / 10); // 0 => buf
		System.out.println((4 % 10) / 10); // 0 => buf
		System.out.println((5 % 10) / 10); // 0 => buf
		System.out.println((6 % 10) / 10); // 0 => buf
		System.out.println((7 % 10) / 10); // 0 => buf
		System.out.println((8 % 10) / 10); // 0 => buf
		System.out.println((9 % 10) / 10); // 0 => buf
		System.out.println((10 % 10) / 10); // 0 => buf
		System.out.println((999 % 10) / 10); // 0 => one

	}

}
