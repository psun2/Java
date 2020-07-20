package thread_p;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.swing.JOptionPane;

class ThQuest {
	String qq, answer, input;
	// 질문, 답변, 내가 쓴 답변
	int res = 0; // 답변 chk 맞을시 1
	ThExam exam; // 모든정보를 가지고 있음....

	public ThQuest(ThExam exam, String qq, String answer) {
		super();
		this.qq = qq;
		this.answer = answer;
		this.exam = exam;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // 쓰레드를 clone 해서 보내주어야함... 아니면 국영수 한번 만 돌고 쓰레드가 죽음
		// TODO Auto-generated method stub
		return new ThQuest(exam, qq, answer);
	}

	void go() { // 문제를 내는 메소드 114Line

		while (true) { // 못맞출시 못넘어감

			if (exam.now) { // ✔ exam.now 가 true 일때 문제 를 냄
				// ✔ 200Line => map.get("국어").now = true; // 국어부터 시작 // 학생에서 시작

				String buf = JOptionPane.showInputDialog(exam.stud.name + " " + qq); // 문제 출제
				examChange(buf); // 만약 change 를 한다면...
				if (exam.end) // end 가 true 로 바뀌었을때의 시점 : 한과목이 다 끝났을때....
					return;

				input = buf; // return 으로 막아줘서 상관은 없지만.... 2중 안전장치 쯤으로...? 생각

				if (input.equals("p")) // p 이면 다음 으로 return
					return;

				if (input.equals(answer)) { // 정답이면 1이고 리턴
					res = 1;
					return;
				}
			}
			try {
				Thread.sleep(1); // while 문이 너무 빨리 돌아서 .......... Error발생
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void examChange(String buf) {
		if (exam.stud.map.containsKey(buf)) { // map에서 해당 키가 존재 한다면
			exam.now = false; // 현재의 exam.now = false;
			exam.stud.map.get(buf).now = true; // 해당 키값의 now 를 true로 바꾸어주어 실행 될수 있도록 합니다.
		}
	}

	@Override
	public String toString() { // ✔ 130 Line 에서 여길 봄
		return qq + ", " + answer + ":" + input;
	}

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

class ThExam extends Thread {

	boolean now = false, end = false;
	ArrayList<ThQuest> arr; // 문제 집합....
	int cnt, jum;
	ThStud stud;

	public ThExam(String name) {
		super(name);
		arr = new ArrayList<ThQuest>();

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		ThExam res = new ThExam(getName());

		for (ThQuest thQuest : arr) {
			ThQuest tq = (ThQuest) thQuest.clone();
			tq.exam = res;
			res.arr.add(tq);
		}

		return res;
	}

	void add(String qq, String answer) {
		arr.add(new ThQuest(this, qq, answer));
	}

	@Override
	public void run() {

		new Timer().start(); // 타이머 ㄱㄱ

		for (ThQuest thQuest : arr) { // 문제집을 다 돌아야지 ...

			thQuest.go(); // 여기서 29Line 의 go 를 호출
			if (end) // 타이머가 다되면 ture 로 바뀌면서 break; 나머지 문제 ㅃ2
				break;
		}

		end = true; // 시간보다 문제를 더 빨리 풀어도 시간 stop

		System.out.println(getName() + " 시험종료 -------");

		stud.turnExam(); // 시험 종료후 ThStud 클래스의 turnExam을 봄 ✔(213 Line)
	}

	void myPPP() { // 결과 출력 // ✔ 204Line 에서 여길 호출
		System.out.println(stud.name + " " + getName() + " >>>>>>");
		cnt = 0;
		for (ThQuest th : arr) { // 한과목당 문제집을 돌면서.. 즉 국어일때 국어에 관련된 문제들을 돌면서...
			System.out.println(th); // 안쪽에 있는 ThQuest 클래스를 출력 => 문제, 답 : input // 즉 한과목을 시험본 결과를 출력
			cnt += th.res; // 답을 맞춰 cnt 가 있다면 계속 더해주어 총 맞춘갯수를 샘...
		}
		jum = 100 * cnt / arr.size(); // 100점으로 환산했을시... 문제의 맞춘 갯수를 곱하고, 문제의 갯수로 나누면 100분위로 환산
		System.out.println("정답갯수:" + cnt + "," + jum); // 정답갯수:5,100
	}

	class Timer extends Thread { // 타이머
		int i = 5; // 5초까지 돌릴 것...

		@Override
		public void run() {
			while (i > 0) {
				try {
					if (now) {
						i--;
						System.out.println(stud.name + " " + ThExam.this.getName() + ":" + i);
					}
					sleep(1000);

					if (end)
						break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			end = true;
		}
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

class ThStud implements Comparable<ThStud> {
	String name;
	LinkedHashMap<String, ThExam> map;
	ArrayList<ThStud> studs;
	int num, tot, avg, rank;

	public ThStud(String name, LinkedHashMap<String, ThExam> map, ArrayList<ThStud> studs, int num) {
		super();
		this.name = name;
		this.map = new LinkedHashMap<String, ThExam>();
		this.studs = studs;
		this.num = num;

		for (Entry<String, ThExam> en : map.entrySet()) {
			try {
				ThExam te = (ThExam) en.getValue().clone();
				te.stud = this;

				this.map.put(en.getKey(), te);

			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	void go() {
		for (ThExam exam : map.values()) {
			// System.out.println(exam.hashCode());
			exam.start();
		}

		map.get("국어").now = true; // 국어부터 시작 // ✔ 33Line 에서 여길 봄
		System.out.println("------------------");
	}

	void ppp() {
		System.out.println(name + " 결과 출력");

		for (ThExam exam : map.values()) {
			exam.myPPP();
		}

	}

	void turnExam() { // ✔ 123 Line 에서 여길 호출

		for (ThExam exam : map.values()) { // map.values 즉... 국영수, mpa을 돌면서
			// 즉 한 과목의 시험이 끝난다면 ... 다음 과목으로 ...
			if (!exam.end) { // exam.end 가 false 이면
				exam.now = true; // true로 바꾸고
				return; // 다음을 진행해야하기 대문 return 이 없으면 모두 들어와서 겹침 그래서 바로 return

			}

		}

		// 위 for 문의 return 으로 인해 exam.end 가 모두 true가 되야 밑으로 흐름.,...

		ppp(); // 시험이 모두 끝나면 결과 출력

		if (num + 1 < studs.size()) // num 1 이 처음 시작이므로 다음 시작은 무조건 num +1 부터 시작해서 size 만큼까지 돌면서 위 과정을 반복
			studs.get(num + 1).go();
		else // 마지막이 끝난다면
			result(); // result 로 계산 ✔ 261 Line 호출
	}

	void myResult() {

		tot = 0;
		for (ThExam exam : map.values()) {
			tot += exam.jum;
		}

		avg = tot / map.size();

	}

	@Override
	public String toString() {

		String ttt = name + "\t";

		for (ThExam exam : map.values()) {

			ttt += exam.jum + "\t";
		}

		ttt += tot + "\t" + avg + "\t" + rank;

		return ttt;

	}

	void result() {

		for (ThStud thStud : studs) {
			thStud.myResult(); // sum 과 avg
//			thStud.rank(); // 내 뇌피셜 : 여기서 돌리면 .... 점수계산이 아직 다 끝나지 않아... Error 이 생기지 않을까 ?
		}

		for (ThStud thStud : studs) { // 이렇게 하는게 사실상
			thStud.rank(); // 랭킹 계산
		}

		show(); // 출력
	}

	void rank() {
		rank = 1;
		for (ThStud you : studs) {
			if (avg < you.avg)
				this.rank++;
		}
	}

	void show() {

		TreeSet<ThStud> studs = new TreeSet<ThStud>(this.studs);

		for (ThStud thStud : studs) {
			System.out.println(thStud);
		}

	}

	@Override
	public int compareTo(ThStud you) {

		int result = rank - you.rank;

		if (result == 0)
			name.compareTo(you.name);

		return result;
	}

}

public class ThreadExamTask_explanation {

	public static void main(String[] args) {

		LinkedHashMap<String, ThExam> map = new LinkedHashMap<String, ThExam>();

		map.put("국어", new ThExam("국어"));
		map.put("영어", new ThExam("영어"));
		map.put("수학", new ThExam("수학"));

		map.get("국어").add("달면 삼키고 쓰면 뱉는다는 뜻을 가진 사자성어는 ?", "감탄고토");
		map.get("국어").add("앞뒤의 잔말을 빼고 요점만 말하는 뜻을 가진 사자성어는 ?", "거두절미");
		map.get("국어").add("평범한 남녀를이르는 사자성어는 ?", "갑남을녀");
		map.get("국어").add("잘못을 고치고 옳은 길에 들어선다는 뜻을 가진 사자성어는 ?", "개과천선");
		map.get("국어").add("입은 은혜에 대한 고마운 마음이 뼈에 사무쳐 잊혀지지 않는다는 뜻을 가진 사자성어는 ?", "각골난망");

		map.get("수학").add("1+1= ?", "2");
		map.get("수학").add("2+2= ?", "4");
		map.get("수학").add("3+3= ?", "6");
		map.get("수학").add("4+4= ?", "8");
		map.get("수학").add("5+5= ?", "10");

		map.get("영어").add("사과는 영어로?", "apple");
		map.get("영어").add("바나나는 영어로?", "banana");
		map.get("영어").add("배는 영어로?", "pear");
		map.get("영어").add("복숭아는 영어로?", "peach");
		map.get("영어").add("자두는 영어로?", "plum");

		ArrayList<ThStud> studs = new ArrayList<ThStud>();

		studs.add(new ThStud("장정호", map, studs, 0));
		studs.add(new ThStud("이호인", map, studs, 1));
		studs.add(new ThStud("김영재", map, studs, 2));
		studs.add(new ThStud("박성언", map, studs, 3));
		studs.add(new ThStud("김예솔", map, studs, 4));

		studs.get(0).go();

	}

}
