package ex2_1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 필드를 받는 생성자 생성
public class PlusOp implements OperatorBean {

	private int operand1;
	private int operand2;
	
	@Override
	public int doOperator() {
		// TODO Auto-generated method stub
		return operand1 + operand2;
	}

}
