const modal = document.querySelector('.modal'),
  closeBtn = document.getElementById('modal__close');

const handelClick = () => {
  modal.classList.add('hidden');
};

const modalInit = () => {
  closeBtn.addEventListener('click', handelClick);
  modal.addEventListener('click', handelClick);
};
modalInit();
