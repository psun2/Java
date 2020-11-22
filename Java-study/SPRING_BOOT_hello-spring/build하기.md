#build

1. 서버종료
2. 프로젝트 폴더 진입
3. ./gradlew build
4. build 폴더 진입
5. build 폴더 내의 libs 폴더 진입
6. java -jar 옵션으로 생성된 jar 실행
7. 재빌드: ./gradlew clean build 

빌드하고 실행하기
콘솔로 이동
1. ./gradlew build
2. cd build/libs
3. java -jar hello-spring-0.0.1-SNAPSHOT.jar
4. 실행 확인
윈도우 사용자를 위한 팁
콘솔로 이동 명령 프롬프트(cmd)로 이동
./gradlew gradlew.bat 를 실행하면 됩니다.
명령 프롬프트에서 gradlew.bat 를 실행하려면 gradlew 하고 엔터를 치면 됩니다.
gradlew build