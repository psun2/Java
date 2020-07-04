package theory.BufferedReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

class Student {
	String name;
	int[] jum = new int[3];
	int avg, tot;

	public Student(String line) {
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

		// 읽을때 input
		// 저장 output
		// 2byte 기반 스트림
		// 2byte 의 장점 - 문자열 만 있는 파일에서 아주 강력합니다.

		try {

			ArrayList<Student> students = new ArrayList<Student>();

			FileReader fr = new FileReader("예제폴더/stud.csv");
			BufferedReader br = new BufferedReader(fr); // 한줄씩 읽어 옵니다. test

//			for (int i = 0; i < 100; i++) {
//				System.out.println((char) br.read()); // 한줄씩 가져 옵니다.
//				System.out.println(br.readLine()); // 한 라인씩 가져옵니다.
//			}

			String line;

			int i = 0;
			while ((line = br.readLine()) != null) {

				if (i != 0) {
					students.add(new Student(line));
				}

				i++;

			}

			br.close();
			fr.close();

			FileWriter fw = new FileWriter("예제폴더2/studCreate.csv");
			BufferedWriter bw = new BufferedWriter(fw);

//			BufferedWriter bw = new BufferedWriter(new FileWriter("예제폴더2/studCreate.csv"));

			for (Student student : students) {
				bw.write(student.toString());
				System.out.println(student.toString());
			}

			bw.close(); // bw 가 fw 를 가지고 있어 bw가 먼저 종료 되어야 합니다.
			fw.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
