package theory;

import java.util.Stack;

class StackBrowser {

	Object now = null; // 현재 페이지
	Stack back = new Stack(); // 뒤로가기
	Stack next = new Stack(); // 앞으로 가기

	void goUrl(String url) {

		System.out.println("goUrl(" + url + ")-----------------------------------");

		if (now != null && next.size() != 0) {
			back.push(now);
			for (int i = 0; i < next.size(); i++) {
				back.push(next.pop());
				now = url;
				next.clear(); // 새 url 이 들어갔을때 next엔 아무것도 없고
				show();
				return;
			}

		}
		// 이전 것들은 모두 back

		if (now != null)
			back.push(now);

		now = url;

		show();

	}

	void goBack() {

		System.out.println("goBack( )-----------------------------------");

		if (back.empty()) {
			System.out.println("뒤로 돌아갈 페이지가 없습니다.");
			return;
		}

		next.push(now);

		now = back.pop();

		show();

	}

	void goNext() {

		System.out.println("goNext( )-----------------------------------");

		if (next.empty()) { // next 가 있나 없나를 boolean 으로 반환 받음
			System.out.println("앞으로갈 페이지가 없습니다.");
			return;
		}

		back.push(now);

		now = next.pop();

		show();
	}

	void show() {
		System.out.println("이전 페이지 : " + back);
		System.out.println("현재 페이지 : " + now);
		System.out.println("다음 페이지 : " + next);
	}

}

public class StackBrowserMain {

	public static void main(String[] args) {

		StackBrowser internet = new StackBrowser();

		internet.goUrl("야후");
		internet.goUrl("다음");
		internet.goUrl("네이버");
		internet.goUrl("구글");
		internet.goUrl("페이스북");
		internet.goUrl("인스타그램");

		internet.goBack();
		internet.goBack();
		internet.goBack();
//		internet.goBack();
//		internet.goBack();
//		internet.goBack();
//		internet.goBack();
//		internet.goBack();
//		internet.goBack();
//		internet.goBack();

		internet.goNext();
		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();
//		internet.goNext();

		internet.goUrl("z코리아");

	}

}
