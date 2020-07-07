package theory.ObjectStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.TreeSet;

public class Exam_ObjectStream_Teacher_In {

	public static void main(String[] args) {

		try {
			FileInputStream fis = new FileInputStream("fff/ooo.ooo");
			ObjectInputStream dis = new ObjectInputStream(fis);

			for (ObjExam obj : (TreeSet<ObjExam>) dis.readObject()) {
				System.out.println(obj);
			}

			dis.close();
			fis.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
