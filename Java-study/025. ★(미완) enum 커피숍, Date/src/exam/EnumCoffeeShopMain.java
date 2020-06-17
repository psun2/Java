package exam;

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
	int totJan;

	private CoffeeShop(int price) {
		this.price = price;
	}

	int resultOrder(String jan) { // 잔과 금액을 더한 값을 내보내주는 메소드

		int janCnt = Integer.parseInt(jan);

		totJan += janCnt; // 인스턴스 마다, 총 몇잔인지 알기 위해 totJan 에 add;

		int result = janCnt * price;

		System.out.print(this + "_" + janCnt + "(" + result + "), ");

		return result;

	}

	int totalOrderSohw() {

		int result = totJan * price;

		System.out.println(this + "_" + totJan + "(" + result + ")");

		return result;
	}

}

public class EnumCoffeeShopMain {

	static void order(String title, String order) {

		System.out.print(title + " : ");

		int result = 0; // resultOrder 의 값을 담을 변수

		for (String str : order.split(",")) {
			String[] element = str.split("_");
			result += CoffeeShop.valueOf(element[0]).resultOrder(element[1]);
			// enum 은 싱글톤, static 과 비슷한 구조
			// 여기서 resultOrder 을 호출 함으로써
			// resultOrder 안에 있는 출력 메세지 출력
		}

		System.out.println(" : " + result); // total 출력

	}

	public static void main(String[] args) {

		order("주문서1", "AMERICANO_2,ASIANO_3,AFRICANO_1");
		order("주문서2", "AFRICANO_2,ASIANO_1");
		order("주문서3", "AFRICANO_2,AMERICANO_1,ASIANO_2");

		System.out.println("-------------------------------------------------");

		int totalPrice = 0;
		for (CoffeeShop coffee : CoffeeShop.values()) { // values() => hasNext() 와 비슷한 역할
			// 인스턴스들을 순회합니다.
			totalPrice += coffee.totalOrderSohw();
			// 함수를 호출하면서 출력메세지를 출력해주고,
			// 각 인스턴스의 총합을 가산해 줍니다.
		}

		System.out.println("-------------------------------------------------");

		System.out.println(totalPrice);

	}

}
