package sup.kitchen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.sun.nio.sctp.SendFailedNotification;

import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import sup.menu.OrderListDBControl;

class KitchenMainController{
	ArrayList<Order> orderList = new ArrayList<Order>();
	ArrayList<orderPane> ords = new ArrayList<orderPane>();
	KitchenMainJFrame kitchenMainJFrame;
	
	Kitchen kitchen;
	String clientName= "�ֹ�";
	KitchenMainController( int serverPort){
		
		kitchenMainJFrame=new KitchenMainJFrame(this);
		kitchen = new Kitchen(clientName,serverPort, this);
		new_orderList();
		
	}
	class Kitchen implements ReceiverObjFromClient{
		
		DefaultClient df;
		private KitchenMainController control;
		
		public Kitchen(String clientName,  int serverPort ,KitchenMainController control) {
			df = new DefaultClient(clientName, this, InitData.ip, serverPort);
			this.control =control;
		}

		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			switch (msgObject.getType()) {
			case "�ֹ�": 
				control.refresh();
				break;
			case "" :
				break;
			default:
				break;
			}
		}
		
	}
	
	public void new_orderList() {
		ArrayList<Order> orders = new OrderListDBControl(InitData.ip).kList();
		if(orderList.size()!=0) {
			System.out.println("��");
			
			for (Order order : orderList) {
				
				checkAdd(orders, order);
			}
		}else {
			orderList = orders;
		}
		addOrderLable();
	}
	public void checkAdd(ArrayList<Order> orders , Order order) {
		for (Order order2 : orders) {
			if(!order.table_num.equals(order2.table_num) && order.menu_Name.equals(order2.menu_Name)
			   && order.time.equals(order2.time)) {
				System.out.println("����");
				orderList.add(order2);
			}
		}
	}
	public void checkAdd(ArrayList<Order> order2 , Order order, ArrayList<Order> orders) {
		for (Order ord : order2) {
			if(!order.table_num.equals(ord.table_num) && order.menu_Name.equals(ord.menu_Name)
			   && order.time.equals(ord.time)) {
				orders.add(ord);
			}
		}
	}
	public ArrayList<Order> checkOrder() {

		ArrayList<Order> orders = new ArrayList<Order>();
		if(ords.size()!=0) {
			for (orderPane orderL : ords) {
				System.out.println("�־ȿ�>?");
				for (Order order : orderList) {
					checkAdd(orderList, order, orders);
				}
			}
		}else {
			orders = orderList;
		}
		
		return orders;
	}
	public void addOrderLable() {
		ArrayList<Order> orders = checkOrder();
		
		for (Order order : orders) {
			if(order.table_num.equals("���̺�_1")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_2")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_3")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_4")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_5")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_6")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_7")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("���̺�_8")) {
				System.out.println("���");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
		}
	}
//				kitchenMainJFrame.waiting_p.setVisible(false);
//				kitchenMainJFrame.waiting_p.setVisible(true);
	public void changeState(Order order, String state, orderPane op2) {
		if(order.menu_state.equals("�������")) {
			op2.menu_state.setText("������");
			order.setCancel(0);
			op2.setBackground(Color.red);
			order.setMenu_state("������");
			new OrderListDBControl(InitData.ip).updateState(order);
			kitchen.df.sendMessage(order.time, "�ֹ�", order.menu_Name);
			
		}
		else if(order.menu_state.equals("������")) {
			op2.menu_state.setText("�����Ϸ�");
			order.setMenu_state("�����Ϸ�");
			op2.setBackground(Color.green);
			new OrderListDBControl(InitData.ip).updateState(order);
			kitchen.df.sendMessage(order.time, "�ֹ�", order.menu_Name);
			
			kitchen.df.sendMessage(order, "ȣ��", "ī����");
		}
		else if(order.menu_state.equals("�����Ϸ�")) {
			orderList.remove(order);
			kitchenMainJFrame.remove(kitchenMainJFrame.waiting_p);
			kitchenMainJFrame.waiting_p = new JPanel();
			for (Order order2 : orderList) {
				orderPane ol = new orderPane(order2, this);
				kitchenMainJFrame.waiting_p.add(ol);
			}
			kitchenMainJFrame.contentPane.add(kitchenMainJFrame.waiting_p);
			kitchenMainJFrame.waiting_p.setVisible(false);
			kitchenMainJFrame.waiting_p.setVisible(true);
			
		}
	}
	public void refresh() {
		orderList = new OrderListDBControl(InitData.ip).kList();
		kitchenMainJFrame.remove(kitchenMainJFrame.waiting_p);
		kitchenMainJFrame.waiting_p = new JPanel();
		for (Order order2 : orderList) {
			orderPane ol = new orderPane(order2, this);
			kitchenMainJFrame.waiting_p.add(ol);
		}
		kitchenMainJFrame.contentPane.add(kitchenMainJFrame.waiting_p);
		kitchenMainJFrame.waiting_p.setVisible(false);
		kitchenMainJFrame.waiting_p.setVisible(true);
	}
		
}

public class KitchenMainControllerMain {

	public static void main(String[] args) {
		new KitchenMainController( 7777);
	}

}
