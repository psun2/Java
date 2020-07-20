package lang_p;

import java.util.Arrays;

class TelePerson { // 가입자

	String name; // 가입자명

	TeleCom.PhoneParis tel; // TeleCom 내부의 PhoneParis 클래스

	public TelePerson(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name + ":" + tel;
	}

}

class TeleCom { // 통신사

	String name; // 통신사 명

	TelePerson[] pers = new TelePerson[0]; // 가입자 정보
	PhoneParis[] pp = new PhoneParis[0]; // 대리점 정보

	public TeleCom(String name) {
		super();
		this.name = name;
	}

	void repair(TelePerson per) {

	}

	TelePerson[] perAdd(TelePerson[] arr, TelePerson ps) {
		
		TelePerson[] buf = new TelePerson[arr.length + 1];

		for (int i = 0; i < arr.length; i++) {
			buf[i] = arr[i];
		}
		buf[arr.length] = ps;
		return buf;
	}

	void perList() {

		System.out.println(name + " 고객명단 >>>>>");

		for (TelePerson telePerson : pers) {
			System.out.println(telePerson);
		}
	}

	class PhoneParis { // 판매점

		String name; // 판매인

		public PhoneParis(String name) {
			super();
			this.name = name;
		}

		TelePerson[] pers = new TelePerson[0]; // 가입자

		void sale(TelePerson per) {
			String ttt = TeleCom.this.name + " " + name + "점 " + per.name + " 에게 핸드폰 팔아요";
			per.tel = this;

			pers = perAdd(pers, per);

			TeleCom.this.pers = perAdd(TeleCom.this.pers, per);

			System.out.println(ttt);
		}

		@Override
		public String toString() {
			return "[" + TeleCom.this.name + " " + name + "]";
		}

		void perList() {
			System.out.println(TeleCom.this.name + " " + name + " 고객명단 >>>>>");

			for (TelePerson telePerson : pers) {
				System.out.println(telePerson);
			}
		}

	}
}

public class InnerPhoneMain {

	public static void main(String[] args) {
		TeleCom sk = new TeleCom("sk"); // 통신사를 만들고
		TeleCom kt = new TeleCom("kt");

		TeleCom.PhoneParis sk_1 = sk.new PhoneParis("강남"); // 각 통신사에 따른 대리점을 생성
		TeleCom.PhoneParis sk_2 = sk.new PhoneParis("서초");
		TeleCom.PhoneParis sk_3 = sk.new PhoneParis("역삼");

		TeleCom.PhoneParis kt_1 = kt.new PhoneParis("강남");
		TeleCom.PhoneParis kt_2 = kt.new PhoneParis("동대문");

		TelePerson[] pers = { new TelePerson("이호인"), new TelePerson("김영재"), new TelePerson("박성언"),
				new TelePerson("김휘진"), new TelePerson("김지민"), new TelePerson("안정민"), new TelePerson("홍성혁"),
				new TelePerson("박시현"), new TelePerson("이주혁"), new TelePerson("김연섭"), new TelePerson("김현준"),
				new TelePerson("오민석"), new TelePerson("김예솔") };

		sk_1.sale(pers[0]);
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
		
		sk.perList();
		
		System.out.println();

		////////////////////////////////////////////////////////////////////

		System.out.println();

		System.out.println(new TelePerson("이호인").toString());

	}

}
