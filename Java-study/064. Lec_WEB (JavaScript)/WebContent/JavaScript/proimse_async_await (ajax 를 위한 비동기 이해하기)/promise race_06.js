const resultPrint = document.getElementById('resultPrint');

// promise.race
// 등록한 promise중에 가장 빨리 끝난 것 하나만 결과 값으로 나타내어 준다.

// promise race
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
  const first = await Promise.race([getDog(), getRabbit(), getTurtle()]);
  console.log(first); // 강아지
  resultPrint.innerText = first;
}

process();
