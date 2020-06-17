
package exam;
//mp3 노래듣기를 구현하세요

// 1. 노래 정보 :  트랙번호, 노래제목, 가수

// 2. 노래 리스트 -->next 에 넣기

// 3. 구현내용 다음곡, 이전곡, 트랙번호

//---------------------------------------------------------------------------------------------------------

//검색기능을 구현하세요
//1. 제목으로 검색
//2. 현재 트랙 이후부터 검색
//3. 제목, 가수를 구분하여 검색

//   ---  동일 검색내용이 있을 경우 
//
//          1. 현재 트랙 바로 다음 나오는 곡으로 이동
//
//          2. 검색된 노래 정보를 보여주고 트랙번호를 입력하여 선택하여 이동

import java.util.Stack;
import java.util.regex.Pattern;

class MusicDate {

	Stack list;

	public MusicDate() {
		list = new Stack();
	}

	void addList(String song) {

		list.push(song);

	}

}

class Mp3 {

	Stack data;
	Stack myList; // 전체곡
	Stack previous; // 이전곡
	Object current; // 현재곡
	Stack next; // 다음곡

	public Mp3(Stack list) {
		myList = new Stack();
		previous = new Stack();
		current = null;
		next = new Stack();
		this.data = list;
	}

//	void addList(String song) {
//
//		myList.push(song);
//
//	}

