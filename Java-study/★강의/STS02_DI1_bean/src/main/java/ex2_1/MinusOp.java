package ex2_1;

import lombok.Data;

@Data
public class MinusOp implements OperatorBean {

	int operand1;
	int operand2;
	
	public MinusOp() {
		super();
	}

	public MinusOp(int operand1, int operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public int doOperate() {
		return operand1 - operand2;
	}

}
