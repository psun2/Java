package io_p;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataScanInMain {

	public static void main(String[] args) {

		try {

			FileInputStream fis = new FileInputStream("fff/ddd.txt");
			DataInputStream dis = new DataInputStream(fis);

			while (dis.available() > 0) {
				System.out.println(dis.readUTF());
			}

			dis.close();
			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
