package array_p;

public class ArrayExamRankMain {

	public static void main(String[] args) {

		// 1. 입력부
		String[] name = { "장동건", "장서건", "장남건", "장중건", "북두신건" };
		int[] kor = { 71, 51, 81, 61, 91 };
		int[] eng = { 73, 53, 83, 63, 93 };
		int[] mat = { 79, 58, 82, 64, 92 };

		int[] sum = new int[name.length];
		int[] avg = new int[name.length];
		int[] rank = new int[name.length];

		// 2. 연산부
		// 2. 1 기본연산
		for (int i = 0; i < name.length; i++) {
			sum[i] = kor[i] + eng[i] + mat[i];
			avg[i] = sum[i] / 3;
		}

		// 2. 2 등수 연산
		for (int i = 0; i < avg.length; i++) {
			rank[i] = 1;
			for (int j = 0; j < avg.length; j++) {
				if (avg[i] < avg[j])
					rank[i]++;
			}
		}

		// 2. 2 통계
//		int buf;
//		String bufN;
//		for (int i = 0; i < name.length; i++) {
//			for (int j = i + 1; j < name.length; j++) {
//				if (avg[i] < avg[j]) {
//
//					bufN = name[i];
//					name[i] = name[j];
//					name[j] = bufN;
//
//					buf = kor[i];
//					kor[i] = kor[j];
//					kor[j] = buf;
//
//					buf = eng[i];
//					eng[i] = eng[j];
//					eng[j] = buf;
//
//					buf = mat[i];
//					mat[i] = mat[j];
//					mat[j] = buf;
//
//					buf = sum[i];
//					sum[i] = sum[j];
//					sum[j] = buf;
//
//					buf = avg[i];
//					avg[i] = avg[j];
//					avg[j] = buf;
//
//				}
//			}
//		}

		// 3. 출력부
		String[] index = { "가", "가", "가", "가", "가", "가", "양", "미", "우", "수", "수" };
		for (int r = 0; r < name.length; r++) {

			for (int i = 0; i < name.length; i++) {
				if (rank[i] == r) { // rank[i] 를 해서 i 번째를 출력해 나가므로 결국 1, 2, 3, 4, 5 등이 순선대로 나온다.
					String ttt = name[i] + "\t" + kor[i] + "\t" + eng[i] + "\t" + mat[i] + "\t" + sum[i] + "\t" + avg[i]
							+ "\t" + index[avg[i] / 10] + "\t" + rank[i];
					System.out.println(ttt);
				}
			}
		}

	}
}