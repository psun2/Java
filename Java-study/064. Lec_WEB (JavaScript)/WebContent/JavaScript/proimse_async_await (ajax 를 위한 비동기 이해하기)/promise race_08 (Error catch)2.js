const resultPrint = document.getElementById('resultPrint');

// promise.race
// promise race는 가장 빨리 끝난게 error 일때만 error로 간주.
// 만약 가장 빨리 끝난것 보다 늦게 끝난 것이 error이 발생하면 error로 간주 하지 않는다

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
  await sleep2(1000);
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
  try {
    const first = await Promise.race([getDog(), getRabbit(), getTurtle()]);
    console.log(first);
    resultPrint.innerText = first;
  } catch (e) {
    console.error(e); // Error: promise race Error
    resultPrint.innerText = e;
  }
}
process();
