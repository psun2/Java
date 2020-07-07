package theory.ObjectStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeSet;

class Student implements Serializable, Comparable<Student> {

	private static final long serialVersionUID = 0;

	String name;
	int[] jum;
	int tot, avg, rank;

	public Student(String name, long serialVersionUID, int... jum) {
		super();
		this.name = name;
		this.jum = jum;
		init(jum);
	}

	void init(int[] jum) {

		this.jum = new int[jum.length];

		for (int i = 0; i < jum.length; i++) {
			this.jum[i] = jum[i];
		}

		calc();
	}

	void calc() {

		for (int i : jum) {
			this.tot += i;
		}

		this.avg = tot / jum.length;

	}

	void rank(Student[] student) {

		this.rank = 1;

		for (Student you : student) {
			if (avg < you.avg)
				rank++;
		}

	}

	@Override
	public String toString() {

		String result = this.name + "\t";

		for (int i : jum) {
			result += i + "\t";
		}

		result += tot + "\t" + avg + "\t" + rank;

		return result;

	}

	@Override
	public int compareTo(Student you) {

		int res = rank - you.rank;

		if (res == 0)
			res = name.compareTo(you.name);

		return res;

	}

}

public class Exam_ObjectStream_My {

	public static void main(String[] args) {

		try {

			Student[] students = { new Student("한가인", 99, 99, 99), new Student("이가인", 36, 89, 41),
					new Student("삼가인", 78, 14, 36), new Student("사가인", 78, 85, 63), new Student("오가인", 11, 22, 33) };

			TreeSet<Student> studentSort = new TreeSet<Student>();

			FileOutputStream fos = new FileOutputStream("예제폴더3/studentRankExam.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (Student student : students) {
				student.rank(students);
				studentSort.add(student);
			}

			for (Student student : studentSort) {
				oos.writeObject(student);
			}

			oos.close();
			fos.close();

			FileInputStream fis = new FileInputStream("fff/studentRankExam.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			System.out.println(ois.available()); // 0 ????

			System.out.println(fis.available());

//			System.out.println(ois.readObject().toString());
//			System.out.println(ois.readObject().toString());
//			System.out.println(ois.readObject().toString());
//			System.out.println(ois.readObject().toString());
//			System.out.println(ois.readObject().toString());

//			System.out.println(ois.readObject());
//			System.out.println(ois.readObject());
//			System.out.println(ois.readObject());
//			System.out.println(ois.readObject());
//			System.out.println(ois.readObject());			

			FileWriter fw = new FileWriter("예제폴더3/studentRankExam2.txt");

			while (fis.available() > 0) {

				System.out.println(ois.readObject());
//				fw.write(ois.readObject().toString() + "\n");
			}

			ois.close();
			fw.close();
			fis.close();

			new File("예제폴더3/studentRankExam.txt").delete();
			new File("예제폴더3/studentRankExam2.txt").renameTo(new File("예제폴더3/studentRankExam.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
