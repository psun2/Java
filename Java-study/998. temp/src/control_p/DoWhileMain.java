package control_p;

public class DoWhileMain {

	public static void main(String[] args) {

		int i = 0;

		while (i < 5) {
			System.out.println("while" + i);
			i++;
		}

		System.out.println("------------------");

//		TODO Do while 은 while 과 다르게 조건부터 보지 않고 첫 실행 한 후에 
//		조건을 봅니다.
		
		int j = 10;

		do {
			System.out.println("Do ~ while" + j);
			j++;
		} while (j < 5);

//		TODO 조건에 맞든 안맞는 최초 1회는 실행을 합니다.
		
		System.out.println("------------------");
	}

}
