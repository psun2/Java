package theory.DataStream_Scanner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

public class Exam_DataStream_Scanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		회원가입 정보를 입력하여 출력하세요
//
//		id
//		이름
//		학년
//		전화번호
//		군필여부
//
//		저장위치 : 예제폴더/exam4.abc

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림
		// DataStream 은 혼자선 사용 불가능 합니다.

		try {

			FileOutputStream fos = new FileOutputStream("예제폴더/exam4.abc");
			DataOutputStream dos = new DataOutputStream(fos);

			String[] title = { "id", "이름", "학년", "전화번호", "군필여부" };

			for (String string : title) {

				String input = JOptionPane.showInputDialog(string + " 을/를 입력해 주세요.");
				dos.writeUTF(string + " : " + input);

			}

			fos.close();
			dos.close();

			FileInputStream fis = new FileInputStream("예제폴더/exam4.abc");
			DataInputStream dis = new DataInputStream(fis);

			while (dis.available() > 0) {
				System.out.println(dis.readUTF());
			}

			fis.close();
			dis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

//		장동건_77,78,75
//		장서건_67,68,65
//		장남건_97,98,95
//		갓동규_57,58,55
//		갓짝퉁_87,88,85
//
//		DataExamIn 클래스에서  위와같은 형태로 5명의 데이터를 GUI 를 이용하여 입력받고
//		DataExamOut 클래스에서 내용을 확인하세요

	}

}
