package io_p;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DataStreamExam {

	public static void main(String[] args) {

//		장동건_77,78,75
//		장서건_67,68,65
//		장남건_97,98,95
//		갓동규_57,58,55
//		갓짝퉁_87,88,85
//
//		DataExamIn 클래스에서  위와같은 형태로 5명의 데이터를 GUI 를 이용하여 입력받고
//		DataExamOut 클래스에서 내용을 확인하세요

		String[] studInfo = { "장동건_77,78,75", "장서건_67,68,65" };

//////////////////////////////////////////////////////////////////////////////////////

//		회원가입 정보를 입력하여 출력하세요
//
//
//		id
//		이름
//		학년
//		전화번호
//		군필여부
//
//		저장위치 : fff/eee.abc

		String[] title = { "id", "이름", "학년", "전화번호", "군필여부" };
		int i = 0;
		try {

			FileOutputStream fos = new FileOutputStream("./fff/eee.abc");
			DataOutputStream dos = new DataOutputStream(fos);
			// Scanner sc = new Scanner(System.in);

			String massage;

			while (i < title.length) {

				massage = JOptionPane.showInputDialog(title[i] + " 을/를 입력해 주세요");
				i++;
				dos.writeUTF(massage);
			}

			// 앞에 항목도 나오게 하세요

			dos.close();
			fos.close();

			FileInputStream fis = new FileInputStream("./fff/eee.abc");
			DataInputStream dis = new DataInputStream(fis);

			while (dis.available() > 0) {

				System.out.println(dis.readUTF());

			}

			fis.close();
			dis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
