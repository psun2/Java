# [Spring] Filter, Interceptor, AOP 차이 및 정리

(출처)[https://goddaehee.tistory.com/154]  

---

공통 프로세스에 대한 고민  

자바 웹 개발을 하다보면, 공통적으로 처리해야 할 업무들이 많다.  

예를들어 로그인 관련(세션체크)처리, 권한체크, XSS(Cross site script)방어, pc와 모바일웹의 분기처리, 로그, 페이지 인코딩 변환 등이 있다.   


공통업무에 관련된 코드를 모든 페이지 마다 작성 해야한다면 중복된 코드가 많아지게 되고  

프로젝트 단위가 커질수록 서버에 부하를 줄 수도있으며, 소스 관리도 되지 않는다.  


즉, 공통 부분은 빼서 따로 관리하는게 좋다.  


이러한 공통업무를 프로그램 흐름의 앞, 중간, 뒤에 추가하여 자동으로 처리할 수 있는 방법이 있고 관련된 내용을 포스팅 하려고 한다.  

위와 같은 공통처리를 위해 활용할 수 있는 것이 3가지가 있다.  

1 . Filter  

2 . Interceptor  

3 . AOP  

스프링에서 사용되는 Filter, Interceptor, AOP 세 가지 기능은 모두 무슨 행동을 하기전에 먼저 실행하거나, 실행한 후에 추가적인 행동을 할 때 사용되는 기능들이다.  


그렇다면 요청에 흐름에 따라 필터, 인터셉터, AOP의 차이점에 대해 알아보자.  


Filter, Interceptor, AOP의 흐름  


ㆍInterceptor와 Filter는 Servlet 단위에서 실행된다. <> 반면 AOP는 메소드 앞에 Proxy패턴의 형태로 실행된다.  

ㆍ실행순서를 보면 Filter가 가장 밖에 있고 그안에 Interceptor, 그안에 AOP가 있는 형태이다.  

 
따라서 요청이 들어오면 Filter → Interceptor → AOP → Interceptor → Filter 순으로 거치게 된다.  


1. 서버를 실행시켜 서블릿이 올라오는 동안에 init이 실행되고, 그 후 doFilter가 실행된다.   

2. 컨트롤러에 들어가기 전 preHandler가 실행된다.  

3. 컨트롤러에서 나와 postHandler, after Completion, doFilter 순으로 진행이 된다.  

4. 서블릿 종료 시 destroy가 실행된다.  


Filter, Interceptor, AOP의 개념  

1.  Filter(필터)  

말그대로 요청과 응답을 거른뒤 정제하는 역할을 한다.  


서블릿 필터는 DispatcherServlet 이전에 실행이 되는데 필터가 동작하도록 지정된 자원의 앞단에서 요청내용을 변경하거나,  여러가지 체크를 수행할 수 있다.  


또한 자원의 처리가 끝난 후 응답내용에 대해서도 변경하는 처리를 할 수가 있다.  

보통 web.xml에 등록하고, 일반적으로 인코딩 변환 처리, XSS방어 등의 요청에 대한 처리로 사용된다.  


EX)  

<!-- 한글 처리를 위한 인코딩 필터 -->  

```
<filter>  

    <filter-name>encoding</filter-name>  

    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>

    <init-param>

        <param-name>encoding</param-name>

        <param-value>UTF-8</param-value>

    </init-param>

</filter>

<filter-mapping>

    <filter-name>encoding</filter-name>

    <url-pattern>/*</url-pattern>

</filter-mapping>
```

해당 필터의 이름은 encoding, 값은 UTF-8인 파라미터를 정의하고 있다.   

필터의 URL-PATTERN을 /*로 정의하면 servlet, jsp뿐만 아니라 이미지와 같은 모든 자원의 요청에도 호출 된다.  

[ 필터의 실행메서드 ]  

ㆍinit() - 필터 인스턴스 초기화  

ㆍdoFilter() - 전/후 처리  

ㆍdestroy() - 필터 인스턴스 종료  

---

2. Interceptor(인터셉터)  


요청에 대한 작업 전/후로 가로챈다고 보면 된다.    


필터는 스프링 컨텍스트 외부에 존재하여 스프링과 무관한 자원에 대해 동작한다.   

하지만 인터셉터는 스프링의 DistpatcherServlet이 컨트롤러를 호출하기 전, 후로 끼어들기 때문에 스프링 컨텍스트(Context, 영역) 내부에서 Controller(Handler)에 관한 요청과 응답에 대해 처리한다.  

스프링의 모든 빈 객체에 접근할 수 있다.  

인터셉터는 여러 개를 사용할 수 있고 로그인 체크, 권한체크, 프로그램 실행시간 계산작업 로그확인 등의 업무처리  

인터셉터의 실행메서드  

preHandler() - 컨트롤러 메서드가 실행되기 전  

postHanler() - 컨트롤러 메서드 실행직 후 view페이지 렌더링 되기 전  

afterCompletion() - view페이지가 렌더링 되고 난 후  

---

3. AOP  

OOP를 보완하기 위해 나온 개념   


객체 지향의 프로그래밍을 했을 때 중복을 줄일 수 없는 부분을 줄이기 위해 종단면(관점)에서 바라보고 처리한다.  


주로 '로깅', '트랜잭션', '에러 처리'등 비즈니스단의 메서드에서 조금 더 세밀하게 조정하고 싶을 때 사용합니다.  

 
Interceptor나 Filter와는 달리 메소드 전후의 지점에 자유롭게 설정이 가능하다.  

Interceptor와 Filter는 주소로 대상을 구분해서 걸러내야하는 반면, AOP는 주소, 파라미터, 애노테이션 등 다양한 방법으로 대상을 지정할 수 있다.  

AOP의 Advice와 HandlerInterceptor의 가장 큰 차이는 파라미터의 차이다.  

Advice의 경우 JoinPoint나 ProceedingJoinPoint 등을 활용해서 호출한다.  

반면 HandlerInterceptor는 Filter와 유사하게 HttpServletRequest, HttpServletResponse를 파라미터로 사용한다.  



AOP의 포인트컷  

@Before: 대상 메서드의 수행 전  

@After: 대상 메서드의 수행 후  

@After-returning: 대상 메서드의 정상적인 수행 후  

@After-throwing: 예외발생 후  

@Around: 대상 메서드의 수행 전/후  
