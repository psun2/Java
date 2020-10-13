package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/*
java.io 패키지의 주요 클래스

1) 바이트(byte) 단위 입출력 스트림 클래스
	java.io.InputStream: 프로그램이 '바이트 단위' 데이터를 읽어들이는(read) 통로
	java.io.OutputStream: 프로그램이 '바이트 단위' 데이터를 쓰는(write) 통로
		** 위 두개 클래스는 '추상클래스' 다
	
2) 문자(character) 단위 입출력 스트림 클래스
	java.io.Reader: 프로그램이 '문자 단위' 데이터를 읽어들이는(read) 통로
	java.io.Writer: 프로그램이 '문자 단위' 데이터를 쓰는(write) 통로

3) java.io.File : 파일 시스템의 파일정보를 얻기 위한 클래스
	
4) java.io.Console : 콘솔로부터 문자을 입출력하기 위한 클래스
*/

/* PrintWriter / 인코딩 
* 
* java.lang.Object
*  └─ java.io.Writer
*      └─ java.io.PrintWriter
*  
*  텍스트 파일 작성시는 PrintWriter 객체 편리
*  	println(), print(), printf() ..
*  텍스트 파일 읽을시는 BufferedReader 객체 편리
*  	read(), read(버퍼), readLine()..
*  
*  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("파일명" 혹은 File)));
*  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("파일명" 혹은 File))));
*  
*  BufferedReader br = new BufferedReader(new FileReader("파일이름" 혹은 File));
*  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("파일이름" 혹은 File))));
*  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("파일이름" 혹은 File)));
*  
*  ★ 문자기반 출력시 꼭 끝에 flush() 해주자 ★
* 
*  인코딩 문제 
*  	FIleReader, FileWriter 는 파일의 인코딩을 무조건 file.encoding 값으로 간주한다.
* 		(ex: LINUX 는  UTF-8,  MacOS 는 한글상위의 경우 euc-kr, 윈도우즈는 Java 소스코드 인코딩 영향) 
*  	
*  인코딩 설정하기
*  	InputStreamReader, OutputStreamWriter 를 사용해서 인코딩 변경을 해야 한다.
*  
*  	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("파일이름" 혹은 File),"인코딩"));
*  	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("파일이름" 혹은 File), "인코딩"));
*   
*  인코딩 : "UTF8", "euc-kr"
*   
*/

public class FileMain {

	private static final String FILE1 = "temp/PrintData.txt";
	private static final String FILE2 = "temp/PrintData_euckr.txt";

	static PrintWriter write = null;
	static BufferedReader br = null;

	public static void main(String[] args) {
		System.out.println("PrintWriter / 인코딩");

		try {

			// 쓰기
			// write = new PrintWriter(new BufferedWriter(new FileWriter(new File(FILE1))));
			write = new PrintWriter( //
					new BufferedWriter( //
							new OutputStreamWriter( //
									new FileOutputStream( //
											new File(FILE1)),
									"UTF-8")));

			testWrite(write);

			// 읽기
			br = new BufferedReader( //
					new InputStreamReader( //
							new FileInputStream( //
									new File(FILE1)),
							"UTF-8"));

			testRead(br);

			System.out.println("현재 OS인코딩" + System.getProperty("file.encoding"));

			System.out.println("euc-kr 인코딩 변경");
			close();

			// 쓰기
			write = new PrintWriter( //
					new BufferedWriter( //
							new OutputStreamWriter( //
									new FileOutputStream( //
											new File(FILE2)),
									"euc-kr")));
			testWrite(write);

			// 읽기 - 한글 깨짐 euc-kr의 인코딩을 utf-8 로 읽기
			br = new BufferedReader( //
					new InputStreamReader( //
							new FileInputStream( //
									new File(FILE2)),
							"UTF-8"));
			testRead(br);
			
			close();

			// 읽기
			br = new BufferedReader( //
					new InputStreamReader( //
							new FileInputStream( //
									new File(FILE2)),
							"euc-kr"));
			testRead(br);

		} catch (Exception e) {
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		System.out.println("\n프로그램 종료");
	}

	private static void testRead(BufferedReader br) {
		String line;
		StringBuffer result = new StringBuffer();

		try {
			while ((line = br.readLine()) != null) {
				result.append(line + "\n");
			}

			System.out.println(result.toString());

		} catch (IOException e) {
			e.printStackTrace();
			close();
		}

	}

	private static void testWrite(PrintWriter write) {
		write.println("안녕하세요 Java 한글은 잘 보이시나요?");
		write.print((2000 + 20) + " " + 3.14);
		write.println();
		write.printf("%d-%d-%d\n", 2020, 10, 13);
		write.flush();
	}

	static void close() {
		if (write != null)
			write.close();
		try {
			if (br != null)
				br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
