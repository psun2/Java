package file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileMain {

	static void fileInspect(File[] files) {

		System.out.println(Arrays.toString(files));

		for (File item : files) {
			if (item.isFile())
				item.delete();

			else {
				fileInspect(item.listFiles());
				if (item.length() == 0)
					item.delete();
			}
		}

//			file.isFile() ? file.delete() : fileInspect(file);
		// 삼항 연산자가 안되네 ??;;;;;;; 자바도 되는데 내가 쓸줄 모르는 거야 ....
		// 저 반환값을 담아야 하는데 무엇으로 담는지를 모르겟네... boolean 도 아니고 File 도 아니고...

	}

	public static void main(String[] args) {

		// 주석 처리 하고 지정된 불럭씩 실행하면서 확인해보시기 바랍니다.

		try {

			// 초기 설정 .... 전 실행 들을 지위기 위한 코드....

			File init = new File("Outer");
			System.out.println(init.exists()); // true
			System.out.println(init.isDirectory()); // true
			System.out.println(init.isFile()); // false
			if (init.exists()) {
				fileInspect(init.listFiles());
				init.delete();
			}
			System.out.println("--------------------------수행완료--------------------------\n");

//--------------------------------------------------------------------------------------------

			File directory = new File("Outer/inner");
			directory.mkdirs();
			System.out.println("--------------------------수행완료--------------------------\n");

//--------------------------------------------------------------------------------------------

			File text = new File("Outer/inner/test.txt");
			text.createNewFile();
			System.out.println("--------------------------수행완료--------------------------\n");

			String fileName = text.getName();

			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			System.out.println(ext);

			fileName = fileName.substring(0, fileName.lastIndexOf(".")) + " 이동 완료." + ext;
			System.out.println(fileName);

			File outer = new File("Outer");
			String savePath = outer.getPath();

			System.out.println(savePath); // Outer

			text.renameTo(new File(savePath, fileName));
			System.out.println("--------------------------수행완료--------------------------\n");

//--------------------------------------------------------------------------------------------

//			File del = new File("Outer/inner/test.txt");

			File del = new File("Outer/test 이동 완료.txt");

			if (del.exists()) {
				del.delete();
			}
			System.out.println("--------------------------수행완료--------------------------\n");

//--------------------------------------------------------------------------------------------

			if (outer.exists()) {
				File[] list = outer.listFiles();
				System.out.println(Arrays.toString(list));

				System.out.println();

				System.out.println(outer.getParent()); // null
				System.out.println(outer.getPath()); // Outer
				System.out.println(outer.getName()); // Outer

				for (File item : list) {

					if (item.isDirectory()) {
						System.out.println("나의 상위 폴더는 : " + item.getParent() + ", 파일경로 : " + item.getPath() + ", "
								+ item.getName() + " 나는 폴더");
					} else {
						System.out.println("나의 상위 폴더는 : " + item.getParent() + ", 파일경로 : " + item.getPath() + ", "
								+ item.getName() + " 나는 파일");
					}

				}
			}
			System.out.println("--------------------------수행완료--------------------------\n");

//--------------------------------------------------------------------------------------------

			if (init.exists()) {
				fileInspect(init.listFiles());
				init.delete();
			}
			System.out.println("--------------------------수행완료--------------------------\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
