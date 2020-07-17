package lastPuyo2;

public class modify {

	1.
	세로일때 회전과 2.
	가로일때 회전
	을 에러를
	잡아줘야 함

	3.
	you가 오른쪽애
	있을때 오른쪽키를
	누르면 you가
	벽에 먹힘
	현상
	
	그냥
	me 와
	you가 벽에
	부딪힐때 문제
}

//벽쪽에 있는 애를 찾을꺼야 그럼 y가 더큰애가 오른쪽

//왼쪽 벽
if(meY==youY){if(meX==0||youX==0){if(meX<youX){meX=0;youX=meX+Puyo.puyoSize;}else{youX=0;meX=youX+Puyo.puyoSize;}}}

//오른쪽 벽
if(meY==youY){if(meX+Puyo.puyoSize==panel.getSize().width||youX+Puyo.puyoSize==panel.getSize().width){if(meX>youX){meX=panel.getSize().width-Puyo.puyoSize;youX=meX+Puyo.puyoSize;}}}
