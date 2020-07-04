package theory.DataStream_Scanner;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Scanner_DataOutputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림
		// DataStream 은 혼자선 사용 불가능 합니다.

		try {

			FileOutputStream fos = new FileOutputStream("./예제폴더/exam3.txt");
			DataOutputStream dos = new DataOutputStream(fos);

			while (true) {

				Scanner sc = new Scanner(System.in);

				System.out.print("입력 : ");
				String input = sc.nextLine();

				if (input.equalsIgnoreCase("x")) {
					sc.close();
					break;
				}

				dos.writeUTF(input + "\n");

			}

			fos.close();
			dos.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
