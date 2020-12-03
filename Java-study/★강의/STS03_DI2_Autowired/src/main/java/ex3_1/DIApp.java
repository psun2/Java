package ex3_1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DIApp {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("컨테이너 생성\n");
		
		OperatorBean op = ctx.getBean("plusOp", OperatorBean.class);
		
		System.out.println("결과 " + op.doOperate());

		op = ctx.getBean("minusOp", OperatorBean.class);
		
		System.out.println("결과 " + op.doOperate());

		ctx.close();
	}

}

















