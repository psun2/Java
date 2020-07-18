package lastPuyo2;

// FIXME 뿌요 터지는 로직 다시 생각 => 대각선도 터져 버림
// FIXME KeyEvent 수정
// FIXME 자꾸 병신이 되어버림 좌표 수정을 어떻게 하지 ?

//벽쪽에 있는 애를 찾을꺼야 그럼 y가 더큰애가 오른쪽

//왼쪽 벽
//if(meY==youY){if(meX==0||youX==0){if(meX<youX){meX=0;youX=meX+Puyo.puyoSize;}else{youX=0;meX=youX+Puyo.puyoSize;}}}

//오른쪽 벽
//if(meY==youY){if(meX+Puyo.puyoSize==panel.getSize().width||youX+Puyo.puyoSize==panel.getSize().width){if(meX>youX){meX=panel.getSize().width-Puyo.puyoSize;youX=meX+Puyo.puyoSize;}}}