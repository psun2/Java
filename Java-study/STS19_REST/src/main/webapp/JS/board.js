const form = document.getElementById('frmWrite');

let page = 1;
let pageRows = 10;
let viewItem = undefined; // 가장 최근에 view 한 글의 데이터

const fetchData = {
  method: undefined, // *GET, POST, PUT, DELETE, etc.
  mode: 'cors', // no-cors, cors, *same-origin
  cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
  // credentials: 'same-origin', // include, *same-origin, omit
  headers: {
    'Content-Type': 'application/json',
    // 'Content-Type': 'application/x-www-form-urlencoded',
  },
  redirect: 'follow', // manual, *follow, error
  referrer: 'no-referrer', // no-referrer, *client
  body: undefined, // body data type must match "Content-Type" header
};

// 페이지 최초 로딩되면 게시글 목록 첫페이지분 로딩
// [이전] 버튼 눌렀을때 -> 이전 페이지 게시글목록 로딩
// [다음] 버튼 눌렀을때 -> 다음 페이지 게시글목록 로딩

const chkWrite = () => {
  const formData = new FormData(form);
  const data = Object.fromEntries(formData);
  fetchData.method = 'POST';
  fetchData.body = JSON.stringify(data);

  fetch('./', fetchData) //
    .then((response) => {
      if (response.status === 200) return response.json();
    }) //
    .then((json) => {
      if (json.status === 'OK') {
        alert(`INSERT 성공 ${json.count}개 : ${json.status}`);
      } else {
        alert(`INSERT 실패  ${json.status} : ${json.message}`);
      }
    }) //
    .catch((e) => alert(e));

  // request 끝나고 나서, 기존 입력된 내용 지우기
  document.getElementById('frmWrite').reset();

  return false;
}; // end chkWrite()

const chkDelete = () => {
  //   console.log(document.querySelectorAll(`#list tbody input[name='uid']`));
  //   console.log(document.querySelectorAll('#list tbody input[name=uid]'));

  let uids = [];

  Array.from(document.querySelectorAll('#list tbody input[name=uid]')).forEach(
    (input) => {
      if (input.checked) {
        uids.push(input.value);
      }
    },
  );

  if (uids.length === 0) {
    alert('삭제할 글을 체크해주세요');
    return;
  } else {
    if (!confirm(`${uids.length} 개의 글을 삭제하시겠습니까 ?`)) return false;
  }

  const data = {
    uids: uids,
  };

  fetchData.method = 'DELETE';
  fetchData.body = JSON.stringify(data);

  fetch('./', fetchData) //
    .then((response) => {
      if (response.status === 200) return response.json();
    }) //
    .then((json) => {
      if (json.status === 'OK') {
        alert(`DELETE 성공 : ${json.count}개`);
        loadPage(window.page); // 현재 페이지 목록 로딩
      } else {
        alert(`DELETE 실패 ${json.message}`);
      }
    }) //
    .catch((e) => alert(e));
}; // end chkDelete()

const deleteUid = (uid) => {
  if (!confirm(`${uid} 글을 삭제하시겠습니까?`)) return false;

  let uids = [uid];

  const data = {
    uids: uids,
  };

  fetchData.method = 'DELETE';
  fetchData.body = JSON.stringify(data);
  fetch('./', fetchData) //
    .then((response) => {
      if (response.status === 200) return response.json();
    }) //
    .then((json) => {
      if (json.status === 'OK') {
        alert(`DELETE 성공 : ${json.count}개`);
        loadPage(window.page); // 현재 페이지 목록 로딩
      } else {
        alert(`DELETE 실패 ${json.message}`);
      }
    }) //
    .catch((e) => alert(e));

  return true;
}; // end deleteUid()

