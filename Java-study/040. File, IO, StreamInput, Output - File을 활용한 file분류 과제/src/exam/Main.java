package exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

class FileGroup {

//	String file;
	File file;
	File[] fileList;
	LinkedHashMap<String, ArrayList<String>> extension; // 확장자들을 모아놓음
	int overlapCnt; // 같은 폴더에 같은 이름이 몇번 있나 확인
	String directoryName; // 폴더명

	public FileGroup(String fileName) {

		init(fileName);
		getFiles();

	}

	void init(String fileName) {

		this.file = new File(fileName);

		mapInit();

	}

	void mapInit() {

		this.extension = new LinkedHashMap<String, ArrayList<String>>();

		String[] key = { "이미지", "음악", "문서", "가사" };
		String[][] extensionArr = { { "bmp", "jpg", "gif", "png" }, { "mp3", "wma", "wav" },
				{ "doc", "hwp", "ppt", "xls", "pptx", "xlsx", "docx" }, { "txt" } };

		for (int i = 0; i < key.length; i++) {

			this.extension.put(key[i], array(extensionArr[i]));

		}

	}

	ArrayList<String> array(String[] arr) {

		ArrayList<String> array = new ArrayList<String>();

		for (String extension : arr) {
			array.add(extension);
		}

		return array;

	}

	void getFiles() { // 1. 가져온 경로의 파일을 배열 형태로 만든다

		this.fileList = this.file.listFiles();

		question();

	}

	void question() { // 2. 파일 배열을 돌면서 파일인지 폴더인지 구분

		for (File file : fileList) {

			if (file.isFile()) { // 파일일때
				fileSeparation(file); // 파일명을 인자로 전달
			} else { // 폴더일때
				directorySeparation(file);
			}

		}

		deleteOriginal(this.fileList);
		System.out.println("파일 분류 종료");

	}

	void fileSeparation(File file) { // 3. 파일이라면 확장자를 확인하여 해당 폴더에 집어 넣습니다.

		String fileExtension = fileExtension(file.getName()); // 확장자 확인

		this.directoryName = directoryName(fileExtension, file); // 확장자가 어떤 폴더에 해당하는지 Chk

		// 해당 파일에 맞는 폴더명이 존재 하나 확인하고 존재 하지 않는다면 폴더 생성
		createDirectory(file);

		// 파일 이동
		moveFile(file);

	}

	String fileExtension(String fileName) { // 3-1. 해당 폴더에 집어 넣기 위하여 확장자 확인

		int index = fileName.lastIndexOf(".");
		String extension = fileName.substring(index + 1);
		// System.out.println(extension); // 확장자 확인 ok

		return extension;

	}

	String directoryName(String fileExtension, File file) { // 3-2. 폴더 생성을 위한 폴더 네임 반환

		String directoryName = "기타";

		for (Entry<String, ArrayList<String>> map : this.extension.entrySet()) {

			for (String extension : map.getValue()) {

				// System.out.println(fileExtension); // 확인 ok
				// System.out.println(extension); // 확인 ok

				if (fileExtension.equalsIgnoreCase(extension)) { // 대소문자 구문없이 맞나 확인
					directoryName = map.getKey(); // 폴더 이름은 맵의 key로 합니다.

					break;
				}

			}

		}

		return directoryName;

	}

	void createDirectory(File file) { // 3-3. 폴더 생성

		File[] upDateFile = this.file.listFiles();
		boolean chk = true;

		for (File f : upDateFile) {

			if (f.isDirectory()) { // 파일이 폴더 일때

				if (f.getName().equals(directoryName)) { // 한번이라도 맞으면 해당폴더가 존재 한다. false => 폴더생성 X
					chk = false;
					break;

				}
			}

		}

		if (chk)
			new File(this.directoryName).mkdir();

		if (this.directoryName.equals("가사")) {
			this.directoryName = genre(file);
			new File(this.directoryName).mkdir();
		}

	}

