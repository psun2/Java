package com.lec.spring.scope.xml;

import com.lec.spring.Score;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PrototypeMain {

    public static void main(String[] args) {
        System.out.println("Main() 시작\n");

        // 컨테이너 생성
        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();
        System.out.println("컨테이너 생성");

        // 설정파일 load
        genericXmlApplicationContext.load("classpath:scopeSetting.xml");

        // 설정파일 재로드
        genericXmlApplicationContext.refresh();

        Score score1 = genericXmlApplicationContext.getBean("score2", Score.class);
        Score score2 = genericXmlApplicationContext.getBean("score2", Score.class);
        System.out.println(score1);
        System.out.println(score2);
        // Score(kor=100, eng=80, math=90, comment=참 잘했어요)
        // Score(kor=100, eng=80, math=90, comment=참 잘했어요)

        score2.setComment("나락입니다.");
        System.out.println(score1);
        System.out.println(score2);
        // Score(kor=100, eng=80, math=90, comment=참 잘했어요)
        // Score(kor=100, eng=80, math=90, comment=나락입니다.)

        if (score1 == score2) System.out.println("같은 객체 입니다.");
        else System.out.println("다른 객체 입니다.");

        // 다른 객체 입니다.

        genericXmlApplicationContext.close();
        System.out.println("\nMain() 종료");
    }
}
