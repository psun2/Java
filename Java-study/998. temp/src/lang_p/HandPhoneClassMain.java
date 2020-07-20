package lang_p;

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
		HandPhone hp = new HandPhone("은하수");

		String cmd = "Camera";

		PhoneGo go;
		switch (cmd) {
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

		String cmd2 = "Music";

		((PhoneGo) Class.forName("lang_p." + cmd2).newInstance()).exec(hp);

	}

}
