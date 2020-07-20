package io_p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class BangTan {
	String name;
	int[] jum = new int[3];
	int avg, tot;

	public BangTan(String line) {
		// TODO Auto-generated constructor stub
		String[] arr = line.split(",");
		this.name = arr[0];

		tot = 0;
		for (int i = 1; i < 3; i++) {
			jum[i - 1] = Integer.parseInt(arr[i]);
			tot += jum[i - 1];
		}

		avg = tot / jum.length;

	}

	@Override
	public String toString() {

		String ttt = name + ", ";

		for (int i : jum) {
			ttt += i + ", ";
		}
		ttt += tot + ", " + avg + "\n";

		return ttt;
	}

}

public class BufferedReaderMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			ArrayList<BangTan> bt = new ArrayList<BangTan>();

			FileReader fr = new FileReader("fff/exam.csv");
//			FileReader fr = new FileReader("fff/bbb.txt");
//			FileReader fr = new FileReader("fff/애상.txt");
			BufferedReader br = new BufferedReader(fr); // 한줄씩 가져옵니다.
			// 아주 강력크한 기능 => 줄 단위 처리

			String line;

			int i = 0;
			while ((line = br.readLine()) != null) {

				System.out.println(i + " : " + line);

				if (i > 0) {

					bt.add(new BangTan(line));
				}

				i++;

			}

			br.close();
			fr.close();

//			for (BangTan bangTan : bt) {
//				System.out.println(bangTan);
//			}

			FileWriter fw = new FileWriter("dst/eee.csv");
			BufferedWriter bw = new BufferedWriter(fw);
			// 사실상 BufferedWriter 필요 없지만, 연습 삼아 하는중...

			for (BangTan bangTan : bt) {
				bw.write(bangTan.toString());
			}

			bw.close();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