	void play(Object song) {

		song = (Object) search(song);

		if (song.equals(""))
			return;
		else
			myList.push(song);

		int check = -1;
		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).equals(song)) {
				check++;
				break;
			}
		}

		if (check == 0) {

			Stack clone = (Stack) myList.clone(); // 비교할 임시 stack 생성

			int playIndex = clone.indexOf(song); // index 번호 추출

			next.clear(); // 해당 플레이 list에 맞게 셋팅하기 위해 모두 초기화
			previous.clear(); // 해당 플레이 list에 맞게 셋팅하기 위해 모두 초기화

			while (!clone.empty()) {

				Object temp = clone.pop();

				int currentIndex = myList.indexOf(temp);

				if (currentIndex > playIndex) // 후입 선출로 뒤 에있으므로 계속 0번 index 에 add
					next.add(0, temp);
				else if (currentIndex == playIndex)
					current = song;
				else if (currentIndex < playIndex)
					previous.add(0, temp);
			}
			System.out.println(song + "을 실행합니다.");
			System.out.println(toString());
		} else {
			System.out.println("해당 곡이 list에 존재 하지 않습니다.");
		}
	}

	String search(Object song) {

		String[] korean = { "[가-깋]", "[나-닣]", "[다-딯]", "[라-맇]", "[마-밓]", "[바-빟]", "[사-싷]", "[아-잏]", "[자-짛]", "[차-칳]",
				"[카-킿]", "[타-팋]", "[파-핗]", "[하-힣]", "[빠-삫]", "[짜-찧]", "[따-띻]", "[까-낗]", "[싸-앃]" };

		String consonant = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㅃㅉㄸㄲㅆ";

		String pattern = ".*";

		for (char ch : ((String) song).toCharArray()) {
			int position = consonant.indexOf(ch);

			if (position >= 0) {
				pattern += korean[position];
			} else { // 초성이 아닌경우
				pattern += ch;
			}
		}

		pattern += ".*";

		String title = "";

//		for (int i = 0; i < myList.size(); i++) {
//			if (Pattern.matches(pattern, (String) myList.get(i))) {
//				title += myList.get(i) + ",";
//			}
//		}
		for (int i = 0; i < data.size(); i++) {
			if (Pattern.matches(pattern, (String) data.get(i))) {
				title += data.get(i) + ",";
			}
		}

		String result = "";
		if (title.split(",").length == 1) {
			result = title.split(",")[0];
		} else {
			for (int i = 0; i < title.split(",").length; i++) {
				if (i == title.split(",").length - 1)
					result += title.split(",")[i];
				result += title.split(",")[i] + ", ";
			}
		}

		if (title.equals(""))
			System.out.println(song + "와 일치하는 노래가 존재 하지 않습니다.");

		if (title.split(",").length < 1) {
			System.out.println(result);
			System.out.println("검색 결과가 많습니다. 정확한 이름으로 다시한번 입력해 주세요.");
		} else {
			return result;
		}

		return "";

	}

	void goTrack(int trackNum) { // 트랙 번호를 받음

		Object change = myList.indexOf(myList.get(trackNum));

		for (int i = 0; i < myList.size(); i++) {
			if (myList.indexOf(myList.get(trackNum)) == -1) {
				System.out.println("해당 트랙 " + trackNum + "번 곡이 존재 하지 않습니다.");
				return;
			}
		}

		System.out.println("해당 트랙 " + trackNum + "번에 곡을 실행합니다.");

		play(myList.get(trackNum)); // 해당 index의 번호로 get을하여,

		// 플레이 함수를 실행

	}

	void goNext() {

		if (next.empty()) {
			System.out.println("다음곡이 존재 하지 않습니다.");
			return;
		}

		System.out.println("다음 곡을 실행합니다.");

		previous.push(current);

		Stack clone = (Stack) next.clone(); // 비교용 deep copy stack

		Stack shallow = new Stack(); // 값 교체용 shallow Stack

		while (!clone.empty()) {

			Object temp = clone.pop();

			int currentIndex = next.indexOf(temp);

			if (currentIndex == 0) {
				current = temp;
				next = shallow;
			} else
				shallow.add(0, temp);
		}

		System.out.println(toString());
	}

	void goPrevious() {

		if (previous.empty()) {
			System.out.println("이전곡이 존재 하지 않습니다.");
			return;
		}

		System.out.println("이전 곡을 실행합니다.");

		next.add(0, current);

		current = previous.pop();

		System.out.println(toString());

	}

	@Override
	public String toString() {
		Object pre = "";
		Object ne = "";
		int preNum = 0;
		int nextNum = 0;

		try {

			preNum = myList.indexOf(current) - 1;
			nextNum = myList.indexOf(current) + 1;

			pre = (String) myList.get(myList.indexOf(current) - 1);
			ne = (String) myList.get(myList.indexOf(current) + 1);

			if (next.size() == 0 && previous.size() == 0) {
				return "Mp3 재생목록 : [ ⏮ 이전곡 = 없음, ▶ 현재곡 = " + current + "(" + myList.indexOf(current)
						+ "), ⏩ 다음곡 = 없음 ]\n";
			} else if (next.size() == 0) {
				return "Mp3 재생목록 : [ ⏮ 이전곡 =" + pre + ", ▶ 현재곡 = " + current + "(" + myList.indexOf(current)
						+ "), ⏩ 다음곡 = 없음 ]\n";
			} else if (previous.size() == 0) {
				return "Mp3 재생목록 : [ ⏮ 이전곡 = 없음, ▶ 현재곡 = " + current + "(" + myList.indexOf(current) + "), ⏩ 다음곡 = "
						+ ne + " ]\n";
			}
			return "Mp3 재생목록 : [ ⏮ 이전곡 =" + pre + "(" + (myList.indexOf(current) - 1) + "), ▶ 현재곡 = " + current + "("
					+ myList.indexOf(current) + ")" + ", ⏩ 다음곡 = " + ne + "(" + (myList.indexOf(current) + 1) + ") ]\n";
		} catch (Exception e) {
			if (preNum < 0) {
				pre = previous;
			}
			if (nextNum > myList.size() - 1) {
				ne = next;
			}
			if (next.size() == 0 && previous.size() == 0) {
				return "Mp3 재생목록 : [ ⏮ 이전곡 = 없음, ▶ 현재곡 = " + current + "(" + myList.indexOf(current)
						+ "), ⏩ 다음곡 = 없음 ]\n";
			} else if (next.size() == 0) {
				return "Mp3 재생목록 : [ ⏮ 이전곡 =" + pre + ", ▶ 현재곡 = " + current + "(" + myList.indexOf(current)
						+ "), ⏩ 다음곡 = 없음 ]\n";
			} else if (previous.size() == 0) {
				return "Mp3 재생목록 : [ ⏮ 이전곡 = 없음, ▶ 현재곡 = " + current + "(" + myList.indexOf(current) + "), ⏩ 다음곡 = "
						+ ne + " ]\n";
			}
			return "Mp3 재생목록 : [ ⏮ 이전곡 =" + pre + "(" + (myList.indexOf(current) - 1) + "), ▶ 현재곡 = " + current + "("
					+ myList.indexOf(current) + ")" + ", ⏩ 다음곡 = " + ne + "(" + (myList.indexOf(current) + 1) + ") ]\n";

		}
	}

}

public class Main {

