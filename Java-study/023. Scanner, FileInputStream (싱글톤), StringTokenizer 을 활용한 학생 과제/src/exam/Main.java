package exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class InputFile {

	private String fileName;

	private static InputFile instance = null;

	private InputFile(String fileName) {
		super();
		this.fileName = fileName;
	}

	public static InputFile getInstance(String fileName) {

		if (instance == null)
			instance = new InputFile(fileName);

		return instance;
	}

	Student[] createStud() {

		Student students[] = new Student[0];

		try {

			FileInputStream file = new FileInputStream(fileName);

			Scanner sc = new Scanner(file);

			while (sc.hasNext()) {

				StringTokenizer st = new StringTokenizer(sc.nextLine(), "[,]");

				String name = null, ban = null;

				int[] jum = new int[0];

				while (st.hasMoreTokens()) {

					String temp = st.nextToken();

					try {

						int[] jumTemp = new int[jum.length + 1];

						for (int i = 0; i < jum.length; i++) {
							jumTemp[i] = jum[i];
						}

						jumTemp[jum.length] = Integer.parseInt(temp); // 이름과 반은 숫자로 바뀔 수 없으므로 Error

						jum = jumTemp;

					} catch (Exception e) {

						if (ban == null) // 반이 맨 처음 항목이르모 ban이 null 일때 반에 nextToken을 줌;
							ban = temp;
						else
							name = temp;

					}

				}

				if (jum.length != 0) { // file의 맨 윗줄은 title 이므로, jum배열에 들어갈 숫자가 없으므로, jum 배열의 길이가 0이 아닐때만 Student 인스턴스
										// 생성
					Student[] studentsTemp = new Student[students.length + 1];

					for (int i = 0; i < students.length; i++) {
						studentsTemp[i] = students[i];
					}

					studentsTemp[students.length] = new Student(name, ban, jum);

					students = studentsTemp;

				}

			}

			sc.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;

	}

}

class Student {

	String name, ban;
	int jum[];
	int total, avg, position, rank, allRank;

	public Student(String name, String ban, int[] jum) {
		super();
		this.name = name;
		this.ban = ban;
		this.jum = jum;
		calc(); // 생성과 동시에 점수 계산
	}

	void calc() {

		for (int i : jum) {
			total += i;
		}

		avg = total / jum.length;

	}

	void rank(Student[] students) {

		int front = 0;
		rank = 1;
		allRank = 1;

		for (Student you : students) {
			if (you.ban.equals("일반생")) // 등수를 구하면서 앞반의 인원수를 구함.
				front++;
			if (avg < you.avg) {
				allRank++;
				if (ban.equals(you.ban))
					rank++;
			}
		}

		if (ban.equals("일반생"))
			position = rank;
		else
			position = front + rank;

	}

	@Override
	public String toString() {

		String print = ban + "\t" + name + "\t";

		for (int i : jum) {
			print += i + "\t";
		}

		if (ban.equals("일반생"))
			print += "\t";

		print += total + "\t" + avg + "\t";

		print += rank + "\t" + allRank;

		return print;
	}

}

public class Main {

	public static void main(String[] args) {

		String fileName = "./students.csv";

		InputFile input = InputFile.getInstance(fileName);

		Student[] students = input.createStud(); // Student 클래스 생성후 점수계산까지 완료

//		System.out.println(Arrays.toString(students));

//		System.out
//				.println(students[0].name + ", " + students[0].ban + ", " + students[0].total + ", " + students[0].avg);

//		System.out.println(students.length); // 10

		for (Student student : students) {
			student.rank(students);
		}

		System.out.println("반\t이름\t국어\t영어\t수학\t예술\t총점\t평균\t반등수\t전체등수\t");
		String ban = "";
		for (int i = 1; i <= students.length; i++) {
			for (int j = 0; j < students.length; j++) {
				if (students[j].position == i) {
					if (!ban.equals(students[j].ban))
						System.out.println(students[j].ban + " >>>>>>>>>>>>>>>>");
					System.out.println(students[j].toString());
					ban = students[j].ban;
					break;
				}
			}
		}

	}

}
