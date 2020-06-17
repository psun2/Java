package answer;

interface RResource { // 자원 캐는 inteface
	boolean get(String name, int limit);
}

interface BBuilding { // 건물 관련 interface
	void build(String name);

	void repair(String name);
}

interface UUnit { // 유닛 관련 interface
	void repair(String name);

	void riding(String name);
}

interface Enemy { // 적 관련 interface
	void attack(String name);
}

interface SSCVWork extends RResource, BBuilding, UUnit, Enemy { // scv는 위에 생성된 모든일을 할 수 있습니다. 그러므로 다 상속 받을 수 있습니다.

}

class SCVData { // 게임의 정보 데이타 클래스로 일꾼에게 상속을 시켜도 되지만, 각자의 일꾼을 생성할때 생성자 함수에서 생성을 시켜주고, total 로 하나
				// 가지고 있어야하기 때문에 메인에서 맨처음에 인스턴스 화 해줍니다.

	String name; // 본진의 위치정보를 셋팅합니다.

	StarIndex[] resource = { new StarIndex("미네랄"), new StarIndex("가스") }; // 일꾼은 미네랄과 가스를 캘수 있습니다.

	boolean resourceChk(String name) { //

		boolean res = true;

		for (StarIndex bb : build) {
			if (bb.name.equals(name)) {
				for (int i = 0; i < resource.length; i++) {
					if (resource[i].cnt[0] < bb.money[i]) {
						res = false;
						break;
					}
				}
				if (res) {
					for (int i = 0; i < resource.length; i++) {
						resource[i].cnt[0] -= bb.money[i];
					}
				}

				break;
			}
		}

		return res;
	}

	StarIndex[] build = { new StarIndex("커맨드센터", 400, 0), new StarIndex("팩토리", 200, 100),
			new StarIndex("스타포트", 150, 100) };
	StarIndex[] unit = { new StarIndex("탱크"), new StarIndex("드랍쉽") };

	StarIndex[] enemy = { new StarIndex("저글링") };

	StarIndex[][] arr = { resource, build, unit, enemy };

	void addCnt(int kind, String name, int type, int cnt) {

		StarIndex[] buf = arr[kind];

		for (StarIndex index : buf) {
			if (index.name.equals(name)) {
				index.cnt[type] += cnt;

				return;
			}
		}
	}

	public SCVData(String name) { // 생성자를 통해 일꾼 객체를 생성시 이름을 받아 초기화 시켜줍니다.
		super();
		this.name = name;
	}

	void ppp() { // 해당 일꾼이 얼마만큼의 일을 했는지 로그를 찍는 메소드
		System.out.println(name + ">>>");
		String[] index = { "자원", "건물", "유닛", "적" };
		for (int i = 0; i < index.length; i++) {
			System.out.println(" ==> [" + index[i] + "]");
			for (StarIndex si : arr[i]) {
				System.out.println(si.ppp());
			}
		}
	}

}

class StarIndex { // 게임의 모든 정보를 가지고 있는 클래스

	String name; // 건물의 이름 또는 유닛의 이름
	int[] cnt = new int[2]; // [건물의 생성갯수, 건물의 수리 갯수]
	int[] money; // 건물 생성 가격 을 담습니다. // 미네랄, 가스

	public StarIndex(String name, int... money) { // 건물의 정보를 받아 data 저장하기 위한, 생성자 메소드
		super();
		this.name = name;
		this.money = money;
	}

	public StarIndex(String name) { // 유닛 정보를 초기화 해주는 생성자 오버로딩
		super();
		this.name = name;
	}

	String ppp() {
		return "[" + name + ":" + cnt[0] + "," + cnt[1] + "]";
	}
}

class SSCV implements SSCVWork {

	SCVData myData, totData;

	public SSCV(SCVData totData, String name) { // q
		super();
		this.totData = totData;
		myData = new SCVData(name);
	}

	@Override
	public boolean get(String name, int limit) {
		int cnt = 8;
		totData.addCnt(0, name, 0, cnt);
		myData.addCnt(0, name, 0, cnt);
		System.out.println(totData.name + " " + myData.name + " " + name + " " + cnt + " 캐요");
		for (StarIndex index : totData.resource) {
			System.out.print(index.ppp() + ",");
		}
		System.out.println();
		return indexCnt(totData.resource, name) >= limit;
	}

	int indexCnt(StarIndex[] arr, String name) {
		for (StarIndex starIndex : arr) {
			if (starIndex.name.equals(name))
				return starIndex.cnt[0];
		}
		return 0;
	}

	@Override
	public void build(String name) {

		if (totData.resourceChk(name)) {

			totData.addCnt(1, name, 0, 1);
			myData.addCnt(1, name, 0, 1);
			System.out.println(totData.name + " " + myData.name + " " + name + " 지어요");
		} else {
			System.out.println(totData.name + " " + myData.name + " " + name + " 자원부족");
		}
	}

	@Override
	public void repair(String name) {
		totData.addCnt(1, name, 1, 1);
		myData.addCnt(1, name, 1, 1);
		totData.addCnt(2, name, 1, 1);
		myData.addCnt(2, name, 1, 1);
		System.out.println(totData.name + " " + myData.name + " " + name + " 고쳐요");

	}

	@Override
	public void riding(String name) {
		System.out.println(totData.name + " " + myData.name + " " + name + " 타요");

	}

	@Override
	public void attack(String name) {
		totData.addCnt(3, name, 0, 1);
		myData.addCnt(3, name, 0, 1);
		System.out.println(totData.name + " " + myData.name + " " + name + " 싸워요");

	}

}

public class InterStarCraftMain {

	static BBuilding bb;
	static RResource rr;
	static UUnit uu;
	static Enemy ee;

	static void getRe(String name, int limit, SSCV... scvs) {
		while (true) {
			for (SSCV sscv : scvs) {
				rr = sscv;
				if (rr.get(name, limit))
					return;
			}
		}
	}

	public static void main(String[] args) {

		SCVData tot = new SCVData("3시");

		SSCV[] scv = { new SSCV(tot, "1호기"), new SSCV(tot, "2호기"), new SSCV(tot, "3호기"), new SSCV(tot, "4호기") };

		// rr = scvs[0];
		getRe("미네랄", 500, scv[0], scv[1], scv[3]);
		bb = scv[2]; // ✔ scv.메소드 명령을 해줘도 되지만, 굳이 bb. 이라고 하는 이유는 그 건물을 클릭했을때 기능이 동작되도록 하기 위한 느낌이 준다고
						// 합니다.;
		bb.build("커맨드센터");
		bb.repair("커맨드센터");
		uu = scv[1];
		uu.riding("드랍쉽");
		uu.repair("드랍쉽");
		ee = scv[0];
		ee.attack("저글링");

		System.out.println();
		for (SSCV sscv : scv) {
			sscv.myData.ppp();
		}
		tot.ppp();
	}

}