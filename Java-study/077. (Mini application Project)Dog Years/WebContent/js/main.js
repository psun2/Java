const resultBtn = document.querySelectorAll('.select__box .box__wrapper button'),
  inputs = document.getElementsByClassName('box__input');
selectBtn = document.querySelectorAll('.select__btn');
let type = 'dog';

const handleClick = (event) => {
  const result =
    event.target.parentElement.parentElement.lastElementChild.lastElementChild;

  let age = event.target.previousElementSibling.value;

  const ele = Array.from(event.target.parentElement.children);
  ele.forEach((index) => {
    if (index.nodeName === 'INPUT') age = index.value;
  });

  if (age === '') {
    const modalTitle = document.querySelector('.modal__wrapper .modal__title h3');
    modalTitle.innerText = '오류메시지';
    const modalContent = document.querySelector('.modal__wrapper .modal__content');
    modalContent.innerHTML =
      '입력한 값이 오류입니다.<br/>다시 확인해 주시기 바랍니다.';
    modal.classList.remove('hidden');
    return;
  }
  let resultAge = 0;
  let roundAge = 0;
  switch (type) {
    case 'dog':
      resultAge = (age * 365) / 52;
      roundAge = Math.round(resultAge);
      result.innerHTML = `${age}살의 반려견은 인간의 나이로 ${resultAge}살 입니다.<br /> 반올림 하여 약 ${roundAge}살 입니다.`;
      break;
    case 'person':
      resultAge = (age * 52) / 365;
      roundAge = Math.round(resultAge);
      result.innerHTML =  `인간 나이${age}살은 반려견의 나이로 ${resultAge}살 입니다.<br /> 반올림 하여 약 ${roundAge}살 입니다.`;
      break;
  }

  event.target.previousElementSibling.value = '';
  event.target.previousElementSibling.focus();
};

const handleKey = (event) => {
  if (event.key === 'Enter') handleClick(event);
};

const handelChoice = (event) => {
  const targetArr = Array.from(event.target.parentElement.parentElement.children);
  type = event.target.nextElementSibling.value;
  let indexNum = 0;
  let i = 0;
  targetArr.forEach((index) => {
    if (index.lastElementChild.value === type) {
      indexNum = i;
      return;
    }
    i++;
  });

  const views = Array.from(document.querySelectorAll('.select__box'));
  views.forEach((index) => {
    index.classList.remove('active');
    if (views[indexNum] === index) index.classList.add('active');
  });

  const result = document.querySelector('.select__box.active .box__result .result');
  result.innerHTML = '';
};

const init = () => {
  Array.from(resultBtn).forEach((index) => {
    index.addEventListener('click', handleClick);
  });
  Array.from(inputs).forEach((index) => {
    index.addEventListener('keyup', handleKey);
    index.focus();
  });
  Array.from(selectBtn).forEach((index) => {
    index.addEventListener('click', handelChoice);
  });
};
init();
