const resultPrint = document.getElementById('resultPrint');

// promise.all

// 지금은 하나씩 실행중인 예 입니다.
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
  const dog = await getDog();
  console.log(dog); // 강아지
  resultPrint.innerHTML += `dog : ${dog} <br />`;

  const rabbit = await getRabbit();
  console.log(rabbit); // 토끼
  resultPrint.innerHTML += `rabbit : ${rabbit} <br />`;

  const turtle = await getTurtle();
  console.log(turtle); // 거북이는 느려요
  resultPrint.innerHTML += `turtle : ${turtle} <br />`;

  resultPrint.innerHTML += `${Date.now() - start} 초 걸렸습니다.`;
}

process();
