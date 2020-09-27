package file;

import java.io.File;

public class nullTest {
	
	public static void main(String[] args) {
		File file = new File("upload/null 인가여?.txt");
		
		System.out.println(file);
		
		System.out.println(file.exists()); // false
	}

}
