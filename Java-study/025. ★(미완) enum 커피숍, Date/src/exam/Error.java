package exam;

import java.util.Arrays;
import java.util.StringTokenizer;

class OrderChange2 {

	String order;
	String[] instance = { "AMERICANO", "ASIANO", "AFRICANO" };
	String[] list;
	int[] jan;

	public OrderChange2(String order) {
		this.order = order;
		this.list = new String[0];
		this.jan = new int[instance.length];
		change();
	}

	void change() {

		StringTokenizer stn = new StringTokenizer(order, "[_,]");

		while (stn.hasMoreTokens()) {

			String str = stn.nextToken();

			try {

				int i = Integer.parseInt(str); // 숫자로 바뀌면 Error가 아님

				for (int j = 0; j < instance.length; j++) {
					if (instance[j].equals(list[list.length - 1])) {
						jan[j] = i;
						break;
					}
				}

			} catch (Exception e) {

				// 문자열이면 Error 이므로, catch에서 처리

				String[] temp = new String[list.length + 1]; // 인스턴스와 주문을 비교할 배열

				for (int i = 0; i < list.length; i++) {
					temp[i] = list[i];
				}

				temp[list.length] = str;

				list = temp;

			}

		}

		System.out.println(Arrays.toString(this.instance));
		System.out.println(Arrays.toString(this.list));
		System.out.println(Arrays.toString(this.jan));
		pushEnum();

	}

	void pushEnum() {
		// enum 클래스는 인스턴스 생성이 안되므로, 제일 마지막에 생성된 OrderChange2의 인스턴스 맴버변수로
		// enum 클래스가 초기화 되기때문에 여기서 enum 의 맴버 변경을 진행하면 이 와 같은 error 이 진행.
		// 결론 : 여기선 enum 의 맴버 변수를 초기화 할 수 없습니다.
		// 결론 : 싱글톤과 enum 은 비슷 유의 합니다.

		for (int i = 0; i < instance.length; i++) {
			System.out.println(i);
			Coffee2.valueOf(instance[i]).name = this.instance[i];
			System.out.println(Coffee2.valueOf(instance[i]).name);
			Coffee2.valueOf(instance[i]).jan = this.jan[i];
			System.out.println(Coffee2.valueOf(instance[i]).jan);
			Coffee2.valueOf(instance[i]).totJan += this.jan[i];
			System.out.println(Coffee2.valueOf(instance[i]).totJan);
		}

	}

}

enum Coffee2 {

	AMERICANO(2000), ASIANO(2300), AFRICANO(2700);

	int price, jan, totJan;
	String name;

	private Coffee2(int price) {
		this.price = price;
	}

	public String toString() {

		String print = "";

		int temp = this.price;

		if (this.jan == 0)
			temp = 0;

		print += name + "_" + jan + "(" + (temp * jan) + "), ";

		if (temp == 0)
			print += "   ";

		return print;

	}

	void show() {

		String print = "";

		if (!name.equals("AMERICANO"))
			print += "     ";

		print += name + "_" + totJan + "(" + totJan * price + ")";

		System.out.println(print);
	}

}

public class Error {

	static OrderChange2[] addOrder(String[] list) {

		OrderChange2[] order = new OrderChange2[0];

		for (int i = 0; i < list.length; i++) {

			OrderChange2[] temp = new OrderChange2[order.length + 1];

			for (int j = 0; j < order.length; j++) {
				temp[j] = order[j];
			}

			temp[order.length] = new OrderChange2(list[i]);

			order = temp;

		}

		return order;

	}

	public static void main(String[] args) {

//               커피를 주문하세요
//        아메리카노:2000
//        아시아노:2300
//        아프리카노:2700
//        
//        주문서1 : AMERICANO_2,ASIANO_3,AFRICANO_1
//        주문서2 : AFRICANO_2,ASIANO_1
//        주문서3 : AFRICANO_2,AMERICANO_1,ASIANO_2
//        
//        출력
//        주문서1 : AMERICANO_2(4000), ASIANO_3(6900), AFRICANO_1(2700) : ?
//        주문서2 : AMERICANO_0(0),    ASIANO_1(2300), AFRICANO_2(5400) : ?
//        주문서3 : AMERICANO_1(2000), ASIANO_2(4600), AFRICANO_2(5400) : ?
//        -------------------------------------------------------------
//        합계 : AMERICANO_3(6000)
//             ASIANO_6(13800)
//             AFRICANO_5(13500)
//             ----------------------
//                  ?

		String[] orderList = { "AMERICANO_2,ASIANO_3,AFRICANO_1", "AFRICANO_2,ASIANO_1",
				"AFRICANO_2,AMERICANO_1,ASIANO_2" };

		OrderChange2[] orderArray = addOrder(orderList);

		for (int i = 0; i < orderArray.length; i++) {

			int orderSum = 0;

			String print = "주문서" + (i + 1) + " :\t";

			for (int j = 0; j < orderArray[i].instance.length; j++) {

				print += Coffee2.valueOf(orderArray[i].instance[j]).toString();
				orderSum += Coffee2.valueOf(orderArray[i].instance[j]).price
						* Coffee2.valueOf(orderArray[i].instance[j]).jan;

			}

			print += "total : " + orderSum;

			System.out.println(print);

		}

		System.out.println("---------------------------------------------------------------------------");

		System.out.print("합계 : ");
		int total = 0;
		for (Coffee2 co : Coffee2.values()) {
			co.show();
			total += co.totJan * co.price;
		}

		System.out.println("\ttotal : " + total);

//		주문서1 :	AMERICANO_1(2000), ASIANO_2(4600), AFRICANO_2(5400), total : 12000
//		주문서2 :	AMERICANO_1(2000), ASIANO_2(4600), AFRICANO_2(5400), total : 12000
//		주문서3 :	AMERICANO_1(2000), ASIANO_2(4600), AFRICANO_2(5400), total : 12000
//		---------------------------------------------------------------------------
//		합계 : AMERICANO_3(6000)
//		     ASIANO_6(13800)
//		     AFRICANO_5(13500)
//			total : 33300

		for (int i = 0; i < orderArray.length; i++) {
			System.out.println(Arrays.toString(orderArray[i].list));
			System.out.println(Arrays.toString(orderArray[i].jan));

		}

	}

}

//		분석하기

//void pushEnum() { 
// enum 클래스는 인스턴스 생성이 안되므로, 제일 마지막에 생성된 OrderChange2의 인스턴스 맴버변수로
// enum 클래스가 초기화 되기때문에 여기서 enum 의 맴버 변경을 진행하면 이 와 같은 error 이 진행.
// 결론 : 여기선 enum 의 맴버 변수를 초기화 할 수 없습니다.
// 결론 : 싱글톤과 enum 은 비슷 유의 합니다.