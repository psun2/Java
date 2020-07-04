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

	public FileGroup(String file) {

//		this.file = file;

		this.extension = extension();
		getFiles(file);

	}

	void getFiles(String file) { // 1. 가져온 경로의 파일을 배열 형태로 만든다

		this.file = new File(file);

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

	}

	void fileSeparation(File file) { // 3. 파일이라면 확장자를 확인하여 해당 폴더에 집어 넣습니다.

		String fileExtension = fileExtension(file.getName()); // 확장자 확인

		String directoryName = directoryName(fileExtension, file); // 확장자가 어떤 폴더에 해당하는지 Chk

		// 해당 파일에 맞는 폴더명이 존재 하나 확인하고 존재 하지 않는다면 폴더 생성

		createDirectory(directoryName, file);

		// 파일 이동
		moveFile(file, directoryName); 여기 서 현재 폴더명 업데이트가 안되서 계속 오류 나는거 엿음

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

	void createDirectory(String directoryName, File file) { // 3-3. 폴더 생성

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
			new File(directoryName).mkdir();

//		if(directoryName.equals("가사"))
//			new File(directoryName + "/" + genre(file)).mkdir();

	}

	void moveFile(File file, String directoryName) { // 파일 이동

		String fileName = file.getName();

		// 해당 폴더에 같은 이름을 가진 파일 여부를 확인
		if (chkFileName(directoryName, fileName))
			fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_overlap_" + overlapCnt
					+ fileName.substring(fileName.lastIndexOf(".")); // overlapCnt = 몇번이 중복되었나 확인

		String path = directoryName + "\\" + fileName;

		file.renameTo(new File(path)); // ✔✔폴더가 생성이 되어 있어야 들어감

		// System.out.println(path);

	}

	boolean chkFileName(String directoryName, String fileName) { // 파일을 이동하기 전 해당 폴더에 같은 이름을 가진 파일 여부를 확인

		boolean chk = false;

		File[] file = new File(directoryName).listFiles(); // 파일 업데이트

		System.out.println(Arrays.toString(file));

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

//	String genre(File file, String directoryName) { // 가사는 문자열이므로, 강려크한 기능이 있는 BufferedReader 사용 (문자열 기반 만 사용 가능)
	String genre(File file) { // 가사는 문자열이므로, 강려크한 기능이 있는 BufferedReader 사용 (문자열 기반 만 사용 가능)

//		System.out.println(file);
//		System.out.println(directoryName);
//		System.out.println(file.getName());
//		System.out.println(file);

		String newDirectory = null;
//		File gasaFile = new File(directoryName);

//		System.out.println("여기 : " + gasaFile.getName());

		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			int i = 0;
			while ((newDirectory = br.readLine()) != null) {

				if (i == 3) {
					System.out.println(i + " : " + newDirectory);
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

//		createDirectory(newDirectory, gasaFile); // 해당 폴더를 만듭니다.

		return newDirectory;

	}

//	boolean chkGasa(String fileExtension) {
//
//		for (Entry<String, ArrayList<String>> file : this.extension.entrySet()) {
//
//			for (String extension : file.getValue()) {
//
//				if (extension.equalsIgnoreCase(fileExtension)) { // 대소문자 구문없이 맞나 확인
//
//					if (file.getKey().equals("가사")) {
//
//						break;
//					}
//				}
//
//			}
//
//		}
//
//	}

	LinkedHashMap<String, ArrayList<String>> extension() { // init map

		LinkedHashMap<String, ArrayList<String>> extension = new LinkedHashMap<String, ArrayList<String>>();

		extension.put("이미지", imgAdd());
		extension.put("음악", musicAdd());
		extension.put("문서", documentAdd());
		extension.put("가사", lyricsAdd());

		return extension;

	}

	ArrayList<String> imgAdd() { // init ArrayList

		ArrayList<String> arr = new ArrayList<String>();
		String list = "bmp, jpg, gif, png";

		for (String l : list.split(",")) {
			arr.add(l.trim());
		}

		return arr;

	}

	ArrayList<String> musicAdd() { // init ArrayList

		ArrayList<String> arr = new ArrayList<String>();
		String list = "mp3, wma, wav";

		for (String l : list.split(",")) {
			arr.add(l.trim());
		}

		return arr;

	}

	ArrayList<String> documentAdd() { // init ArrayList

		ArrayList<String> arr = new ArrayList<String>();
		String list = "doc, hwp, ppt, xls, pptx, xlsx. docx";

		for (String l : list.split(",")) {
			arr.add(l.trim());
		}

		return arr;

	}

	ArrayList<String> lyricsAdd() { // init ArrayList

		ArrayList<String> arr = new ArrayList<String>();
		String list = "txt";

		for (String l : list.split(",")) {
			arr.add(l.trim());
		}

		return arr;

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

//		File file = new File("exam_zip");
//		File[] lists = file.listFiles();

		// 확장자 따오기
//		for (File file2 : lists) {
//
//			System.out.println(file2.isFile());
//
//			if (file2.isFile()) { // 파일 일때
//
//			} else { // 폴더 일때
//
//			}

//			String name = file2.getName();
//			int index = name.indexOf(".");
//			System.out.println(name.substring(index + 1));

//		}

	}

}
