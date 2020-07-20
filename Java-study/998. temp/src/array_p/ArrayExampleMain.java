package array_p;

public class ArrayExampleMain {

	public static void main(String[] args) {

//		String[] name = { "장동건", "장서건", "장남건", "장중건", "북두신건" };
//		int[] kor = { 71, 51, 81, 61, 91 };
//		int[] eng = { 73, 53, 83, 63, 93 };
//		int[] mat = { 79, 58, 82, 64, 92 };
//		
//		int[] sum = new int[name.length];
//		int[] avg = new int[name.length];
//
//		for (int i = 0; i < name.length; i++) {
//			sum[i] = kor[i] + eng[i] + mat[i];
//			avg[i] = sum[i] / 3;
//		}
//
//		for (int i = 0; i < name.length; i++) {
//			String ttt = name[i] + "\t" + kor[i] + "\t" + eng[i] + "\t" + mat[i] + "\t" + sum[i] + "\t" + avg[i];
//			System.out.println(ttt);
//		}

		System.out.println("\n풀이\n");

		// 1. 입력부
		String[] name = { "장동건", "장서건", "장남건", "장중건", "북두신건" };
		int[] kor = { 71, 51, 81, 61, 91 };
		int[] eng = { 73, 53, 83, 63, 93 };
		int[] mat = { 79, 58, 82, 64, 92 };

		int[] sum = new int[name.length];
		int[] avg = new int[name.length];

		// 2. 연산부
		// 2. 1 기본연산
		for (int i = 0; i < name.length; i++) {
			sum[i] = kor[i] + eng[i] + mat[i];
			avg[i] = sum[i] / 3;
		}

		// 2. 2 통계
		int buf;
		String bufN;
		for (int i = 0; i < name.length; i++) {
			for (int j = i + 1; j < name.length; j++) {
				if (avg[i] < avg[j]) {
					bufN = name[i];
					name[i] = name[j];
					name[j] = bufN;

					buf = kor[i];
					kor[i] = kor[j];
					kor[j] = buf;

					buf = eng[i];
					eng[i] = eng[j];
					eng[j] = buf;

					buf = mat[i];
					mat[i] = mat[j];
					mat[j] = buf;

					buf = sum[i];
					sum[i] = sum[j];
					sum[j] = buf;

					buf = avg[i];
					avg[i] = avg[j];
					avg[j] = buf;

				}
			}
		}

		// 3. 출력부
		String[] index = { "가", "가", "가", "가", "가", "가", "양", "미", "우", "수", "수", };
		for (int i = 0; i < name.length; i++) {
			if (i == 0) {
				System.out.print("이름\t국어\t영어\t수학\t총점\t평균\t등급\n");
			}
			String ttt = name[i] + "\t" + kor[i] + "\t" + eng[i] + "\t" + mat[i] + "\t" + sum[i] + "\t" + avg[i] + "\t"
					+ index[avg[i] / 10];
			System.out.println(ttt);
		}

	}

}
