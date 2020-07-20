package coll_p;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackMain {

	public static void main(String[] args) {

		Stack st = new Stack();

		Queue qq = new LinkedList();

//		st.add(1);
//		st.add(2);
//		st.add(3);
//		
//		qq.add(1);
//		qq.add(2);
//		qq.add(3);

		// 위 와 같은 결과 발생

		st.push(1);
		st.push(2);
		st.push(3);

		qq.offer(1);
		qq.offer(2);
		qq.offer(3);

		System.out.println(st.get(0));
//		System.out.println(qq.get(0));

		System.out.println(st);
		System.out.println(qq);

//		System.out.println(st.pop());
//		System.out.println(qq.poll());

		System.out.println(st);

		System.out.println(qq);

		System.out.println();
		System.out.println("Stack - 후입선출");
		while (!st.empty()) {
			System.out.println(st.pop());
		}

		System.out.println();
		System.out.println("Queue - 선입선출");

		while (!qq.isEmpty()) {
			System.out.println(qq.poll());
		}

		System.out.println("stack 과 Queue 는 꺼내는 순서가 반대입니다.");

	}

}
