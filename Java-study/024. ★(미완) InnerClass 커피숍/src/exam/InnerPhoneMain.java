package exam;

class TelePerson { // 가입자

	String name; // 가입자명

	Telecom.PhoneParis tel; // TeleCom 내부의 PhoneParis 클래스

	public TelePerson(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name + ":" + tel; // name = 고객이름, tel 의 tostring = 통신사 대리점
	}

}

class Telecom { // 통신사

	String name; // 통신사 명

	TelePerson[] pers = new TelePerson[0]; // 가입자 정보
	PhoneParis[] pp = new PhoneParis[0]; // 대리점 정보

	public Telecom(String name) {
		super();
		this.name = name;
	}

	void repair(TelePerson per) {

	}

	TelePerson[] perAdd(TelePerson[] arr, TelePerson ps) {

		TelePerson[] buf = new TelePerson[arr.length + 1];

		for (int i = 0; i < arr.length; i++) {
			buf[i] = arr[i];
			// 임시배열 = 인자로 받아온 배열
		}

		buf[arr.length] = ps; // ps = 폰파리스에 방문한 각 한 사람의 객체

		return buf;
	}

	void perList() {

		System.out.println(name + " 고객명단 >>>>>");

		for (TelePerson telePerson : pers) {
			System.out.println(telePerson);
		}
	}

	class PhoneParis { // 대리점

		String name; // 대리점명

		public PhoneParis(String name) {
			super();
			this.name = name;
		}

		TelePerson[] pers = new TelePerson[0]; // 가입자

		void sale(TelePerson per) { // 고객 개인 한 사람의 객체가 넘어옴

			String ttt = Telecom.this.name + " " + name + "점 " + per.name + " 에게 핸드폰 팔아요";

			per.tel = this; // 받아온 사람 객체에 자신의 통신사 정보를 가지고 있는 대리점 객체를 push 합니다.

			pers = perAdd(pers, per); // perAdd 고객 명단 배열 리턴 (배열과 배열에 삽입할 값을 줌)
			// 판매가 되면 그 사람을 그 지점과 그 본사에 고객 명단에 집어 넣어줍니다.
			// 대리점 고유의 고객

			Telecom.this.pers = perAdd(Telecom.this.pers, per); // perAdd 고객 명단 배열 리턴 (배열과 배열에 삽입할 값을 줌)
			// 판매가 되면 그 사람을 그 지점과 그 본사에 고객 명단에 집어 넣어줍니다.
			// Telecom 을 static 이라고 생각한뒤, 모든 대리점에서 구매한 고객의 명단

			System.out.println(ttt);
		}

		@Override
		public String toString() {
			return "[" + Telecom.this.name + " " + name + "]"; // 통신사 판매점
		}

		void perList() {

			System.out.println(Telecom.this.name + " " + name + " 고객명단 >>>>>");

			for (TelePerson telePerson : pers) {
				System.out.println(telePerson);
			}
		}

	}
}

public class InnerPhoneMain {

	public static void main(String[] args) {

		Telecom sk = new Telecom("sk"); // 통신사를 만들고
		Telecom kt = new Telecom("kt");

		Telecom.PhoneParis sk_1 = sk.new PhoneParis("강남"); // 각 통신사에 따른 대리점을 생성
		Telecom.PhoneParis sk_2 = sk.new PhoneParis("서초");
		Telecom.PhoneParis sk_3 = sk.new PhoneParis("역삼");

		Telecom.PhoneParis kt_1 = kt.new PhoneParis("강남");
		Telecom.PhoneParis kt_2 = kt.new PhoneParis("동대문");

		TelePerson[] pers = { new TelePerson("이호인"), new TelePerson("김영재"), new TelePerson("박성언"),
				new TelePerson("김휘진"), new TelePerson("김지민"), new TelePerson("안정민"), new TelePerson("홍성혁"),
				new TelePerson("박시현"), new TelePerson("이주혁"), new TelePerson("김연섭"), new TelePerson("김현준"),
				new TelePerson("오민석"), new TelePerson("김예솔") };

		sk_1.sale(pers[0]); // sale 과 동시에 TelePerson 클래스에 PhoneParis 객체가 넘어감
		sk_2.sale(pers[1]);
		kt_1.sale(pers[2]);
		sk_3.sale(pers[6]);
		sk_2.sale(pers[4]);
		kt_1.sale(pers[1]);
		kt_2.sale(pers[5]);
		sk_1.sale(pers[7]);
		kt_1.sale(pers[9]);

		System.out.println();

		System.out.println(pers[0]);

		System.out.println();

		sk_1.perList();

		System.out.println();

		sk_2.perList();

		System.out.println();

		sk_3.perList();

		System.out.println();

		sk.perList();

	}

}
