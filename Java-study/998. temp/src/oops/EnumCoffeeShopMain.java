package oops;

//
/*        커피를 주문하세요
    아메리카노:2000
    아시아노:2300
    아프리카노:2700
 
    주문서1 : AMERICANO_2,ASIANO_3,AFRICANO_1
    주문서2 : AFRICANO_2,ASIANO_1
    주문서3 : AFRICANO_2,AMERICANO_1,ASIANO_2
 
    출력
    주문서1 : AMERICANO_2(4000), ASIANO_3(6900), AFRICANO_1(2700) : ?
    주문서2 : AMERICANO_0(0),    ASIANO_1(2300), AFRICANO_2(5400) : ?
    주문서3 : AMERICANO_1(2000), ASIANO_2(4600), AFRICANO_2(5400) : ?
    -------------------------------------------------------------
    합계 : AMERICANO_3(6000)
         ASIANO_6(13800)
         AFRICANO_5(13500)
         ----------------------
              ?
 */

enum CoffeeShop {
	AMERICANO(2000), ASIANO(2300), AFRICANO(2700);

	int price;
	int tot;

	private CoffeeShop(int price) {
		this.price = price;
	}

	int order(String str) { // 인스턴스당 출력 및 계산

		int cnt = Integer.parseInt(str);

		tot += cnt; // 주문시마다 잔을 저장할 필요는 없고 전체 잔만 가지고 있으면 됩니다.
		int res = cnt * price; // 잔 * 가격으로 return 합니다
		System.out.print(this + "_" + cnt + "(" + res + ") , ");

		return res;

	}

	int ppp() {// 전체 금액 계산 및 전체 출력

		int res = tot * price; // order에서 구한 전체 ( 잔 * price )
		System.out.println(this + "_" + tot + "(" + res + ")");
		return res;

	}
}

public class EnumCoffeeShopMain {

	static void order(String title, String str) {

		System.out.print(title + " : ");
		int res = 0;
		for (String ss : str.split(",")) {
			String[] arr = ss.split("_");
			res += CoffeeShop.valueOf(arr[0]).order(arr[1]);
		}
		System.out.println(":" + res);

	}

	public static void main(String[] args) {
		order("주문서1", "AMERICANO_2,ASIANO_3,AFRICANO_1");
		order("주문서2", "AFRICANO_2,ASIANO_1");
		order("주문서3", "AFRICANO_2,AMERICANO_1,ASIANO_2");

		System.out.println("total----------------------");
		int res = 0;
		for (CoffeeShop co : CoffeeShop.values()) {
			res += co.ppp();
		}
		System.out.println("----------------------\n" + res);

//		주문서1 : AMERICANO_2(4000) , ASIANO_3(6900) , AFRICANO_1(2700) , :13600
//		주문서2 : AFRICANO_2(5400) , ASIANO_1(2300) , :7700
//		주문서3 : AFRICANO_2(5400) , AMERICANO_1(2000) , ASIANO_2(4600) , :12000
//		total----------------------
//		AMERICANO_3(6000)
//		ASIANO_6(13800)
//		AFRICANO_5(13500)
//		----------------------
//		33300

	}

}
