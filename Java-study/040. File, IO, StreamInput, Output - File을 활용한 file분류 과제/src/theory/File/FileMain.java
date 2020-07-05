package theory.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import theory.FileStream.FileOutputStreamMain;

public class FileMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		File ff = new File("예제폴더/exam1.txt");
		File ff = new File("./예제폴더");

		System.out.println("존재유무(boolean) : " + ff.exists());
		System.out.println("파일인지(boolean) : " + ff.isFile());
		System.out.println("폴더인지(boolean) : " + ff.isDirectory());
		System.out.println("절대경로인지(boolean) : " + ff.isAbsolute());
		System.out.println("숨김파일인지(boolean) : " + ff.isHidden());

		System.out.println("부모폴더(String) : " + ff.getParent());
		System.out.println(ff.getParent() instanceof String);
		System.out.println("파일명(String) : " + ff.getName());
		System.out.println(ff.getName() instanceof String);

		System.out.println("절대경로(String) : " + ff.getAbsolutePath());
		System.out.println(ff.getAbsolutePath() instanceof String);

		System.out.println("경로(String) : " + ff.getPath());
		System.out.println(ff.getPath() instanceof String);

		System.out.println("실행유무(boolean) : " + ff.canExecute());
		System.out.println("읽기유무(boolean) : " + ff.canRead());
		System.out.println("쓰기유무(boolean) : " + ff.canWrite());

		System.out.println("파일크기 : " + ff.length()); // byte 기준 적어도 1byte는 잡혀야 합니다...

		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss:mm");
		System.out.println("마지막수정(Date) : " + sdf.format(new Date(ff.lastModified())));

		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println();

		System.out.println("Delete");
		try {

			FileOutputStream fos = new FileOutputStream("./예제폴더/deleteFileTest.txt");

			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File deleteFile = new File("./예제폴더/deleteFileTest.txt");

		deleteFile.delete();

		System.out.println();
		System.out.println("피일생성");
		try {

			File newFile = new File("./예제폴더/CreateFileTest.txt");
			// File newFile = new File("./없는경로/CreateFileTest.txt");
			// 경로가 없을시 Error : 지정된 경로를 찾을 수 없습니다
			newFile.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("폴더생성");
		File newDirectory = new File("새로운 폴더 생성");
		newDirectory.mkdir();

		try {

			newDirectory = new File("./새로운 폴더 생성/test.txt");
			newDirectory.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("다중 폴더 생성");
		File newMultiDirectory = new File("./다중폴더(outer)/다중폴더(inner)");
		newMultiDirectory.mkdirs();

		try {

			// 문자열만 사용 가능한 2바이트 기반의 BufferedWriter 사용
			FileWriter fw = new FileWriter("./다중폴더(outer)/다중폴더(inner)/한줄씩추가.txt");
			BufferedWriter bw = new BufferedWriter(fw);

			String[] str = { "한줄씩", "쓰기", "테스트", "중", "입니다." };

			for (String string : str) {
				bw.write(string + "\n");
			}

			bw.close();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("File들을 배열화 => listFiles");
		System.out.println();

		File file = new File("./예제폴더");
		File[] fileTree = file.listFiles();
		System.out.println(Arrays.toString(fileTree));
		System.out.println();

		for (File file2 : fileTree) {
			System.out.println(file2.getName());
		}
	}

}
