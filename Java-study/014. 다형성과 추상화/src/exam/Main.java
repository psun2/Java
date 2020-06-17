package exam;

abstract class Bombalcohol {

	private String kind;
	private int jan;

	public Bombalcohol(String kind, int jan) {
		this.kind = kind;
		this.jan = jan;
	}

	public String getKind() {
		return kind;
	}

	public int getJan() {
		return jan;
	}

	public void setJan(int jan) {
		this.jan = jan;
	}

	abstract void calc();

	abstract void show();

}

class Soju extends Bombalcohol {

	private int soju;
	private final int volume = 50;

	public Soju(int jan) {
		super("소주", jan);
		calc();
	}

	public int getSoju() {
		return soju;
	}

	void calc() {
		this.soju = getJan() * volume;
	}

	void show() {
		String result = getKind() + "\t" + getJan() + "\t" + soju + "CC\t";
		System.out.println(result);
	}

}

class Beer extends Bombalcohol {

	private int beer;
	private final int volume = 500;

	public Beer(int jan) {
		super("맥주", jan);
		calc();
	}

	public int getBeer() {
		return beer;
	}

	void calc() {

		this.beer = getJan() * volume;
	}

	void show() {
		String result = getKind() + "\t" + getJan() + "\t" + beer + "CC\t";
		System.out.println(result);
	}

}

class Ssomac extends Bombalcohol {

	private final double[] ratio = { 0.8, 0.2 }; // 쏘맥 비율
	private int soju, beer;
	private final int volume = 500;

	public Ssomac(int jan) {
		super("쏘맥", jan);
		calc();
	}

	public int getSoju() {
		return soju;
	}

	public int getBeer() {
		return beer;
	}

	final void calc() {
		this.beer = (int) (getJan() * volume * ratio[0]); // 소주용량
		this.soju = (int) (getJan() * volume * ratio[1]); // 맥주용량
	}

	void show() {
		String result = getKind() + "\t" + getJan() + "\t" + soju + "CC\t" + beer + "CC\t";
		System.out.println(result);
	}

}

class Sacol extends Bombalcohol {

	private int sa2da, coke;
	private double[] ratio;// 사콜 비율
	private final int volume = 450;

	public Sacol(int jan, int... sacol) {
		super("사콜", jan);
		init(sacol);
		calc();
	}

	public int getSa2da() {
		return sa2da;
	}

	public int getCoke() {
		return coke;
	}

	void init(int[] sacol) {
		ratio = new double[0];
		for (int sc : sacol) {
			double[] temp = new double[ratio.length + 1];
			for (int i = 0; i < ratio.length; i++) {
				temp[i] = ratio[i];
			}
			temp[ratio.length] = (sc * 0.1);
			ratio = temp;
		}
	}

	void calc() {
		this.sa2da = (int) (getJan() * volume * ratio[0]); // 사이다 용량
		this.coke = (int) (getJan() * volume * ratio[1]); // 콜라 용량
	}

	void show() {
		String result = getKind() + "\t" + getJan() + "\t" + sa2da + "CC\t" + coke + "CC\t";
		System.out.println(result);
	}

}

class Calculation {

	private Bombalcohol[] arr;
	private final String[] kind = { "소주", "맥주", "사이다", "콜라" };
	private double[] result = new double[kind.length];
	private double[] jan = new double[kind.length];

	public Calculation(Bombalcohol... arr) {
		this.arr = arr;
	}

	void init() {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] instanceof Soju) {
				result[0] += ((Soju) arr[i]).getSoju();
				jan[0] += arr[i].getJan();
			} else if (arr[i] instanceof Beer) {
				result[1] += ((Beer) arr[i]).getBeer();
				jan[1] += arr[i].getJan();
			} else if (arr[i] instanceof Ssomac) {
				result[0] += ((Ssomac) arr[i]).getSoju();
				result[1] += ((Ssomac) arr[i]).getBeer();
				jan[0] += ((Ssomac) arr[i]).getSoju() / 50;
				jan[1] += (double) ((Ssomac) arr[i]).getBeer() / 500;
			} else if (arr[i] instanceof Sacol) {
				result[2] += ((Sacol) arr[i]).getSa2da();
				result[3] += ((Sacol) arr[i]).getCoke();
				jan[2] += arr[i].getJan();
				jan[3] += arr[i].getJan();
			} else {
				System.out.println("Error");
			}
		}

	}

	void show() {
		init();
		String result = "\n일일정산\n";
		for (int i = 0; i < this.result.length; i++) {
			result += kind[i] + "\t" + jan[i] + "잔\t" + this.result[i] + "CC\n";
		}
		System.out.println(result);
	}
}

public class Main {

	public static void main(String[] args) {

		// 칵테일을 만들어 주세요
		// 소맥 ::> 2 : 8 (변경불가)
		// 사콜 ::> 5 : 5 (변경가능)

		// 총 소주, 맥주, 사이다, 콜라 사용량을 출력하세요 (단위 cc )
		// ex) 소맥 : 5,(잔당:500) 사콜 : 2(잔당:450), 소주:3 (잔당:50) 맥주:2 (잔당 500), 콜라:3

		Bombalcohol[] pub = { new Soju(5), new Beer(7), new Ssomac(7), new Sacol(8, 5, 5), new Sacol(7, 8, 2) };

		for (Bombalcohol drink : pub) {
			drink.show();
		}

		Calculation cc = new Calculation(pub);

		cc.show();

	}

}

//소주	5	250CC	
//맥주	7	3500CC	
//쏘맥	7	700CC	2800CC	
//사콜	8	1800CC	1800CC	
//사콜	7	2520CC	630CC	
//
//일일정산
//소주	19.0잔	950.0CC
//맥주	12.6잔	6300.0CC
//사이다	15.0잔	4320.0CC
//콜라	15.0잔	2430.0CC
