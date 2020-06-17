package theory;

abstract class ClaShape { // 결과 값을 가지고 있는 기본 틀이 되는 메인 클래스

	double area, border;

	String name;

	public ClaShape(String name) {
		super();
		this.name = name;
	}

	abstract void calc(); // 계산방식은 각 도형마다 다르기 때문에 추상화 메소드 사용

	@Override
	public String toString() { // toString 함수를 오버라딩
		calc();
		return "ClaShape [name=" + name + ", area=" + area + ", border=" + border + "]";
	}

}

class ClaRec extends ClaShape { // 결과값을 보관하는 메인 클래스를 상속받은 사각형 클래스

	int w, h;

	public ClaRec(Integer w, Integer h) {
		super("직사각형");
		this.w = w;
		this.h = h;
	}

	@Override
	void calc() {
		area = w * h;
		border = (w + h) * 2;
	}

}

class ClaCir extends ClaShape { // 결과값을 보관하는 메인 클래스를 상속받은 원 클래스

	int r;

	public ClaCir(Integer r) {
		super("원");
		this.r = r;
	}

	@Override
	void calc() {
		area = Math.PI * Math.pow(r, 2);
		border = Math.PI * r * 2;
	}

}

class BindCla {

	String claName; // Class.forName 으로 생성할 인스턴스 클래스의 이름 을 받습니다.
	// ClaCir as = new ClaCir(7);
	// System.out.println(as.getClass().getName()); // => theory.ClaCir

	Class[] type; // 인스턴스생성시 생정자로 초기화 되는 맴버변수의 prameter의 형식을 저장합니다.

	Integer[] init; // 생성자에 들어갈 값을 저장합니다.

	public BindCla(String claName, Class[] type, Integer... init) {
		super();
		this.claName = claName;
		this.type = type;
		this.init = init;
	}

}

public class ClassShapeMain {

	public static void main(String[] args) throws Exception {

//		ClaShape[] arr = { (ClaShape) Class.forName("theory.ClaRec").getConstructor(Integer.class, Integer.class)
//				.newInstance(5, 6),
//				(ClaShape) Class.forName("theory.ClaCir").getConstructor(Integer.class).newInstance(5) };
//
//		for (ClaShape sh : arr) {
//			System.out.println(sh);
//		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		BindCla[] bbArr = { new BindCla("theory.ClaRec", new Class[] { Integer.class, Integer.class }, 7, 8),
				new BindCla("theory.ClaCir", new Class[] { Integer.class }, 9) };

		// 굳이 바인딩 클래스를 따로 만들어서 해야만 했을까 ? => 공부를 위햐여 ?
		// 밑에 예제를 진행하는 도중에... 상속은 받았지만, 서로 매개변수가 다르고, 매개변수의 갯수도 다릅니다.
		// 그러니 인스턴스를 한번에 생성하기 위해선 바인드 클래스를 받듯이 만들어 바인드 클래스 배열에서 바인크 클래스의 인스턴스를 생성하여 그 값을
		// 예제와 같이 for문을 돌리면서 바인드 클래스의 type
		// 을 하나하나 빼서 집어 넣으면 되겟습니다.

//		ClaShape[] shape = new ClaShape[2];
//
//		for (int i = 0; i < shape.length; i++) {
//			shape[i] = (ClaShape) Class.forName("theory.ClaRec").getConstructor(Integer.class, Integer.class)
//					.newInstance(7, 8); // 아 한번에 하려고 햇는데, 다르구나 .... 매개변수와, 매개변수의 수가... 이렇게 되면 계속 똑같은 인스턴스만 생성됨.
//		}

		for (BindCla bc : bbArr) {
			System.out.println((ClaShape) Class.forName(bc.claName).getConstructor(bc.type).newInstance(bc.init));
		}

		/// 학생 ---> 보기, 쓰기, 삭제

	}

}