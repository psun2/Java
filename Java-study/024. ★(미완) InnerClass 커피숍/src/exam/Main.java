package exam;

import java.util.Arrays;
import java.util.Random;

class Company {

	String name;
	String[] menue; // 메뉴 는 본사가 결정
	int[] price; // 가격은 본사가 결정
	Franchise[] franchise; // 지점
	int[][] result; // 전지점 합계

//	 result = { 
//	 {,}, // 아메리카노 - 누적 수량, 누적 금액 
//	 {,}, // 아시아노 - 누적 수량, 누적 금액 
//	 {,}, // 아프리카노 - 누적 수량, 누적 금액
//	 {,}  //  total - 누적수량, 누적 금액
//	 };

	public Company(String name) {
		super();
		this.name = name;
		franchise = new Franchise[0];
		init();
	}

	void init() {

		this.menue = new String[] { "아메리카노", "아시아노", "아프리카노", "total" };

		if (this.name.equals("커피콩"))
			this.price = new int[] { 2100, 2500, 2200 };
		else
			this.price = new int[] { 2000, 2300, 2700 };

		this.result = new int[menue.length][2];
	}

	Franchise addFranchise(String name) {

		Franchise[] temp = new Franchise[franchise.length + 1];

		for (int i = 0; i < franchise.length; i++) {
			temp[i] = franchise[i];
		}

		temp[franchise.length] = new Franchise(name);

		franchise = temp;

		return new Franchise(name);

	}

	@Override
	public String toString() {

		String result = this.name + " 판매현황  >>>>>>>>\n";

		for (int i = 0; i < this.result.length; i++) {
			result += menue[i] + " : " + this.result[i][0] + "ea, " + this.result[i][1] + "원\n";
		}

		result += "\n";

		for (Franchise fc : this.franchise) {
			result += fc.toString() + "\n";
		}

		return result;
	}

	void salesList() {
		System.out.println(toString());
	}

/////////////////////////////////////////////////////////////////////////////
// inner
	class Franchise {

		String name;
		int[][] result;

		public Franchise(String name) {
			super();
			this.name = name;
			this.result = new int[menue.length][2];
		}

		void sales(String menue) {

//			System.out.println(Company.this.name + " " + this.name + "점 에서 " + menue + "가 팔렷습니다.");

			for (int i = 0; i < Company.this.menue.length; i++) {
				if (Company.this.menue[i].equals(menue)) {
					this.result[i][0]++; // 갯수
					this.result[i][1] += Company.this.price[i]; // 가격
					Company.this.result[i][0]++;
					Company.this.result[i][1] += Company.this.price[i];
					this.result[this.result.length - 1][0]++;
					this.result[this.result.length - 1][1] += Company.this.price[i];
					Company.this.result[this.result.length - 1][0]++;
					Company.this.result[this.result.length - 1][1] += Company.this.price[i];
					break;
				}
			}

		}

		@Override
		public String toString() {

			String result = Company.this.name + " " + this.name + "점 판매현황  >>>>>>>>\n";

			for (int i = 0; i < this.result.length; i++) {
				result += menue[i] + " : " + this.result[i][0] + "ea, " + this.result[i][1] + "원\n";
			}

			return result;
		}

		void salesList() {
			System.out.println(toString());
		}

	}
}

public class Main {

	static void order(Company company) {

		String[] franchise;
		String[] menue = { "아메리카노", "아시아노", "아프리카노" };

		if (company.name.equals("커피콩")) {
			franchise = new String[] { "bran_yeoksam", "bean_guro", "bean_cheongnyangni" };
			testOrder(company, franchise, menue);
		} else {
			franchise = new String[] { "star_yeoksam", "star_jongro", "star_sinchon" };
			testOrder(company, franchise, menue);
		}

	}

	static void testOrder(Company company, String[] franchise, String[] menue) {

		int cnt = new Random().nextInt(1000);

		for (int i = 0; i < cnt; i++) {

			int a = new Random().nextInt(3);

			int b = new Random().nextInt(3);

			company.franchise[a].sales(menue[b]);

		}

	}

	public static void main(String[] args) {

		Company bean = new Company("커피콩"); // 커피콩 설립 (outer)
		Company.Franchise bran_yeoksam = bean.addFranchise("역삼"); // 프랜차이즈 생성 (inner)
		Company.Franchise bean_guro = bean.addFranchise("구로");
		Company.Franchise bean_cheongnyangni = bean.addFranchise("청량리");

		order(bean); // random order
		bean.salesList();
		System.out.println("------------------------------------------------------");

		Company star = new Company("별다방"); // 별다방 설립 (outer)
		Company.Franchise star_yeoksam = star.addFranchise("역삼"); // 프랜차이즈 생성 (inner)
		Company.Franchise star_jongro = star.addFranchise("종로");
		Company.Franchise star_sinchon = star.addFranchise("신촌");

		order(star);
		star.salesList();

//		System.out.println(Arrays.deepToString(bin.result));

	}

}

//커피콩 판매현황  >>>>>>>>
//아메리카노 : 317ea, 665700원
//아시아노 : 303ea, 757500원
//아프리카노 : 285ea, 627000원
//total : 905ea, 2050200원
//
//커피콩 역삼점 판매현황  >>>>>>>>
//아메리카노 : 97ea, 203700원
//아시아노 : 110ea, 275000원
//아프리카노 : 90ea, 198000원
//total : 297ea, 676700원
//
//커피콩 구로점 판매현황  >>>>>>>>
//아메리카노 : 107ea, 224700원
//아시아노 : 83ea, 207500원
//아프리카노 : 89ea, 195800원
//total : 279ea, 628000원
//
//커피콩 청량리점 판매현황  >>>>>>>>
//아메리카노 : 113ea, 237300원
//아시아노 : 110ea, 275000원
//아프리카노 : 106ea, 233200원
//total : 329ea, 745500원
//
//
//------------------------------------------------------
//별다방 판매현황  >>>>>>>>
//아메리카노 : 292ea, 584000원
//아시아노 : 257ea, 591100원
//아프리카노 : 253ea, 683100원
//total : 802ea, 1858200원
//
//별다방 역삼점 판매현황  >>>>>>>>
//아메리카노 : 98ea, 196000원
//아시아노 : 79ea, 181700원
//아프리카노 : 89ea, 240300원
//total : 266ea, 618000원
//
//별다방 종로점 판매현황  >>>>>>>>
//아메리카노 : 107ea, 214000원
//아시아노 : 90ea, 207000원
//아프리카노 : 83ea, 224100원
//total : 280ea, 645100원
//
//별다방 신촌점 판매현황  >>>>>>>>
//아메리카노 : 87ea, 174000원
//아시아노 : 88ea, 202400원
//아프리카노 : 81ea, 218700원
//total : 256ea, 595100원
