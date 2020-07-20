package coll_p;

import java.util.Stack;
import java.util.regex.Pattern;

class StMusic { // 노래 셋팅
	int no;
	String title, singer;
	String[] arr;

	public StMusic(int no, String title, String singer) {
		super();
		this.no = no;
		this.title = title;
		this.singer = singer;
		arr = new String[] { title, singer };
	}

	@Override
	public String toString() {
		return "[" + no + ", " + title + ", " + singer + "]";
	}

	boolean search(int i, String pp) {
		return Pattern.matches(pp, arr[i]);
	}

}

class StMP3 { // mp3

	Stack back = new Stack();
	Stack next = new Stack();
	StMusic now;
	int max = -1;

	void goNext() { // 다음곡
		System.out.println("goNext()------------------------");
		if (next.empty()) {
			System.out.println("다음곡이 없습니다.");
			return;
		}

		next_M();
		ppp();
	}

	void next_M() {

		if (now != null)
			back.push(now);

		now = (StMusic) next.pop();
	}

	void goBack() { // 이전곡
		System.out.println("goBack()------------------------");
		if (back.empty()) {
			System.out.println("다음곡이 없습니다.");
			return;
		}

		back_M();
		ppp();
	}

	void back_M() {
		if (now != null)
			next.push(now);

		now = (StMusic) back.pop();
	}

	void goNo(int no) {
		System.out.println("goNo(" + no + ")------------------------");

		if (no < 1 || no > max) {
			System.out.println("없는 번호 입니다.");
			return;
		}

		while (now.no < no) {
			next_M();
		}

		while (now.no > no) {
			back_M();
		}

		ppp();
	}

	void ppp() {

		System.out.println("현재:" + now);
		System.out.println("이전곡들:" + back);
		System.out.println("다음곡들:" + next);
	}

	void add(int no, String title, String singer) { // 후입선출 그래서 추가시 역순으로 추가
		if (max < no)
			max = no;

		next.push(new StMusic(no, title, singer)); // Stack 은 Object 를 가질 수 있다.
	}

	String ppStr(String str) {

		String pp = ".*";
		String[] hangul = { "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㅃㅉㄸㄲㅆ", "가나다라마바사아자차카타파하빠짜따까싸", "깋닣딯맇밓빟싷잏짛칳킿팋핗힣삫찧띻낗앃" };

		for (char ch : str.toCharArray()) {
			int p = hangul[0].indexOf(ch);
			if (p >= 0) {
				pp += "[" + hangul[1].charAt(p) + "-" + hangul[2].charAt(p) + "]";
			} else {
				pp += ch;
			}
		}

		pp += ".*";
		return pp;
	}

	void search(int i, String str) {

		String[] title = { "제목", "가수" };
		System.out.println("search(" + title[i] + "," + str + ")------------------------");

		int nowNum = now.no; // 현재의 트럭번호
		boolean chk = false; // 초기값은 false
		String pp = ppStr(str); // 정규식 반환

		while (!next.empty()) { // next가 empty 가 될때 까지 반복
			next_M();
			if (chk = now.search(i, pp)) // true or false
				break;
		}

		if (!chk) { // next에서 못 찾았다면.....

			while (!back.empty()) { // back이 empty 가 될때 까지 반복
				back_M();
			}

			next_M();
			while (nowNum != now.no) {

				if (chk = now.search(i, pp))
					break;

				next_M();
			}
		}

		if (!chk) { // back 에서도 못 찾았다면 ....
			System.out.println("찾으시는 곡이 없습니다.");
		} else { // 찾았을경우
			ppp(); // 출력문 실행
		}

	}
}

public class StackMP3Main {

	public static void main(String[] args) {
		StMP3 mp3 = new StMP3();

		mp3.add(8, "그때 그 아인", "김필");
		mp3.add(7, "모든 날, 모든 순간", "폴킴");
		mp3.add(6, "시든 꽃에 물을 주듯", "HYNN");
		mp3.add(5, "먼지가 되어", "김광석");
		mp3.add(4, "Attention", "Charlie Puth");
		mp3.add(3, "Please Don't Touch", "RAYE");
		mp3.add(2, "Faded", "Alan Walker");
		mp3.add(1, "All of Me", "John Legend");

		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNo(11);
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNo(2);
		mp3.goBack();
		mp3.goBack();
		mp3.goNo(4);
		mp3.search(0, "모든");
		mp3.search(1, "Wal");
		mp3.search(0, "ㄲ에");
	}

}
