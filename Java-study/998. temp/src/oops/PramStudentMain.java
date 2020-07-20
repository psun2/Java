package oops;

class ParamStudent {
	String ban, name;
	int sum, avg, rank;
	int[] score;

	void init(String name, int... score) {
		this.score = new int[score.length];
		this.score = score;

		int len = score.length;

		switch (len) {
		case 3:
			this.name = name;
			this.ban = "일반";
			for (int i : score)
				this.sum += i;
			this.avg = sum / score.length;
//			this.print(score);
			break;
		case 4:
			this.name = name;
			this.ban = "특기생";
			for (int i : score)
				this.sum += i;
			this.avg = sum / score.length;
//			this.print(score);
			break;
		}
	}

	void rank(ParamStudent[] arr) {
		this.rank = 1;
		for (int i = 0; i < arr.length; i++) {
			if (this.ban == arr[i].ban && this.avg < arr[i].avg)
				rank++;
		}

	}

//	void print(int... jum) {
////		String result = "이름\t반\t국어\t영어\t수학\t특기\t총점\t평균\n" + name + "\t" + ban + "\t";
//		String result = name + "\t" + ban + "\t";
//		for (int i : jum) {
//			result += i + "\t";
//		}
//		if (jum.length == 3)
//			result += "\t";
//		result += sum + "\t" + avg + "\t" + rank + "\t";
////		System.out.println(result + "\n");
//		System.out.println(result);
//	}

}

public class PramStudentMain {

	public static void main(String[] args) {

		// 국어, 영어, 수학 - 일반
		// 국어, 영어, 수학, 예체능 - 특기
		// 총점, 평균까지 출력하세요.

		ParamStudent[] student = new ParamStudent[10];
		for (int i = 0; i < student.length; i++) {
			student[i] = new ParamStudent();
		}

		student[0].init("홍길동1", 99, 45, 12);
		student[1].init("홍길동2", 100, 33, 12, 45);
		student[2].init("홍길동3", 100, 78, 12);
		student[3].init("홍길동4", 81, 45, 78, 45);
		student[4].init("홍길동5", 11, 78, 23);
		student[5].init("홍길동6", 99, 45, 65, 33);
		student[6].init("홍길동7", 77, 45, 56);
		student[7].init("홍길동8", 88, 45, 43, 78);
		student[8].init("홍길동9", 66, 45, 34);
		student[9].init("홍길동10", 55, 45, 69, 46);

		for (int i = 0; i < student.length; i++) {
			student[i].rank(student);
		}

		for (int i = 0; i < student.length; i++) {
			String result = student[i].name + "\t" + student[i].ban + "\t";
			for (int j = 0; j < student[i].score.length; j++) {
				result += student[i].score[j] + "\t";
			}
//			if (student[i].score.length == 3)
			if (student[i].ban.equals("일반"))
				result += "\t";
			result += student[i].sum + "\t" + student[i].avg + "\t" + student[i].rank + "\t";
			System.out.println(result);
		}

		// TODO JavaScript
//////////////////////////////////////////////////////////////////////////////
//		function say(b, ...a) {
//			  a.forEach(index => console.log(index));
//			  console.log("난 b 이지 스프레드 문법 앞에 들어 갈 수 없지", b);
//			};
//
//			say(100, "하이", 2, "방갑습니다.", 100);

//		하이
//		2
//		방갑습니다.
//		100
//		난 b 이지 스프레드 문법 앞에 들어 갈 수 없지 100

//			main: while(true) {
//			  let a = 1;
//			  for(let i = 0; i < 10; i++) {
//			    a++;
//			    console.log(i);
//			    if(a == 5) {
//			      break main;
//			    }
//			  }
//			}

//		0
//		1
//		2
//		3
//////////////////////////////////////////////////////////////////////////////

	}

}