const chkUpdate = () => {
  const formData = new FormData(form);
  const data = Object.fromEntries(formData);
  fetchData.method = 'PUT';
  fetchData.body = JSON.stringify(data);
  fetch('./', fetchData) //
    .then((response) => {
      if (response.status === 200) return response.json();
    }) //
    .then((json) => {
      if (json.status === 'OK') {
        alert(`UPDATE 성공 : ${json.count}개`);
        loadPage(window.page); // 현재 페이지 목록 로딩
      } else {
        alert(`UPDATE 실패 ${json.message}`);
      }

      // 미비 : 폼 검증
      //      : 업데이트 후 목록의 변경된 제목 반영
      // 현재 팝업 닫기 (???)
      document.getElementById('dlg_write').style.display = 'none';
    }) //
    .catch((e) => alert(e));
}; // end chkUpdate()

const setPopup = (mode) => {
  switch (mode) {
    // 글 읽기
    case 'view':
      document.querySelector('#dlg_write .title').innerText = '글 읽기';
      document.querySelector('#dlg_write .btn_group_header').style.display =
        'bolck';
      document.querySelector('#dlg_write .btn_group_write').style.display =
        'none';
      document.querySelector('#dlg_write .btn_group_view').style.display =
        'bolck';
      document.querySelector('#dlg_write .btn_group_update').style.display =
        'none';

      document.querySelector(
        '#dlg_write #viewcnt',
      ).innerText = `# ${window.viewItem.uid} - 조회수: ${window.viewItem.viewcnt}`;
      document.querySelector('#dlg_write #regdate').innerText =
        window.viewItem.regdate;

      document.querySelector("#dlg_write input[name='uid']").value =
        window.viewItem.uid; // 나중에 삭제나 수정을 위해 필요

      document.querySelector("#dlg_write input[name='subject']").value =
        window.viewItem.subject;
      document
        .querySelector("#dlg_write input[name='subject']")
        .setAttribute('readonly', true);
      document.querySelector("#dlg_write input[name='subject']").style.border =
        'none';

      document.querySelector("#dlg_write input[name='name']").value =
        window.viewItem.name;
      document
        .querySelector("#dlg_write input[name='name']")
        .setAttribute('readonly', true);
      document.querySelector("#dlg_write input[name='name']").style.border =
        'none';

      document.querySelector("#dlg_write textarea[name='content']").value =
        window.viewItem.content;
      document
        .querySelector("#dlg_write textarea[name='content']")
        .setAttribute('readonly', true);
      document.querySelector(
        "#dlg_write textarea[name='content']",
      ).style.border = 'none';
      break;

    // 글 쓰기
    case 'write':
      document.querySelector('#frmWrite')[0].reset(); // form 내의 기존 내용 reset
      document.querySelector('#dlg_write .title').innerText = '새글 작성';
      document.querySelector('#dlg_write .btn_group_header').style.display =
        'none';
      document.querySelector('#dlg_write .btn_group_write').style.display =
        'block';
      document.querySelector('#dlg_write .btn_group_view').style.display =
        'none';
      document.querySelector('#dlg_write .btn_group_update').style.display =
        'none';

      document
        .querySelector("#dlg_write input[name='subject']")
        .setAttribute('readonly', false);
      document.querySelector("#dlg_write input[name='subject']").style.border =
        '1px solid #ccc';
      document
        .querySelector("#dlg_write input[name='name']")
        .setAttribute('readonly', false);
      document.querySelector("#dlg_write input[name='name']").style.border =
        '1px solid #ccc';
      document
        .querySelector("#dlg_write textarea[name='content']")
        .setAttribute('readonly', false);
      document.querySelector(
        "#dlg_write textarea[name='content']",
      ).style.border = '1px solid #ccc';
      break;

    // 글 수정
    case 'update':
      document.querySelector('#dlg_write .title').innterText = '글 수정';

      document.querySelector('#dlg_write .btn_group_header').style.display =
        'block';
      document.querySelector('#dlg_write .btn_group_write').style.display =
        'none';
      document.querySelector('#dlg_write .btn_group_view').style.display =
        'none';
      document.querySelector('#dlg_write .btn_group_update').style.display =
        'block';

      document
        .querySelector("#dlg_write input[name='subject']")
        .setAttribute('readonly', false);
      document.querySelector("#dlg_write input[name='subject']").style.border =
        '1px solid #ccc';
      document
        .querySelector("#dlg_write input[name='name']")
        .setAttribute('readonly', true); // 작성자는 수정 불가
      document
        .querySelector("#dlg_write textarea[name='content']")
        .setAttribute('readonly', false);
      document.querySelector(
        "#dlg_write textarea[name='content']",
      ).style.border = '1px solid #ccc';
      break;
  }
}; // end setPopup()

