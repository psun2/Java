package exam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

class StudentData { // outer 에서는 학생정보 객체를 가지고 등수 계산

	ArrayList data = new ArrayList();

	void rank() {

		ListIterator lit = data.listIterator();

		while (lit.hasNext()) {

			Student buf = (Student) lit.next();

			ArrayList data = new ArrayList(this.data);
			ListIterator lit2 = data.listIterator();

			while (lit2.hasNext()) {

				Student buf2 = (Student) lit2.next();

				if (buf.avg < buf2.avg) {
					buf.allRank++; // 전체등수
					if (buf.ban == buf2.ban) {
						buf.banRank++; // 반등수
						if (buf.kind.equals(buf2.kind))
							buf.banKindRank++; // 반에서 분류 등수
					}
					if (buf.kind.equals(buf2.kind))
						buf.kindRank++;
				}

			}

		}

	}

	class Student { // inner

		int ban, id, tot, avg, banKindRank = 1, banRank = 1, kindRank = 1, allRank = 1;
		String name, kind;
		int[] jum;

		public Student(int id, int ban, String name, int... jum) {
			super();
			this.ban = ban;
			this.name = name;
			this.jum = jum;
			this.id = id;
			init();
		}

		void init() {
			int index = jum.length - 3;

			this.kind = new String[] { "일반생", "특기생" }[index];
			calc();
		}

		void calc() {

			for (int i = 0; i < jum.length; i++) {
				this.tot += jum[i];
			}

			this.avg = tot / jum.length;

			StudentData.this.data.add(this);
		}

		@Override
		public String toString() {

			String result = this.ban + "반\t" + this.kind + "\t" + this.name + "\t";

			for (int i = 0; i < jum.length; i++) {
				result += jum[i] + "\t";
			}

			if (jum.length == 3)
				result += "\t";

			result += this.tot + "\t" + this.avg + "\t" + this.banKindRank + "\t" + this.banRank + "\t" + kindRank
					+ "\t" + this.allRank;

			return result;
		}

	}
}

class RankComparator implements Comparator {

	@Override
	public int compare(Object StudMe, Object StudYou) {

		StudentData.Student me = (StudentData.Student) StudMe;
		StudentData.Student you = (StudentData.Student) StudYou;

		// me가 기준

		// 음수 : me 를 기준 앞
		// 0 : 자신
		// 양수 : me 를 기준 뒤

		// 반을 찾고 (중복가능)
		// 분류 찾고 (중복가능)
		// 등수 찾고 (중복가능)
		// 이름 찾고 (중복가능)
		// 이름이 같다면 ...?

		// 반 me 를 기준으로 음수면 나보다 앞반

		int res = me.ban - you.ban;

		if (res == 0) // 반이 같을때 분류가 같나 ?
			res = me.kind.compareTo(you.kind); // ()

		if (res == 0) // 반이 같고, 분류가 같을때 등수별 출력
			res = me.banKindRank - you.banKindRank; // 등수는 양수일때 뒤에 출력 (1들이 제일먼저 출력 음수)

		if (res == 0) // 혹시 등수가 같다면 이름순으로 출력
			res = me.name.compareTo(you.name);

		if (res == 0) // 모든 경우의 수가 같다면 ... id 값이 빠른 순으로 출력
			res = me.id - you.id;

		return res;
	}

}

class BanrankComparator implements Comparator { // 반등수별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		StudentData.Student me = (StudentData.Student) StudMe;
		StudentData.Student you = (StudentData.Student) StudYou;

		int res = me.ban - you.ban;

//		if (res == 0) // 반이 같을때 분류가 같나 ?
//			res = me.kind.compareTo(you.kind); // ()

		if (res == 0) // 밭이 같다면 => 등수별 출력
			res = me.banRank - you.banRank; // 등수는 양수일때 뒤에 출력 (1들이 제일먼저 출력 음수)

