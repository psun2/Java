package test_FileWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileMain {

	public static void main(String[] args) throws IOException {

		FileWriter log = new FileWriter("./src/test/log.txt");

		log.write("log 기록");
		log.write("\nlog 기록2"); // 리눅스 에서의 줄바꿈
		log.write("\r\nlog 기록3"); // 윈도우 에서의 줄바꿈

		log.close();

		FileWriter resource = new FileWriter("./src/test/resourceLog.txt");

		resource.write("자원기록");

		resource.close();

		Scanner sc = new Scanner((Readable) log);

		// Exception in thread "main" java.lang.ClassCastException: java.io.FileWriter
		// cannot be cast to java.lang.Readable
		// at test.fileMain.main(fileMain.java:26)

		// 스레드 "main" java.lang의 예외.ClassCastException: java.io.FileWriter를 java.lang에
		// 캐스트할 수 없음.읽을 수 있는
		// 시험 중에fileMain.main(fileMain.java:26)

		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}

		sc.close();

	}

}