const addViewEvent = (e) => {
  // 읽어오기
  fetch(`./${e.target.getAttribute('data-uid')}`) //
    .then((response) => {
      if (response.status === 200) return response.json();
    }) //
    .then((json) => {
      if (json.status === 'OK') {
        // 글 읽어오기 성공하면, 내부 데이터 세팅하고, 핍업대화상자 보여주기
        window.viewItem = json.data[0];
        setPopup('view'); // 글 읽기 팝업 셋팅
        // $('#dlg_write').show(); // 팝업 띄우기
        document.getElementById('dlg_write').style.display = 'block';
        // document.getElementById('dlg_write').style.display = 'flex';

        // 리스트 상의 조회수도 증가
        // TODO:
        document.querySelector(
          `#list span[data-viewcnt="${window.viewItem.uid}"]`,
        ).innerText = window.viewItem.viewcnt;
      } else {
        alert(`view 실패 ${data.message}`);
      }
    }); //
  // .catch((e) => alert(e));
}; // end addViewEvent()

const buildPagination = (writePages, totalPage, curPage, pageRows) => {
  let result = ''; // 최종적으로 페이징에 나타날 HTML 문자열 <li> 태그로 구성

  // 페이징에 보여질 숫자들 (시작숫자 start_page ~ 끝숫자 end_page)
  const start_page = parseInt((curPage - 1) / writePages) * writePages + 1;
  let end_page = start_page + writePages - 1;

  if (end_page >= totalPage) {
    end_page = totalPage;
  }

  // ■ << 표시 여부
  if (curPage > 1) {
    result += `<li><a onclick='loadPage(1);' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>`;
  }
  // ■  < 표시 여부
  if (start_page > 1) {
    result += `<li><a onclick='loadPage(${
      start_page - 1
    });' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>`;
  }

  // ■  페이징 안의 '숫자' 표시
  if (totalPage > 1) {
    // 전체페이지가 2 이상일때만 실행
    // 페이지가 1개밖에없는데 페이지 네이션 보여줄 필요가 없음
    for (let i = start_page; i <= end_page; i++) {
      if (curPage != i) {
        result += `<li><a onclick=loadPage(${i});>${i}</a><li>`;
      } else {
        result += `<li><a class='active tooltip-top' title='현재페이지'>${i}</a><li>`;
      }
    }
  }

  // ■ > 표시
  if (totalPage > end_page) {
    result += `<li><a onclick=loadPage(${
      end_page + 1
    }); class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>`;
  }

  // ■ >> 표시
  if (curPage < totalPage) {
    result += `<li><a onclick=loadPage(${totalPage}); class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>`;
  }

  return result;
}; // end buildPagination()

// <select> 에서 pageRows 값 변경할때마다 호출
const changPageRows = () => {
  windows.pageRows = document.getElementById('rows').value;
  loadPage(window.page);
}; // end changPageRows()

