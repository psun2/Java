package control_p;

public class BreakContinue {

	public static void main(String[] args) {

		for (int i = 0;; i++) {

			System.out.println("break 시작 " + i);

			if (i == 5)
				break;
//			break; 를 사용하여, 반복문 중간에 강제적으로 탈출합니다. 

			System.out.println("break 끝 " + i);
		}

		System.out.println("----------------------");

		for (int i = 0; i < 10; i++) {

			System.out.println("continue 시작 " + i);

			if (i == 5)
				continue;
//			continue; 를 사용하여, 해당 구문을 건너 뜁니다. 

			System.out.println("continue 끝 " + i);
		}

		System.out.println("----------------------");

		for (int i = 0; i < 10; i++) {

			System.out.println("if 시작 " + i);

			if (i != 5)
				// continue 와 똑같은 출력결과를 나타냅니다.
				// continue 사용을 지양합니다.
				System.out.println("if 끝 " + i);

		}

		System.out.println("----------------------");

		for (int big = 0; big < 5; big++) {

			System.out.println("big 시작 " + big);

			for (int small = 100; small < 500; small += 100) {
				System.out.println("\t small 시작 " + small);
				if (small == 200)
					break;
			}

			System.out.println("big 끝 " + big);

		}

		System.out.println("----------------------");

//		반복문에 이름을 할당 할 수 있으며,
//		반복문 내 에서 break 를 사용할 일이 있다면,
//		break name; 으로 조건에 충족시 해당 반복문을 탈출합니다.
		AAA: for (int big = 0; big < 5; big++) {

			System.out.println("big2 시작 " + big);

			BBB: for (int small = 100; small < 500; small += 100) {
				System.out.println("\t small2 시작 " + small);
				if (small == 200)
					break AAA;
			}

			System.out.println("big2 끝 " + big);

		}

		System.out.println("----------------------");

		for (int big = 0; big < 5; big++) {

			System.out.println("big2 시작 " + big);

			boolean chk = false;

			for (int small = 100; small < 500; small += 100) {
				System.out.println("\t small2 시작 " + small);
				if (small == 200)
					chk = true;
				break;
			}

			if (chk)
				break;

			System.out.println("big2 끝 " + big);

		}

		System.out.println("----------------------");

	}

}
