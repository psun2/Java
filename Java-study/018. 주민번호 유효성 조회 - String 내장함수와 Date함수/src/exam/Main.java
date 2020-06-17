package exam;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class Jumin {

	private String[] title = { "성별", "국적", "출생신고 지역", "생년월일", "한국나이", "만나이", "올해생일", "다가올 생일(내년생일)", "생일파티 DDay" };
	private String[][] result = { title, new String[title.length] };

	String date;

	private String jumin, frontNum, backNum;

	public Jumin(String jumin) {
		super();
		this.jumin = jumin;
		dateFormat();
	}

	private void dateFormat() {

		Date date = new Date();

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

		this.date = formatDate.format(date);

		init();
	}

	private void init() {
		this.frontNum = jumin.split("[-]")[0]; // split 메소드는 인자로 받는 구분기호를 기준으로 배열로 반환, 반환된 배열의 0번 index 는 주민번호의 앞자리
		this.backNum = jumin.split("[-]")[1]; // 반환된 배열의 1번 index 는 주민번호의 앞자리
		inspectGender();
	}

	private int position(int num, int digit) { // 자릿수를 구해주는 재귀 함수

		int result = 1;

		if (digit == 1)
			return result;

		result = num * position(num, (digit - 1));

		return result;
	}

	private int parseInt(String str) { // string 을 받아 아스키코드로 변환 하여 0을 빼줘서 문자를 숫자로 바꾸어 주는 메소드

		int result = 0;
		int digit = position(10, str.length());

		for (int i = 0; i < str.length(); i++) {
			result += ((int) str.charAt(i) - (int) '0') * digit;
			digit /= 10;
		}

		return result;
	}

	private void calc(int num) {

		String birth;
		if (num == 3 || num == 4 || num == 7 || num == 8)
			birth = "20";
		if (num == 9 || num == 0)
			birth = "18";
		else
			birth = "19";

		int year = parseInt(date.split("[-]")[0]); // 올늘의 년도
		int myYear = parseInt(birth + frontNum.substring(0, 2)); // 나의 출생년도

		int month = parseInt(date.split("[-]")[1]); // 이번달
		int day = parseInt(date.split("[-]")[2]); // 오늘 날짜

		int myMonth = parseInt(frontNum.substring(2, 4)); // 주민등록 상의 달
		int myday = parseInt(frontNum.substring(4, 6)); // 주민등록 상의 일

		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String dday = "", age = "", KoreaAge = "", birthday = "", nextBirthday = "";

		if (myMonth > month) { // 생일은 지나지 않았다
			int result = 0;
			for (int i = 1; i <= days.length; i++) {
				if (i == month) {
					result = days[i - 1] - day;
					for (int j = month + 1; j < myMonth; j++) {
						result += days[j - 1];
					}
					result += myday;
					break;
				}
			}
			age += ((year - myYear) - 1); // 만나이
			dday += "D - " + result + " 일"; // 생일 디데이
			nextBirthday += year + "년 " + frontNum.substring(2, 4) + "월 " + frontNum.substring(4, 6) + "일 "; // 다가올 생일
		} else { // 생일이 지났다.
			int result = 0;
			// 생일이 지났을때 다음년도 까지의 Dday = 365 - 오늘까지 지난 날짜
			for (int i = 1; i <= days.length; i++) {
				if (i == myMonth) {
					result = days[i - 1] - myday;
					for (int j = myMonth + 1; j < month; j++) {
						result += days[j - 1];
					}
					result += day;
					break;
				}
			}

			result = 365 - result;
			age += (year - myYear);
			dday += "D - " + result + "일";
			nextBirthday += (year + 1) + "년 " + frontNum.substring(2, 4) + "월 " + frontNum.substring(4, 6) + "일 ";
		}
		KoreaAge += ((year - myYear) + 1);
		birthday += year + "년 " + frontNum.substring(2, 4) + " 월" + frontNum.substring(4, 6) + " 일"; // 내 생일
		pushResult(title[8], dday);
		pushResult(title[7], nextBirthday);
		pushResult(title[6], birthday);
		pushResult(title[5], age);
		pushResult(title[4], KoreaAge);
	}

	private void inspectArea() { // 출생지역

		String twoThree = this.backNum.substring(1, 3); // 출생신고 지역을 알기 위한 주민번호 두번째와 세번째

		if (Integer.parseInt(twoThree) >= 0 && Integer.parseInt(twoThree) <= 8)
			pushResult(title[2], "서울");
		if (Integer.parseInt(twoThree) >= 9 && Integer.parseInt(twoThree) <= 12)
			pushResult(title[2], "부산");
		if (Integer.parseInt(twoThree) >= 13 && Integer.parseInt(twoThree) <= 15)
			pushResult(title[2], "인천");
		if (Integer.parseInt(twoThree) >= 16 && Integer.parseInt(twoThree) <= 25)
			pushResult(title[2], "경기도");
		if (Integer.parseInt(twoThree) >= 26 && Integer.parseInt(twoThree) <= 34)
			pushResult(title[2], "강원도");
		if (Integer.parseInt(twoThree) >= 35 && Integer.parseInt(twoThree) <= 47)
			pushResult(title[2], "충청도");
		if (Integer.parseInt(twoThree) >= 48 && Integer.parseInt(twoThree) <= 66)
			pushResult(title[2], "전라도");
		if (Integer.parseInt(twoThree) >= 67 && Integer.parseInt(twoThree) <= 90)
			pushResult(title[2], "경상도");

	}

	private void pushResult(String title, String element) {
		for (int i = 0; i < this.title.length; i++) {
			if (this.title[i].equals(title))
				this.result[1][i] = element;
		}

	}

	private void bind(String gender, String nationality, int num) {
		pushResult(title[0], gender);
		pushResult(title[1], nationality);
		pushResult(title[3], this.frontNum);
		calc(num);
		inspectArea();
	}

	private void inspectGender() {

		String one = this.backNum.substring(0, 1); // 주민번호 뒷자리의 첫번째 자리의 수

		int comdition = parseInt(one);

		switch (one) {
		case "1": // 1. 1900 년대 내국인 남성
			bind("남성", "내국인", comdition);
			break;
		case "2": // 2. 1900 년대 내국인 여성
			bind("여성", "내국인", comdition);
			break;
		case "3": // 3. 2000 년대 내국인 남성
			bind("남성", "내국인", comdition);
			break;
		case "4": // 4. 2000 년대 내국인 여성
			bind("여성", "내국인", comdition);
			break;
		case "5": // 5. 1900 년대 외국인 남성
			bind("남성", "외국인", comdition);
			break;
		case "6": // 6. 1900 년대 외국인 여성
			bind("여성", "외국인", comdition);
			break;
		case "7": // 7. 2000 년대 외국인 남성
			bind("남성", "외국인", comdition);
			break;
		case "8": // 8. 2000 년대 외국인 여성
			bind("여성", "외국인", comdition);
			break;
		case "9": // 9. 1800 년대 내국인 남성
			bind("남성", "내국인", comdition);
			break;
		case "0": // 0. 1800 년대 내국인 여성
			bind("여성", "내국인", comdition);
			break;
		default:
			System.out.println("존재하지 않음 주민등록 번호 확인 요망");
		}

	}

	@Override
	public String toString() { // toString 메소드 오버라이딩
		String print = "";

		for (int i = 0; i < result[0].length; i++) {
			print += result[0][i] + " : " + result[1][i] + "\n";
		}

		return print;
	}

}

