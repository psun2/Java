package exam;

import java.util.Stack;

class StMusicFail {

	int no; // 트랙넘버
	String title; // 제목
	String singer; // 가수

	public StMusicFail(int no, String title, String singer) {
		super();
		this.no = no;
		this.title = title;
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "StMusic [no=" + no + ", title=" + title + ", singer=" + singer + "]";
	}

}

class StMP3Fail { // mp3는 곡추가와 함께 바로 재생

	Stack back;
	StMusicFail now;
	Stack next;
	int lastSong = -1;

	public StMP3Fail() {
		back = new Stack();
		next = new Stack();
	}

	void add(int no, String title, String singer) {

		if (now != null && !next.empty()) {
			next.push(now);
			int lastIndext = next.size();
			for (int i = 0; i < lastIndext; i++) {
				back.push(next.pop());
			}
			next.clear();
			now = new StMusicFail(no, title, singer);
			last(no); // 추가된 곡의 갯수를 저장
			play();
			return;
		}

		if (now != null)
			next.push(now);

		now = new StMusicFail(no, title, singer);
		last(no); // 추가된 곡의 갯수를 저장
		play();

	}

	void last(int num) {

		if (lastSong < num)
			lastSong = num;

	}

	void goBack() {

		if (back.empty()) {
			System.out.println("이전 곡이 존재하지 않습니다.");
			return;
		}

		System.out.println("goBack(" + now + ")-----의 이전곡을 재생합니다.");

		System.out.println(next);
		System.out.println(back);

//		next.push(now);
//		now = (StMusic) back.pop();

		backFunctionModul();

		play();
	}

	void backFunctionModul() {

//		if (now != null)
		next.push(now);
		now = (StMusicFail) back.pop();

	}

	void goNext() {

		if (next.empty()) {
			System.out.println("다음 곡이 존재하지 않습니다.");
			return;
		}

		System.out.println("goNext(" + now + ")의-----다음곡을 재생합니다.");

		nextFunctionModul();

		play();
	}

	void nextFunctionModul() {

//		if (now != null)
		back.push(now);
		now = (StMusicFail) next.pop();

	}

	void goNumber(int num) {

		if (lastSong < 1 || num > lastSong) {
			System.out.println("해당 " + num + " 의 트랙에 곡이 존재 하지 않습니다.");
		}

		while (now.no < num) { // 다음곡에서 찾기
			nextFunctionModul();
		}

		while (now.no > num) { // 이전곡에서 찾기
			System.out.println(now.no);
			System.out.println(num);
			backFunctionModul();
		}

		play();

	}

	void inspect(int i, String str) {

	}

	void search(int i, String str) { // 제목별 검색은 0 가수별 검색은 1

		String[] title = { "제목", "가수" };
		System.out.println("search(" + title[i] + "," + str + ")------------------------");

		boolean chk = false;

		while (!next.empty()) { // next 에서 먼저 찾아보자

			StMusicFail buf = (StMusicFail) next.pop();

			switch (i) {
			case 0: // 제목검색
				String bufTitle = buf.title; // 제목
				break;
			case 1: // 제목검색
				String bufSinger = buf.singer; // 가수
				break;
			}

		}
//			if(chk = inspect(i, str)) // 여기서 정규식에서 반환

	}

	void play() {

		System.out.println("이전곡" + back);
		System.out.println("현재곡" + now);
		System.out.println("다음곡" + next);

	}

}

public class StackMP3MainFail {

	public static void main(String[] args) {

		StMP3Fail mp3 = new StMP3Fail();

		mp3.add(8, "그때 그 아인", "김필");
		mp3.add(7, "모든 날, 모든 순간", "폴킴");
		mp3.add(6, "시든 꽃에 물을 주듯", "HYNN");
		mp3.add(5, "먼지가 되어", "김광석");
		mp3.add(4, "Attention", "Charlie Puth");
		mp3.add(3, "Please Don't Touch", "RAYE");
		mp3.add(2, "Faded", "Alan Walker");
		mp3.add(1, "All of Me", "John Legend");

		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();

		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();
		mp3.goNext();

		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();
		mp3.goBack();

		mp3.add(9, "861", "go"); // 마지막으로 추가해준거임 다음곡이 존재 하면 안됨

		System.out.println();
		mp3.goNumber(7);

	}

}
