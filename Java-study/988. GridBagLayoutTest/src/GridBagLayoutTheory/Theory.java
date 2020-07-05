package GridBagLayoutTheory;

public class Theory {

	// GridBagLayout은 GridLayout을 더 유연하게 사용할 수 있습니다.

	// GridLayout은 셀 영역이 (1x1) 모두 같은 형태이지만

	// GridBagLayout 은 엑셀처럼 셀을 병합하여 사용자가 원하는 스타일로 지정할 수 있습니다.

	// 당연히 레이아웃이 통제를 받으니 GridBagConstraints는 필요한 존재입니다.

	// GridBagConstraints의 인스턴스를 확인하도록 하겠습니다.

	// GridBagConstraints.girdx 는 grid행렬의 시작점 x좌표

	// GridBagConstraints.girdy 는 grid행렬의 시작점 y좌표

	// GridBagConstraints.gridwidth 는grid행렬의 w너비(버튼이면 버튼의 가로 길이)

	// GridBagConstraints.gridheight 는grid행렬의 h너비(버튼이면 버튼의 세로 길이)

//	┌───────────────┐
//	│0,0│1,0│2,0│3,0│
//	└───────────────┘
//	│0,1│1,1│2,1│3,1│	
//	└───────────────┘

	// GridBagConstraints.weightx 는 비율로 영역 분배

	// GridBagConstraints.weighty 는 비율로 영역 분배
}
