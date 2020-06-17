package theory;

class Capsule {

	private int abc;
	// 캡슐화로 외부에서 직접적으로 접근하지 못하게 하여, 정보를 은닉하고 마음대로 바꿀 수 없게 합니다.

	private boolean ref;

	public Capsule(int abc, boolean ref) {
		super();
		this.abc = abc;
		this.ref = ref;
	}

	public int getAbc() { // getter
		return abc;
	}

	public void setAbc(int abc) { // setter
		this.abc = abc;
	}

	public boolean isRef() { // getter
		return ref;
	}

	public void setRef(boolean ref) { // setter
		this.ref = ref;
	}

}

public class CapsuleMain {

	public static void main(String[] args) {

		Capsule cc = new Capsule(100, true);

		System.out.println(cc.getAbc() + ", " + cc.isRef());

		cc.setAbc(1234);

		System.out.println(cc.getAbc() + ", " + cc.isRef());
	}

}