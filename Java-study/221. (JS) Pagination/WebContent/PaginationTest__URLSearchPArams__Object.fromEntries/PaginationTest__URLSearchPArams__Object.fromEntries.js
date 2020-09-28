const body = document.querySelector('body');
let links = null;
const pageCnt = 10;
let pageNum = 1;
let startPage = 1;
let groupingBtn = [];

const beforeButton = () => {
  const gtButton = document.createElement('button');
  gtButton.style.width = '50px';
  gtButton.style.height = '50px';
  gtButton.style.setProperty('border-left', 'none');

  const gtA = document.createElement('a');
  gtA.innerHTML = '&gt';
  gtA.href = `${location.pathname}?pageNum=${startPage * 10 + 1}`;

  if (startPage === groupingBtn.length) {
    gtButton.setAttribute('disabled', 'disabled');
    gtButton.innerHTML = '&gt';
  } else {
    gtButton.appendChild(gtA);
  }

  body.appendChild(gtButton);

  links = document.querySelectorAll('button a');
};

const currentButton = () => {
  for (let i = 1; i <= groupingBtn[startPage - 1]; i++) {
    const button = document.createElement('button');
    button.style.width = '50px';
    button.style.height = '50px';
    button.style.setProperty('border-right', 'none');
    button.style.setProperty('border-left', 'none');

    const a = document.createElement('a');

    a.href = `${location.pathname}?pageNum=${i}`;
    a.innerText = (startPage - 1) * 10 + i;

    button.appendChild(a);
    body.appendChild(button);
  }
};

const afterButton = () => {
  const ltButton = document.createElement('button');
  ltButton.style.width = '50px';
  ltButton.style.height = '50px';
  ltButton.style.setProperty('border-right', 'none');

  const ltA = document.createElement('a');

  ltA.innerHTML = '&lt';
  ltA.href = `${location.pathname}?pageNum=${(startPage - 1) * 10}`;

  if (startPage === 1) {
    ltButton.setAttribute('disabled', 'disabled');
    ltButton.innerHTML = '&lt';
  } else {
    ltButton.appendChild(ltA);
  }

  body.appendChild(ltButton);
};

const settings = () => {
  const search = new URLSearchParams(location.search);
  const searchObj = Object.fromEntries(search);
  console.log(searchObj);

  //   if (searchObj['pageNum']) pageNum = searchObj['pageNum'];
  pageNum = searchObj.pageNum ? searchObj.pageNum : 1;
  console.log('pageNum =', pageNum);
  startPage = Math.floor((pageNum - 1) / 10) + 1;
  console.log('startPage =', startPage);

  const btnCnt = Math.ceil(267 / 10);
  console.log(btnCnt);

  const btnGroup = Math.floor(btnCnt / 10);
  console.log(btnGroup);

  const remainder = btnCnt % 10;
  console.log(remainder);

  for (let i = 0; i < btnGroup; i++) {
    groupingBtn.push(10);
  }
  groupingBtn.push(remainder);

  console.log(groupingBtn);
};

const init = () => {
  settings();
  afterButton();
  currentButton();
  beforeButton();
  Array.from(links).forEach((link) => link.classList.remove('active'));
  console.dir(links);
  links[pageNum - 1].classList.add('active');
};
init();
