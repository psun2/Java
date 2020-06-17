package exam;

public class Main {

	public static void main(String[] args) {

		for (int i = 1; i <= 100; i++) {
			int a = i % 3;
			try { // 3의 배수 일때 catch 문에서 짝 출력
				int b = i / a;
				try { // 10의 자리대 가 3, 6, 9 일때 catch 에서 짝출력
					int c = i % 10;
					int d = c % 3;
					int f = i / d;
					try { // 십의자리가 3의 배수이면 짝
						int g = i / 10;
						int h = g % 3;
						int j = i / h;
						System.out.println(i);
					} catch (Exception e2) { // 십의 자리가 3의 배수가 아닐시 숫자 출력
						try { // 10으로 나눈 나머지가 0인걸 exception 해서 숫자 출력
							int k = i / 10;
							int l = i / k;
							System.out.println("짝 : " + i);
						} catch (Exception e) {
							System.out.println(i);
						}
					}
//					System.out.println(i + "여기 아이는 ?");
				} catch (Exception e) { // 10의 자리대 가 3, 6, 9 일때 catch 에서 짝출력
					try { // 10, 20 이 짝으로 출력 될때 catch 에서 10과 20을 숫자로 출력
						int g = i % 10;
						int h = i / g;
						System.out.println("짝 : " + i);
					} catch (Exception e2) { // 10, 20 이 짝으로 출력 될때 catch 에서 10과 20을 숫자로 출력
						System.out.println(i);
					}
				}
			} catch (Exception e) { // 3의 배수 일때 catch 문에서 짝 출력
				try { // 3의 배수이면서 3, 6, 9 가 아닌 수 숫자 출력
					int b = i % 10;
					int c = b % 3;
					int d = i / c;
					System.out.println(i);
				} catch (Exception e2) {
					try {
						int f = i / 10;
						int g = f % 3;
						int h = i / f;
						try {
							int j = i % 10;
							int k = i / j;
							System.out.println("짝짝 : " + i);
						} catch (Exception e3) {
							System.out.println("짝 : " + i);
						}
					} catch (Exception e3) {
						System.out.println("짝 : " + i);
					}
				}
			}
		}

//		1
//		2
//		짝 : 3
//		4
//		5
//		짝 : 6
//		7
//		8
//		짝 : 9
//		10
//		11
//		12
//		짝 : 13
//		14
//		15
//		짝 : 16
//		17
//		18
//		짝 : 19
//		20
//		21
//		22
//		짝 : 23
//		24
//		25
//		짝 : 26
//		27
//		28
//		짝 : 29
//		짝 : 30
//		짝 : 31
//		짝 : 32
//		짝짝 : 33
//		짝 : 34
//		짝 : 35
//		짝짝 : 36
//		짝 : 37
//		짝 : 38
//		짝짝 : 39
//		40
//		41
//		42
//		짝 : 43
//		44
//		45
//		짝 : 46
//		47
//		48
//		짝 : 49
//		50
//		51
//		52
//		짝 : 53
//		54
//		55
//		짝 : 56
//		57
//		58
//		짝 : 59
//		짝 : 60
//		짝 : 61
//		짝 : 62
//		짝짝 : 63
//		짝 : 64
//		짝 : 65
//		짝짝 : 66
//		짝 : 67
//		짝 : 68
//		짝짝 : 69
//		70
//		71
//		72
//		짝 : 73
//		74
//		75
//		짝 : 76
//		77
//		78
//		짝 : 79
//		80
//		81
//		82
//		짝 : 83
//		84
//		85
//		짝 : 86
//		87
//		88
//		짝 : 89
//		짝 : 90
//		짝 : 91
//		짝 : 92
//		짝짝 : 93
//		짝 : 94
//		짝 : 95
//		짝짝 : 96
//		짝 : 97
//		짝 : 98
//		짝짝 : 99
//		100

	}

}