const updateList = (json) => {
  let result = '';
  const count = json.count;
  window.page = json.page;
  window.pageRows = json.pagerows;

  json.data.forEach((item) => {
    result += '<tr>';
    result += `<td><input type='checkbox' name='uid' value='${item.uid}' /></td>`;
    result += `<td> ${item.uid}</td>`;
    result += `<td><span class='subject' data-uid='${item.uid}'>${item.subject}</span></td>`;
    result += `<td>${item.name}</td>`;
    result += `<td><span data-viewcnt='${item.uid}'>${item.viewcnt}</span></td>`;
    result += `<td>${item.regdate}</td>`; // DTO 의 getRegDate() 를 수정했으면 원하는 문자열 형태로 받을수도 있다.
    result += '</tr>';
  });

  document.querySelector('#list tbody').innerHTML = result; // 업데이트

  // 페이지 정보 업데이트
  document.getElementById(
    'pageinfo',
  ).innerText = `${json.page}/${json.totalpage}페이지, ${json.totalcnt}개의 글`;

  // pageRows [~ 몇개씩 보기]
  let txt = `<select id='rows' onchange='changPageRows();'>`;
  txt += `<option value=${10} ${
    window.pageRows === 10 ? 'selected' : ''
  }>10개씩</option>`;
  txt += `<option value=${20} ${
    window.pageRows === 20 ? 'selected' : ''
  }>20개씩</option>`;
  txt += `<option value=${30} ${
    window.pageRows === 30 ? 'selected' : ''
  }>30개씩</option>`;
  txt += `<option value=${100} ${
    window.pageRows === 100 ? 'selected' : ''
  }>100개씩</option>`;
  txt += '</select>';

  document.getElementById('pageRows').innerHTML = txt;

  // [페이징] 정보 업데이트
  const pagination = buildPagination(
    json.writepages,
    json.totalpage,
    json.page,
    json.pageRows,
  );
  document.getElementById('pagination').innerHTML = pagination;
}; // end updateList()

const loadPage = (page) => {
  // 현재 경로 /board/rest
  fetch(`./${page}/${pageRows}`) // /board/{page}/{pageRows}
    .then((response) => {
      if (response.status === 200) {
        return response.json();
      }
    }) //
    .then((json) => {
      if (json.status === 'OK') {
        // 페이지 로딩 성공한 뒤에
        // 업데이트 된 list 에 view 동작 이벤트 추가
        updateList(json); // view 그리기
        Array.from(document.querySelectorAll('#list .subject')).forEach(
          (subject) => {
            subject.addEventListener('click', addViewEvent); // 업데이트된 list에 view 동작 이벤트 추가
          },
        );
      } else {
        alert('내용이 없습니다.');
      }
    }) //
    .catch((e) => alert(e));
}; // end loadPage()

const init = () => {
  // 페이지 최조 로딩되면, 게시판 1page 분량 가져오기
  loadPage(page, pageRows);

  // 대화상자 close 버튼 누르면
  document
    .querySelector('.modal .close')
    .addEventListener('click', function () {
      document.querySelector('.modal').style.display = 'none';
    });

  // 글 작성 버튼 누르면 팝업
  document.querySelector('#btnWrite').addEventListener('click', function () {
    setPopup('write');
    document.querySelector('#dlg_write').style.display = 'block';
  });

  // 글 작성 폼 submit 되면
  document.querySelector('#frmWrite').addEventListener('submit', function (e) {
    e.preventDefault();
    document.querySelector('.modal').style.display = 'none';
    loadPage(1);
    return chkWrite();
  });

  // 글 삭제 버튼 누르면
  document.querySelector('#btnDel').addEventListener('click', function () {
    chkDelete();
  });

  // 글 읽기 대화상자에서 삭제버튼 누르면 해당 글(uid) 삭제 진행
  document.querySelector('#viewDelete').addEventListener('click', function () {
    const uid = window.viewItem.uid;
    if (deleteUid(uid)) {
      document.querySelector('.modal').style.display = 'none';
    }
  });

  // 글 읽기 대화상자에서 수정버튼 누르면
  document.querySelector('#viewUpdate').addEventListener('click', function () {
    setPopup('update');
  });

  // 글 수정 완료 버튼 누름
  document.querySelector('#updateOk').addEventListener('click', function () {
    chkUpdate();
  });
}; // end init()

init();
