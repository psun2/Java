package control_p;



public class ForStarMain {

	public static void main(String[] args) {
		
		
		int cnt = 6;
		
		String odd = "*", even = "+";
		
		System.out.println("1 ¹ø");
		for (int line = 0; line < cnt; line++) {
			
			String ttt = odd;
			
			if(line%2==0)
				ttt=even;
			
			for (int star = 0; star <= line; star++) {
				System.out.print(ttt);
			}
			
			System.out.println();
		}
		
		System.out.println("2 ¹ø");
		for (int line = 0; line < cnt; line++) {
			
			//for (int star = line; star <cnt; star++) {
			for (int star = cnt; star >line; star--) {
				
				String ttt = odd;
				
				if(star%2!=0)
					ttt=even;
				
				System.out.print(ttt);
			}
			
			System.out.println();
		}
		
		/*
		
----*
---**
--***
-****
*****
		 */
		
		System.out.println("3 ¹ø");
		for (int line = 0; line < cnt; line++) {
			String ttt = odd;
			for (int star = line; star <cnt-1; star++) {
				System.out.print(" ");
			}
			for (int star = 0; star <= line; star++) {
				System.out.print(ttt);
			}
			
			System.out.println();
		}
		
		System.out.println("4 ¹ø");
		for (int line = 0; line < cnt; line++) {
			String ttt = odd;
			for (int star = 0; star < line; star++) {
				System.out.print(" ");
			}
			for (int star = line; star <cnt; star++) {
				System.out.print(ttt);
			}
			
			
			System.out.println();
		}
		
		System.out.println("5 ¹ø");
		for (int line = 0; line < cnt; line++) {
			String ttt = odd;
			for (int star = line; star <cnt-1; star++) {
				System.out.print(" ");
			}
			for (int star = 0; star <= line*2; star++) {
				System.out.print(ttt);
			}
			
			
			System.out.println();
		}
		
		System.out.println("6 ¹ø");
		for (int line = 0; line < cnt; line++) {
			String ttt = odd;
			for (int star = 0; star < line; star++) {
				System.out.print(" ");
			}
			for (int star = line*2+1; star <cnt*2; star++) {
				System.out.print(ttt);
			}

			System.out.println();
		}

	}

}