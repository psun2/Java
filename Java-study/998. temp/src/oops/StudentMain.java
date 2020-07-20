package oops;

import java.util.Arrays;

class Student {
	String name, tel;
	int number, ban;
	int[] result, jum;

	void print() {
		System.out.println(ban + "\t" + number + "\t" + name + "\t" + tel + "\t");
	}

}

public class StudentMain {

	public static void main(String[] args) {

		Student asd = new Student();
		System.out.println("Student 를 가진 asd의 주소: " + asd); // Student 를 가진 asd의 주소: oops.Student@15db9742

		// ✔
		Student[] student = new Student[5];

		// ✔
//		for (Student st : student) { // forEach 는 index의 번호가 아닌 index값을 가져옴 즉 
//			st = new Student();	// 즉 지금 인덱스의 값이 아닌 인덱스를 건드려 줘야 함으로
//		}						// forEach 는 사용 할 수 없습니다.
//		

		System.out.println(student[0]); // null
		System.out.println(Arrays.toString(student)); // [null, null, null, null, null]

		// ✔
		for (Student i : student) {
			System.out.print("forEach: " + i + "\t");
		} // forEach: null forEach: null forEach: null forEach: null forEach: null
		System.out.println();
		for (int i = 0; i < student.length; i++) {
			System.out.print("for: " + student[i] + "\t");
		} // for: null for: null for: null for: null for: null
		System.out.println();

		int[] ban = { 1, 2, 2, 1, 2 };
		int[] number = { 1, 4, 3, 2, 5 };
		String[] name = { "골빈", "젤리빈", "현빈", "원빈", "장희빈" };
		String[] tel = { "3333-3333", "5555-5555", "1111-1111", "2222-2222", "4444-4444" };
		int len = 3; // 과목수 (총 평 등)

//		int random = Math.floor((Math.random() * 100 + 1));
//		int random = Math.ceil((Math.random() * 100));

		// ✔
		for (int i = 0; i < student.length; i++) { // student 배열을 순회하면서 Student 클래스의 인스턴스를 생성합니다.
			System.out.println("인스턴스 생성 전: " + student[i] + ", " + Arrays.toString(student)); //
			student[i] = new Student();
			System.out.println("인스턴스 생성 후: " + student[i] + ", " + Arrays.toString(student)); //

			System.out.println();

			student[i].ban = ban[i];
			student[i].number = number[i];
			student[i].name = name[i];
			student[i].tel = tel[i];
			student[i].jum = new int[len];
			student[i].result = new int[len];
			for (int j = 0; j < len; j++) {
				int random = (int) (Math.random() * 100 + 1);
				student[i].jum[j] = random;
				student[i].result[0] += student[i].jum[j];
			}
			student[i].result[1] = student[i].result[0] / student[i].jum.length;
		}

	}

}
