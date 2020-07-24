package test;

import client.Client;
import client.ClinetIter;
import data.Data;

public class Test implements ClinetIter {

	Client client;
	Data data;

	public Test() {
		// TODO Auto-generated constructor stub
		this.client = new Client();
		client.client = this; // 하.....이구분이 없으니 에러가 심각.....
		this.data = new Data();
		data.type = "채팅";
		data.data = new String("이말이 잘 가나요 ?");
		client.send(data);
	}

	@Override
	public void receive(Data data) {
		// TODO Auto-generated method stub
		System.out.println((String) data.data);
	}

	public static void main(String[] args) {
		new Test();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
