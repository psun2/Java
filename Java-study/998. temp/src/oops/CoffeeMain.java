package oops;

class Coffee {
	int price, milk, syrup, quantity, amount;
	String kind, print;
	boolean hp;

	void init(String kind) {
		this.price = 3000;
		this.kind = kind;
		this.print = this.kind + "\t" + this.price;
		this.show(print);
	}

	void init(String kind, int milk) {
		this.price = 3300;
		this.kind = kind;
		this.milk = milk;
		this.print = this.kind + "\t" + this.price + "\t" + milk;
		this.show(print);
	}

	void init(String kind, int milk, int syrup) {
		this.price = 3500;
		this.kind = kind;
		this.milk = milk;
		this.syrup = syrup;
		this.print = this.kind + "\t" + this.price + "\t" + milk + "\t" + syrup;
		this.show(print);
	}

	void init(String kind, int milk, boolean bo) {
		this.price = 3500;
		this.kind = kind;
		this.milk = milk;
		this.hp = bo;
		this.print = this.kind + "\t" + this.price + "\t" + milk + "\t" + bo;
		this.show(print);
	}

	void pro(Coffee[] cof) {
		for (int i = 1; i <= cof.length; i++) {
			this.quantity += i;
			this.amount += cof[i].price;
		}
	}

	void show(String print) {
		System.out.println(print);
	}

}

//class MakeCoffee {
//	
//	String[] kind = {"아메리카노", "카페라떼", "카라멜마끼아또", "카페모카"};
//	int[][] pq = new int[kind.length][];
//	
//	
//
//	Coffee make(String kind) {
//		Coffee result = new Coffee();
//		result.init(kind);
//		return result;
//	}
//
//	Coffee make(String kind, int milk) {
//		Coffee result = new Coffee();
//		result.init(kind, milk);
//		return result;
//	}
//
//	Coffee make(String kind, int milk, int syrup) {
//		Coffee result = new Coffee();
//		result.init(kind, milk, syrup);
//		return result;
//	}
//
//	Coffee make(String kind, int mlik, boolean hp) {
//		Coffee result = new Coffee();
//		result.init(kind, mlik, hp);
//		return result;
//	}
//
//	void setPq() {
//		for (int i = 0; i < kind.length; i++) {
//			kind[i] = new int [i][];
//		}
//	}
//	
//}

public class CoffeeMain {

	public static void main(String[] args) {

//	커피를 주문하세요
//	
//	아메리카노(커피종류) - 3000
//	카페라떼(커피종류, 우유량) - 3300
//	카라멜마끼아또(커피종류, 우유량, 카레멜 시럽) - 3500
//	카페모카(커피종류, 우유량, 휘핑있음 없음) - 3500

// 	각 커피별 주문 수량과 총 금액을 출력하세요.

		Coffee coffee = new Coffee();

		Coffee[] cof = { coffee.make("아메리카노"), coffee.make("카페라떼", 300), coffee.make("카라멜마끼아또", 300, 5),
				coffee.make("카페모카", 300, true), coffee.make("카페모카", 300, false), };

		for (int i = 0; i < cof.length; i++) {
			cof[i].pro(cof);
		}

	}

}
