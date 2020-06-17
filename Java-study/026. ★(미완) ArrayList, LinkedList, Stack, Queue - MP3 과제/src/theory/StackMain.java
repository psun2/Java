package theory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackMain {

	public static void main(String[] args) {

		// ✔ Stack => 후입선출
		Stack st = new Stack();

		// ✔ Queue => 선입선출
		Queue qq = new LinkedList();

		st.add(1);
		st.add(2);
		st.add(3);
		System.out.println(st); // [1, 2, 3]

		qq.add(1);
		qq.add(2);
		qq.add(3);
		System.out.println(qq); // [1, 2, 3]

		st.push(4);
		st.push(5);
		st.push(6);
		System.out.println(st); // [1, 2, 3, 4, 5, 6]

		qq.offer(4);
		qq.offer(5);
		qq.offer(6);
		System.out.println(qq); // [1, 2, 3, 4, 5, 6]

		System.out.println();

		System.out.println("st.get(0) : " + st.get(0)); // st.get(0) : 1
//		System.out.println("qq.get(0) : " + qq.get(0));

		// ✔ Queue 는 get으로 값을 얻을 수 없습니다.

		System.out.println();
		System.out.println("pop 과 poll");
		System.out.println("empty 와 isEmpty");

		while (!st.empty()) {
			System.out.println("st.pop() : " + st.pop());
		}
//		st.pop() : 6
//		st.pop() : 5
//		st.pop() : 4
//		st.pop() : 3
//		st.pop() : 2
//		st.pop() : 1
//		Stack 은 나중에 추가 된 것 부터 꺼내옵니다.

		while (!qq.isEmpty()) {
			System.out.println("qq.poll() : " + qq.poll());
		}
//		qq.poll() : 1
//		qq.poll() : 2
//		qq.poll() : 3
//		qq.poll() : 4
//		qq.poll() : 5
//		qq.poll() : 6
//		Queue 는 추가된 순서대로 꺼내옵니다.

		System.out.println("stack 과 Queue 는 꺼내는 순서가 반대입니다.");

	}

}
