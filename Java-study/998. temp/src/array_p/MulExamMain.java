package array_p;

import java.util.Arrays;

public class MulExamMain {

	public static void main(String[] args) {

		String[] name = { "원빈", "현빈", "김우빈", "장희빈", "미스터빈", "투빈", "커피빈", "골빈", "젤리빈", "집이빈", "터빈" };

		int[] ban = { 1, 2, 3, 2, 1, 2, 3, 1, 2, 3, 1 };

		int[][] jum = { { 76, 77, 71 }, { 56, 57, 51 }, { 96, 97, 91 }, { 66, 67, 61 }, { 86, 87, 81 }, { 79, 77, 71 },
				{ 56, 52, 51 }, { 96, 91, 91 }, { 66, 63, 61 }, { 80, 87, 81 }, { 86, 87, 78 } };

		int[][] res = new int[jum.length][4];
		// 0 - 총점, 1-평균, 2-등수, 3-자리
		/*
		 * { // i V {0,0}, {0,0}, {0,0}, {0,0}, {0,0} // }
		 */

		// 2.1 기본연산
		int banMax = 0;
		for (int i = 0; i < jum.length; i++) {
			if (banMax < ban[i])
				banMax = ban[i];

			for (int j = 0; j < jum[i].length; j++) {
				res[i][0] += jum[i][j];
			}
			res[i][1] = res[i][0] / jum[i].length;
		}

		int[] banCnt = new int[banMax + 1];

		// 2.2 등수계산

		for (int me = 0; me < res.length; me++) {
			banCnt[ban[me]]++;
			res[me][2] = 1;
			for (int you = 0; you < res.length; you++) {
				if (res[me][1] < res[you][1] && ban[me] == ban[you])
					res[me][2]++;
			}
		}

		// 2.3 자리계산
		for (int i = 0; i < res.length; i++) {
			res[i][3] = res[i][2];
			for (int j = 1; j < ban[i]; j++) {
				res[i][3] += banCnt[j];
			}
		}
		System.out.println(Arrays.toString(banCnt));

		// 3. 출력부

		// System.out.println(b+" 반 >>>>>>>");
		int banIndex = 0;
		for (int r = 1; r <= res.length; r++) {

			for (int i = 0; i < jum.length; i++) {

//				if(res[i][3] == 1)
//					System.out.println(ban[i] + "  반 >>>>>>>");

				if (r == res[i][3]) {
					if (banIndex != ban[i]) {
						banIndex = ban[i];
						System.out.println(banIndex + "  반 >>>>>>>");
					}

					String ttt = ban[i] + "\t" + name[i] + "\t";

					for (int j = 0; j < jum[i].length; j++) {
						ttt += jum[i][j] + "\t";
					}
					for (int j = 0; j < res[i].length; j++) {
						ttt += res[i][j] + "\t";
					}

					System.out.println(ttt);
				}
			}

		}

		/////// 반을 추가하여 등수순서대로 출력하세요
		//// 출력 방식
		//// 반별 등수
		/*
		 * 1반 1 2 3 4 2반 1 2 3 3반 1 2 3
		 */

		System.out.println();
		System.out.println(Arrays.toString(ban));
		System.out.println(Arrays.toString(banCnt));
		System.out.println(Arrays.deepToString(res));

	}

}
