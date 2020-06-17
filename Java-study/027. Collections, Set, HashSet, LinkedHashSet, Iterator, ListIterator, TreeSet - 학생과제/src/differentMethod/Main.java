package differentMethod;

import java.util.Comparator;
import java.util.TreeSet;

class Student {

	int ban, tot, avg, rank = 1, banRank = 1, allRank = 1;
	String name, kind;
	int[] jum;

	public Student(int ban, String name, int... jum) {
		super();
		this.ban = ban;
		this.name = name;
		this.jum = jum;
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
	}

	void rank(Student[] Student) {

		for (int i = 0; i < Student.length; i++) {
			if (this.avg < Student[i].avg) {
				allRank++; // 전체등수
				if (this.ban == Student[i].ban) {
					banRank++; // 반등수
					if (this.kind.equals(Student[i].kind))
						rank++; // 반에서 분류 등수
				}
			}

		}

	}

	@Override
	public String toString() {

		String result = this.ban + "반\t" + this.kind + "\t" + this.name + "\t";

		for (int i = 0; i < jum.length; i++) {
			result += jum[i] + "\t";
		}

		if (jum.length == 3)
			result += "\t";

		result += this.tot + "\t" + this.avg + "\t" + this.rank + "\t" + this.banRank + "\t" + this.allRank;

		return result;
	}

}

class RankComparator implements Comparator {

	@Override
	public int compare(Object StudMe, Object StudYou) {

		Student me = (Student) StudMe;
		Student you = (Student) StudYou;

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
			res = me.rank - you.rank; // 등수는 양수일때 뒤에 출력 (1들이 제일먼저 출력 음수)

//		if (res == 0) // 이름별로 정렬하면
//			res = me.name.compareTo(you.name);

		return res;
	}

}

class NameComparator implements Comparator { // 반과 분류가 같을시 이름별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		Student me = (Student) StudMe;
		Student you = (Student) StudYou;

		int res = me.ban - you.ban;

		if (res == 0) // 반이 같을때 분류가 같나 ?
			res = me.kind.compareTo(you.kind); // ()

//		if (res == 0) // 반이 같고, 분류가 같을때 등수별 출력
//			res = me.rank - you.rank; // 등수는 양수일때 뒤에 출력 (1들이 제일먼저 출력 음수)

		if (res == 0) // 반과 분류가 같을시 등수에 상관없이 이름별 정렬
			res = me.name.compareTo(you.name);

		return res;
	}
}

class BanrankComparator implements Comparator { // 반등수별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		Student me = (Student) StudMe;
		Student you = (Student) StudYou;

		int res = me.ban - you.ban;

//		if (res == 0) // 반이 같을때 분류가 같나 ?
//			res = me.kind.compareTo(you.kind); // ()

		if (res == 0) // 밭이 같다면 => 등수별 출력
			res = me.banRank - you.banRank; // 등수는 양수일때 뒤에 출력 (1들이 제일먼저 출력 음수)

		if (res == 0) // 등수가 같다면 => 이름별로 출력
			res = me.name.compareTo(you.name);

		return res;
	}
}

class AllrankComparator implements Comparator { // 전체등수별 출력

	@Override
	public int compare(Object StudMe, Object StudYou) {

		Student me = (Student) StudMe;
		Student you = (Student) StudYou;

		int res = me.allRank - you.allRank;

		if (res == 0) // 등수가 같다면
			res = me.name.compareTo(you.name);
		return res;
	}
}

public class Main {

	public static void main(String[] args) {

//		long start = System.currentTimeMillis();

		Student[] buf = { new Student(3, "한가인", 78, 66, 55), new Student(2, "두가인", 99, 55, 48, 15),
				new Student(1, "삼가인", 78, 65, 24), new Student(1, "사가인", 55, 95, 78, 65),
				new Student(2, "오가인", 10, 20, 30), new Student(3, "육가인", 55, 62, 71, 25),
				new Student(3, "칠가인", 78, 36, 45), new Student(2, "팔가인", 98, 46, 85, 67),
				new Student(1, "구가인", 45, 65, 37), new Student(1, "씹가인", 83, 73, 63, 43),
				new Student(2, "씹일가인", 75, 12, 32), new Student(3, "씹이가인", 78, 95, 25, 87),
				new Student(3, "씹삼가인", 89, 58, 79), new Student(2, "씹사가인", 65, 67, 34, 74),
				new Student(1, "씹오가인", 91, 67, 37)

		};

		// 등수 계산
		for (int i = 0; i < buf.length; i++) {
			buf[i].rank(buf);
		}

		// 등수 계산이 끝나 있어야 정렬 후 출력 이 가능
		RankComparator rank = new RankComparator();

		TreeSet students1 = new TreeSet(rank); // 이미 정렬 끝
		TreeSet students2 = new TreeSet(new NameComparator());
		TreeSet students3 = new TreeSet(new BanrankComparator());
		TreeSet students4 = new TreeSet(new AllrankComparator());

		for (int i = 0; i < buf.length; i++) {
			students1.add(buf[i]); // 객체 배열에서 하나씩 꺼내어 정렬 순서대로 push
			students2.add(buf[i]);
			students3.add(buf[i]);
			students4.add(buf[i]);
		}

		System.out.println("반과 분류가 같을때 등수별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		for (Object object : students1) {
			System.out.println(object);
		}

		System.out.println("\n반과 분류가 같을때 이름별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		for (Object object : students2) {
			System.out.println(object);
		}

		System.out.println("\n반과 분류가 같을때 반등수별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		for (Object object : students3) {
			System.out.println(object);
		}

		System.out.println("\n전체등수 별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		for (Object object : students4) {
			System.out.println(object);
		}

//		long end = System.currentTimeMillis();
//
//		System.out.println(end - start + "초");

	}

}

