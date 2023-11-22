# Spring Bean을 활용하여 Plug-in 형태로 간단한 프로젝트 구현하기

플러그인 형태. 즉, api를 구현한 플러그인을 다운로드 받아 기능을 확장하는 형태의 프로젝트. <br>
기능 확장을 위해 Spring Bean을 메인 모듈에서 주입받아서 사용하도록 구현하였으며, app은 코드레벨에서 plugin의 코드를 알지 못하고 api로만 플러그인 클래스 및 메서드를 호출하여 사용하게 된다.

## 프로젝트 구조 설명
### 1. api
플러그인 및 app에서 공통적으로 사용하기 위해 알아야 할 클래스 (추상클래스) 및 인터페이스를 구현하는 모듈

### 2. app
플러그인을 사용하는 메인 모듈

### 3. plugins:plugin들
플러그인 모듈들이 들어있는 모듈과 각 플러그인이 구현되어 있는 하위 모듈

### 4. run directory
전체 프로젝트가 빌드되면 실행시 필요한 jar 파일이 떨어지는 디렉토리

---

## 실행방법
### 1. 디렉토리 이동 <br>
```
cd run
```
### 2. jar 파일 실행 <br>
- 파일 실행 시 plugin jar 파일을 class path에 추가하여 ComponentSacn 대상이 되게 함
```
java -cp app-0.0.1-SNAPSHOT.jar -Dloader.path=plugins/* org.springframework.boot.loader.PropertiesLauncher
```

### 3. 주의사항 <br>
1) ComponentScan 대상이 되도록 신경쓸 것.
   - 현재 프로젝트에서는 jar 패키징 시 java 아래 package naming을 동일하게 가져가고, ComponentScan 패키지를 명시해줌.
2) 프로젝트 빌드 시 루트 프로젝트에 정의된 build task를 실행해야 함.
   - depends On으로 연쇄적인 빌드가 되도록 설정해두었음.

### 4. 참고자료 <br>
- pf4j 라이브러리: https://github.com/pf4j/pf4j
- 외부 라이브러리 추가 부트 옵션: https://www.masterspringboot.com/configuration/web-server/how-to-use-an-external-jar-in-a-spring-boot-application/