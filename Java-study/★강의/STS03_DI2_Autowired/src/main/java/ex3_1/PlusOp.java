package ex3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.lec.beans.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlusOp implements OperatorBean {

	@Autowired
	@Qualifier("op1")
	Operand operand1;
	
	@Autowired
	@Qualifier("op2")
	Operand operand2;
	
	@Autowired(required = false)  // 자동주입 대상 객체가 없는경우 자동 주입 진행하지 않음
	DAO dao;
	
	// TODO
	
	public int doOperate() {
		
		return operand1.getValue() + operand2.getValue();
	}

}








