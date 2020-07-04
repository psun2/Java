package theory.DataStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataOutputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림
		// DataStream 은 혼자선 사용 불가능 합니다.

		try {

			FileOutputStream fos = new FileOutputStream("./예제폴더/exam2.txt");

			// DataStream 은 혼자선 사용 불가능 합니다.
			DataOutputStream dos = new DataOutputStream(fos);

			// 특징 => 바이트 뿐만 아니라 모든 자료형을 쓸 수 있습니다.
			// 단점 => 써지긴 써지는데 파일을 직접 열어 보았을시 읽을 수 없습니다.

			dos.writeBoolean(true);
			dos.writeChar('a');
			dos.writeDouble(123.456);
			dos.writeInt(123);
			dos.writeFloat(789.654f);
			dos.writeLong(123456745678l);
			dos.writeUTF("우리는 슈퍼슈퍼 슈퍼잭");
			dos.writeUTF("여기 참나무  숲 ");

			fos.close();
			dos.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
