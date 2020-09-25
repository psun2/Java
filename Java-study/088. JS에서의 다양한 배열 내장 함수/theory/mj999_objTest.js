const title = 'Test';

const obj = {
  title,
};

console.log(obj);

// TODO: 비구조화 할당
{
  const deepObject = {
    state: {
      information: {
        name: 'frogteam',
        languages: ['korean', 'english', 'chinese'],
      },
    },
    value: 5,
  };

  const {
    state: {
      information: {
        name,
        languages: [firstLang, secondLang, thirdLang],
      },
    },
    value,
  } = deepObject;

  const extracted = {
    name,
    // languages, // languages 가 한번더 구조분해 되어 languages 는 not defined
    firstLang,
    secondLang,
    thirdLang,
    value,
  };

  console.log(extracted);
}
