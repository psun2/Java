package exam;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class StudentData {

	TreeSet data;

	public StudentData() {
		super();
		this.data = new TreeSet();
	}

	void rankCalc() {

		for (Object meObj : data) {

			Student me = (Student) meObj;

			for (Object youObj : data) {

				Student you = (Student) youObj;

				if (me.avg < you.avg && me.ban == you.ban && me.kind.equals(you.kind) && me.gender.equals(you.gender))
					// 이미 TreeMap 으로 모든 분류를 다 정했으므로, 평균만 비교하여 등수를 선정하면 됩니다.
//				if (me.avg < you.avg)
					// 여기는 outer 의 TreeSet 데이터를 이용하므로,
					// Error 전에꺼 성공
					me.rank++;

			}

		}

	}

	class Student implements Comparable {

		int ban, tot, avg, rank;
		int[] jum;
		String name, kind, gender;

		public Student(int ban, String name, String gender, int... jum) {
			super();
			this.ban = ban;
			this.name = name;
			this.gender = gender;
			this.jum = jum;
			calc();
		}

		void calc() {

			this.kind = new String[] { "일반", "특기생" }[jum.length - 3];

			tot = 0;
			rank = 1;

			for (int i : jum) {
				tot += i;
			}

			avg = tot / jum.length;

			StudentData.this.data.add(this);

		}

		@Override
		public int compareTo(Object studYou) {

			StudentData.Student you = (StudentData.Student) studYou;

//			int res = ban - you.ban; // 반별
//
//			if (res == 0)
//				res = kind.compareTo(you.kind);
//
//			if (res == 0)
//				res = rank - you.rank;
//
//			if (res == 0)
//				res = name.compareTo(you.name);
//
//			if (res == 0)
//				res = gender.compareTo(you.gender);

			// 위항목은 모두 map 으로 나누어 주었기때문에 정렬할 필요 X

			int res = rank - you.rank;

			if (res == 0)
				res = name.compareTo(you.name);

			return res;
		}

		@Override
		public String toString() {

			String print = ban + "\t" + kind + "\t" + name + "(" + gender + ")\t";

			if (name.length() == 3)
				print += "\t";

			for (int su : jum) {
				print += su + "\t";
			}

			if (kind.equals("일반"))
				print += "\t";

			print += tot + "\t" + avg + "\t" + rank;

			return print;
		}

	}
}

public class Main {

