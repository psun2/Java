package chat;

public class DBDateReturnTest {

	// 날짜 변환 함수
	static String conversionDate(String date, int start, int end) {
		return date.substring(start, end);
	}

	public static void main(String[] args) {

		String chatTimeStamp = "20/09/06 13:26:48.000000000";

		int chatTime = Integer.parseInt(chatTimeStamp.substring(9, 11));

		String timeType = "오전";
		if (chatTime > 12) {
			timeType = "오후";
			chatTime -= 12;
		}

		System.out.println("chatTimeStamp : " + conversionDate(chatTimeStamp, 0, 9) + " " + timeType + " " + chatTime
				+ conversionDate(chatTimeStamp, 11, 16));

		System.out.println();

		String time = "2020/09/06 13:26:48";

		int integerTime = Integer.parseInt(conversionDate(time, 11, 13));

		timeType = "오전";
		if (integerTime > 12) {
			timeType = "오후";
			integerTime -= 12;
		}

		System.out.println("time : " + conversionDate(time, 0, 10) + " " + timeType + " " + integerTime
				+ conversionDate(time, 13, 16));

	}

}
