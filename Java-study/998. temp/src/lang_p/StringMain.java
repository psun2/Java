package lang_p;

public class StringMain {

	public static void main(String[] args) {

		String str1 = "홍성혁 나를 봐라";
		String str2 = "홍성혁 나를 봐라";
		String str3 = new String("홍성혁 나를 봐라"); // 인스턴스
		char[] arr = { '홍', '성', '혁', ' ', '나', '를', ' ', '봐', '라' };
		String str4 = new String(arr); // 인스턴스
		String str5 = str1;

		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		System.out.println(str5);
		System.out.println("홍성혁 나를 봐라");

		System.out.println();
		System.out.println("toString() ------------");
		System.out.println(str1.toString());
		System.out.println(str2.toString());
		System.out.println(str3.toString());
		System.out.println(str4.toString());
		System.out.println(str5.toString());
		System.out.println("홍성혁 나를 봐라".toString());
		// ""(쌍 따옴표) => 인스턴스 주소 입니다. why? .toString() 사용 가능

		System.out.println();
		System.out.println(".hashCode() ------------");
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str3.hashCode());
		System.out.println(str4.hashCode());
		System.out.println(str5.hashCode());
		System.out.println("홍성혁 나를 봐라".hashCode());
		// value 가 같으면 주소가 같다 => String class 에서 .hashCode() 을 오버 라이딩 했기 때문

		System.out.println();
		System.out.println("== (주소 비교)");
		System.out.println("str1 == str2 : " + (str1 == str2));
		System.out.println("str1 == str3 : " + (str1 == str3));
		System.out.println("str1 == str4 : " + (str1 == str4));
		System.out.println("str1 == str5 : " + (str1 == str5));

		System.out.println();
		System.out.println(".equals() ------------");
		System.out.println("str1.equals(str2) : " + str1.equals(str2));
		System.out.println("str1.equals(str3) : " + str1.equals(str3));
		System.out.println("str1.equals(str4) : " + str1.equals(str4));
		System.out.println("str1.equals(str5) : " + str1.equals(str5));
		System.out.println("str1.equals(\"홍성혁 나를 봐라\") : " + str1.equals("홍성혁 나를 봐라"));

	}

}
