package theory;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadMain {

	public static void main(String[] args) {

		try {
			Workbook wb = Workbook.getWorkbook(new File("./exam/exam.xls"));

			System.out.println("wb.getNumberOfSheets() [시트의 갯수] : " + wb.getNumberOfSheets());

			Sheet sheet = wb.getSheet(1);
			System.out.println("sheet.getColumns() [행] : " + sheet.getColumns());
			System.out.println("sheet.getRows() [열] : " + sheet.getRows());

			System.out.println("------------------------------------------------------------------");

			Cell cell = sheet.getCell(1, 2);
			System.out.println("cell.getType() : " + cell.getType());
			System.out.println("cell.getContents() : " + cell.getContents());

			System.out.println("------------------------------------------------------------------");

			Sheet[] arr = wb.getSheets();
			for (Sheet sh : arr) {
				System.out.println("sh.getName() [시트명] : " + sh.getName());
			}

			System.out.println("------------------------------------------------------------------");

			int column = 1;
			for (int i = 0; i < sheet.getRows(); i++) {
				System.out.print(wb.getSheet(1).getCell(1, i).getType() + "\t");
				System.out.println(wb.getSheet(1).getCell(column, i).getContents());
			}

			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
