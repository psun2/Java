package oops;

class MethRec {
	int width, height;

	void init(int w, int h) {
		this.width = w;
		this.height = h;
		int area = width * height;
		int border = (width + height) * 2;
	}

	void ppp() {
		System.err.println("직사각형" + width + ", " + height);
	}
}

public class MethRectangleMain {

	public static void main(String[] args) {

//		MethRec mr = new MethRec();
		MethRec[] mr = new MethRec[5];

//		mr.init(5, 6);
//		mr.ppp();

		for (int i = 0; i < mr.length; i++) {
			mr[i] = new MethRec();
		}

		mr[0].init(5, 6);
		mr[0].ppp();


		
	}

}
