package sup.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Basket extends JPanel {
	JSpinner menuCnt;
	public Basket(Menu menu,JPanel panel8, JLabel sumPrice, OrderMenu orderMenu,MenuMainController vm) {
	
		
		setLayout(new BorderLayout());
		
		SpinnerModel numberModel = new SpinnerNumberModel(1, 1, 30, 1);
		menuCnt = new JSpinner(numberModel);
		JLabel menuNameLable = new JLabel();
		JLabel menuPrice = new JLabel();
		
	
		menuNameLable.setText("  "+menu.menu_name);
		menuNameLable.setSize(150, 30);
		
		
		menuPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		menuPrice.setText(menu.menu_price+"  ");
		menuPrice.setSize(150,30);
		
		menuCnt.setSize(100, 50);
		
		
		add(menuCnt,"East");
		add(menuPrice,"Center");
		add(menuNameLable, "West");
		panel8.add(this);
		setVisible(true);
		
		menuCnt.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				orderMenu.setCnt(menuCnt.getModel().getValue());
				vm.sumPrice(sumPrice);
				
			}
		});
	}
	
}
