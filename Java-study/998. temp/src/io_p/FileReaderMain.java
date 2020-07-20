package io_p;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderMain {

	public static void main(String[] args) {
		try {

			FileInputStream fis = new FileInputStream("fff/bbb.txt");

			while (fis.available() > 0) {

				System.out.print((char) fis.read());

			}

			fis.close();

//			asdhjkashfdjkash jkshadhkjashbfjkas yhUdcahsj dhjka
//			123456789
//			!@#$%^&*()_+
//			ìí¼ ìí¼ ìí¼ì­ íì íì íìì­
//			ìë¹ë¤ì´ ëíëë©´ ëª¨ëëª¨ëëª¨ë ë¬ë ¤ìì

			System.out.println("\n----------------------------------------");

			// 2byte 기반 으로 한글이 깨지지 않고 잘 나옵ㅂ니다.

			FileReader fr = new FileReader("fff/애상.txt");
//			FileReader fr = new FileReader("fff/bbb.txt");
//			FileWriter fw = new FileWriter("dst/www.txt");
//			FileReader fr = new FileReader("img/son_6.jpg");
//			FileWriter fw = new FileWriter("dst/child_6.jpg");
			// 이미지 파일을 읽지 못합니다.

			int data;

			while ((data = fr.read()) >= 0) {
				System.out.print((char) data);
//				fw.write(data);
			}

//			fw.close();
			fr.close();

//			asdhjkashfdjkash jkshadhkjashbfjkas yhUdcahsj dhjka
//			123456789
//			!@#$%^&*()_+
//			수퍼 수퍼 수퍼잭 파워 파워 파워잭
//			악당들이 나타나면 모두모두모두 달려와요

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
