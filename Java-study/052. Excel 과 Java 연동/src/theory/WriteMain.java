package theory;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteMain {

	public static void main(String[] args) {

		new File("exam").mkdir();
		try {
			WritableWorkbook wwk = Workbook.createWorkbook(new File("./exam/exam.xls"));

			WritableSheet sheet1 = wwk.createSheet("시트_1", 0);
			WritableSheet sheet2 = wwk.createSheet("시트_2", 1);
			WritableSheet sheet3 = wwk.createSheet("시트_3", 2);

			Label lb = new Label(2, 3, "2행 3열이지");
			sheet1.addCell(lb);

			int column = 1;
			int row = 2;
			for (int i = 100; i <= 1000; i += 100) {
				sheet2.addCell(new Label(column, row, i + ""));
				row += 2;
			}

			wwk.write();
			wwk.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("작업 끝");

	}

}
