package pagination;

public class 동빈나알고리즘 {

	public static void main(String[] args) {

		int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1;
		if (Integer.parseInt(pageNumber) % 10 == 0)
			startPage -= 10;
		int targetPage = new BoardDAO().targetPage(pageNumber);
		System.out.println("targetPage : " + targetPage);
		if (startPage != 1) {

		} else {

		}

		// 앞쪽
		for (int i = startPage; i < Integer.parseInt(pageNumber); i++) {

		}

		// current

		// 뒷쪽
		for (int i = Integer.parseInt(pageNumber) + 1; i <= targetPage + Integer.parseInt(pageNumber); i++) {
			if (i < startPage + 10) {

			}
		}

		if (targetPage + Integer.parseInt(pageNumber) > startPage + 9) {

		} else {

		}

	}

}
