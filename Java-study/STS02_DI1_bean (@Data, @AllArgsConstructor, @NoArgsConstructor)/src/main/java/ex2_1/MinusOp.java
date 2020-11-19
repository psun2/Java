package ex2_1;


import lombok.Data;

@Data
public class MinusOp implements OperatorBean {

	private int operand1;
	private int operand2;
	
	@Override
	public int doOperator() {
		// TODO Auto-generated method stub
		return operand1 - operand2;
	}

}
