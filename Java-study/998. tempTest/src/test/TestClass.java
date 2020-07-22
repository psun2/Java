package test;

import java.io.Serializable;

public class TestClass implements Serializable {

	private static final long serialVersionUID = 1L;

	int second = 0;

	@Override
	public String toString() {
		return "TestClass [second=" + second + "]";
	}

}
