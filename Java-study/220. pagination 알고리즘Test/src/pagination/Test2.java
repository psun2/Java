package pagination;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) {
		
		int totalDataCnt = 167;

		int[] totalData = new int[totalDataCnt];

		for (int i = 0; i < totalData.length; i++) {
			totalData[i] = i + 1;
//			System.out.println(i);
		}

		System.out.println(Arrays.toString(totalData));

		//--------------------------------------------------------------------
		
		int pageNum = 10;
		int cnt = 1;
		for (int i = 0; i < totalData.length; i++) {
			if(totalData[i] > (pageNum - 1) * 10) cnt++;
		}
		
		System.out.println(cnt);
		System.out.println(cnt / 10);
	}

}
