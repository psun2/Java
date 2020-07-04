package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class SplitTest {

	public static void main(String[] args) {

		String a = "exam_zip\\cross.png";

		String b = "sdfsdf,sdfsdf,sdfsdf";

//		System.out.println(Arrays.toString(a.split(".")));
//
//		System.out.println(a.lastIndexOf("."));
//		System.out.println(a.substring(0, a.lastIndexOf(".")));

		String aa = "중복확인.gif";
		String bb = "중복확인_overlap_1.gif";
		String cc = "중복확인";
		String dd = "중복확인_overlap_1";

//		System.out.println(aa.contains(bb));
//		System.out.println(cc.contains(dd));
//
//		System.out.println(bb.contains(aa));
//		System.out.println(dd.contains(cc));

		File test = new File("C:\\Users\\admin\\Desktop\\Java\\Java-study\\040. File\\exam_zip\\song\\걸어서 하늘까지.txt");

		try {
			FileReader fr = new FileReader(test);
			BufferedReader br = new BufferedReader(fr);

			String ttt = null;

			int i = 0;
			while ((ttt = br.readLine()) != null) {
//			while (br.readLine() != null) {

//				System.out.println(i + " : " + br.readLine());
				System.out.println(i + " : " + ttt);
				i++;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
