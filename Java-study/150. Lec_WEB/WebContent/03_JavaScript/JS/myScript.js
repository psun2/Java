/**
 * TODO
 */
function light(sw) {
  var pic;
  if (sw === 0) {
    pic = 'https://www.w3schools.com/html/pic_bulboff.gif';
  } else {
    pic = 'https://www.w3schools.com/html/pic_bulbon.gif';
  }
  // element 의 src attribute 사용
  document.getElementById('myImage').src = pic;
}
const img = document.getElementById('myImage');
const on = document.querySelector('div .on'),
  off = document.querySelector('div .off'),
  dual = document.querySelector('div .dual');

function handleClickOn(event) {
  console.log('handleClickOn');
  console.log(myImage.width);
  img.src = 'https://www.w3schools.com/html/pic_bulbon.gif';
  img.width = '200';
  img.height = '280';
}
function handleClickOff(event) {
  console.log('handleClickOff');
  console.log(myImage.width);
  img.src = 'https://www.w3schools.com/html/pic_bulboff.gif';
  img.width = '100';
  img.height = '180';
}

function handleClick(event) {
  console.log('handleClick');
  let src = img.src;
  console.log(src === 'https://www.w3schools.com/html/pic_bulbon.gif');
  if (src === 'https://www.w3schools.com/html/pic_bulbon.gif')
    src = 'https://www.w3schools.com/html/pic_bulboff.gif';
  else src = 'https://www.w3schools.com/html/pic_bulbon.gif';
  img.src = src;
}

function init() {
  on.addEventListener('click', handleClickOn);
  off.addEventListener('click', handleClickOff);
  dual.addEventListener('click', handleClick);
}

init();
