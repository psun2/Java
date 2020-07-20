package oops;

class ReturnStud {

	ReturnMakeStud rrr;

	String name, kind; // 1. name(이름), kind(분류) 맴버 변수 선언

	int[] jum; // 2. 점수를 담을 수 있는 배열 맴버 변수 선언

	int tot, avg, totRank, rank, pos, cateNum;
	// 3. 총점 - tot, 평균 - avg, totRank - 전체등수, rank - 반등수 맴버변수 선언

	void init(ReturnMakeStud rrr, String name, int... jum) {
		// 4. init => 맴버 변수를 초기화 하는 메소드 생성 parameter 로는 name 과 // 점수들을 배열의 형태로 받아 옵니다.
		this.rrr = rrr;
		this.name = name;
		// 5. 클래스에 속한 name 을 parameter 의 name 값으로 교체 해줍니다.
		this.jum = jum;
		// 6. 클래스에 속한 jum 배열 을 parameter 의 jum 배열의 값으로 교체 해줍니다.

		cateNum = jum.length - 3;
		// 7. 익명 배열 형태를 사용하기 위한 index번호 값을 저장하는 cateNum 변수 선언 및 초기화
		this.kind = new String[] { "일반", "특기생" }[cateNum];
		// 8. 클래스에 속한 kind 맴버 변수를 익명 배열 형식의 배열에서 index번호를 받아서 바로 값을 초기화 합니다.
		// index 번호는 cateNum 에서 초기화 한 cateNum 변수를 사용 합니다.

		for (int i : jum) { // 9. 총점 계산을 위한 반복문
			this.tot += i; // 10. 클래스에 속한 tot 맴버 변수에 jum 배열을 돌면서 jum 배열의 index 값을 더해 줍니다.
		}
		this.avg = tot / jum.length; // 11. 클래스에 속한 avg(평균) 맴버 변수를 초기화 하기 위해서 합계에서 jum배열의 길이 즉 과목 수 로 나누어 줍니다.
	}

	void ppp() { // 12. 출력하기 위한 메소드
		String ttt = kind + "\t" + name + "\t";

		for (int i : jum) { // 13. 점수는 배열 형태이기 때문에 반복문 을 사용하여 출력 할 수 있게 합니다.
			ttt += i + "\t";
		}

		if (cateNum == 0)
			// 14. cateNum = jum.length - 3; 즉 과목의 길이가 3일때 0의 값을 가지기 때문에,
			// 일반 학생이 되면 출력시 특기생보다 한과목이 모잘라 \t 을 사용 하여 마지막 위치에 공백을 한 탭 주도록 합니다.
			ttt += "\t";

		ttt += tot + "\t" + avg + "\t" + totRank + "\t" + rank;

		System.out.println(ttt);
	}

	void rankCal(ReturnStud[] studs) {
		// 15. rankCal 은 순위를 계산하는 메소드이며,
		// 인자로 studs 즉 ReturnStud의 인스턴스를 담은 배열을 가져오는 이유는
		// rankCal 메소드를 사용 하면 본인의 평균 값밖에 모르기 때문에 전 인원의 평균을 알아야 순위 계산을 할 수 있으므로,
		// ReturnStud의 인스턴스를 담은 studs 배열을 parameter로 받아 옵니다.
		totRank = 1;
		rank = 1;
		// 16. 전체 등수 와 반 등수 는 1부터 시작합니다. why? 0등은 없기 때문입니다.
		for (ReturnStud you : studs) {
			// 17. ReturnStud type 의 studs 배열을 순회 하면서
			if (avg < you.avg) {
				// 평균을 비교 하여
				totRank++;
				// 나보다 높다면 전체등수에 1씩 더해줍니다.
				if (cateNum == you.cateNum)
					// 또한 그중에서 cateNum => 과목 갯수 즉 분류가 같다면
					rank++;
				// 분류 등수(반등수) 를 1씩 증가시켜줍니다.
			}
		}

		pos = rank - 1;
		// 26. pos = position 자신의 인덱스의 위치를 아는 방법:
		// 현재 정렬이 순위 별로 되어 있기때문에 자신의 순위 에서 index를 위해 -1 를 해주게 되면 자신의 인덱스 번호를 알 수 있습니다.

		rrr.studs[cateNum][pos] = this;
		// ReturnStud[][] studs = new ReturnStud[2][]; // => [ [], [] ]

		if (kind.equals("특기생"))
			pos += rrr.cateCnt[0];

	}
}

