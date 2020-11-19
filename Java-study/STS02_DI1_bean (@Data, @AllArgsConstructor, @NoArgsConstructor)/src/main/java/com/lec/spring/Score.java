package com.lec.spring;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

/*
 * Lombok 라이브러리 사용
 * https://projectlombok.org/features/all
 */

@Data // getter, setter, toString, equals, hashCode ..
// @Getter   // getter
// @Setter   // setter
// @ToString  // toString()
// @NoArgsConstructor  // 기본생성자
// @AllArgsConstructor  // 모든 필드 생성자
// @RequiredArgsConstructor // @NonNull 이 붙은 필드 생성자
// @EqualsAndHashCode // equals(), hashCode()

public class Score {

	private int kor; // 국어점수
	private int eng; // 영어점수
	private int math; // 수학점수
	// @NonNull
	// NonNull => null 을 허용하지 않음
	private String comment; // 평가 코멘트

	public Score() {
		super();
		System.out.println("Score() 생성");
	}

	public Score(int kor, int eng, int math, String comment) {
		super();
		System.out.printf("Score(%d, %d, %d, %s) 생성\n", kor, eng, math, comment);
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.comment = comment;
	}

// ambiguity? 발생 안함
//	public Score(int kor, int eng, String comment, int math) {
//		super();
//		System.out.printf("Score(%d, %d, %s, %d) 생성\n", kor, eng, comment, math);
//		this.kor = kor;
//		this.eng = eng;
//		this.math = math;
//		this.comment = comment;
//	}

	public Score(int[] arr) {
		super();
		System.out.printf("Score(%s) 생성\n", Arrays.toString(arr));
		this.kor = arr[0];
		this.eng = arr[1];
		this.math = arr[2];
	}

	public void setScore(List<Integer> score) {
		System.out.println("setScore(" + score + ") 호출");
		this.kor = score.get(0);
		this.eng = score.get(0);
		this.math = score.get(0);
	}

}
