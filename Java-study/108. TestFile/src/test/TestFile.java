package test;

import java.io.File;

public class TestFile {
	
	public static void main(String[] args) {
		
		new File("newFolder").mkdir();
		new File("./src/test/newFolder").mkdir();
		
	}

}
