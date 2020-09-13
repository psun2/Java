const resultPrint = document.getElementById('resultPrint');

// promise.all
// promise all은 등록한 promise중 하나라도 error가 나도
// 전체 promise가 error가 난 것으로 간주 된다.

// promise error
function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function sleep2(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const error = new Error('promise all Error');
      reject(error);
    }, ms);
  });
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
  await sleep2(10000);
  return '거북이는 느려요';
};

async function process() {
  try {
    const [dog, rabbit, turtle] = await Promise.all([
      getDog(),
      getRabbit(),
      getTurtle(),
    ]);

    console.log([dog, rabbit, turtle]);
    console.log(dog);
    console.log(rabbit);
    console.log(turtle);
    resultPrint.innerHTML = `[dog, rabbit, turtle] : [${[
      dog,
      rabbit,
      turtle,
    ]}] <br /> dog : ${dog} <br /> rabbit : ${rabbit} <br /> turtle : ${turtle} <br />`;
  } catch (e) {
    console.error(e);
    resultPrint.innerHTML = `${e}`;
  }
}

process();
