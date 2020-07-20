package io_p;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DateInMain {

	public static void main(String[] args) {

		try {

			FileInputStream fis = new FileInputStream("fff/ccc.txt");
			DataInputStream dis = new DataInputStream(fis);

			// 순서 있게 가져오는 것이 가장 중요 !!!!!!!!!!!!!!!!!
			System.out.println(dis.readBoolean());
			System.out.println(dis.readChar());
			System.out.println(dis.readDouble());
			System.out.println(dis.readInt());
			System.out.println(dis.readFloat());
			System.out.println(dis.readLong());
			System.out.println(dis.readUTF());
			System.out.println(dis.readUTF());

			dis.close();
			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
