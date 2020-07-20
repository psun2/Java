package oops;

import java.util.Arrays;

enum Cafe {

	AMERICANO("아메리카노", 2000, "0"), ASIANO("아시아노", 2300, "0"), AFRICANO("아프리카노", 2700, "0");

	String name, cnt;
	int price;

	Cafe(String name, int price, String cnt) {
		this.name = name;
		this.price = price;
		this.cnt = cnt;
	}

	int calc() {
		int cnt;

		cnt = Integer.parseInt(this.cnt);

		return cnt;
	}

	void show() {

		int cnt = calc();

		String result = "";

		result += this + "_" + cnt + "(" + (this.price * cnt) + ")";

		System.out.println(result);

	}

}

public class EnumExamMain {

	public static void main(String[] args) {

		Cafe coffee;

		String order1 = "2,3,1";
		String order2 = "2,3,1";

		Cafe.valueOf("AMERICANO").cnt = "1";

		Cafe.valueOf("AMERICANO").show();
		
//		"AMERICANO_2,ASIANO_3,AFRICANO_1", 
//        "AFRICANO_2,ASIANO_1", 
//        "AFRICANO_2,AMERICANO_1,ASIANO_2"   

	}

}
