package sup.bills;

import java.util.ArrayList;

import javax.swing.JPanel;

import hong.table.TableMainController;
import sup.menu.OrderListDBControl;
import sup.menu.OrderMenu;

public class BillsControl {
	public BillsView billsMain;
	Bills bills;
	ArrayList<OrderedMenusLable> orderedMenus;
	String ip;
	String tableNum;
	TableMainController tbControl;
	public BillsControl(String tableNum, String ip, TableMainController tbControl) {
		this.tableNum = tableNum;
		this.ip=ip;
		this.tbControl = tbControl;
		bills = new Bills();
		bills.orderMenus =  new OrderListDBControl(ip).list(tableNum);
		billsMain = new BillsView(this);
		
		addOrderList();
		bills.sum();
		setTot();
	}
	void cancelOrder(OrderMenu orderMenu) {
		int i = 0;
		for (OrderMenu om : bills.orderMenus) {
			if(orderMenu==om && orderMenu.getCancel()==1) {
				new OrderListDBControl(ip).del_menu(om);
				bills.orderMenus.remove(i);
				billsMain.panel_2.remove(i);
				break;
			}
			i++;
		}
		billsMain.panel_2.setVisible(false);
		billsMain.panel_2.setVisible(true);
		
	}
	void setTot() {
		billsMain.totLable.setText(bills.sumPrice+"");
	}
	public void addOrderList() {
		orderedMenus = new ArrayList<OrderedMenusLable>();
		bills.orderMenus =  new OrderListDBControl(ip).list(tableNum);
		for (OrderMenu orderMenu : bills.orderMenus) {
			System.out.println(orderMenu);
			OrderedMenusLable orderedMenusLable = new OrderedMenusLable(billsMain,orderMenu);
			orderedMenus.add(orderedMenusLable);
		}
	}
	
	public void receiveMessage(Object orderState) {
//		bills.orderMenus = new OrderListDBControl(ip).list(tableNum);
		OrderMenu om1 = new OrderListDBControl(ip).detail(orderState, tableNum);
		changeOrderMenu(om1);
		
	}
	public void changeOrderMenu(OrderMenu om1) {
		int i = 0;
		for (OrderedMenusLable oms : orderedMenus) {
			if(om1.getMenuName().equals(oms.orderMenu.getMenuName())&&om1.getTimestamp().equals(oms.orderMenu.getTimestamp())) {
				System.out.println("¿À´Ï");
				oms.menuState.setText(om1.getState());
			}
			i++;
		}
	}
	void closeBills() {
		billsMain.setVisible(false);
	}
	boolean listCheck(OrderMenu order) {
		
		for (OrderMenu you : bills.orderMenus) {
			if(order.getMenuName().equals(you.getMenuName())&&order.getTimestamp().equals(you.getTimestamp())) {
				
				return true;
			}
			
		}
		return false;
	}
	public void changeOrderMenuLable() {
		int i = 0;
		ArrayList<OrderMenu> orders=new OrderListDBControl(ip).list(tableNum);
		for (OrderMenu oms : orders) {
			if(listCheck(oms)) {
				OrderedMenusLable oml = orderedMenus.get(i);
				oml.menuState.setText(oms.getState());
				orderedMenus.set(i, oml);
				
			}else {
				OrderedMenusLable om2 = new OrderedMenusLable(billsMain, oms);
				orderedMenus.add(om2);
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
//		new BillsControl();
	}

}
