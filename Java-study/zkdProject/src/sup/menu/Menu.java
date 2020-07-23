package sup.menu;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;

public class Menu {
	
	

	String menu_code, menu_name, menu_catag;
	int menu_price;
	File imageF;
//	public Menu(String menu_code, String menu_name, String menu_catag, int menu_price, File imageF) {
//		super();
//		this.menu_code = menu_code;
//		this.menu_name = menu_name;
//		this.menu_catag = menu_catag;
//		this.menu_price = menu_price;
//		this.imageF = imageF;
//	}
	public Menu(String menu_code, String menu_name, String menu_catag, int menu_price) {
		super();
		this.menu_code = menu_code;
		this.menu_name = menu_name;
		this.menu_catag = menu_catag;
		this.menu_price = menu_price;
		this.imageF = imageF;
	}
	
	public File getImageF() {
		return imageF;
	}


	public void setImageF(File imageF) {
		this.imageF = imageF;
	}


	public String getMenu_code() {
		return menu_code;
	}

	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_catag() {
		return menu_catag;
	}
	public void setMenu_catag(String menu_catag) {
		this.menu_catag = menu_catag;
	}
	public int getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}
	
	
}
