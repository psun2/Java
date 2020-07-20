package dynamic_P;

class InheriStud {

	public InheriStud(String kind, String name, double[] rate, int... jum) {
		super();
		this.kind = kind;
		this.name = name;
		this.rate = rate;
		this.jum = jum;

		calc();

	}

	String name, kind;
	int tot, rank;
	int[] jum;
	double[] rate;
	double res;

	void calc() {

		for (int i = 0; i < jum.length; i++) {
			tot += jum[i];
			res += rate[i] * jum[i];
		}
	}

	String[] ppp() {
		String ttt = kind + "\t" + name + "\t";

		for (int i : jum) {
			ttt += i + "\t";
		}

		String ttt2 = tot + "\t" + res + "\t" + rank;

		return new String[] { ttt, ttt2 };
	}
}

class GenStud extends InheriStud {
	public GenStud(String name, int kor, int eng, int mat) {
		super("일반", name, new double[] { 0.4, 0.3, 0.3 }, kor, eng, mat);

	}

	void rankCalc(GenStud[] arr) {
		rank = 1;
		for (GenStud gs : arr) {
			if (res < gs.res)
				rank++;
		}
		print();
	}

	void print() {
		String[] arr = ppp();
		System.out.println(arr[0] + "\t" + arr[1]);
	}
}

class SpecStud extends InheriStud {
	public SpecStud(String name, int kor, int eng, int mat, int art) {
		super("특기생", name, new double[] { 0.2, 0.1, 0.1, 0.6 }, kor, eng, mat, art);

	}

	void rankCalc(SpecStud[] arr) {
		rank = 1;
		for (SpecStud gs : arr) {
			if (res < gs.res)
				rank++;
		}
		print();
	}

	void print() {
		String[] arr = ppp();
		System.out.println(arr[0] + arr[1]);
	}
}

public class InheriStudMain {

	public static void main(String[] args) {
		GenStud[] gsArr = { new GenStud("일호아웃", 49, 43, 42), new GenStud("이호아웃", 97, 93, 92),
				new GenStud("삼호아웃", 77, 73, 72), new GenStud("사호아웃", 87, 83, 82) };

		SpecStud[] ssArr = { new SpecStud("일호인", 83, 85, 87, 82), new SpecStud("이호인", 53, 55, 57, 50),
				new SpecStud("삼호인", 64, 63, 66, 60), new SpecStud("사호인", 99, 90, 99, 92) };

		for (GenStud gs : gsArr) {
			gs.rankCalc(gsArr);

		}

		for (SpecStud gs : ssArr) {
			gs.rankCalc(ssArr);

		}

	}

}
