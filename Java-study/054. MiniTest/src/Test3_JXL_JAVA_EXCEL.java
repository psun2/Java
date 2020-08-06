import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//  { { "이름", "반", "전화", "생일" }, { "정우성", "1", "010-1111-1111", "1997-05-06" },
//	{ "정좌성", "1", "010-2222-2222", "1997-08-08" }, { "정북성", "3", "010-3333-3333", "1997-02-05" },
//	{ "정남성", "2", "010-4444-4444", "1996-06-02" }, { "정중성", "3", "010-5555-5555", "1998-11-13" } };
// 정보를 엑셀 파일에 입력하고
// 엑셀파일을 통해 출력 하시오.

class Write {

	WritableWorkbook wwk;
	WritableSheet sheet;

	public Write(File file, String[][] arr) {
		// TODO Auto-generated constructor stub

		try {
			this.wwk = Workbook.createWorkbook(file);
			this.sheet = wwk.createSheet("시험sheet", 0);

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					sheet.addCell(new Label(j, i, arr[i][j]));
				}
			}

			wwk.write();
			wwk.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				wwk.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		System.out.println("쓰기 완료");

	}

}

class Read {

	Workbook wb;
	Sheet sheet;

	public Read(File file) {
		// TODO Auto-generated constructor stub

		try {
			this.wb = Workbook.getWorkbook(file);
			this.sheet = wb.getSheet(0);

			for (int i = 0; i < sheet.getRows(); i++) {

				for (int j = 0; j < sheet.getColumns(); j++) {

					System.out.print(sheet.getCell(j, i).getContents() + "\t");

				}
				System.out.println();

			}

			wb.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {
				wb.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		System.out.println("읽기 완료");

	}

}

public class Test3_JXL_JAVA_EXCEL {

	public static void main(String[] args) {

		new File("./test").mkdir();

		String[][] arr = { { "이름", "반", "전화", "생일" }, { "정우성", "1", "010-1111-1111", "1997-05-06" },
				{ "정좌성", "1", "010-2222-2222", "1997-08-08" }, { "정북성", "3", "010-3333-3333", "1997-02-05" },
				{ "정남성", "2", "010-4444-4444", "1996-06-02" }, { "정중성", "3", "010-5555-5555", "1998-11-13" } };

		new Write(new File("./test/test.xls"), arr);

		new Read(new File("./test/test.xls"));

	}

}
