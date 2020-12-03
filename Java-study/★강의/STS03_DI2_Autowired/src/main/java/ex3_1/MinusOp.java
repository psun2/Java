package ex3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinusOp implements OperatorBean {

	@Autowired
	@Qualifier("op1")
	Operand operand1;
	
	@Autowired
	@Qualifier("op2")
	Operand operand2;
	
	public int doOperate() {
		return operand1.getValue() - operand2.getValue();
	}

}














