package theory.pac_2;

public class NePar {
	public int a = 1000;
	protected int b = 2000;
	int c = 3000;
	private int d = 4000;

	public void meth_1() {
		System.out.println("pac2.NePar \'public\' meth_1()");
	}

	protected void meth_2() {
		System.out.println("pac2.NePar \'protected\' meth_2()");
	}

	void meth_3() {
		System.out.println("pac2.NePar \'default\' meth_3()");
	}

	private void meth_4() {
		System.out.println("pac2.NePar \'private\' meth_4()");
	}

}