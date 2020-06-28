package exam3;

class AAA extends Thread {
	
	int i = 0;
	
	@Override
	public void run() {

		while(true) {
			
			if(i == 10)
				break;
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(i);
			
			i++;
		}
		
	}
	
	
}

public class ThreadTest {
	
	public static void main(String[] args) {
		
		AAA a = new AAA();
		
		a.start();
		
	}

}
