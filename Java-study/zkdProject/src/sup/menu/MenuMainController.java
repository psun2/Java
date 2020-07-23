package sup.menu;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import hong.table.TableMainController;
import sup.bills.Bills;
import sup.bills.BillsControl;
import sup.menuManagement.MenuManagementDBControl;
                                                                  
public class MenuMainController {
	public ArrayList<Menu> menus;                                 
	DefaultClient dfc;                                            
	ArrayList<OrderMenu> orderMenus;                              
	ArrayList<Basket> baskets;                                    
	MenuMainView menuGui;                                             
	int tot;
	String ip;
	private TableMainController tbControl;
//	String clientName = "손님";
	public MenuMainController(String tableNum,DefaultClient dfc, TableMainController tbControl) {
		this.dfc=dfc;
		this.tbControl = tbControl;
		init(tableNum);
	}
	//얘는 테스트용
//	public MenuMainController(String tableNum, String ip, int port) {
//		this.ip = ip;
//		dfc = new DefaultClient(clientName, this, ip, port);
//		init(tableNum);
//		
//	}
//	public MenuMainController(String tableNum, String ip, int port) {
//		this.ip = ip;
//		dfc = new DefaultClient(clientName, this, ip, port);
//		menuGui = new MenuMainView(tableNum,this);
//		insertDrinkList();
//		insertFoodList();
//		orderMenus = new ArrayList<OrderMenu>();
//		baskets = new ArrayList<Basket>();
//		
//	}
	void init(String tableNum) {
		menuGui = new MenuMainView(tableNum,this);
		insertDrinkList();
		insertFoodList();
		orderMenus = new ArrayList<OrderMenu>();
		baskets = new ArrayList<Basket>();
	}
	public void getMenuList() {
		menus = new MenuManagementDBControl().list();
		System.out.println(new MenuManagementDBControl().list());
	}
	public boolean checkOrder(Menu menu, JLabel sumPrice) {
		boolean chk= true;
		for (OrderMenu ordermenu : orderMenus) {
			if(menu.menu_name.equals(ordermenu.menuName)&&menu.menu_price==ordermenu.price) {
				System.out.println(menu);
				return false;
			}
		}
		return chk;
	}
	public void addOrderCnt(Menu menu, JLabel sumPrice, Basket basket) {
		int i=0;
		for (OrderMenu orderMenu : orderMenus) {
			if(menu.menu_name.equals(orderMenu.menuName)&&menu.menu_price==orderMenu.price) {
//				System.out.println(menu+"하나만 들어와"+i);
				orderMenu.setCnt(orderMenu.cnt+1);
				baskets.get(i).menuCnt.setValue(orderMenu.cnt);
			}
			i++;
		}
		sumPrice(sumPrice);
	}
	public void addMenuPrice(JLabel sumPrice,Menu menu) {
		tot = Integer.parseInt(sumPrice.getText())+menu.menu_price;
		new PriceThread(sumPrice,tot).start();
	}
	public void sumPrice(JLabel sumPrice) {
		tot = 0;
		
		for (OrderMenu orderMenu : orderMenus) {
//			System.out.println("더해");
			tot += orderMenu.sum;
		}
		new PriceThread(sumPrice,tot).start();
	}
	public void insertFoodList() {
		
		for (Menu menu : menus) {
			if(menu.getMenu_catag().equals("안주")) {
				MenuBtn menuBtn = new MenuBtn(menu, menuGui.panel_8, menuGui.sumPrice, this, menuGui.orderMenu);
				menuBtn.setText(menu.getMenu_name()+" ("+menu.getMenu_price()+")");
				menuGui.anjuPane.add(menuBtn);
			}
		}
		
	}
	public void insertDrinkList() {
		for (Menu menu : menus) {
			if(menu.getMenu_catag().equals("술")) {
				MenuBtn menuBtn = new MenuBtn(menu ,menuGui.panel_8, menuGui.sumPrice, this,  menuGui.orderMenu);
				menuBtn.setText(menu.getMenu_name()+" ("+menu.getMenu_price()+")");
				menuGui.drinkPane.add(menuBtn);
			}
		}
		
	}
	public void sendOrderList(String tableNum) {
		System.out.println(tableNum);
		for (OrderMenu orderMenu : orderMenus) {
			new OrderListDBControl(ip).insert(orderMenu, tableNum);
		}
		tbControl.billsControl.changeOrderMenuLable();
		closeMenuMain();
		
//		new BillsControl(tableNum, ip);
	}
	public void addBills(String tableNum) {
		tbControl.billsControl.addOrderList();
	}
	public void closeMenuMain() {
		menuGui.dispose();
	}
	
	public void sendMessage () {
		dfc.sendMessage("ord_tableNum", "주문","주방");
	}
	
}
class PriceThread extends Thread{
	JLabel sumPrice;
	int tot;
	public PriceThread(JLabel sumPrice,int tot) {
		super();
		this.sumPrice = sumPrice;
		this.tot = tot;
	}
	@Override
	public void run() {
		try {
			
			for (int i = 0; i <= tot; i++) {
				sumPrice.setText(i+"");
				sleep(0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}