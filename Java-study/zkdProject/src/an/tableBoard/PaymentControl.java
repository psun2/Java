package an.tableBoard;

public class PaymentControl {

	PaymentView payView;
	public PaymentControl() {
			payView = new PaymentView(this);
			payView.main();
			totPrice();
	}
	
	public void totPrice() {
		int tot=0;
		for (PaymentDTO pay : payView.payment) {
			tot += pay.bills_price;
		}
		payView.textField_11.setText(tot+" ");
	}
}
