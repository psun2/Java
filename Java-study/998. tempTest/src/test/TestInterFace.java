package test;

interface AAA {

	public void ccc();

	public int DDD();

	public void aaa(int a);

}

public class TestInterFace implements AAA {

	@Override
	public void ccc() {
		// TODO Auto-generated method stub

	}

	@Override
	public int DDD() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void aaa(int a) {
		// TODO Auto-generated method stub

	}

}

class TTest {

	AAA aaa;

	void abc() {
		
		aaa.aaa(1); // 특정메소드에 들어 갈 값 지정
		
	}

}