	public static void main(String[] args) {

		StudentData data = new StudentData();

		StudentData.Student[] studArr = { data.new Student(1, "한가인", "남", 11, 12, 13),
				data.new Student(2, "두가인", "여", 21, 22, 23, 24), data.new Student(1, "삼가인", "남", 31, 32, 33),
				data.new Student(2, "사가인", "여", 71, 72, 73), data.new Student(2, "오가인", "남", 71, 72, 73),
				data.new Student(2, "육가인", "여", 80, 85, 99, 90), data.new Student(1, "칠가인", "남", 87, 76, 70, 90),
				data.new Student(1, "팔가인", "여", 81, 82, 83, 84), data.new Student(1, "구가인", "남", 11, 12, 16),
				data.new Student(1, "십가인", "여", 99, 99, 55), data.new Student(2, "십일가인", "남", 90, 90, 90),
				data.new Student(1, "십이가인", "여", 99, 88, 77), data.new Student(2, "심삼가인", "남", 41, 42, 43, 44),
				data.new Student(2, "십사가인", "여", 51, 52, 53, 54), data.new Student(1, "십오가인", "남", 61, 62, 63, 64),
				data.new Student(2, "십육가인", "여", 99, 90, 81), data.new Student(1, "십칠가인", "남", 90, 80, 80, 70),
				data.new Student(2, "십칠가인", "여", 99, 99, 99)

		};

		data.rankCalc();

		TreeMap Students = new TreeMap(); // 반 map을 가짐

		// Students map(반 => 분휴 => 성별)

		// 구조
//		Students(map) = {
//				반 : map = {
//						kind : map = {
//								gender : set,
//						},
//				},
//		}

		for (StudentData.Student st : studArr) {

			TreeMap kind = new TreeMap(); // gender map을 가짐 // 계속 새로 생성
			if (Students.containsKey(st.ban)) // 반에 따른 분류
				kind = (TreeMap) Students.get(st.ban);

			TreeMap gender = new TreeMap(); // 새로생성
			if (kind.containsKey(st.kind)) // 분류에 따른 븐루
				gender = (TreeMap) kind.get(st.kind);

			TreeSet stud = new TreeSet(); // 새로생성
			if (gender.containsKey(st.gender)) // 성별에 따른 분휴
				stud = (TreeSet) gender.get(st.gender);

			stud.add(st); // set 에 클래스를 추가 합니다.
//			System.out.println(st);
			gender.put(st.gender, stud); // ⬆⬆ 성별 안에는 set 이 있습니다.
//			System.out.println(gender);
			kind.put(st.kind, gender); // ⬆⬆ 분류 안에는 성별이 있습니다.
//			System.out.println(kind);
			Students.put(st.ban, kind); // ⬆⬆ 안에는 분류가 있습니다.
//			System.out.println(Students);

		}

		for (Object banObj : Students.entrySet()) {

			Entry ban = (Entry) banObj;

//			System.out.println(((TreeMap) ban.getValue()).size());

			System.out.println("[" + ban.getKey()
					+ "반] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			for (Object kindrObj : ((TreeMap) ban.getValue()).entrySet()) {

				Entry kind = (Entry) kindrObj;

//				System.out.println(((TreeMap) kind.getValue()).size());

				System.out.println("\t[" + kind.getKey()
						+ "] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

//				Entry asd = (Entry) ((TreeMap) kind.getValue()).entrySet();

				for (Object genderObj : ((TreeMap) kind.getValue()).entrySet()) {

					Entry gender = (Entry) genderObj;

					System.out.println("\t\t[" + gender.getKey()
							+ "성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

					TreeSet stud = (TreeSet) gender.getValue();
					// 결국 Student 클래스를 담고 있는것은 set
					// Comparable 사용 가능

//					System.out.println(stud.size());

					for (Object studSet : stud) {
						System.out.println(studSet);
					}
				}
				System.out.println();
			}
			System.out.println();
		}

	}
}

//[1반] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//[일반] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//	[남성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//1	일반	삼가인(남)		31	32	33		96	32	1
//1	일반	구가인(남)		11	12	16		39	13	2
//1	일반	한가인(남)		11	12	13		36	12	3
//	[여성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//1	일반	십이가인(여)	99	88	77		264	88	1
//1	일반	십가인(여)		99	99	55		253	84	2
//
//[특기생] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//	[남성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//1	특기생	십칠가인(남)	90	80	80	70	320	80	1
//1	특기생	칠가인(남)		87	76	70	90	323	80	1
//1	특기생	십오가인(남)	61	62	63	64	250	62	3
//	[여성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//1	특기생	팔가인(여)		81	82	83	84	330	82	1
//
//
//[2반] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//[일반] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//	[남성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//2	일반	십일가인(남)	90	90	90		270	90	1
//2	일반	오가인(남)		71	72	73		216	72	2
//	[여성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//2	일반	십칠가인(여)	99	99	99		297	99	1
//2	일반	십육가인(여)	99	90	81		270	90	2
//2	일반	사가인(여)		71	72	73		216	72	3
//
//[특기생] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//	[남성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//2	특기생	심삼가인(남)	41	42	43	44	170	42	1
//	[여성] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//2	특기생	육가인(여)		80	85	99	90	354	88	1
//2	특기생	십사가인(여)	51	52	53	54	210	52	2
//2	특기생	두가인(여)		21	22	23	24	90	22	3
