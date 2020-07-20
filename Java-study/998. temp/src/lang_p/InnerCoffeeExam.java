package lang_p;

import java.util.Arrays;

class Company {

	String name;

	Franchise[] franchise;

	String[] menue;

	int[] price, cnt;

//	[ // result
//	 메뉴1 : [수량, 금액],
//	 메뉴2 : [수량, 금액],
//	 메뉴3 : [수량, 금액],
// 	메뉴의 추가와 제거에 따라 result[이쪽 변경][]
// 	사용자가 필요 없을시 함수를 이용하여 result 배열에서 삭제된 항목 지우기 또는 값이 0 이라면 지우기(메뉴는 가지고 있어야 되나 ?)
//	 ]

	public Company(String name) {
		super();
		this.name = name;
		menue = new String[0];
		price = new int[0];
		cnt = new int[0];
		franchise = new Franchise[0];
	}

	void addMenue(String menue, int price) { // 메뉴는 본사가 결정 합니다.

//		menueChecking(menue); // 체킹이 되면 딜리트 배열에서는 그 항목이 사라집니다.

		// 메뉴 추가
		String[] menueTemp = new String[this.menue.length + 1];
		int[] priceTemp = new int[this.price.length + 1];

		for (int i = 0; i < this.menue.length; i++) {
			menueTemp[i] = this.menue[i];
			priceTemp[i] = this.price[i];
		}

		menueTemp[this.menue.length] = menue;
		priceTemp[this.price.length] = price;

		this.menue = menueTemp;
		this.price = priceTemp;

//		addChangeResultArray(); // result 배열에 받아온 메뉴의 항목 추가

		addCntArray();

	}

	void deleteMenue(String menue) { // 메뉴 삭제

		String[] menueTemp = new String[this.menue.length - 1];
		int[] priceTemp = new int[this.price.length - 1];

		int index = 0;

		for (int i = 0; i < this.menue.length; i++) {
			for (int j = 0; j < priceTemp.length; j++) {
				if (!this.menue[i].equals(menue)) {
					menueTemp[j] = this.menue[i];
					priceTemp[j] = this.price[i];
				} else {
					index = i;
					break;
				}
			}
		}

		this.menue = menueTemp;
		this.price = priceTemp;

		deleteCntArray(index); // 메뉴가 삭제되면 삭제되는 메뉴의 index 번호를 넘겨줌

//		deleteChangeResultArray(); // result 배열에 받아온 메뉴의 항목 삭제

	}

//	private void menueChecking(String menue) {
//
//		// 메뉴가 딜리트 배열에 존재 하면 딜리트 배열에서 그 메뉴를 빼줍니다. (딜리트 메뉴, 프라이스);
//
//		for (int i = 0; i < deleteMenue.length; i++) {
//			if (deleteMenue[i].equals(menue)) {
//	
//			}
//
//		}
//
//	}

	private void addCntArray() { // 결과 값을 쌓아 나갈 cnt 배열의 길이를 메뉴가 추가 될때마다 1씩 늘려줌

		int[] temp = new int[this.cnt.length + 1];

		for (int i = 0; i < cnt.length; i++) {
			temp[i] = this.cnt[i];
		}

		cnt = temp;

//		이게 맞나 확인 할 것  => 틀림 확인

cxvgfdg 이럼 안대
		for (int i = 0; i < franchise.length; i++) {
			int[] cntTemp = this.cnt.clone();
			System.out.println(cntTemp);
>>>>>>>>>>>>franchise[i].cnt = cntTemp;
		}
	}

	private void deleteCntArray(int index) {

		int[] temp = new int[this.cnt.length - 1];

		for (int i = 0; i < cnt.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (index != i) {
					temp[j] = cnt[i];
				} else {
					break;
				}
			}
		}

		cnt = temp;

	}

	Franchise addFranchise(String name) {

		Franchise franchise = new Franchise(name);

		Franchise[] temp = new Franchise[this.franchise.length + 1];

		for (int i = 0; i < this.franchise.length; i++) {
			temp[i] = this.franchise[i];
		}

		temp[this.franchise.length] = franchise;

		this.franchise = temp;

		return franchise;
	}
//////////////////////////////////////////////////////////////////////////////////
// inner

	class Franchise {

		String name;

		int[] cnt;

		public Franchise(String name) {
			this.name = name;
//			cnt = new int[Company.];
		}

	}

}

public class InnerCoffeeExam {

	public static void main(String[] args) {

//		✔ 요구사항
//		
//		본사 - 메뉴 와 가격 (+ 다른 회사의 메뉴와 가격은 가지고 있을 필요 ❌)
//		지점 - 판매(메뉴, 몇잔 주세요) 

//		+ 본사를 설립할때는 이름만...
//		+ 본사에서 addMenue
//		+ 본사에서 deleteMenue
//		+ 삭제된 메뉴를 다시 추가 할 수 도 있다..... 

		Company star = new Company("별다방");
		Company bean = new Company("커피콩");

		Company.Franchise chong = star.addFranchise("청담");

		star.addMenue("아메리카노", 2100);
		star.addMenue("아시아노", 2500);
		star.addMenue("아프리카노", 2200);

		System.out.println(Arrays.toString(star.menue));
		System.out.println(Arrays.toString(star.price));
		System.out.println(star.cnt.length);

		star.deleteMenue("아메리카노");
		System.out.println(Arrays.toString(star.menue));
		System.out.println(Arrays.toString(star.price));
		System.out.println(star.cnt.length);

	}
}
