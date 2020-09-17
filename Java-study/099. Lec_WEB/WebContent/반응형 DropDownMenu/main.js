const projectsBtn = document.querySelector('.projects button'),
  productsBtn = document.querySelector('.products button'),
  projectsUl = document.querySelector('.projects ul'),
  productsUl = document.querySelector('.products ul');

let currentMenu = undefined;

const mouseleave = () => {
  projectsUl.addEventListener('mouseleave', handleFocus);
  productsUl.addEventListener('mouseleave', handleFocus);
};

const handleClick = (event) => {
  if (currentMenu) currentMenu.classList.remove('active');
  const target = event.target;
  const displayTarget = target.nextElementSibling; // ul
  if (currentMenu === displayTarget) return;
  displayTarget.classList.add('active');
  currentMenu = displayTarget;
};

const handleFocus = (event) => {
  // ul에서 나갔을때
  const target = event.target;
  target.classList.remove('active');
  currentMenu = undefined;
};

const init = () => {
  projectsBtn.addEventListener('click', handleClick);
  productsBtn.addEventListener('click', handleClick);
  mouseleave();
};

init();
