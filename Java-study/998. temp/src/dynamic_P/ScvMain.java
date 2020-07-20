package dynamic_P;

interface Resource {
	void get(String name);
}

interface Building {
	void build(String name);

	void repair(String name);
}

interface Unit {
	void repair(String name);

	void riding(String name);
}

interface SCVWork extends Resource, Building, Unit {

}

class ResourceImpl implements Resource {

	String name;

	public void get(String name) {
		System.out.println(this.name + " " + name + " 캐요");

	}
}

class BuildingImpl extends ResourceImpl implements Building {

	public void repair(String name) {
		System.out.println(this.name + " " + name + " 고쳐요");

	}

	public void build(String name) {
		System.out.println(this.name + " " + name + " 지어요");
	}
}

class UnitImpl extends BuildingImpl implements Unit {

	public void riding(String name) {
		System.out.println(this.name + " " + name + " 타요");
	}
}

class SCV extends UnitImpl implements SCVWork {

	public SCV(String name) {
		super();
		this.name = name;
	}

}

public class ScvMain {

	public static void main(String[] args) {
		SCV scv1 = new SCV("3시 1호기");
		SCV scv2 = new SCV("3시 2호기");
		Resource rr;
		Building bb;
		Unit uu;

		rr = scv1;
		rr.get("미네랄");
		bb = scv2;
		bb.build("커맨드센터");
		bb = scv1;
		bb.build("팩토리");
		bb.repair("커맨드센터");
		uu = scv2;
		uu.riding("오버로드");
		uu.repair("울트라리스크");
	}

}
