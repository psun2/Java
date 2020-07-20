package oops;

class OverStud {
	String name, kind;
	int[] jum;
	int tot, avg, rank, pos, cateNum;

	void init(String name, int... jum) {
		this.name = name;
		this.jum = jum;
		kind = new String[] { "일반", "특기생" }[cateNum]; // ✔ 익명배열 (?)
		// cateNum = jum.length - 3
		cal();
		// ppp();
	}

	void cal() {
		tot = 0;
		for (int i : jum) {
			tot += i;
		}
		avg = tot / jum.length;
	}

	void ppp() {
		String ttt = kind + "\t" + name + "\t";

		for (int i : jum) {
			ttt += i + "\t";
		}

		if (cateNum == 0)
			ttt += "\t";

		ttt += tot + "\t" + avg + "\t" + rank;

		System.out.println(ttt);
	}

	void rankCal(OverMakeStud rms, OverStud[] studs) {
		rank = 1;
		if (cateNum == 1)
			pos += rms.general;

		for (OverStud you : studs) {
			if (avg < you.avg && cateNum == you.cateNum)
				rank++;
		}
		pos += rank; // (this.rank)

	}
}

class OverMakeStud {
	int general = 0;

	OverStud make(String name, int kor, int eng, int mat) {
		OverStud res = new OverStud();
		res.cateNum = 0;
		general++;
		res.init(name, kor, eng, mat);
		return res;

	}

	OverStud make(String name, int kor, int eng, int mat, int art) {
		OverStud res = new OverStud();
		res.cateNum = 1;
		res.init(name, kor, eng, mat, art);
		return res;

	}
}

public class OverStudMain {

	public static void main(String[] args) {
		OverMakeStud rms = new OverMakeStud();

		OverStud[] studs = { rms.make("한가인", 76, 77, 71), rms.make("두가인", 66, 67, 61, 62), rms.make("삼가인", 96, 97, 91),
				rms.make("사가인", 56, 57, 51), rms.make("손예진", 80, 87, 81, 83), rms.make("한예진", 76, 70, 71),
				rms.make("두예진", 66, 60, 61, 62), rms.make("삼예진", 96, 89, 91), rms.make("사예진", 56, 48, 51),
				rms.make("오예진", 83, 80, 81, 83) };

		for (OverStud me : studs) {

			me.rankCal(rms, studs);
		}
		String kind = "";
		for (int i = 1; i <= studs.length; i++) {
			for (OverStud me : studs) {
				if (me.pos == i) {
					if (!kind.equals(me.kind)) {
						kind = me.kind;
						System.out.println(kind + ">>>>>>>>");

					}
					me.ppp();
				}

			}
		}

	}

}
