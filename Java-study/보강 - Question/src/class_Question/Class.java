package class_Question;

// 출력 순서를 보고 클래스의 작동 원리 이해

class 부모 {

	private String name;

	public 부모(String name) { // 1. 인스턴스 생성시 제일 먼저 호출 ✔ if static 이 있다면 static 먼저 호출
		this.name = name;
		System.out.println("부모1  생성자");
		init(); // 2. 생성자를 통해 2번째로 호출 됩니다.
	}

	void init() { // 3. 생성자의 호출에 따라 호출되어 내용 반환
		System.out.println("부모 init()");
	}

	public String getName() { // 4. 생성이 끝난뒤, 호출 함으로서 제일 마지막에 호출됨.
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class 자식1 extends 부모 {

	public 자식1(String name) { // 1. 자식 인스턴스 생성시 제일 먼저 호출 됩니다. ✔ if static 이 있다면 static 먼저 호출
		super(name); // 2. 실질적으로 호출되는건 자식 생성자가 제일 먼저 되지만 바로 다음 줄에 나오는 부모생성자를 호출하기 때문에 부모의 정보를 초기화합니다.
						// 즉 생성자로 받아온 name은 super 을 통해 부모 생성자를 호출해서 부모 생성자의 인자로 넘기고, 부모 class 의 맴버 변수를
						// 초기화 하며, 자식 클래스에서는 부모에 생성된 맴버 변수를 끌어 옵니다.
		System.out.println("자식 1  생성자"); // 4. init 끝난뒤 마지막으로 실행 됩니다.
	}

	void init() { // 3. 부모 생성자에 init 함수 호출이 명령이 되어있기 때문에 자식 생성자의 동장이 끝나기 전에 먼저 호출합니다.
		System.out.println("자식1 init()");
	}
}

class 자식2 extends 부모 {

	public 자식2(String name) {
		super(name);
		System.out.println("자식 2  생성자");
	}

	void init() {
		System.out.println("자식2 init()");
	}

}

public class Class {
	public static void main(String[] args) {

		부모 부모 = new 부모("부모");
		System.out.println(부모.getName());
		System.out.println();

		자식1 자식1 = new 자식1("자식1");
		System.out.println(자식1.getName());
		System.out.println();

		자식2 자식2 = new 자식2("자식2");
		System.out.println(자식2.getName());
		System.out.println();

	}
}
