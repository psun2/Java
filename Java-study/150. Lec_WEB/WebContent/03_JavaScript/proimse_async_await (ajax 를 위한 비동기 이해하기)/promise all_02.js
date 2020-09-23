const resultPrint = document.getElementById('resultPrint');

// promise.all

// promise를 동시에 처리하기
function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

const getDog = async () => {
  await sleep(1000);
  return '강아지';
};

const getRabbit = async () => {
  await sleep(5000);
  return '토끼';
};

const getTurtle = async () => {
  await sleep(10000);
  return '거북이는 느려요';
};

async function process() {
  const start = Date.now();
  const result = await Promise.all([getDog(), getRabbit(), getTurtle()]);
  console.log(result);
  console.log(Date.now() - start + '초 걸렸습니다.');
  resultPrint.innerHTML = `result : [${result}] <br /> dog : ${result[0]} <br /> rabbit : ${result[1]} <br /> turtle : ${result[2]} <br />`;
}

process();

// ["강아지", "토끼", "거북이는 느려요"]
// 0: "강아지"
// 1: "토끼"
// 2: "거북이는 느려요"
// 10000초 걸렸습니다.
