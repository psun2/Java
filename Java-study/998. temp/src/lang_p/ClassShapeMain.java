package lang_p;

abstract class ClaShape {
	double area, border;

	String name;

	public ClaShape(String name) {
		super();
		this.name = name;
	}

	abstract void calc();

	@Override
	public String toString() {
		calc();
		return "ClaShape [name=" + name + ", area=" + area + ", border=" + border + "]";
	}

}

class ClaRec extends ClaShape {
	int w, h;

	public ClaRec(Integer w, Integer h) {
		super("직사각형");
		this.w = w;
		this.h = h;
	}

	@Override
	void calc() {
		area = w * h;
		border = (w + h) * 2;
	}

}

class ClaCir extends ClaShape {
	int r;

	public ClaCir(Integer r) {
		super("원");
		this.r = r;
	}

	@Override
	void calc() {
		area = Math.PI * Math.pow(r, 2);
		border = Math.PI * r * 2;
	}

}

class BindCla {
	String claName;

	Class[] type;

	Integer[] init;

	public BindCla(String claName, Class[] type, Integer... init) {
		super();
		this.claName = claName;
		this.type = type;
		this.init = init;
	}

}

public class ClassShapeMain {

	public static void main(String[] args) throws Exception {

		ClaShape[] arr = { (ClaShape) Class.forName("lang_p.ClaRec").getConstructor(Integer.class, Integer.class)
				.newInstance(5, 6),
				(ClaShape) Class.forName("lang_p.ClaCir").getConstructor(Integer.class).newInstance(5) };

		for (ClaShape sh : arr) {
			System.out.println(sh);
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		BindCla[] bbArr = { new BindCla("lang_p.ClaRec", new Class[] { Integer.class, Integer.class }, 7, 8),
				new BindCla("lang_p.ClaCir", new Class[] { Integer.class }, 9) };

		for (BindCla bc : bbArr) {
			System.out.println((ClaShape) Class.forName(bc.claName).getConstructor(bc.type).newInstance(bc.init));
		}

		/// 학생 ---> 보기, 쓰기, 삭제
	}

}
