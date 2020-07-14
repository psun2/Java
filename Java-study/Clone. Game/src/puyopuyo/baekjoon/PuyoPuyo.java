package puyopuyo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PuyoPuyo {

	static class Puyo {

		int x, y;

		public Puyo(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

	}

	static char[][] map = new char[12][6]; // 뿌요뿌요 맵 12 X 6

	static boolean[][] visit; // 전체 visit 배열
	static boolean[][] alpha; // 세부 visit 배열

	static int alphaCnt; // 터질수 있는지 개수 체크

	static ArrayList<Puyo> list; // 뿌요 저장하는 리스트

	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우

	static int time; // 연쇄시간

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {

			String[] str = br.readLine().split("");

			for (int j = 0; j < 6; j++) {
				map[i][j] = str[i].charAt(0);
			}
		}

		time = 0;

		sol();

		System.out.println(time);

	}

	public static void sol() {

		int cnt = 0; // 연쇄시간

		while (true) {

			list = new ArrayList<PuyoPuyo.Puyo>();

			// puyo 위치 저장 (마지막 줄 부터 확인하기)

			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.')
						list.add(new Puyo(i, j));
				}
			}

			// 아무것도 없으면 종료
			if (list.size() == 0) {
				time = cnt;
				break;
			}

			visit = new boolean[12][6];

			// 뿌요마다 dfs를 돌리면서 4개이상 모여있는 곳 체크 (체크된 곳 시자부분 저장)
			for (int i = 0; i < alpha.length; i++) {

				int y = list.get(i).y;
				int x = list.get(i).x;

				if (visit[y][x])
					continue; // 이미 뿌요인 곳은 넘어감

				char alpha = map[y][x];
				
				this.alpha = new boolean[12][6];
				
				alphaCnt = 1; // 갯수

			}

		}
	}

}
