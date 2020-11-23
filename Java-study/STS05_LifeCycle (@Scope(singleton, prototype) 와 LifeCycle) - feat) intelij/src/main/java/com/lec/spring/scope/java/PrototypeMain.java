package com.lec.spring.scope.java;

import com.lec.spring.Score;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PrototypeMain {
    public static void main(String[] args) {
        System.out.println("Main() 시작\n");

        // 컨테이너 생성
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        System.out.println("컨테이너 생성");

        // 설정 파일 register
        annotationConfigApplicationContext.register(ScoreConfig.class);

        // 설정파일 재 로드
        annotationConfigApplicationContext.refresh();

        Score score1 = annotationConfigApplicationContext.getBean("score2", Score.class);
        Score score2 = annotationConfigApplicationContext.getBean("score2", Score.class);
        System.out.println(score1);
        System.out.println(score2);
        // Score(kor=100, eng=80, math=90, comment=참 잘했어요)
        // Score(kor=100, eng=80, math=90, comment=참 잘했어요)

        score2.setComment("극락 입니다.");
        System.out.println(score1);
        System.out.println(score2);
        // Score(kor=100, eng=80, math=90, comment=참 잘했어요)
        // Score(kor=100, eng=80, math=90, comment=극락 입니다.)

        if (score1 == score2) System.out.println("같은 객체 입니다.");
        else System.out.println("다른 객체 입니다.");

        // 다른 객체 입니다.

        annotationConfigApplicationContext.close();
        System.out.println("\nMain() 종료");
    }
}
