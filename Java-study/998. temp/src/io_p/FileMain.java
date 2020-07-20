package io_p;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import test_FileWriter.fileMain;

public class FileMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File ff = new File("fff/asdasda.txt");

		System.out.println(ff.exists()); // false - 파일 존재 여부

		File ff2 = new File("C:\\Users\\admin\\Desktop\\Korea-IT-Academy\\study_b\\testWork\\workProj\\fff\\aaa.txt");

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		System.out.println(ff2.exists()); // true - 파일 존재 여부
		System.out.println(ff2.isFile()); // 파일이니?
		System.out.println(ff2.isDirectory()); // 폴더니?
		System.out.println(ff2.isDirectory()); // 폴더니?
		System.out.println(ff.isAbsolute()); // 절대 경로 이니?
		System.out.println(ff2.isAbsolute()); // 절대 경로 이니?
		System.out.println(ff2.isHidden()); // 숨김파일이니 ?
		System.out.println(ff2.getParent()); // 부모가 누구니?
		System.out.println(ff.getAbsolutePath()); // 절대 경로를 찾아줌
		System.out.println(ff2.getName()); // 이름
		System.out.println(ff2.getPath()); // 경로 를 반환하니 경로에 있는 모든 것들이 나옵니다.
		System.out.println("실행유무:" + ff2.canExecute());
		System.out.println("읽기유무:" + ff2.canRead());
		System.out.println(ff2.canWrite()); // 쓸수 있어 ? 읽기전용?
		System.out.println(ff2.length()); // 파일의 크기
		System.out.println(sdf.format(ff2.lastModified())); // 마지막 수정

		System.out.println("----------------------------------------------");

		ff2 = new File("src");
		File[] arr = ff2.listFiles();

		for (File file : arr) {

//			System.out.println(file.getParent());
			System.out.println(file.getPath());
			// System.out.println(ff.getAbsolutePath());

		}

		// 삭제
		new File("fff/ddd.txt").delete(); // 삭제

		// 이동
		new File("fff/ccc.txt").renameTo(new File("dst/rrr.txt"));
		new File("dst/rrr.txt").renameTo(new File("fff/ccc.txt"));

		// project 단 디렉토리 생성
		new File("zzz").mkdir();

		// project 단 하위 디렉토리 생성
		new File("xxx/nnn").mkdirs();

		//
		try {
//			new File("aaa/bbb/erty.txt").createNewFile(); // 경로 생성 안됨
			new File("xxx/nnn/erty.txt").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
