const resultPrint = document.getElementById('resultPrint');

// promise.race
// promise race는 가장 빨리 끝난게 error 일때만 error로 간주.
// 만약 가장 빨리 끝난것 보다 늦게 끝난 것이 error가 발생하면 error로 간주 하지 않는다

// promise race error
function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function sleep2(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const error = new Error('promise race Error');
      reject(error);
    }, ms);
  });
}

const getDog = async () => {
  await sleep(1000);
  return '강아지';
};

const getRabbit = async () => {
  await sleep2(5000);
  return '토끼';
};

const getTurtle = async () => {
  await sleep2(10000);
  return '거북이는 느려요';
};

async function process() {
  try {
    const first = await Promise.race([getDog(), getRabbit(), getTurtle()]);
    console.log(first); // 강아지
    resultPrint.innerText = first;
  } catch (e) {
    console.error(e);
    resultPrint.innerText = e;
  }
}
process();
