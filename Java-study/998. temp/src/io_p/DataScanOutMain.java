package io_p;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class DataScanOutMain {

	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);
			FileOutputStream fos = new FileOutputStream("fff/ddd.txt");
			DataOutputStream dos = new DataOutputStream(fos);

			String line;

			while (true) {
				line = sc.nextLine();
				if (line.equalsIgnoreCase("X"))
					break;

				dos.writeUTF(line);
			}

			dos.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
