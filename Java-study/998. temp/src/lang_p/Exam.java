package lang_p;

import java.util.Arrays;
import java.util.Scanner;

interface Show {
	void show(StuInfo info);
}

class StuInfo {
//	[이름, 나이, 주소, 반, 아이디, 수업, 알바, 이메일]
	String name, age, address, ban, id, study, alba, email;

	public StuInfo(String name, String age, String address, String ban, String id, String study, String alba,
			String email) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.ban = ban;
		this.id = id;
		this.study = study;
		this.alba = alba;
		this.email = email;
	}

}

class GeneralStud extends StuInfo {

	public GeneralStud(String name, String age, String address, String ban, String id, String study, String alba,
			String email) {
		super(name, age, address, ban, id, study, alba, email);
		// TODO Auto-generated constructor stub
	}

}

class SpecialStud extends StuInfo {

	public SpecialStud(String name, String age, String address, String ban, String id, String study, String alba,
			String email) {
		super(name, age, address, ban, id, study, alba, email);
		// TODO Auto-generated constructor stub
	}

}

class StudBind { // 바인드 클래스

	String className;

	Class[] parameterType;

	String[] info;

	public StudBind(String className, Class[] parameterType, String[] info) {
		this.className = className;
		this.parameterType = parameterType;
		this.info = info;
	}

}

class Study implements Show { // 수업 클래스

	@Override
	public void show(StuInfo info) {
		System.out.println(info.ban + " 반 " + info.name + " 이 " + info.study + " 수업을 들어요.");

	}

}

class Alba implements Show { // 알바 클래스

	@Override
	public void show(StuInfo info) {
		if (info.alba.equalsIgnoreCase("null")) {
			System.out.println(info.ban + " 반 " + info.name + " 은 알바를 하지 않습니다.");
			return;
		}
		System.out.println(info.ban + " 반 " + info.name + " 이 " + info.alba + " 알바를 가요.");

	}
}

class Game2 implements Show { // 게임 클래스

	@Override
	public void show(StuInfo info) {
		System.out.println(info.ban + " 반 " + info.id + " 이  아이디: " + info.id + " 로 게임을 해요.");

	}
}

class Gohome implements Show { // 집 클래스

	@Override
	public void show(StuInfo info) {
		System.out.println(info.ban + " 반 " + info.name + " 이 " + info.address + " 의 집으로 가요.");

	}
}

class Help implements Show { // 도움말
	String[] cmd = { "Study", "Alba", "Game2", "GoHome" };

	@Override
	public void show(StuInfo info) {
		System.out.println("학생의 명령어 목록 : " + Arrays.toString(cmd) + "\n");

	}
}

class CmdChk {

	String check() {

		Scanner sc = new Scanner(System.in);

		System.out.println("명령어를 입력해주세요.");
		System.out.println("명령어를 모를시 help 를 입력해주세요.");
		System.out.print("입력 : ");
		String cmd = sc.nextLine();
		System.out.println();
		String changedCmd = cmdChange(cmd);

		return changedCmd;
	}

	String cmdChange(String Getcmd) {

		String[] cmd = { "Help", "Study", "Alba", "Game2", "Gohome" };

		String result;

		result = Getcmd.trim().substring(0, 1).toUpperCase() + Getcmd.trim().substring(1).toLowerCase();

		try {
			int cnt = 0;
			for (int i = 0; i < cmd.length; i++) {
				if (cmd[i].equals(result)) {
					cnt++;
					break;
				}

			}
			if (cnt == 0)
				throw new Exception();
		} catch (Exception e) {
			System.out.println("해당하는 명령어가 존재하지 않습니다.");
			result = check();
		}

		return result;
	}

}

public class Exam {

	static Class[] bind(int length) {

		Class result[] = new Class[length];

		for (int i = 0; i < result.length; i++) {
			result[i] = String.class;
		}

		return result;

	}

	public static void main(String[] args) throws Exception {

		String[] classType = { "lang_p.SpecialStud", "lang_p.GeneralStud" };

		String[][] info = { { "학생1", "20", "서울시 강남구", "일반", "아이디5", "국어", "Null", "이메일@naver.com" },
				{ "학생2", "21", "서울시 강동구", "특기생", "아이디4", "미술", "편의점", "이메일2@google.com" },
				{ "학생3", "22", "서울시 강서구", "일반", "아이디3", "사회", "백화점", "이메일3@hanmail.net" },
				{ "학생4", "23", "서울시 강북구", "특기생", "아이디2", "체육", "음식점", "이메일4@yahoo.com" },
				{ "학생5", "24", "서울시 중랑구", "일반", "아이디1", "영어", "카페", "이메일5@facebook.com" } };

		StudBind[] stuBind = new StudBind[info.length]; // bind 클래스 맴버변수 초기화

		Class type[] = new Class[info[0].length];

		type = bind(info[0].length);

		System.out.println(Arrays.toString(type));

		for (int i = 0; i < stuBind.length; i++) {
			if (info[i][3].equals("일반"))
				stuBind[i] = new StudBind(classType[1], type, info[i]);
			else
				stuBind[i] = new StudBind(classType[0], type, info[i]);

		}

		StuInfo[] students = {
				(StuInfo) Class.forName(stuBind[0].className).getConstructor(stuBind[0].parameterType)
						.newInstance("학생1", "20", "서울시 강남구", "일반", "아이디5", "국어", "Null", "이메일@naver.com"),
				(StuInfo) Class.forName(stuBind[1].className).getConstructor(stuBind[1].parameterType)
						.newInstance("학생2", "21", "서울시 강동구", "특기생", "아이디4", "미술", "편의점", "이메일2@google.com"),
				(StuInfo) Class.forName(stuBind[2].className).getConstructor(stuBind[2].parameterType)
						.newInstance("학생3", "22", "서울시 강서구", "일반", "아이디3", "사회", "백화점", "이메일3@hanmail.net"),
				(StuInfo) Class.forName(stuBind[3].className).getConstructor(stuBind[3].parameterType)
						.newInstance("학생4", "23", "서울시 강북구", "특기생", "아이디2", "체육", "음식점", "이메일4@yahoo.com"),
				(StuInfo) Class.forName(stuBind[4].className).getConstructor(stuBind[4].parameterType)
						.newInstance("학생5", "24", "서울시 중랑구", "일반", "아이디1", "영어", "카페", "이메일5@facebook.com") };

//		newInstance 를 배열로 받기위해서 배열의 최상위 부모 Object로 형변환 하면 인자로 받을 수 있으나, 아직까지 왜 그렇게 되는지 잘 모르겠습니다.

		// System.out.println(String[].class instanceof Class); // true
		// Class 는 true 인데 Class로 형변환을 하면 안된다? ㄷㄷ;
		// System.out.println(String[].class instanceof Object); // true

		CmdChk command = new CmdChk(); // 명령어 를 입력받음

		String cmd = command.check(); // 명령어를 체크함

		for (StuInfo stud : students) {
			((Show) Class.forName("lang_p." + cmd).newInstance()).show(stud);
			if (cmd.equalsIgnoreCase("help")) {
				cmd = command.check();
				((Show) Class.forName("lang_p." + cmd).newInstance()).show(stud);
			}
		}
	}
}