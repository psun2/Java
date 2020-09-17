/*
// function f1(){
//     alert('짜잔');
// }
// f1();  // <-- f1 은 함수이름이자, 함수데이터 타입을 담은 변수

// (function f2(){
//     alert('f2짜잔2')
// })();   // 함수를 곧바로 호출 가능

// (function f3(a, b){
//     alert("f3 " + (a + b))
// })(10, 200);

// (function(a, b){  // 이름없는 함수
//     alert("f3 " + (a + b))
// })(10, 200);

// 불필요한 전역변수 만들지 않기 위한 목적도 
// 함수의 지역변수화?
*/

(function () {
  // 바다
  var $waterFront = $('#water-front');
  var $waterBack = $('#water-back');

  (function loopSea() {
    $waterFront
      .animate({ bottom: '-65px', left: '-30px' }, 500)
      .animate({ bottom: '-60px', left: '-25px' }, 500);

    $waterBack
      .animate({ bottom: '15px', left: '-20px' }, 500)
      .animate({ bottom: '10px', left: '-25px' }, 500);

    $.when($waterFront, $waterBack).done(loopSea);
  })();

  // 구름
  var $cloud1 = $('#cloud-group-1');
  var $cloud2 = $('#cloud-group-2');

  (function loopCloud() {
    $cloud1
      .animate({ left: '-720px' }, 10000, 'linear')
      .animate({ left: '0px' }, 0);
    $cloud2
      .animate({ left: '0px' }, 10000, 'linear')
      .animate({ left: '720px' }, 0, loopCloud);
    // easing 값: "swing" (디폴트),  "linear"
  })();

  // 보트 & 타이틀 & 폼
  var $boat = $('#boat');
  var $questionMark = $('#question-mark');
  var $title1 = $('#title1');
  var $title2 = $('#title2');
  var $form = $('#form');

  $boat.css({ left: '-220px' }); // 화면 왼쪽 바깥에서 시작
  $questionMark.css({ opacity: 0 }); //  최초에 투명
  $title1.css({ opacity: 0 });
  $title2.css({ opacity: 0 });
  $form.css({ left: '370px' });

  // TODO:
  // 방법1 : animate() + 콜백함수
  //  ↓ callback hell 발생
  // $boat.animate({'left' : '20px'}, 2000, function(){  // $boat 등장
  //     loopBoat();  // $boat 두둥실
  //     $questionMark.delay(500).animate({'opacity': 1}, 1000, function(){  // $questionMark 등장
  //         $title1.animate({'opacity': 1}, 1000, function(){  // $title1 등장
  //             $title2.animate({'opacity': 1}, 1000, function(){ // $title2 등장
  //                 $form.animate({'left': 0}, 500);  // $form 등장
  //             }); // end $title2
  //         }); // end $title1
  //     }); // end $questionMark
  // });  // end $boat

  // TODO:
  // 방법2 : $.when()  .then() 사용
  // $.when() 은 promise 객체를 리턴
  //   .then(success, fail) : 성공, 실패시 콜백함수 지정
  //   .done(success)  : 성공 시 콜백함수 지정
  // $.when($boat.animate({'left' : '20px'}, 2000)) // $boat 등장
  //     .then(function(){
  //         loopBoat();  // $boat 두둥실
  //         return $.when($questionMark.delay(500).animate({'opacity': 1}, 1000))
  //     })
  //     .then(function(){  // $title1 등장
  //         return $.when($title1.animate({'opacity': 1}, 1000))
  //     })
  //     .then(function(){  // $title2 등장
  //         return $.when($title2.animate({'opacity': 1}, 1000))
  //     })
  //     .then(function(){  // $form 등장
  //         $form.animate({'left': 0}, 500);
  //     })

  // TODO:
  // 방법3 : 단일 애니메이션의 연속인 경우 promise() 만으로도 가능.
  $boat
    .animate({ left: '20px' }, 2000)
    .promise()
    .then(function () {
      loopBoat();
      $questionMark.delay(500).animate({ opacity: 1 }, 1000).promise();
    })
    .then(function () {
      $title1.animate({ opacity: 1 }, 1000).promise();
    })
    .then(function () {
      $title2.animate({ opacity: 1 }, 1000).promise();
    })
    .then(function () {
      $form.animate({ left: 0 }, 500);
    });

  // TODO: 방법4
  // 프로미스 객체를 만들어서 리턴
  // 실패
  //   function getPromise(element, animationObject, time, delay) {
  //     return new Promise((resovle, reject) => {
  //       setTimeout(() => {
  //         if (element && animationObject && time) {
  //           setTimeout(() => {}, delay);
  //           console.log(element.animate(animationObject, time));
  //           resovle(element.animate(animationObject, time));
  //         } else {
  //           reject(new Error('모든 엘레멘트들을 넣어주세요'));
  //         }
  //       }, 1);
  //     });
  //   }

  //   getPromise($boat, { left: '20px' }, 2000, 0)
  //     .then((result) => {
  //       $boat = result;
  //       loopBoat();
  //       return getPromise($questionMark, { opacity: 1 }, 1000, 500);
  //     })
  //     .then((result) => {
  //       $questionMark = result;
  //       return getPromise($title1, { opacity: 1 }, 1000, 0);
  //     })
  //     .then((result) => {
  //       console.log(result);
  //       $title1 = result;
  //       return getPromise($title2, { opacity: 1 }, 1000, 0);
  //     })
  //     .then(() => getPromise($form, { left: 0 }, 500, 0))
  //     .catch((e) => console.error(e));

  function loopBoat() {
    $boat
      .animate({ bottom: '15px' }, 500)
      .animate({ bottom: '25px' }, 500, loopBoat);
  }
})();
