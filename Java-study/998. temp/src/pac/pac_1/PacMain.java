//우선순위
// 
//1. 선언 및 생성시 직접명시
//    pac.pac_1.CCC p1c = new pac.pac_1.CCC();
//
//2. import 시 클래스 직접 호출
//     import pac.pac_2.CCC;
//
//3. 같은 패키지 내 클래스 호출
//
//4. import 시 패키지내 클래스 자동 호출
//      import pac.pac_3.*; 
//
//public -> protected -> default -> private

package pac.pac_1;

import pac.pac_2.*; // 전부 import // 다른 패키지와 클래스의 이름이 같을때 우선권은 나의 패키지 않의 클래스가 됩니다.
import pac.pac_2.GGG; // 명시적 방법으로, 겹치는 클래스 를 우선권과 상관없이 사용 가능
import pac.pac_2.AAA; // 명시적 방법으로, 겹치는 클래스 를 우선권과 상관없이 사용 가능
//import pac.pac_2.AAA; // AAA만 import
//import pac.pac_2.BBB; // 한 자바 파일에서 public 은 하나만 만들수 있으므로 접근 불가
//import pac.pac_2.AAA.BBB; // Error
import pac.pac_1.sub.*;
import pac.pac_1.sub.DDD;

// bin  => class 가 같은 파일 내에 있어도 분리되어 file 생성
// src

class CCC {
	int a = 5000;
	int b = 6000;

	void meth_1() {
		System.out.println("pac.pac_1.CCC meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_1.CCC meth_2() 실행");
	}
}

public class PacMain {

	public static void main(String[] args) { // public 클래스는 java 파일로 만든 클래스만 가능

		pac.pac_1.AAA p1a = new pac.pac_1.AAA(); // 우선순위가 겹칠시 한번더 명시적으로 함으로서 가져 접근이 가능 합니다.
		AAA p2a = new AAA();
//		BBB p2b = new BBB();
		CCC p1c = new CCC();
		DDD psd = new DDD();
		pac.pac_1.DDD p1d = new pac.pac_1.DDD();
		EEE p1e = new EEE();
		FFF p2f = new FFF();
		GGG p2g = new GGG();

		System.out.println("p1a.a: " + p1a.a);
		System.out.println("p2a.a: " + p2a.a);
//		System.out.println("p2a.b: " + p2a.b); // public 이 아니기 때문제 접근 불가
		p2a.meth_1();
//		p2a.meth_2(); // public 이 아니기 때문제 접근 불가
//		System.out.println("p2b.a: " + p2b.a); // public 클래스는 java 파일로 만든 클래스만 가능

		System.out.println("p1c.a: " + p1c.a);
		System.out.println("p1d.a: " + p1d.a);
		System.out.println("psd.a: " + psd.a);
		System.out.println("p1e.a: " + p1e.a);

		System.out.println("p2f.a: " + p2f.a);
		p2f.meth_1();
		System.out.println("p1g.a: " + p2g.a);
		p2g.meth_1();

		p1e.ppp();

//		AAA a = new AAA();
//		a.meth_1(); // 접근제어자가 public 이 아닐시 호출 불가능

//		System.out.println(p1e.b); // 같은 패키지 내에서도 private 면 접근 불가
//		p1e.meth_2(); // 같은 패키지 내에서도 private 면 접근 불가
	}

}
