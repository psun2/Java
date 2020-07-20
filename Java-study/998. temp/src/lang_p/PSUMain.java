package lang_p;

import java.util.Arrays;

interface ShowEE {
	void show(StuInfoEE info);
}

class StuInfoEE {
//    [이름, 나이, 주소, 반, 아이디, 수업, 알바, 이메일]
	String name, age, addr, ban, id, cc, alba, email;

	public StuInfoEE(String... info) {
		name = info[0];
		age = info[1];
		addr = info[2];
		ban = info[3];
		id = info[4];
		cc = info[5];
		alba = info[6];
		email = info[7];

	}
}

class GeneralStudEE extends StuInfoEE {

	public GeneralStudEE(String... info) {
		super(info);
	}

	public GeneralStudEE(String name, String age, String addr, String ban, String id, String cc, String alba,
			String email) {
		super(name, age, addr, ban, id, cc, alba, email);
	}
}

class SpecialStudEE extends StuInfoEE {

	public SpecialStudEE(String... info) {
		super(info);
	}

	public SpecialStudEE(String name, String age, String addr, String ban, String id, String cc, String alba,
			String email) {
		super(new String[] { name, age, addr, ban, id, cc, alba, email });
	}

}

class StudBindEE { // 바인드 클래스

	String className;

	String[] info;

	Class parameterType;

	Class[] paramArr;

	public StudBindEE(String className, String[] info) {
		this.className = className;
		this.parameterType = String[].class;
		this.paramArr = new Class[] { String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class };
		this.info = info;
	}

}

class StudyEE implements ShowEE { // 수업 클래스

	@Override
	public void show(StuInfoEE info) {
		System.out.println(info.ban + " 반 " + info.name + " 이 " + info.cc + " 수업을 들어요.");

	}
}

class AlbaEE implements ShowEE { // 알바 클래스

	@Override
	public void show(StuInfoEE info) {
		if (info.alba.equalsIgnoreCase("null")) {
			System.out.println(info.ban + " 반 " + info.name + " 은 알바를 하지 않습니다.");
			return;
		}
		System.out.println(info.ban + " 반 " + info.name + " 이 " + info.alba + " 알바를 가요.");
	}
}

class GameEE implements ShowEE { // 게임 클래스

	@Override
	public void show(StuInfoEE info) {
		System.out.println(info.ban + " 반 " + info.name + " 이  아이디: " + info.id + " 로 게임을 해요.");

	}
}

class GohomeEE implements ShowEE { // 집 클래스

	@Override
	public void show(StuInfoEE info) {
		System.out.println(info.ban + " 반 " + info.name + " 이 " + info.addr + " 의 집으로 가요.");

	}
}

public class PSUMain {

	public static void main(String[] args) throws Exception {

		String[] classType = { "lang_p.SpecialStudEE", "lang_p.GeneralStudEE" };

		String[][] info = { { "학생1", "20", "서울시 강남구", "일반", "아이디5", "국어", "Null", "이메일@naver.com" },
				{ "학생2", "21", "서울시 강동구", "특기생", "아이디4", "미술", "편의점", "이메일2@google.com" },
				{ "학생3", "22", "서울시 강서구", "일반", "아이디3", "사회", "백화점", "이메일3@hanmail.net" },
				{ "학생4", "23", "서울시 강북구", "특기생", "아이디2", "체육", "음식점", "이메일4@yahoo.com" },
				{ "학생5", "24", "서울시 중랑구", "일반", "아이디1", "영어", "카페", "이메일5@facebook.com" } };

		StudBindEE[] stuBind = new StudBindEE[info.length]; // bind 클래스 맴버변수 초기화

		for (int i = 0; i < stuBind.length; i++) {
			if (info[i][3].equals("일반"))
				stuBind[i] = new StudBindEE(classType[1], info[i]);
			else
				stuBind[i] = new StudBindEE(classType[0], info[i]);

		}

		StuInfoEE[] students = new StuInfoEE[info.length];

//        for (int i = 0; i < students.length; i++) { // StuInfo 를 상속받은 클래스들을 bind 클래스의 정보로 인스턴스 생성
//            students[i] = (StuInfo) Class.forName(stuBind[i].className).getConstructor(stuBind[i].parameterType)
//                    .newInstance((Object) stuBind[i].info);
//        }

		for (int i = 0; i < students.length; i++) { // StuInfo 를 상속받은 클래스들을 bind 클래스의 정보로 인스턴스 생성
			students[i] = (StuInfoEE) Class.forName(stuBind[i].className).getConstructor(stuBind[i].paramArr)
					.newInstance(stuBind[i].info);
		}

		// newInstance() 시 배열로 받고 싶어서 배열을 인자로 줄때 계속 에러가 발생했는데
// (Object) 로 형변환을 하니 에러가 사라졌습니다 
// 그 이유를 알고 싶습니다.

		for (String cmd : new String[] { "StudyEE", "AlbaEE", "GameEE", "GohomeEE" }) {
			System.out.println(cmd + " >>>>>>>>>>>>>>");
			for (StuInfoEE stud : students) {
				((ShowEE) Class.forName("lang_p." + cmd).newInstance()).show(stud);

			}
		}
	}
}