class ReturnMakeStud {
	// 18. ReturnStud 클래스의 인스턴스 생성과 init 를 한꺼번에 진행하기 위하여,
	// ReturnMakeStud 클래스를 만들어 그 작업들을 한번에 처리 할 수 있도록 진행하는 코드를 입력합니다.

	String[] cate = new String[] { "일반", "특기생" };
	int[] cateCnt = new int[cate.length];
	// [0, 1]

	ReturnStud[][] studs = new ReturnStud[2][]; // => [ [0,,], [] ]

	ReturnStud make(String name, int... line) {
		// 19. make 함수는 리턴형이 ReturnStud type 인 함수이면서
		// name 과 점수들을 배열 형태로 받는 rest 문법의 parameter을 받 습니다.
		cateCnt[line.length - 3]++;
		ReturnStud res = new ReturnStud();
		// 20. ReturnStud type을 res 변수에 담아 인스턴스를 생성하고
		res.init(this, name, line);
		// 21. res를 받아온 parameter로 init 함수를 실행시킵니다.
		// 현재 ReturnStud 클래스의 인스턴스 이기때문에
		// ReturnStud 에 존재하는 모든 메소드에 접근이 가능합니다.
		return res;
		// 22. res 즉, init가 끝난 ReturnStud 인스턴스를 반환 합니다.
	}

	void studsArrSet() {
		for (int i = 0; i < cateCnt.length; i++) {
			studs[i] = new ReturnStud[cateCnt[i]];
		}
		
		/*
		 * studs = { { null, null ,null ,null ,null ,null }, //일반 일반1등 일반2등 일반3등 ...
		 * 
		 * { null, null ,null ,null } //특기생 특기생1등,특기생2등,특기생3등,특기생4등
		 * 
		 * };
		 */
	}

	void ppp() {
		for (int i = 0; i < cate.length; i++) {
			System.out.println(cate[i] + ">>>>>");

			for (ReturnStud st : studs[i]) {
				st.ppp();
			}
		}
	}
}

public class ReturnStudMain {

	public static void main(String[] args) {

		ReturnMakeStud rms = new ReturnMakeStud();
		// 23. 초기화 와 인스턴스 생성을 한번에 진행해서 리턴 받기를 원하기 때문에
		// ReturnStud 의 인스턴스를 생성 하는 것이 아니라 위의 작업을 한번에 할 수 있게 만든
		// ReturnMakeStud 클래스의 make 메소드를 사용 하기 위해 ReturnMakeStud 를 인스턴스 생성합니다.

		ReturnStud[] studs = { rms.make("한가인", 76, 77, 71), rms.make("두가인", 66, 67, 61, 62),
				rms.make("삼가인", 96, 97, 91), rms.make("사가인", 56, 57, 51), rms.make("손예진", 80, 87, 81, 83),
				rms.make("한예진", 76, 70, 71), rms.make("두예진", 66, 60, 61, 62), rms.make("삼예진", 96, 89, 91),
				rms.make("사예진", 56, 48, 51), rms.make("오예진", 83, 80, 81, 83) };
		// 24. ReturnStud type 배열에 서 ReturnMakeStud 인스턴스의 make 메소드를 통해 ReturnStud 의
		// 인스턴스를 생성합니다.

		rms.studsArrSet();

		for (ReturnStud me : studs) {
			// 25. 순위 계산을 위해 ReturnStud 의 인스턴스를 담고있는 studs 배열을 순회하면서
			// rank를 계산하는 함수인 rankCal 을 호출합니다.
			me.rankCal(studs);
			// 각각의 인스턴스에서 자신의 순위를 구하기 위하여, rankCal 호출 할때 전체 인스턴스가 담긴 studs 배열을 인자로 보내줍니다.
		}

		System.out.println("1번-----------------------");

		for (String kind : rms.cate) {
			System.out.println(kind + " >>>>>>>>>>");

			for (int i = 1; i <= studs.length; i++) {
				for (ReturnStud me : studs) {
					if (kind.equals(me.kind) && me.rank == i) {
						me.ppp();

					}
				}
			}
		}

		System.out.println("2번-----------------------");
		String title = "";
		for (int i = 0; i < studs.length; i++) {

			for (ReturnStud me : studs) {

				if (me.pos == i) {
					if (!title.equals(me.kind)) {
						title = me.kind;
						System.out.println(me.kind + " >>>>>>>>>>");
					}
					me.ppp();

				}
			}
		}

		System.out.println("3번-----------------------");

		rms.ppp();

	}

}



asdjkasjfklajklfajksjdkjasdjkalsjfklajkjklafj