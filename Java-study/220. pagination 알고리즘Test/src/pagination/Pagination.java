package pagination;

import java.util.Arrays;

public class Pagination {

	int totalData = 167;
	static final int pageCnt = 10;

	static void goPage(int page) {

		int startPage = (page - 1) * pageCnt + 1;
		int endPage = startPage + pageCnt - 1;

		int[] result = new int[0];

		for (int i = startPage; i <= endPage; i++) {
//			System.out.println("함수 전 : " + result.length);
//			System.out.println("함수 전 : " + Arrays.toString(result));
			result = append(result, i);
//			System.out.println("함수 후 : " + result.length);
//			System.out.println("함수 후 : " + Arrays.toString(result));
		}

		System.out.println(page + "페이지 : " + Arrays.toString(result));

	}

	static void initPage() {

	}

	static int[] append(int[] arr, int value) {

		int[] result = arr;
		int temp[] = new int[result.length + 1];
//		System.out.println("result 길이 : " + result.length);
//		System.out.println("temp 길이 : " + temp.length);

		for (int i = 0; i < result.length; i++) {
			temp[i] = result[i];
		}

		temp[temp.length - 1] = value;
		result = temp;

		return result;
	}

	public static void main(String[] args) {

		// ex page1 => 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		// ex page2 => 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
		// ex page3 => 21, 22, 23, 24, 25, 26, 27, 28, 29, 30

		goPage(3);

		System.out.println((13 / 10) * 10 + 1);

		int pageNumber = 13;
		int startPage = (pageNumber / 10) * 10 + 1;
		if (pageNumber % 10 == 0)
			startPage -= 10;

		System.out.println("startPage : " + startPage); // 11

		pageNumber = 20;
		startPage = (pageNumber / 10) * 10 + 1;
		if (pageNumber % 10 == 0)
			startPage -= 10;

		System.out.println("startPage : " + startPage); // 11

		pageNumber = 25;
		startPage = (pageNumber / 10) * 10 + 1;
		if (pageNumber % 10 == 0)
			startPage -= 10;
		
		System.out.println("startPage : " + startPage); // 21

		pageNumber = 30;
		startPage = (pageNumber / 10) * 10 + 1;
		if (pageNumber % 10 == 0)
			startPage -= 10;

		System.out.println("startPage : " + startPage); // 21

//		int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1; // 11
//		if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10; // 1
//		int targetPage = new BoardDAO().targetPage(pageNumber);
//		for(int i = startPage; i < Integer.parseInt(pageNumber); i++)

	}

}
