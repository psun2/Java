const submitBtn = document.getElementById('submitBtn');
const radius = document.querySelectorAll('.table__head .table__title input');
const radiusArr = Array.from(radius);
const tableContents = document.querySelectorAll('.content__table .table__content');
let targetNum;

const handleKindClick = (event) => {
  const forefather = event.target.parentElement.parentElement;
  const parent = event.target.parentElement;
  const childs = forefather.childNodes;
  const targets = Array.from(childs).filter((index) => index.nodeType === 1);
  targetNum = targets.indexOf(parent);

  tableContents.forEach((index) => {
    index.classList.remove('active');
  });

  Array.from(tableContents)[targetNum].classList.add('active');
};

const inspectEmail = (value) => {
  // email 패턴에 대한 정규표현식
  const emailPat = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
  return emailPat.test(value);
};

const inspectCard = (value) => {
  return Number(value.length) === 4 ? true : false;
};

const handelSubmit = (event) => {
  event.preventDefault();

  const active = document.querySelectorAll('.content__table .table__content.active input');
  const inputs = Array.from(active);

  for (i in inputs) {
    const parent = inputs[i].parentElement.previousElementSibling;
    const message = parent.innerText;
    const value = inputs[i].value.trim();
    if (value === '') {
      inputs[i].focus();
      alert(`${message} (을/를) 확인해 주세요.`);
      return;
    }
    if (inputs[i].name === 'email')
      if (!inspectEmail(value)) {
        inputs[i].focus();
        alert(`${message} (을/를) 확인해 주세요.`);
        return;
      }
    if (inputs[i].name === 'card')
      if (!inspectCard(value)) {
        inputs[i].focus();
        alert(`${message} (을/를) 확인해 주세요.`);
        return;
      }
  }

  if (confirm('제출하시겠습니까?')) document.forms.form.submit();
  else return;
};

const init = () => {
  targetNum = undefined;

  radiusArr.forEach((index) => {
    index.addEventListener('click', handleKindClick);
  });
  submitBtn.addEventListener('click', handelSubmit);
};

init();