//반과 분류가 같을때 등수별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//1반	일반생	씹오가인	91	67	37		195	65	1	2	6
//1반	일반생	삼가인	78	65	24		167	55	2	4	9
//1반	일반생	구가인	45	65	37		147	49	3	5	13
//1반	특기생	사가인	55	95	78	65	293	73	1	1	3
//1반	특기생	씹가인	83	73	63	43	262	65	2	2	6
//2반	일반생	씹일가인	75	12	32		119	39	1	4	14
//2반	일반생	오가인	10	20	30		60	20	2	5	15
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	2
//2반	특기생	씹사가인	65	67	34	74	240	60	2	2	8
//2반	특기생	두가인	99	55	48	15	217	54	3	3	10
//3반	일반생	씹삼가인	89	58	79		226	75	1	1	1
//3반	일반생	한가인	78	66	55		199	66	2	3	5
//3반	일반생	칠가인	78	36	45		159	53	3	4	11
//3반	특기생	씹이가인	78	95	25	87	285	71	1	2	4
//3반	특기생	육가인	55	62	71	25	213	53	2	4	11
//
//반과 분류가 같을때 이름별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//1반	일반생	구가인	45	65	37		147	49	3	5	13
//1반	일반생	삼가인	78	65	24		167	55	2	4	9
//1반	일반생	씹오가인	91	67	37		195	65	1	2	6
//1반	특기생	사가인	55	95	78	65	293	73	1	1	3
//1반	특기생	씹가인	83	73	63	43	262	65	2	2	6
//2반	일반생	씹일가인	75	12	32		119	39	1	4	14
//2반	일반생	오가인	10	20	30		60	20	2	5	15
//2반	특기생	두가인	99	55	48	15	217	54	3	3	10
//2반	특기생	씹사가인	65	67	34	74	240	60	2	2	8
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	2
//3반	일반생	씹삼가인	89	58	79		226	75	1	1	1
//3반	일반생	칠가인	78	36	45		159	53	3	4	11
//3반	일반생	한가인	78	66	55		199	66	2	3	5
//3반	특기생	씹이가인	78	95	25	87	285	71	1	2	4
//3반	특기생	육가인	55	62	71	25	213	53	2	4	11
//
//반과 분류가 같을때 반등수별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//1반	특기생	사가인	55	95	78	65	293	73	1	1	3
//1반	특기생	씹가인	83	73	63	43	262	65	2	2	6
//1반	일반생	씹오가인	91	67	37		195	65	1	2	6
//1반	일반생	삼가인	78	65	24		167	55	2	4	9
//1반	일반생	구가인	45	65	37		147	49	3	5	13
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	2
//2반	특기생	씹사가인	65	67	34	74	240	60	2	2	8
//2반	특기생	두가인	99	55	48	15	217	54	3	3	10
//2반	일반생	씹일가인	75	12	32		119	39	1	4	14
//2반	일반생	오가인	10	20	30		60	20	2	5	15
//3반	일반생	씹삼가인	89	58	79		226	75	1	1	1
//3반	특기생	씹이가인	78	95	25	87	285	71	1	2	4
//3반	일반생	한가인	78	66	55		199	66	2	3	5
//3반	특기생	육가인	55	62	71	25	213	53	2	4	11
//3반	일반생	칠가인	78	36	45		159	53	3	4	11
//
//전체등수 별 출력>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//3반	일반생	씹삼가인	89	58	79		226	75	1	1	1
//2반	특기생	팔가인	98	46	85	67	296	74	1	1	2
//1반	특기생	사가인	55	95	78	65	293	73	1	1	3
//3반	특기생	씹이가인	78	95	25	87	285	71	1	2	4
//3반	일반생	한가인	78	66	55		199	66	2	3	5
//1반	특기생	씹가인	83	73	63	43	262	65	2	2	6
//1반	일반생	씹오가인	91	67	37		195	65	1	2	6
//2반	특기생	씹사가인	65	67	34	74	240	60	2	2	8
//1반	일반생	삼가인	78	65	24		167	55	2	4	9
//2반	특기생	두가인	99	55	48	15	217	54	3	3	10
//3반	특기생	육가인	55	62	71	25	213	53	2	4	11
//3반	일반생	칠가인	78	36	45		159	53	3	4	11
//1반	일반생	구가인	45	65	37		147	49	3	5	13
//2반	일반생	씹일가인	75	12	32		119	39	1	4	14
//2반	일반생	오가인	10	20	30		60	20	2	5	15
