package test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;

class Quiz{
	String qq, answer, inp;
	LinkedHashMap<String, Exam> map;
	Exam exam;

	
	
	public Quiz(String qq, String answer, LinkedHashMap<String, Exam> map, Exam exam) {
		super();
		this.qq = qq;
		this.answer = answer;
		this.map = map;
		this.exam = exam;
	}

	int gogo() {
		String buf = JOptionPane.showInputDialog(qq);
		
		if(exam.end)
			return 1;
		
		if(map.containsKey(buf)) {
			exam.go = false;
			map.get(buf).go = true;
		}
		
		if(buf.equals("p"))
			return 1;
		
		inp = buf;
		
		
		return jum();
	}
	
	int jum() {
		

		if(answer.equals(inp))
			return 1;
		
		return 0;
	}


	public String toString() {
		return qq + "=" + answer + ", " + inp;
	}
	
	
}

class Exam extends Thread{
	
	boolean go = false, end = false;
	
	String title;
	
	ArrayList<Quiz> qq;
	
	LinkedHashMap<String, Exam> map;
	
	Timer tt;
	
	int second = 20;

	public Exam(String title, LinkedHashMap<String, Exam> map) {
		super();
		this.title = title;
		this.map = map;
		qq = new ArrayList<Quiz>();
		
	}
	
	@Override
	public void run() {
		tt = new Timer();
		tt.start();
		
		AAA:for (Quiz quiz : qq) {
			
			while(true) {
				
				try {
					sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(end)
					break AAA;
				if(go && quiz.gogo()==1) { //문제 풀기 && 문제 정답 -> 다음문제
					break;
				}
				
				if(end)
					break AAA;
				
			}
			
		}
		
		end = true;
		System.out.println(title +" 종료");
		
		
		boolean totEnd = true;
		
		for (Exam ex : map.values()) {
			if(!ex.end) {
				totEnd = false;
				ex.go = true;
				break;
			}
		}
		
		if(totEnd) {
			for (Exam ex : map.values()) {
				ex.report();
			}
		}
	}
	
	void report() {
		System.out.println(title+" ===================");
		int cnt = 0;
		for (Quiz qu : qq) {
			System.out.println(qu);
			cnt+=qu.jum();
		}
		System.out.println(" 정답갯수:"+cnt);
	}
	

	void add(String qq, String answer) {
		this.qq.add(new Quiz(qq, answer, map, this));
	}
	
	class Timer extends Thread{
		
		@Override
		public void run() {
			
			
			
			while(true) {
				
				try {
					sleep(1000);
					
					if(go) {
						second--;
						System.out.println(title+":"+second);
					}
					
					
					if(end || second==0)
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

public class ExamMain {

	public static void main(String[] args) {
		LinkedHashMap<String, Exam> map = new LinkedHashMap<String, Exam>();
		
		map.put("국어", new Exam("국어", map));
		map.put("영어", new Exam("영어", map));
		map.put("수학", new Exam("수학", map));
		
		map.get("영어").add("narrow","좁은");
        map.get("영어").add("sharp","날카로운");
        map.get("영어").add("tight","조이는");
        map.get("영어").add("tiny","아주작은");
        map.get("영어").add("gule","풀");
		
		map.get("국어").add("흑형이 울때 내는 소리는?","흑흑");  
        map.get("국어").add("날마다 흑심을 품고 다니는 것은?","연필");
        map.get("국어").add("학생들이 싫어하는 피자는?","책피자");
        map.get("국어").add("곰이 다니는 목욕탕 이름은?", "곰탕");
        map.get("국어").add("장사하는 사람이 가장 싫어하는 경기는?", "불경기");

        map.get("수학").add("11 x 11","121");
        map.get("수학").add("12 x 12","144");
        map.get("수학").add("13 x 13","169");
        map.get("수학").add("14 x 14","196");
        map.get("수학").add("15 x 15","225");
        
        for (Exam ex : map.values()) {
			ex.start();
		}
        
        map.get("국어").go = true;
	}

}