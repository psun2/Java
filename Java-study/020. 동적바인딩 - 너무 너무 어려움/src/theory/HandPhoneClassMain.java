package theory;

class HandPhone {

	String name;

	public HandPhone(String name) {
		super();
		this.name = name;
	}

}

interface PhoneGo {

	void exec(HandPhone phone);
}

class Music implements PhoneGo {
	@Override
	public void exec(HandPhone phone) {
		System.out.println(phone.name + " 노래 재생");
	}
}

class Camera implements PhoneGo {
	@Override
	public void exec(HandPhone phone) {
		System.out.println(phone.name + " 사진 찍기");
	}
}

class Call implements PhoneGo {
	@Override
	public void exec(HandPhone phone) {
		System.out.println(phone.name + " 통화");
	}
}

class Game implements PhoneGo {
	@Override
	public void exec(HandPhone phone) {
		System.out.println(phone.name + " 게임 시작");
	}
}

class DDD implements PhoneGo {
	@Override
	public void exec(HandPhone phone) {
		System.out.println("기본값");
	}
}

public class HandPhoneClassMain {

	public static void main(String[] args) throws Exception {
		HandPhone hp = new HandPhone("은하수"); // 핸드폰이란 클래스를 실체화 시킨 인스턴스

		String cmd = "Camera"; // 명령어가 camera로 왔을시

		PhoneGo go;

		switch (cmd) { // 바인딩 처리가 되지 않은 경우, 명령어를 하나하나 스위치 케이스로 비교하여 실행합니다.
		case "Game":
			go = new Game();
			break;
		case "Camera":
			go = new Camera();
			break;
		case "Call":
			go = new Call();
			break;
		case "Music":
			go = new Music();
			break;
		default:
			go = new DDD();
			break;
		}

		go.exec(hp);

//////////////////////////////////////////////////////////////////////////////////////////////////////

		// 반면 동적 바인딩 처리를 했을때 명령어를 입력받아 그에 해당하는 기능을 수행 가능 합니다.

		String cmd2 = "Music";

		((PhoneGo) Class.forName("lang_p." + cmd2).newInstance()).exec(hp); // 명령어에 맞는 인스턴스를 생성후 메소드 실행방법

	}

}