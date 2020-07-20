package dynamic_P;

class ConstShape { // 부모는 자식을 포함하지 않습니다.
	String name;

	double border, area;

	void calc() {
		System.out.println("부모의 계산을 받아랐");
	}

	void ppp() {
//		this.calc(); // 이렇게 해도 안됨
		calc(); // 오버라이딩 된 자식의 메소드를 호출합니다.
		System.out.println(name + ": " + area + ", " + border);
	}

}

class ConstRec extends ConstShape { // 자식은 부모를 포함합니다.

//	hide : 숨는 장소
//	hidding => String name;

	int w, h;

	ConstRec(int w, int h) {
		name = "직사각형";
		this.w = w;
		this.h = h;
	}

	void calc() {
		System.out.println("자식의 오버라이딩 계산이다.");
		area = w * h;
		border = (w + h) * 2;
	}

}

public class ConstShapeMain {

	public static void main(String[] args) {

//		ConstRec[] rec = { new ConstRec(5, 6), new ConstRec(5), new ConstRec(5, 6) };

//		for (ConstRec ConstiRec : rec) {
//			ConstRec.ppp(); // 상속을 받아 부모의 메소드 를 사용합니다.
//		}

		ConstRec rec = new ConstRec(5, 6);
		rec.ppp();

//		ConstRec[] clone = rec.clone();

		System.out.println(rec instanceof ConstShape); // true
		System.out.println(rec instanceof ConstRec); // true
		System.out.println(rec instanceof Object); // true

		
		
	}

}
