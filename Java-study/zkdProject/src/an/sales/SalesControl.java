package an.sales;

public class SalesControl {
	SalesView salesview;
	public SalesControl() {
		
		salesview = new SalesView(this);
		salesview.main();
	}
	
	
}