public class Main {

	public static void main(String[] args) throws Exception {

//		성별, 국적, 생년월일, 한국나이(생일이 지났다면 + 1), 만나이(생일이 지낫다면 + 1), 올해생일, 다가올 생일(내년생일), 생일파티 DDay를
//
//		 출력하세요
//
//		첫번째는 남녀 성별구분입니다
//
//		1. 1900 년대 내국인 남성
//
//		2. 1900 년대 내국인 여성
//
//		########################
//
//		3. 2000 년대 내국인 남성
//
//		4. 2000 년대 내국인 여성
//
//		########################
//
//		-----------------------------------------------------
//
//		5. 1900 년대 외국인 남성
//
//		6. 1900 년대 외국인 여성
//
//		########################
//
//		7. 2000 년대 외국인 남성
//
//		8. 2000 년대 외국인 여성
//
//		########################
//
//		9. 1800 년대 내국인 남성
//
//		0. 1800 년대 내국인 여성
//
//		두번째 세번째는 해당지역 고유번호입니다.
//
//		00~08 서울
//
//		09~12 부산
//
//		13~15 인천
//
//		16~25 경기도
//
//		26~34 강원도
//
//		35~47 충청도
//
//		48~66 전라도
//
//		67~90 경상도
//
//		넷째 다섯째는 각 신고기간(동.읍.면사무소)
//
//		지정번호 입니다.
//
//		여섯번쩨는 같은날 같은관공서에 출생신고
//
//		순서입니다.
//		
//		일곱번째는 위조 변조방지를위한
//
//		식별번호라고 하네요.

		String[] jumin = { "100101-0000000", "200201-1090000", "300301-2130000", "400401-3160000", "500501-4260000",
				"600601-5350000", "700701-6480000", "800801-7670000", "900901-8670000", "000901-9670000" };

		for (int i = 0; i < jumin.length; i++) {
			System.out.println(new Jumin(jumin[i]).toString());

		}

//		성별 : 여성
//		국적 : 내국인
//		출생신고 지역 : 서울
//		생년월일 : 100101
//		한국나이 : 211
//		만나이 : 210
//		올해생일 : 2020년 01 월01 일
//		다가올 생일(내년생일) : 2021년 01월 01일 
//		생일파티 DDay : D - 213일
//
//		성별 : 남성
//		국적 : 내국인
//		출생신고 지역 : 부산
//		생년월일 : 200201
//		한국나이 : 101
//		만나이 : 100
//		올해생일 : 2020년 02 월01 일
//		다가올 생일(내년생일) : 2021년 02월 01일 
//		생일파티 DDay : D - 244일
//
//		성별 : 여성
//		국적 : 내국인
//		출생신고 지역 : 인천
//		생년월일 : 300301
//		한국나이 : 91
//		만나이 : 90
//		올해생일 : 2020년 03 월01 일
//		다가올 생일(내년생일) : 2021년 03월 01일 
//		생일파티 DDay : D - 272일
//
//		성별 : 남성
//		국적 : 내국인
//		출생신고 지역 : 경기도
//		생년월일 : 400401
//		한국나이 : 81
//		만나이 : 80
//		올해생일 : 2020년 04 월01 일
//		다가올 생일(내년생일) : 2021년 04월 01일 
//		생일파티 DDay : D - 303일
//
//		성별 : 여성
//		국적 : 내국인
//		출생신고 지역 : 강원도
//		생년월일 : 500501
//		한국나이 : 71
//		만나이 : 70
//		올해생일 : 2020년 05 월01 일
//		다가올 생일(내년생일) : 2021년 05월 01일 
//		생일파티 DDay : D - 333일
//
//		성별 : 남성
//		국적 : 외국인
//		출생신고 지역 : 충청도
//		생년월일 : 600601
//		한국나이 : 61
//		만나이 : 60
//		올해생일 : 2020년 06 월01 일
//		다가올 생일(내년생일) : 2021년 06월 01일 
//		생일파티 DDay : D - 334일
//
//		성별 : 여성
//		국적 : 외국인
//		출생신고 지역 : 전라도
//		생년월일 : 700701
//		한국나이 : 51
//		만나이 : 49
//		올해생일 : 2020년 07 월01 일
//		다가올 생일(내년생일) : 2020년 07월 01일 
//		생일파티 DDay : D - 29 일
//
//		성별 : 남성
//		국적 : 외국인
//		출생신고 지역 : 경상도
//		생년월일 : 800801
//		한국나이 : 41
//		만나이 : 39
//		올해생일 : 2020년 08 월01 일
//		다가올 생일(내년생일) : 2020년 08월 01일 
//		생일파티 DDay : D - 60 일
//
//		성별 : 여성
//		국적 : 외국인
//		출생신고 지역 : 경상도
//		생년월일 : 900901
//		한국나이 : 31
//		만나이 : 29
//		올해생일 : 2020년 09 월01 일
//		다가올 생일(내년생일) : 2020년 09월 01일 
//		생일파티 DDay : D - 91 일
//
//		성별 : 남성
//		국적 : 내국인
//		출생신고 지역 : 경상도
//		생년월일 : 000901
//		한국나이 : 221
//		만나이 : 219
//		올해생일 : 2020년 09 월01 일
//		다가올 생일(내년생일) : 2020년 09월 01일 
//		생일파티 DDay : D - 91 일

	}

}
