package io_p;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataOutMain {

	public static void main(String[] args) {

		try {

			FileOutputStream fos = new FileOutputStream("fff/ccc.txt");
			DataOutputStream dos = new DataOutputStream(fos);

			dos.writeBoolean(true);
			dos.writeChar('a');
			dos.writeDouble(123.456);
			dos.writeInt(123);
			dos.writeFloat(789.654f);
			dos.writeLong(123456745678l);
			dos.writeUTF("우리는 슈퍼슈퍼 슈퍼잭");
			dos.writeUTF("여기 참나무  숲 ");
			
			dos.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
