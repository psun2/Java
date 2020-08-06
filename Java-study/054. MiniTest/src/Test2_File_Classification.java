import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

// 스캐너로 파일명을 입력 받아 파일을 분류 하시오.
//  "doc", "txt", "hwp" => 문서파일
// "jpg" => 기타파일

class Inspect {

	private static Inspect inspect;
	String file, extension, fileName, kind;
	HashMap<String, ArrayList<String>> map;

	private Inspect() {
		// TODO Auto-generated constructor stub
		init();
	}

	void init() {

		this.map = new HashMap<String, ArrayList<String>>();

		ArrayList<String> document = new ArrayList<String>();

		for (String doc : "doc,txt,hwp".split(",")) {
			document.add(doc);
		}

		map.put("문서파일", document);

		ArrayList<String> ect = new ArrayList<String>();
		ect.add("jpg");

		map.put("기타파일", ect);

	}

	public static Inspect getInstance() {

		if (inspect == null)
			inspect = new Inspect();

		return inspect;

	}

	void chk(String fileName) {

		this.fileName = fileName.toLowerCase();
		int index = this.fileName.lastIndexOf(".");
		this.file = this.fileName.substring(0, index);
		this.extension = this.fileName.substring(index + 1);

		kind();
		System.out.println(toString());
		clear();

	}

	void kind() {

		for (Entry<String, ArrayList<String>> en : map.entrySet()) {

			for (String ext : en.getValue()) {

				if (this.extension.equals(ext))
					this.kind = en.getKey();

			}

		}

	}

	@Override
	public String toString() {
		return "Inspect [kind=" + kind + ", fileName=" + fileName + ", file=" + file + ", extension=" + extension + "]";
	}

	void clear() {
		this.extension = null;
		this.file = null;
		this.kind = null;
		this.fileName = null;
	}

}

public class Test2_File_Classification {

	public static void main(String[] args) {

		Inspect inspect = Inspect.getInstance();

		while (true) {
			System.out.println("종료 : 0");
			System.out.print("파일 : ");
			Scanner sc = new Scanner(System.in);
			String fileName = sc.nextLine();
			if (fileName.equals("0"))
				break;
			inspect.chk(fileName);
			System.out.println();
		}
	}

}
