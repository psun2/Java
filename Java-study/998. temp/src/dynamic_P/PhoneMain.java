package dynamic_P;

interface CameraMP3 { // 카메라 와 MP3 공통

	void play(); // 동영상, mp3

	void pause(); // 동영상, mp3

	void stop(); // 동영상, mp3
}

interface CallMP3 { // 전화기 와 MP3 공통
	void recording(); // 전화기, mp3
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
		
	}
	
	@Override
	public void recording() {
		System.out.println();
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void directory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void picture() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		
	}
	
	
}

public class PhoneMain {

	public static void main(String[] args) {
		

//카메라, mp3 공통 - 동영상(정지), 동영상(플레이), 동영상(퍼즈) ,MP3(정지), MP3(플레이), MP3(퍼즈)
//
//전화기, mp3 - 녹음
//
//전화기 - 전화걸기, 전화번호부

	}

}
