<사용 방법>

오후 5:48 2020-07-21
TestDefaultClientMain 을 예시로 보세요!

1. 클래스를 ReceiverObjFromClient를 구현합니다.

2.. DefaultClient를 멤버변수로 생성합니다.

3. getMsgObjectFromClient(MessageObject msgObject)를 구현하여,
msg를 받으시면 됩니다!!!! 
	
예시)
	@Override
	public void getMsgObjectFromClient(MessageObject msgObject) {
		System.out.println("받았당");
		System.out.println(msgObject.getMessageMain());
	}
