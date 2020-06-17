package exam;

import java.util.Arrays;
import java.util.StringTokenizer;

class OrderChange {

	String order;
	String[] instance = { "AMERICANO", "ASIANO", "AFRICANO" };
	String[] list;
	int[] jan;

	public OrderChange(String order) {
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

	}

}

enum Coffee {

	AMERICANO(2000), ASIANO(2300), AFRICANO(2700);

	int price, jan, totJan;
	String name;

	private Coffee(int price) {
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

public class Main {

	static OrderChange[] addOrder(String[] list) {

		OrderChange[] order = new OrderChange[0];

		for (int i = 0; i < list.length; i++) {

			OrderChange[] temp = new OrderChange[order.length + 1];

			for (int j = 0; j < order.length; j++) {
				temp[j] = order[j];
			}

			temp[order.length] = new OrderChange(list[i]);

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

		OrderChange[] orderArray = addOrder(orderList);

		for (int i = 0; i < orderArray.length; i++) {

			for (int j = 0; j < orderArray[i].instance.length; j++) {
				Coffee.valueOf(orderArray[i].instance[j]).name = orderArray[i].instance[j];
				Coffee.valueOf(orderArray[i].instance[j]).jan = orderArray[i].jan[j];
				Coffee.valueOf(orderArray[i].instance[j]).totJan += orderArray[i].jan[j];
			}

			int orderSum = 0;

			String print = "주문서" + (i + 1) + " :\t";

			for (int j = 0; j < orderArray[i].instance.length; j++) {

				print += Coffee.valueOf(orderArray[i].instance[j]).toString();
				orderSum += Coffee.valueOf(orderArray[i].instance[j]).price
						* Coffee.valueOf(orderArray[i].instance[j]).jan;

			}

			print += "total : " + orderSum;

			System.out.println(print);

		}

		System.out.println("---------------------------------------------------------------------------");

		System.out.print("합계 : ");
		int total = 0;
		for (Coffee co : Coffee.values()) {
			co.show();
			total += co.totJan * co.price;
		}

		System.out.println("\ttotal : " + total);

//		주문서1 :	AMERICANO_2(4000), ASIANO_3(6900), AFRICANO_1(2700), total : 13600
//		주문서2 :	AMERICANO_0(0),    ASIANO_1(2300), AFRICANO_2(5400), total : 7700
//		주문서3 :	AMERICANO_1(2000), ASIANO_2(4600), AFRICANO_2(5400), total : 12000
//		---------------------------------------------------------------------------
//		합계 : AMERICANO_3(6000)
//		     ASIANO_6(13800)
//		     AFRICANO_5(13500)
//			total : 33300

	}

}
