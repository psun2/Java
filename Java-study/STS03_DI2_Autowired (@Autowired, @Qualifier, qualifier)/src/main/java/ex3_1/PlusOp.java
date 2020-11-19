package ex3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Data;

@Data
public class PlusOp implements OperatorBean {

	@Autowired
	@Qualifier("op1")
	private Operand operand1;
	@Autowired
	@Qualifier("op2")
	private Operand operand2;

	@Override
	public int doOperate() {
		// TODO Auto-generated method stub
		return operand1.getValue() + operand2.getValue();
	}

}
