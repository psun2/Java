package exam;

import java.util.Arrays;
import java.util.Scanner;

interface Show {
	void show(StuInfo info);
}

class StuInfo {
//	[이름, 나이, 주소, 반, 아이디, 수업, 알바, 이메일]
	private String[] info;

	public StuInfo(String[] info) {
		this.info = info;
	}

	public String[] getInfo() {
		String[] CloneArr = this.info.clone();
		return CloneArr;
	}

	@Override
	public String toString() {
		return "StuInfo2 [info=" + Arrays.toString(info) + "]";
	}
}

class GeneralStud extends StuInfo {

	public GeneralStud(String[] info) {
		super(info);
	}

}

class SpecialStud extends StuInfo {

	public SpecialStud(String[] info) {
		super(info);
	}

}

class StudBind { // 바인드 클래스

	String className;

	Class parameterType;

	String[] info;

	public StudBind(String className, String[] info) {
		this.className = className;
		this.parameterType = String[].class;
		this.info = info;
	}

}

class Study implements Show { // 수업 클래스

	@Override
	public void show(StuInfo info) {
		System.out.println(info.getInfo()[3] + " 반 " + info.getInfo()[0] + " 이 " + info.getInfo()[5] + " 수업을 들어요.");

	}

}

class Alba implements Show { // 알바 클래스

	@Override
	public void show(StuInfo info) {
		if (info.getInfo()[6].equalsIgnoreCase("null")) {
			System.out.println(info.getInfo()[3] + " 반 " + info.getInfo()[0] + " 은 알바를 하지 않습니다.");
			return;
		}
		System.out.println(info.getInfo()[3] + " 반 " + info.getInfo()[0] + " 이 " + info.getInfo()[6] + " 알바를 가요.");

	}
}

class Game implements Show { // 게임 클래스

	@Override
	public void show(StuInfo info) {
		System.out.println(
				info.getInfo()[3] + " 반 " + info.getInfo()[0] + " 이  아이디: " + info.getInfo()[4] + " 로 게임을 해요.");

	}
}

class Gohome implements Show { // 집 클래스

	@Override
	public void show(StuInfo info) {
		System.out.println(info.getInfo()[3] + " 반 " + info.getInfo()[0] + " 이 " + info.getInfo()[2] + " 의 집으로 가요.");

	}
}

class Help implements Show { // 도움말
	String[] cmd = { "Study", "Alba", "Game", "GoHome" };

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

		String[] cmd = { "Help", "Study", "Alba", "Game", "Gohome" };

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

public class Main {

	public static void main(String[] args) throws Exception {

		String[] classType = { "exam.SpecialStud", "exam.GeneralStud" };

		String[][] info = { { "학생1", "20", "서울시 강남구", "일반", "아이디5", "국어", "Null", "이메일@naver.com" },
				{ "학생2", "21", "서울시 강동구", "특기생", "아이디4", "미술", "편의점", "이메일2@google.com" },
				{ "학생3", "22", "서울시 강서구", "일반", "아이디3", "사회", "백화점", "이메일3@hanmail.net" },
				{ "학생4", "23", "서울시 강북구", "특기생", "아이디2", "체육", "음식점", "이메일4@yahoo.com" },
				{ "학생5", "24", "서울시 중랑구", "일반", "아이디1", "영어", "카페", "이메일5@facebook.com" } };

		StudBind[] stuBind = new StudBind[info.length]; // bind 클래스 맴버변수 초기화

		for (int i = 0; i < stuBind.length; i++) {
			if (info[i][3].equals("일반"))
				stuBind[i] = new StudBind(classType[1], info[i]);
			else
				stuBind[i] = new StudBind(classType[0], info[i]);

		}

		StuInfo[] students = new StuInfo[info.length];

		for (int i = 0; i < students.length; i++) { // StuInfo 를 상속받은 클래스들을 bind 클래스의 정보로 인스턴스 생성
			students[i] = (StuInfo) Class.forName(stuBind[i].className).getConstructor(stuBind[i].parameterType)
					.newInstance((Object) stuBind[i].info);
				     // .newInstance((Class) stuBind[i].info); // Error
		}

//		newInstance 를 배열로 받기위해서 배열의 최상위 부모 Object로 형변환 하면 인자로 받을 수 있으나, 아직까지 왜 그렇게 되는지 잘 모르겠습니다.

		 // System.out.println(String[].class instanceof Class); // true
    		 // Class 는 true  인데 Class로 형변환을 하면 안된다? ㄷㄷ;
    		 // System.out.println(String[].class instanceof Object); // true
		
		CmdChk command = new CmdChk(); // 명령어 를 입력받음

		String cmd = command.check(); // 명령어를 체크함

		for (StuInfo stud : students) {
			((Show) Class.forName("exam." + cmd).newInstance()).show(stud);
			if (cmd.equalsIgnoreCase("help")) {
				cmd = command.check();
				((Show) Class.forName("exam." + cmd).newInstance()).show(stud);
			}
		}

//        for (int i = 0; i < students.length; i++) { // 각자의 인스턴스 생성 확인 완료
//            System.out.println(students[i].toString());
//            System.out.println(students[i] instanceof SpecialStud); // 생성된 인스턴스 체크
//            System.out.println(students[i] instanceof GeneralStud); // 생성된 인스턴스 체크
//        }

//        명령어를 입력해주세요.
//        명령어를 모를시 help 를 입력해주세요.
//        입력 : help
//
//        학생의 명령어 목록 : [Study, Alba, Game, Gohome]
//
//        명령어를 입력해주세요.
//        명령어를 모를시 help 를 입력해주세요.
//        입력 : study
//
//        일반 반 학생1 이 국어 수업을 들어요.
//        특기생 반 학생2 이 미술 수업을 들어요.
//        일반 반 학생3 이 사회 수업을 들어요.
//        특기생 반 학생4 이 체육 수업을 들어요.
//        일반 반 학생5 이 영어 수업을 들어요.

//        명령어를 입력해주세요.
//        명령어를 모를시 help 를 입력해주세요.
//        입력 :             ALBA
//
//        일반 반 학생1 은 알바를 하지 않습니다.
//        특기생 반 학생2 이 편의점 알바를 가요.
//        일반 반 학생3 이 백화점 알바를 가요.
//        특기생 반 학생4 이 음식점 알바를 가요.
//        일반 반 학생5 이 카페 알바를 가요.

//        명령어를 입력해주세요.
//        명령어를 모를시 help 를 입력해주세요.
//        입력 : gohome
//
//        일반 반 학생1 이 서울시 강남구 의 집으로 가요.
//        특기생 반 학생2 이 서울시 강동구 의 집으로 가요.
//        일반 반 학생3 이 서울시 강서구 의 집으로 가요.
//        특기생 반 학생4 이 서울시 강북구 의 집으로 가요.
//        일반 반 학생5 이 서울시 중랑구 의 집으로 가요.

//        명령어를 입력해주세요.
//        명령어를 모를시 help 를 입력해주세요.
//        입력 : game
//
//        일반 반 학생1 이 아이디: 아이디5 로 게임을 해요.
//        특기생 반 학생2 이 아이디: 아이디4 로 게임을 해요.
//        일반 반 학생3 이 아이디: 아이디3 로 게임을 해요.
//        특기생 반 학생4 이 아이디: 아이디2 로 게임을 해요.
//        일반 반 학생5 이 아이디: 아이디1 로 게임을 해요.

	}

}