		if (res == 0) // 반과 등수가 같다면 분류로 분류
			res = me.kind.compareTo(you.kind);

		if (res == 0) // 반 등수 분류 가 같다면 이름 순으로 출력
			res = me.name.compareTo(you.name);

		if (res == 0) // 모든 경우의 수가 같다면 ... id 값이 빠른 순으로 출력
			res = me.id - you.id;

		return res;
	}
}

class AllrankComparator implements Comparator { // 전체등수별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		StudentData.Student me = (StudentData.Student) StudMe;
		StudentData.Student you = (StudentData.Student) StudYou;

		int res = me.allRank - you.allRank;

		if (res == 0) // 등수가 같다면 이름별
			res = me.name.compareTo(you.name);

		if (res == 0) // 등수와 이름이 같다면 분류별
			res = me.kind.compareTo(you.kind);

		if (res == 0) // 모든 경우의 수가 같다면 ... id 값이 빠른 순으로 출력
			res = me.id - you.id;

		return res;
	}
}

class NameComparator implements Comparator { // 반과 분류가 같을시 이름별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		StudentData.Student me = (StudentData.Student) StudMe;
		StudentData.Student you = (StudentData.Student) StudYou;

		int res = me.name.compareTo(you.name);

		if (res == 0) // 이름이 같다면...
			me.kind.compareTo(you.kind);

		if (res == 0) // 이름과 분류가 같다면
			res = me.allRank - you.allRank; // 전체 등수 높은 순으로 출력

		if (res == 0) // 전체등수가 같다면 id 값으로 출력
			res = me.id - you.id;

		return res;
	}
}

class KindComparator implements Comparator { // 분류별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		StudentData.Student me = (StudentData.Student) StudMe;
		StudentData.Student you = (StudentData.Student) StudYou;

		int res = me.kind.compareTo(you.kind);

		if (res == 0) // 분류가 같다면 ... 이름순으로 ....
			me.name.compareTo(you.name);

		if (res == 0) // 분류 와 이름이 같다면... 전체 등수로...
			res = me.allRank - you.allRank; // 전체 등수 높은 순으로 출력

		if (res == 0) // 모든 경우의 수가 같다면 ... id 값이 빠른 순으로 출력
			res = me.id - you.id;

		return res;
	}
}

public class Main {

