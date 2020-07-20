package util_p;

al,jklasjdlk;asjkldj

import java.util.Calendar;

class DiarySpec {
	int m, d;

	String title;

	public DiarySpec(int m, int d, String title) {
		super();
		this.m = m - 1;
		this.d = d;
		this.title = title;
	}

	String eq(int m, int d) {

		if (this.m == m && this.d == d)
			return title;

		return null;
	}

}

class DiaryMM {

	int year, mm;

	String[][][] dd;

	Calendar today;

	DiarySpec[] spArr;

	public DiaryMM(Calendar today, int mm, DiarySpec[] spArr) {
		super();
		this.today = today;
		this.mm = mm;
		this.spArr = spArr;

		init();
	}

	String spStr(int i) {

		for (DiarySpec sp : spArr) {

			String res = sp.eq(mm, i);
			if (res != null) {
				return res;
			}
		}

		return "";

	}

	void init() {

		dd = new String[2][6][7];

		year = today.get(Calendar.YEAR);

		Calendar mmday = Calendar.getInstance();

		mmday.set(year, mm, 1);

		for (int i = 1; i <= mmday.getActualMaximum(Calendar.DATE); i++) {
			mmday.set(Calendar.DATE, i);

			String tt = " ", sur = " ";

			if (mm == today.get(Calendar.MONTH) && today.get(Calendar.DATE) == i) {
				tt = "[";
				sur = " ]";
			}

			if (i < 10)
				tt += " ";

			tt += i + sur;

			// 날짜
			dd[0][mmday.get(Calendar.WEEK_OF_MONTH) - 1][mmday.get(Calendar.DAY_OF_WEEK) - 1] = tt;

			dd[1][mmday.get(Calendar.WEEK_OF_MONTH) - 1][mmday.get(Calendar.DAY_OF_WEEK) - 1] = spStr(i);

//			System.out.println(i+":"+mmday.get(Calendar.WEEK_OF_MONTH)+","+
//					mmday.get(Calendar.DAY_OF_WEEK));
		}
	}

	String weekStr(int i, int kind) {
		String res = " ";

		for (String st : dd[kind][i]) {
			if (st == null)
				res += "";
			else
				res += st;
			res += "\t";
		}

		return res;
	}

}

public class DiaryYearMain {

	public static void main(String[] args) {

		DiarySpec[] spArr = { new DiarySpec(1, 1, "신정"), new DiarySpec(1, 24, "설"), new DiarySpec(1, 27, "날"),
				new DiarySpec(3, 20, "탄신일"), new DiarySpec(5, 5, "어린이날"), new DiarySpec(5, 8, "어버이날"),
				new DiarySpec(5, 20, "석탄일"), new DiarySpec(6, 6, "현충일"), new DiarySpec(8, 15, "광복절"),
				new DiarySpec(9, 30, "추"), new DiarySpec(10, 2, "석"), new DiarySpec(12, 10, "수료일"),
				new DiarySpec(12, 25, "성탄절")

		};

		Calendar today = Calendar.getInstance();

		DiaryMM[] tot = new DiaryMM[12];

		for (int i = 0; i < tot.length; i++) {
			tot[i] = new DiaryMM(today, i, spArr);
		}

		for (int i = 0; i < tot.length; i += 3) {

			for (int w = 0; w < 6; w++) {

				/// 각 주
				for (int sm = i; sm < i + 3; sm++) {
					System.out.print(tot[sm].weekStr(w, 0) + "\t");
				}

				System.out.println();

				/// 각 spec
				for (int sm = i; sm < i + 3; sm++) {
					System.out.print(tot[sm].weekStr(w, 1) + "\t");
				}

				System.out.println();

			}
			System.out.println();
		}

	}

}
