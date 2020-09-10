var id = 'name';

function inputData(type, id, value, inNum) {
  var parent = document.querySelector('#paymentForm .formTable .formTable__body');
  var tr = document.createElement('tr');
  var td = document.createElement('td');
  var td2 = document.createElement('td');
  td2.setAttribute('colspan', '2');

  var label = document.createElement('label');
  var value = document.createTextNode(value);
  label.setAttribute('for', id);
  label.appendChild(value);
  td.appendChild(label);

  for (var i = 0; i < inNum; i++) {
    var input = document.createElement('input');
    input.type = type;
    if (inNum !== 1) {
      input.style.width = '75px';
      input.name = id + i;
      input.id = id + i;
      input.setAttribute('maxlength', 4);
    } else {
      input.name = id;
      input.id = id;
    }
    td2.appendChild(input);

    if (inNum !== 1 && i !== 3) {
      var textNode = document.createTextNode(' - ');
      td2.appendChild(textNode);
    }
  }

  tr.appendChild(td);
  tr.appendChild(td2);
  parent.appendChild(tr);
}

function createTbody() {
  var parent = document.querySelector('#paymentForm .formTable');
  var child = document.querySelector('#paymentForm .formTable .formTable__body');

  parent.removeChild(child);

  var tbody = document.createElement('tbody');
  tbody.className = 'formTable__body';

  parent.appendChild(tbody);
}

function clickHandler(event) {
  var eventType = event.type;
  var inputs = document.querySelectorAll('.formTable thead input');
  var checked = undefined;

  var len = inputs.length;
  for (var i = 0; i < len; i++) {
    if (inputs[i].checked) {
      checked = inputs[i];
      break;
    }
  }

  id = checked.id;

  switch (id) {
    case 'name':
      if (eventType !== 'load') createTbody();
      inputData('text', id, '구매자명', 1);
      inputData('number', 'pay', '결제금액', 1);
      return;
    case 'email':
      createTbody();
      inputData('email', id, '이메일', 1);
      return;
    case 'cardNumber':
      createTbody();
      inputData('number', id, '카드번호', 4);
      return;
  }
}

function submitHandler() {
  var input = document.querySelectorAll('#paymentForm .formTable .formTable__body input');

  var len = input.length;
  for (var i = 0; i < len; i++) {
    var message = input[i].parentElement.previousElementSibling.innerText;
    if (input[i].value.trim() === '') {
      input[i].focus();
      alert(message + '(을/를) 입력하세요.');
      return;
    } else {
      if (input[i].name === 'email') {
        // email 패턴에 대한 정규표현식
        var emailPat = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if (!emailPat.test(input[i].value.trim())) {
          input[i].focus();
          alert(message + '(을/를) 다시 확인해주세요.');
          return;
        }
      }
      var name = input[i].name;
      if (name.includes('cardNumber')) {
        var value = input[i].value.trim();
        if (value.length != 4) {
          input[i].focus();
          alert(message + '(을/를) 다시 확인해주세요.');
          return;
        }
      }
    }
  }

  document.forms.paymentForm.submit();
}

function init() {
  var radius = document.getElementsByName('formTable__select');
  var submitBtn = document.getElementById('submitBtn');

  var len = radius.length;
  for (var i = 0; i < len; i++) {
    radius[i].addEventListener('click', clickHandler);
  }

  window.addEventListener('load', clickHandler);

  submitBtn.addEventListener('click', submitHandler);
}

init();