	public static void main(String[] args) {

		String[] song = { "에잇(Prod.&Feat. SUGA of BTS)", "Candy", "ON", "아로하", "MORE & MORE", "사랑하게 될 줄 알았어",
				"깡 Official Remix", "작은 것들을 위한 시 (Boy With Luv) (Feat. Halsey)", "00:00 (Zero O’Clock)", "Bungee" };

		MusicDate data = new MusicDate();

		for (int i = 0; i < song.length; i++) {
			data.addList(song[i]);
		}

		Mp3 mp3 = new Mp3(data.list);

		for (int i = 0; i < song.length; i++) {
			mp3.play(song[i]);
		}

		mp3.play("ㅇㄹㅎ");

		mp3.goPrevious();
		for (int i = 0; i < song.length; i++) {
			mp3.goPrevious();
		}

		for (int i = 0; i < song.length * 2; i++) {
			mp3.goNext();
		}

		mp3.play("아ㄹㅎ");

		mp3.goTrack(6);

//		에잇(Prod.&Feat. SUGA of BTS)와 일치하는 노래가 존재 하지 않습니다.
//		Candy을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 = 없음, ▶ 현재곡 = Candy(0), ⏩ 다음곡 = 없음 ]
//
//		ON을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =Candy, ▶ 현재곡 = ON(1), ⏩ 다음곡 = 없음 ]
//
//		아로하을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =ON, ▶ 현재곡 = 아로하(2), ⏩ 다음곡 = 없음 ]
//
//		MORE & MORE을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =아로하, ▶ 현재곡 = MORE & MORE(3), ⏩ 다음곡 = 없음 ]
//
//		사랑하게 될 줄 알았어을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =MORE & MORE, ▶ 현재곡 = 사랑하게 될 줄 알았어(4), ⏩ 다음곡 = 없음 ]
//
//		깡 Official Remix을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =사랑하게 될 줄 알았어, ▶ 현재곡 = 깡 Official Remix(5), ⏩ 다음곡 = 없음 ]
//
//		작은 것들을 위한 시 (Boy With Luv) (Feat. Halsey)와 일치하는 노래가 존재 하지 않습니다.
//		00:00 (Zero O’Clock)와 일치하는 노래가 존재 하지 않습니다.
//		Bungee을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =깡 Official Remix, ▶ 현재곡 = Bungee(6), ⏩ 다음곡 = 없음 ]
//
//		아로하을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =ON(1), ▶ 현재곡 = 아로하(2), ⏩ 다음곡 = MORE & MORE(3) ]
//
//		이전 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =Candy(0), ▶ 현재곡 = ON(1), ⏩ 다음곡 = 아로하(2) ]
//
//		이전 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 = 없음, ▶ 현재곡 = Candy(0), ⏩ 다음곡 =  ]
//
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		이전곡이 존재 하지 않습니다.
//		다음 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =Candy(0), ▶ 현재곡 = ON(1), ⏩ 다음곡 = 아로하(2) ]
//
//		다음 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =ON(1), ▶ 현재곡 = 아로하(2), ⏩ 다음곡 = MORE & MORE(3) ]
//
//		다음 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =아로하(2), ▶ 현재곡 = MORE & MORE(3), ⏩ 다음곡 = 사랑하게 될 줄 알았어(4) ]
//
//		다음 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =MORE & MORE(3), ▶ 현재곡 = 사랑하게 될 줄 알았어(4), ⏩ 다음곡 = 깡 Official Remix(5) ]
//
//		다음 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =사랑하게 될 줄 알았어(4), ▶ 현재곡 = 깡 Official Remix(5), ⏩ 다음곡 = Bungee(6) ]
//
//		다음 곡을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =깡 Official Remix, ▶ 현재곡 = Bungee(6), ⏩ 다음곡 = 없음 ]
//
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		다음곡이 존재 하지 않습니다.
//		아로하을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =ON(1), ▶ 현재곡 = 아로하(2), ⏩ 다음곡 = MORE & MORE(3) ]
//
//		해당 트랙 6번에 곡을 실행합니다.
//		Bungee을 실행합니다.
//		Mp3 재생목록 : [ ⏮ 이전곡 =깡 Official Remix, ▶ 현재곡 = Bungee(6), ⏩ 다음곡 = 없음 ]

	}

}

//		Error ???? System.out.println(Pattern.matches(".*작은 것들을 위한 시 (Boy With Luv) (Feat. Halsey).*", song[7]));
//		Error ???? System.out.println(Pattern.matches(".*작은 것들을 위한 시 (Boy With Luv) (Feat. Halsey).*",
//				"작은 것들을 위한 시 (Boy With Luv) (Feat. Halsey)"));