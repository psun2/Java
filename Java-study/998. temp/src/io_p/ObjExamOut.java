package io_p;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeSet;

class ObjExam implements Serializable, Comparable<ObjExam> {
	private static final long serialVersionUID = 5678L;

	String name, grade;

	int[] jum;
	int tot, avg, rank;

	public ObjExam(String name, int... jum) {
		super();
		this.name = name;
		this.jum = jum;
		calc();
	}

	void calc() {
		tot = 0;
		for (int i : jum) {
			tot += i;
		}
		avg = tot / jum.length;
	}

	void rankCalc(TreeSet<ObjExam> studs) {
		rank = 1;

		for (ObjExam obj : studs) {
			if (avg < obj.avg)
				rank++;
		}

	}

	@Override
	public String toString() {
		return "ObjExam [name=" + name + ", jum=" + Arrays.toString(jum) + ", tot=" + tot + ", avg=" + avg + ", grade="
				+ grade + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(ObjExam o) {
		int res = o.avg - avg;
		if (res == 0)
			res = 1;
		return res;
	}

}

public class ObjExamOut {

	public static void main(String[] args) {
		try {
			
			FileOutputStream fos = new FileOutputStream("fff/ooo.ooo");
			ObjectOutputStream dos = new ObjectOutputStream(fos);

			TreeSet<ObjExam> arr = new TreeSet<ObjExam>();

			arr.add(new ObjExam("홍성혁", 77, 78, 72));
			arr.add(new ObjExam("이주혁", 67, 68, 62));
			arr.add(new ObjExam("김연섭", 97, 98, 92));
			arr.add(new ObjExam("김현준", 57, 58, 52));
			arr.add(new ObjExam("장정호", 67, 78, 92));
			arr.add(new ObjExam("이호인", 97, 86, 32));

			for (ObjExam objExam : arr) {
				objExam.rankCalc(arr);
			}

			dos.writeObject(arr);

			dos.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
