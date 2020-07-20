package array_p;

import java.util.Arrays;

public class MulAddrExamMain {

	public static void main(String[] args) {

		String[] name = { "한가인", "한나인", "한다인", "한라인", "한마인", "한바인", "한사인" };

		int[][] jum = { { 78, 77, 79, 72 }, { 68, 67, 62 }, { 98, 97, 99 }, { 88, 87, 89, 82 }, { 97, 95, 92 },
				{ 58, 57, 59, 52 }, { 78, 77, 81 } };

		int[][] res = new int[jum.length][4];
		// 총점, 평균, 전체등수, 분류등수

		/// 분류별로 나누어 출력하세요
		//// 분류별 등수를 출력하세요

		String[] kind = { "일반", "특기생" };
		int[][] kindArr = new int[kind.length][0];

		/// 일반 연산
		for (int i = 0; i < res.length; i++) {

			int pos = jum[i].length - 3; // 일반일 경우 0번 배열 // 특기생일 경우 1번 배열에
											// 자신의 위치를 담습니다.

			int[] buf = new int[kindArr[pos].length + 1];

			for (int j = 0; j < kindArr[pos].length; j++) {
				buf[j] = kindArr[pos][j];

			}

			buf[kindArr[pos].length] = i;
			kindArr[pos] = buf;

			// 합계와 평균
			for (int j : jum[i]) {
				res[i][0] += j;
			}
			res[i][1] = res[i][0] / jum[i].length;
		}

		System.out.println(Arrays.deepToString(kindArr));

		/// 등수
		for (int me = 0; me < res.length; me++) {
			res[me][2] = 1;
			res[me][3] = 1;
			
			for (int you = 0; you < res.length; you++) {

				if (res[me][1] < res[you][1]) {
					res[me][2]++;

					if (jum[me].length == jum[you].length) // 길이가 같다면 같은 분류
						res[me][3]++; // 같은 분류라면 같은 분류내 등수 
				}
			}
		}

		/// 출력
		for (int p = 0; p < kindArr.length; p++) {
			System.out.println(kind[p] + ">>>>");
			// 등수
			for (int r = 1; r <= kindArr[p].length; r++) {

				// 사람
				for (int k = 0; k < kindArr[p].length; k++) {
					int i = kindArr[p][k];

					if (r == res[i][3]) {

						int pos = jum[i].length - 3;

						String ttt = kind[pos] + "\t" + name[i] + "\t";
						for (int j : jum[i]) {
							ttt += j + "\t";
						}

						if (pos == 0)
							ttt += "\t";

						for (int j : res[i]) {
							ttt += j + "\t";
						}
						System.out.println(ttt);
					}
				}
			}
		}
	}

}
