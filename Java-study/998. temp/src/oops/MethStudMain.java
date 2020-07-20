package oops;
////학생클래스를 정의, 선언, 생성, 호출 하여 학생정보를 출력하세요

//---  반, 이름, 성별,  점수 [국어, 영어, 수학], 총점, 평균, 등수
class MethStud {
	int ban, tot, avg, rank = 1, totRank = 1;
	String name, gender;
	int[] jum;

	void init(int ban, String name, String gender, int kor, int eng, int mat) {
		this.ban = ban;
		this.name = name;
		this.gender = gender;
		this.jum = new int[] { kor, eng, mat };

		for (int i : jum) {
			tot += i;
		}
		avg = tot / jum.length;

	}

	void ppp() {
		String res = ban + "\t" + name + "\t" + gender + "\t";
		for (int i : jum) {
			res += i + "\t";
		}
		res += tot + "\t" + avg + "\t";
		res += totRank + "\t" + rank;

		System.out.println(res);
	}

	void rankCal(MethStud[] arr) { // method의 parameter 로 전체 배열을 받습니다.
		for (MethStud you : arr) { // 받아온 배열을 for문 을 사용해 반복하여 돌립니다.
			if (avg < you.avg) { // this.avg < you.avg
				totRank++;
				if (ban == you.ban)
					rank++;
			}
		}
	}
}

public class MethStudMain {

	public static void main(String[] args) {
		MethStud[] studs = new MethStud[10];
		for (int i = 0; i < studs.length; i++) {
			studs[i] = new MethStud();
		}

		studs[0].init(2, "현빈", "남", 63, 67, 69);
		studs[1].init(1, "장희빈", "여", 73, 77, 79);
		studs[2].init(3, "커피빈", "여", 53, 57, 59);
		studs[3].init(1, "원빈", "남", 93, 97, 99);
		studs[4].init(3, "미스터빈", "남", 43, 47, 49);
		studs[5].init(2, "투빈", "여", 83, 87, 89);
		studs[6].init(2, "골빈", "남", 23, 27, 29);
		studs[7].init(1, "자바빈", "여", 83, 57, 49);
		studs[8].init(3, "젤리빈", "남", 63, 76, 91);
		studs[9].init(1, "텅빈", "남", 31, 27, 94);

		int max = 0;
		for (MethStud me : studs) {
			if (max < me.ban)
				max = me.ban;

			me.rankCal(studs);
		}

		for (int i = 1; i <= max; i++) {
			System.out.println(i + "반 >>>>>");
			for (int r = 1; r <= studs.length; r++) {
				for (MethStud st : studs) {
					if (st.ban == i && st.rank == r)
						st.ppp();
				}
			}

		}

	}

}