	void moveFile(File file) { // 파일 이동

		String fileName = file.getName();

		// 해당 폴더에 같은 이름을 가진 파일 여부를 확인
		if (chkFileName(fileName))
			fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_overlap_" + overlapCnt
					+ fileName.substring(fileName.lastIndexOf(".")); // overlapCnt = 몇번이 중복되었나 확인

		String path = directoryName + "/" + fileName;

		file.renameTo(new File(path)); // ✔✔폴더가 생성이 되어 있어야 들어감

		// System.out.println(path);

	}

	boolean chkFileName(String fileName) { // 파일을 이동하기 전 해당 폴더에 같은 이름을 가진 파일 여부를 확인

		boolean chk = false;

		File[] file = new File(directoryName).listFiles(); // 파일 업데이트

		// System.out.println(Arrays.toString(file)); // 확인 ok

		// 중복확인
		overlapCnt = 0;
		for (File f : file) {

			String getName = f.getName();
			int getIndex = getName.lastIndexOf(".");
			int fileIndex = fileName.lastIndexOf(".");

			if (getName.substring(0, getIndex).contains(fileName.substring(0, fileIndex))) // 이름이 같고
				if (getName.substring(getIndex + 1).equalsIgnoreCase(fileName.substring(fileIndex + 1))) // 확장자가 같다면
					overlapCnt++; // 이름에 붙일 숫자 를 올려 줍니다.

		}

		if (overlapCnt != 0)
			chk = true;

		return chk;

	}

	void directorySeparation(File file) {

		File[] newFile = file.listFiles(); // 폴더일때 폴더를 받아와 배열로 다시 쪼갭니다.

		for (File f : newFile) { // 쪼개진 파일을 돌아요

			// 폴더 안에 폴더가 있을 수 있으므로 또 돌아요
			if (f.isFile()) { // 파일일때
				fileSeparation(f); // 파일명을 인자로 전달
			} else { // 폴더일때
				directorySeparation(f); // 재귀적 구성
			}

		}

	}

	String genre(File file) { // 가사는 문자열이므로, 강려크한 기능이 있는 BufferedReader 사용 (문자열 기반 만 사용 가능)

		String newDirectory = null;

		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			int i = 0;
			while ((newDirectory = br.readLine()) != null) {

				if (i == 3) {
					// System.out.println(i + " : " + newDirectory); // 확인 ok
					break;
				}

				i++;

			}

			br.close();
			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = this.directoryName + "/" + newDirectory;

		return result;

	}

	void deleteOriginal(File[] fileList) {

		// System.out.println(Arrays.toString(this.fileList));
		// [exam_zip\ggg, exam_zip\song, exam_zip\따오기]

		// tip : 내부에 다른 폴더가 있으면 삭제 되지 않습니다.

		for (File file : fileList) {

			if (file.length() != 0) { // 파일의 크기가 0이 아니다. 즉 폴더 내부에 다른 폴더 가 존재
				deleteOriginal(file.listFiles()); // 다시 파일 배열을 만들어 재귀 호출
			}

			file.delete(); // 내부 폴더 삭제
			// System.out.println(file.length());
		}

		this.file.delete(); // 마지막으로 외부 폴더 삭제

	}

}

public class Main {

	public static void main(String[] args) {

		// 지정한 폴더의 파일을 각각 종류별로 지정 폴더에 저장하시오

		/// 하위 폴더까지 검색하세요

		/// 같은 파일명인 경우 중복 처리 하세요

		// 이미지 : bmp, jpg, gif, png

		// 음악 : mp3, wma, wav

		// 문서 :doc, hwp, ppt, xls, pptx, xlsx. docx

		// 가사 :txt

		// 기타 : 위의 분류 이외

		// 확장자의 대소문자 구분하지 않음

///////////////////////////////////////////////////////////////////////

		// 음악파일을 장르별로 폴더에 저장하세요

		// 같은 파일명인 경우 중복 처리 하세요

		// 하위 폴더까지 검색하세요

		// 저장시 - 장르 폴더 - 국내, 국외 별로 구분하여 저장하세요

		// 파일 정보

//		    1- 노래제목
//
//		    2- 장르
//
//		    3- 가수
//
//		    4- 국적
//
//		    5- 가사

		FileGroup file = new FileGroup("exam_zip");

	}

}
