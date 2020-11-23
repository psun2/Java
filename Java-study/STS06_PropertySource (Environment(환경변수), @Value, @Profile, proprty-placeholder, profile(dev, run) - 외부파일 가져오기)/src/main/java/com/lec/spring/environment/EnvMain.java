package com.lec.spring.environment;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

public class EnvMain {
	public static void main(String[] args) {
		System.out.println("Main() 시작\n");

		// ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(); // <-- ConfigurableApplicationContext 소속
																				// 메소드
		// 결국 GenericXmlApplicationContext 도 ConfigurableApplicationContext 를 상속 받기 때문에
		// getEnvironment 메소드 사용 가능
		System.out.println("외부파일 컨테이너 생성");

		ConfigurableEnvironment environment = ctx.getEnvironment();
		MutablePropertySources propertySources = environment.getPropertySources();

		try {
			// getPropertySources 에 propertySources 를 addLast 가장 마지막 파일로 setting 합니다.
			propertySources.addLast(new ResourcePropertySource("admin.auth"));
			propertySources.addLast(new ResourcePropertySource("member.auth"));
			// 결론적으로 등록되는 순서가 굉장히 중요합니다.!!!
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 이제, Environment 에서 원하는 Property 에 접근 가능
		// 굳이 '어느 PS' 의 '어느 property' 를 요청할 필요가 없다
		// '어느 property' 에 대한것만 요청하면
		// propertySources 에 소속된 모든 PS 들을 다 스캔하면서 찾아낸다!
		System.out.println(environment.getProperty("admin.id")); // system
		System.out.println(environment.getProperty("admin.pw")); // 123456
		// 외부파일에 똑같의 key 가 있다면 나중에 생성된 key 로 덮어씌우기 합니다.

		// ✔ member.id 가 kim이 아닌 이유
		// member.auth 파일을 addLast 로 가장 마지막 파일이 되었고,
		// environment.getProperty 을 할때 맨 앞의 [파일]을 기준으로 찾기 시작합니다.
		// 현재 제일 앞의 파일인 admin.auth 파일을 끝까지 읽어 member.id 를 찾았고
		// environment.getProperty 는 찾았으면 더이상 다음 파일을 읽지 않습니다.
		// admin.id 가 sys 가 system인 이유도 일단 한 파일을 전부다 읽기때문에
		// 마지막에 있는 system이 읽히는 것 입니다.
		System.out.println(environment.getProperty("member.id")); // park
		System.out.println(environment.getProperty("member.pw")); // aaaa

		// 환경파일을 설정한뒤 바로 load 해서 bean 객체를 얻어 올 수 있습니다.
		ctx.load("classpath:environmentSetting.xml");
		ctx.refresh();
		AdminConnection adminConnection = ctx.getBean("adminConnection", AdminConnection.class);
		System.out.println(adminConnection.getAdminId()); // system
		System.out.println(adminConnection.getAdminPw()); // 123456
		System.out.println(adminConnection.getEnv());
		// StandardEnvironment {activeProfiles=[], defaultProfiles=[default], propertySources=[PropertiesPropertySource@1301664418 {name='systemProperties', properties={java.runtime.name=Java(TM) SE Runtime Environment, sun.boot.library.path=C:\Program Files\Java\jdk1.8.0_241\jre\bin, java.vm.version=25.241-b07, java.vm.vendor=Oracle Corporation, java.vendor.url=http://java.oracle.com/, path.separator=;, java.vm.name=Java HotSpot(TM) 64-Bit Server VM, file.encoding.pkg=sun.io, user.country=KR, user.script=, sun.java.launcher=SUN_STANDARD, sun.os.patch.level=, java.vm.specification.name=Java Virtual Machine Specification, user.dir=D:\Study\Java\Java-study\STS06_PropertySource (Environment(환경변수), @Value, @Profile, proprty-placeholder, profile(dev, run) - 외부파일 가져오기), java.runtime.version=1.8.0_241-b07, java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment, java.endorsed.dirs=C:\Program Files\Java\jdk1.8.0_241\jre\lib\endorsed, os.arch=amd64, java.io.tmpdir=C:\Users\박성언\AppData\Local\Temp\, line.separator=
		// , java.vm.specification.vendor=Oracle Corporation, user.variant=, os.name=Windows 10, sun.jnu.encoding=MS949, java.library.path=C:\Program Files\Java\jdk1.8.0_241\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:\oraclexe\app\oracle\product\11.2.0\server\bin;C:\Program Files\Java\jdk1.8.0_241\bin;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Windows\System32\OpenSSH\;C:\Program Files\Git\cmd;C:\Program Files\nodejs\;C:\ProgramData\chocolatey\bin;C:\ProgramData\chocolatey\lib\maven\apache-maven-3.6.3\bin;C:\Users\박성언\AppData\Local\Microsoft\WindowsApps;C:\Users\박성언\AppData\Local\atom\bin;C:\Users\박성언\AppData\Roaming\npm;C:\Users\박성언\AppData\Local\GitHubDesktop\bin;C:\Users\박성언\AppData\Local\Programs\Microsoft VS Code\bin;;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\bin;;C:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin;;., java.specification.name=Java Platform API Specification, java.class.version=52.0, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, os.version=10.0, user.home=C:\Users\박성언, user.timezone=Asia/Seoul, java.awt.printerjob=sun.awt.windows.WPrinterJob, file.encoding=UTF-8, java.specification.version=1.8, java.class.path=D:\Study\Java\Java-study\STS06_PropertySource (Environment(환경변수), @Value, @Profile, proprty-placeholder, profile(dev, run) - 외부파일 가져오기)\target\classes;C:\Users\박성언\.m2\repository\org\springframework\spring-context\5.2.11.RELEASE\spring-context-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\springframework\spring-aop\5.2.11.RELEASE\spring-aop-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\springframework\spring-beans\5.2.11.RELEASE\spring-beans-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\springframework\spring-core\5.2.11.RELEASE\spring-core-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\springframework\spring-jcl\5.2.11.RELEASE\spring-jcl-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\springframework\spring-expression\5.2.11.RELEASE\spring-expression-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\springframework\spring-tx\5.2.11.RELEASE\spring-tx-5.2.11.RELEASE.jar;C:\Users\박성언\.m2\repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;C:\Users\박성언\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\박성언\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\박성언\.m2\repository\org\hibernate\hibernate-entitymanager\5.4.24.Final\hibernate-entitymanager-5.4.24.Final.jar;C:\Users\박성언\.m2\repository\org\jboss\logging\jboss-logging\3.4.1.Final\jboss-logging-3.4.1.Final.jar;C:\Users\박성언\.m2\repository\org\hibernate\hibernate-core\5.4.24.Final\hibernate-core-5.4.24.Final.jar;C:\Users\박성언\.m2\repository\org\javassist\javassist\3.27.0-GA\javassist-3.27.0-GA.jar;C:\Users\박성언\.m2\repository\antlr\antlr\2.7.7\antlr-2.7.7.jar;C:\Users\박성언\.m2\repository\org\jboss\jandex\2.1.3.Final\jandex-2.1.3.Final.jar;C:\Users\박성언\.m2\repository\com\fasterxml\classmate\1.5.1\classmate-1.5.1.jar;C:\Users\박성언\.m2\repository\javax\activation\javax.activation-api\1.2.0\javax.activation-api-1.2.0.jar;C:\Users\박성언\.m2\repository\javax\xml\bind\jaxb-api\2.3.1\jaxb-api-2.3.1.jar;C:\Users\박성언\.m2\repository\org\glassfish\jaxb\jaxb-runtime\2.3.1\jaxb-runtime-2.3.1.jar;C:\Users\박성언\.m2\repository\org\glassfish\jaxb\txw2\2.3.1\txw2-2.3.1.jar;C:\Users\박성언\.m2\repository\com\sun\istack\istack-commons-runtime\3.0.7\istack-commons-runtime-3.0.7.jar;C:\Users\박성언\.m2\repository\org\jvnet\staxex\stax-ex\1.8\stax-ex-1.8.jar;C:\Users\박성언\.m2\repository\com\sun\xml\fastinfoset\FastInfoset\1.2.15\FastInfoset-1.2.15.jar;C:\Users\박성언\.m2\repository\org\dom4j\dom4j\2.1.3\dom4j-2.1.3.jar;C:\Users\박성언\.m2\repository\org\hibernate\common\hibernate-commons-annotations\5.1.2.Final\hibernate-commons-annotations-5.1.2.Final.jar;C:\Users\박성언\.m2\repository\javax\persistence\javax.persistence-api\2.2\javax.persistence-api-2.2.jar;C:\Users\박성언\.m2\repository\net\bytebuddy\byte-buddy\1.10.17\byte-buddy-1.10.17.jar;C:\Users\박성언\.m2\repository\org\jboss\spec\javax\transaction\jboss-transaction-api_1.2_spec\1.1.1.Final\jboss-transaction-api_1.2_spec-1.1.1.Final.jar;C:\Users\박성언\.m2\repository\org\projectlombok\lombok\1.18.16\lombok-1.18.16.jar, user.name=박성언, java.vm.specification.version=1.8, sun.java.command=com.lec.spring.environment.EnvMain, java.home=C:\Program Files\Java\jdk1.8.0_241\jre, sun.arch.data.model=64, user.language=ko, java.specification.vendor=Oracle Corporation, awt.toolkit=sun.awt.windows.WToolkit, java.vm.info=mixed mode, java.version=1.8.0_241, java.ext.dirs=C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext;C:\Windows\Sun\Java\lib\ext, sun.boot.class.path=C:\Program Files\Java\jdk1.8.0_241\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\rt.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\sunrsasign.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_241\jre\classes, java.vendor=Oracle Corporation, file.separator=\, java.vendor.url.bug=http://bugreport.sun.com/bugreport/, sun.io.unicode.encoding=UnicodeLittle, sun.cpu.endian=little, sun.desktop=windows, sun.cpu.isalist=amd64}}, SystemEnvironmentPropertySource@1408652377 {name='systemEnvironment', properties={USERDOMAIN_ROAMINGPROFILE=DESKTOP-EJRGSL9, LOCALAPPDATA=C:\Users\박성언\AppData\Local, ChocolateyLastPathUpdate=132479765148853380, PROCESSOR_LEVEL=6, IntelliJ IDEA=C:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin;, USERDOMAIN=DESKTOP-EJRGSL9, FPS_BROWSER_APP_PROFILE_STRING=Internet Explorer, LOGONSERVER=\\DESKTOP-EJRGSL9, JAVA_HOME=C:\Program Files\Java\jdk1.8.0_241, SESSIONNAME=Console, ALLUSERSPROFILE=C:\ProgramData, PROCESSOR_ARCHITECTURE=AMD64, PSModulePath=C:\Program Files\WindowsPowerShell\Modules;C:\Windows\system32\WindowsPowerShell\v1.0\Modules, SystemDrive=C:, OneDrive=C:\Users\박성언\OneDrive, APPDATA=C:\Users\박성언\AppData\Roaming, USERNAME=박성언, ChocolateyInstall=C:\ProgramData\chocolatey, ProgramFiles(x86)=C:\Program Files (x86), CommonProgramFiles=C:\Program Files\Common Files, Path=C:\oraclexe\app\oracle\product\11.2.0\server\bin;C:\Program Files\Java\jdk1.8.0_241\bin;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Windows\System32\OpenSSH\;C:\Program Files\Git\cmd;C:\Program Files\nodejs\;C:\ProgramData\chocolatey\bin;C:\ProgramData\chocolatey\lib\maven\apache-maven-3.6.3\bin;C:\Users\박성언\AppData\Local\Microsoft\WindowsApps;C:\Users\박성언\AppData\Local\atom\bin;C:\Users\박성언\AppData\Roaming\npm;C:\Users\박성언\AppData\Local\GitHubDesktop\bin;C:\Users\박성언\AppData\Local\Programs\Microsoft VS Code\bin;;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\bin;;C:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin;, FPS_BROWSER_USER_PROFILE_STRING=Default, PATHEXT=.COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC, DriverData=C:\Windows\System32\Drivers\DriverData, OS=Windows_NT, COMPUTERNAME=DESKTOP-EJRGSL9, IntelliJ IDEA Community Edition=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\bin;, PROCESSOR_REVISION=3a09, CommonProgramW6432=C:\Program Files\Common Files, ComSpec=C:\Windows\system32\cmd.exe, ProgramData=C:\ProgramData, ProgramW6432=C:\Program Files, HOMEPATH=\Users\박성언, SystemRoot=C:\Windows, TEMP=C:\Users\박성언\AppData\Local\Temp, HOMEDRIVE=C:, PROCESSOR_IDENTIFIER=Intel64 Family 6 Model 58 Stepping 9, GenuineIntel, USERPROFILE=C:\Users\박성언, TMP=C:\Users\박성언\AppData\Local\Temp, M2_HOME=C:\ProgramData\chocolatey\lib\maven\apache-maven-3.6.3, CommonProgramFiles(x86)=C:\Program Files (x86)\Common Files, ProgramFiles=C:\Program Files, PUBLIC=C:\Users\Public, NUMBER_OF_PROCESSORS=4, windir=C:\Windows, =::=::\}}, ResourcePropertySource@990416209 {name='class path resource [admin.auth]', properties={admin.id=system, member.id=park, admin.pw=123456}}, ResourcePropertySource@394714818 {name='class path resource [member.auth]', properties={admin.id=master, member.id=kim, member.pw=aaaa}}]}
		// 04:56:30.275 [main] DEBUG org.springframework.context.support.GenericXmlApplicationContext - Closing org.springframework.context.support.GenericXmlApplicationContext@73a28541, started on Tue Nov 24 04:56:30 KST 2020

		ctx.close();
		System.out.println("\nMain() 종료");
	}
}