	public static void main(String[] args) {

//		long start = System.currentTimeMillis();

		StudentData studData = new StudentData(); // 학생들의 정보를 담을 outer 클래스 생성

		// id, 반, 이름, 점수...
		StudentData.Student[] buf = { studData.new Student(1, 3, "한가인", 78, 66, 55),
				studData.new Student(2, 2, "이계인", 78, 95, 25, 87), studData.new Student(3, 1, "삼가인", 78, 65, 24),
				studData.new Student(4, 3, "이계인", 78, 95, 25, 87), studData.new Student(5, 2, "오가인", 10, 20, 30),
				studData.new Student(6, 3, "육가인", 55, 62, 71, 25), studData.new Student(7, 3, "칠가인", 78, 36, 45),
				studData.new Student(8, 2, "팔가인", 98, 46, 85, 67), studData.new Student(9, 1, "구가인", 45, 65, 37),
				studData.new Student(10, 1, "삼가인", 83, 73, 63, 43), studData.new Student(11, 2, "일계인", 75, 12, 32),
				studData.new Student(12, 3, "이계인", 78, 95, 25, 87), studData.new Student(13, 3, "삼계인", 89, 58, 79),
				studData.new Student(14, 2, "사계인", 65, 67, 34, 74), studData.new Student(15, 1, "오계인", 91, 67, 37)

		};

		// 등수 계산
		studData.rank();

		// 등수 계산이 끝나 있어야 정렬 후 출력 이 가능
		RankComparator rank = new RankComparator();

		// 전체등수 11등 - 3반 칠가인, 3반 육가인
		// 2반 이계인 1명, 3반 이계인 두명 => 총3명 전체등수 3등
		// 삼가인 - 3반과 1반 두명
		// 오계인, 삼가인 - 반에서 등수가 같을 경우
		TreeSet students1 = new TreeSet(rank); // 이미 정렬 끝
		TreeSet students2 = new TreeSet(new BanrankComparator());
		TreeSet students3 = new TreeSet(new AllrankComparator());
		TreeSet students4 = new TreeSet(new NameComparator());
		TreeSet students5 = new TreeSet(new KindComparator());

		for (int i = 0; i < buf.length; i++) {
			students1.add(buf[i]); // 객체 배열에서 하나씩 꺼내어 정렬 순서대로 push
			students2.add(buf[i]);
			students3.add(buf[i]);
			students4.add(buf[i]);
			students5.add(buf[i]);
		}

		System.out.println("반, 분류별 등수 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("반\t분류\t이름\t국어\t영어\t수학\t특기\t총점\t평균\t등수\t반등수\t분류등수\t전체등수");
//		int ban = 1;
		Iterator it = students1.iterator();
		while (it.hasNext()) { // Iterator 를 시용한 출력

			StudentData.Student stud = (StudentData.Student) it.next();

//			if (ban != stud.ban) {
//				System.out.println();
//				ban = stud.ban;
//			}

			System.out.println(stud);
		}

		System.out.println("\n반 등수별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("반\t분류\t이름\t국어\t영어\t수학\t특기\t총점\t평균\t등수\t반등수\t분류등수\t전체등수");
		for (Object object : students2) {
			System.out.println(object);
		}

		System.out.println("\n전체 등수별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("반\t분류\t이름\t국어\t영어\t수학\t특기\t총점\t평균\t등수\t반등수\t분류등수\t전체등수");
		for (Object object : students3) {
			System.out.println(object);
		}

		System.out.println("\n이름별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("반\t분류\t이름\t국어\t영어\t수학\t특기\t총점\t평균\t등수\t반등수\t분류등수\t전체등수");
		for (Object object : students4) {
			System.out.println(object);
		}

		System.out.println("\n분류별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("반\t분류\t이름\t국어\t영어\t수학\t특기\t총점\t평균\t등수\t반등수\t분류등수\t전체등수");
		for (Object object : students5) {
			System.out.println(object);
		}

//		long end = System.currentTimeMillis();
//
//		System.out.println(end - start + "초");

	}

}

//반, 분류별 등수 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//반	분류	이름	국어	영어	수학	특기	총점	평균	등수	반등수	분류등수	전체등수
//1반	일반생	오계인	91	67	37		195	65	1	1	3	7
//1반	일반생	삼가인	78	65	24		167	55	2	3	4	10
//1반	일반생	구가인	45	65	37		147	49	3	4	6	13
//1반	특기생	삼가인	83	73	63	43	262	65	1	1	5	7
//
//2반	일반생	일계인	75	12	32		119	39	1	4	7	14
//2반	일반생	오가인	10	20	30		60	20	2	5	8	15
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	1	2
//2반	특기생	이계인	78	95	25	87	285	71	2	2	2	3
//2반	특기생	사계인	65	67	34	74	240	60	3	3	6	9
//
//3반	일반생	삼계인	89	58	79		226	75	1	1	1	1
//3반	일반생	한가인	78	66	55		199	66	2	4	2	6
//3반	일반생	칠가인	78	36	45		159	53	3	5	5	11
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	특기생	육가인	55	62	71	25	213	53	3	5	7	11
//
//반 등수별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//반	분류	이름	국어	영어	수학	특기	총점	평균	등수	반등수	분류등수	전체등수
//1반	특기생	삼가인	83	73	63	43	262	65	1	1	5	7
//1반	일반생	오계인	91	67	37		195	65	1	1	3	7
//1반	일반생	삼가인	78	65	24		167	55	2	3	4	10
//1반	일반생	구가인	45	65	37		147	49	3	4	6	13
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	1	2
//2반	특기생	이계인	78	95	25	87	285	71	2	2	2	3
//2반	특기생	사계인	65	67	34	74	240	60	3	3	6	9
//2반	일반생	일계인	75	12	32		119	39	1	4	7	14
//2반	일반생	오가인	10	20	30		60	20	2	5	8	15
//3반	일반생	삼계인	89	58	79		226	75	1	1	1	1
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	일반생	한가인	78	66	55		199	66	2	4	2	6
//3반	특기생	육가인	55	62	71	25	213	53	3	5	7	11
//3반	일반생	칠가인	78	36	45		159	53	3	5	5	11
//
//전체 등수별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//반	분류	이름	국어	영어	수학	특기	총점	평균	등수	반등수	분류등수	전체등수
//3반	일반생	삼계인	89	58	79		226	75	1	1	1	1
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	1	2
//2반	특기생	이계인	78	95	25	87	285	71	2	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	일반생	한가인	78	66	55		199	66	2	4	2	6
//1반	특기생	삼가인	83	73	63	43	262	65	1	1	5	7
//1반	일반생	오계인	91	67	37		195	65	1	1	3	7
//2반	특기생	사계인	65	67	34	74	240	60	3	3	6	9
//1반	일반생	삼가인	78	65	24		167	55	2	3	4	10
//3반	특기생	육가인	55	62	71	25	213	53	3	5	7	11
//3반	일반생	칠가인	78	36	45		159	53	3	5	5	11
//1반	일반생	구가인	45	65	37		147	49	3	4	6	13
//2반	일반생	일계인	75	12	32		119	39	1	4	7	14
//2반	일반생	오가인	10	20	30		60	20	2	5	8	15
//
//이름별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//반	분류	이름	국어	영어	수학	특기	총점	평균	등수	반등수	분류등수	전체등수
//1반	일반생	구가인	45	65	37		147	49	3	4	6	13
//2반	특기생	사계인	65	67	34	74	240	60	3	3	6	9
//1반	특기생	삼가인	83	73	63	43	262	65	1	1	5	7
//1반	일반생	삼가인	78	65	24		167	55	2	3	4	10
//3반	일반생	삼계인	89	58	79		226	75	1	1	1	1
//2반	일반생	오가인	10	20	30		60	20	2	5	8	15
//1반	일반생	오계인	91	67	37		195	65	1	1	3	7
//3반	특기생	육가인	55	62	71	25	213	53	3	5	7	11
//2반	특기생	이계인	78	95	25	87	285	71	2	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//2반	일반생	일계인	75	12	32		119	39	1	4	7	14
//3반	일반생	칠가인	78	36	45		159	53	3	5	5	11
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	1	2
//3반	일반생	한가인	78	66	55		199	66	2	4	2	6
//
//분류별 출력 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//반	분류	이름	국어	영어	수학	특기	총점	평균	등수	반등수	분류등수	전체등수
//3반	일반생	삼계인	89	58	79		226	75	1	1	1	1
//3반	일반생	한가인	78	66	55		199	66	2	4	2	6
//1반	일반생	오계인	91	67	37		195	65	1	1	3	7
//1반	일반생	삼가인	78	65	24		167	55	2	3	4	10
//3반	일반생	칠가인	78	36	45		159	53	3	5	5	11
//1반	일반생	구가인	45	65	37		147	49	3	4	6	13
//2반	일반생	일계인	75	12	32		119	39	1	4	7	14
//2반	일반생	오가인	10	20	30		60	20	2	5	8	15
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	1	2
//2반	특기생	이계인	78	95	25	87	285	71	2	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//3반	특기생	이계인	78	95	25	87	285	71	1	2	2	3
//1반	특기생	삼가인	83	73	63	43	262	65	1	1	5	7
//2반	특기생	사계인	65	67	34	74	240	60	3	3	6	9
//3반	특기생	육가인	55	62	71	25	213	53	3	5	7	11
