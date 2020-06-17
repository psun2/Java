package theory;

interface CameraMP3 { // 카메라 와 MP3 공통

	void play(String srt); // 동영상, mp3

	void pause(String srt); // 동영상, mp3

	void stop(String srt); // 동영상, mp3
}

interface CallMP3 { // 전화기 와 MP3 공통
	void recording(String srt); // 전화기, mp3
}

interface Camera extends CameraMP3 {

	void picture();

	void view();

}

interface MP3 extends CameraMP3, CallMP3 {

}

interface Call extends CallMP3 {

	void call();

	void directory();

}

class Phone implements Camera, MP3, Call {

	String name;

	public Phone(String name) {
		this.name = name;
	}

	@Override
	public void recording(String srt) { // 통화 or 노래
		System.out.println(this.name + " 으로 " + srt + " 을 녹음해요");

	}

	@Override
	public void play(String srt) { // 동영상 or 노래
		System.out.println(this.name + " 으로 " + srt + " 을 재생해요");

	}

	@Override
	public void pause(String srt) { // 동영상 or 노래
		System.out.println(this.name + " 으로 " + srt + " 을 일시정지해요");
	}

	@Override
	public void stop(String srt) { // 동영상 or 노래
		System.out.println(this.name + " 으로 " + srt + " 을 정지해요");

	}

	@Override
	public void call() {
		System.out.println(this.name + " 으로 " + "전화 를 해요");

	}

	@Override
	public void directory() {
		System.out.println(this.name + " 으로 " + "전화번호부를 보아요");

	}

	@Override
	public void picture() {
		System.out.println(this.name + " 으로 " + "사진을 찍어요");

	}

	@Override
	public void view() {
		System.out.println(this.name + " 으로 " + "사진첩을 보아요");

	}

}

public class ExamPhoneMAin {

	public static void main(String[] args) {

//카메라, mp3 공통 - 동영상(정지), 동영상(플레이), 동영상(퍼즈) ,MP3(정지), MP3(플레이), MP3(퍼즈)
//
//전화기, mp3 - 녹음
//
//전화기 - 전화걸기, 전화번호부

		Phone iphone = new Phone("아이폰");

		iphone.call();
		iphone.directory();
		iphone.play("음악");
		iphone.picture();
		iphone.pause("동영상");
		iphone.recording("통화");
		iphone.stop("노래");
		iphone.view();

	}

}