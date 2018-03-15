** 개발 환경 
- JAVA 8
- Spring Boot 1.5.2
- DB: H2 (in-memory db)
- Angularjs 
- Bootstrap 활용

** 문제해결 전략 
- Java, Spring Boot를 활용하여 어플리케이션 개발 
- SPA (Single Page Application)로 개발
  > Angularjs를 활용하여 Front-End 구현 
- 쿠폰번호 구성 
  > 16자리의 알파벳, 숫자로 이루어진 쿠폰번호 구현
  > 발급 된 쿠폰번호가 중복되는 경우 재발급 처리(반복)
- 중복된 이메일 쿠폰 발행 불가 
  > 쿠폰 발급 요청 시 이메일 조회를 통해 중복 발급 불가 처리
- 쿠폰번호 생성은 라이브러리 사용없이 직접 구현
  > 난수 생성하는 부분에서 java.util.Random 사용
  > 난수를 사용하여 0-9, a-z, A-Z의 문자를 랜덤 생성
- REST API 구현 및 JSON Object로 통신
  > 쿠폰목록 조회 : GET http://localhost:8080/coupons
  > 쿠폰등록 : POST http://localhost:8080/coupons
- Pagination 기능 구현
  > JPA의 Pageable을 통해 Pagination 기능 구현(Request시에 page번호 입력)

** 프로젝트 빌드 및 실행 방법 
- 실행 방법 
1. 구글 드라이브 jar 파일 다운 
  > https://drive.google.com/file/d/1WHH4NwbfloLS5cicXYE1RS8seo5NdbPI/view?usp=sharing
2. 실행: java -jar spring-boot-coupon-0.0.1-SNAPSHOT.jar
3. http://localhost:8080/ 접속 

- 소스 다운로드 및 실행 방법 
1. github 프로젝트 다운로드 
  > https://github.com/hansolJo/hswork.git
  > zip 파일 압축해제 후 하위의 spring-boot-copuon 폴더로 이동 
2. 빌드: mvnw package
3. 실행: java -jar target/spring-boot-coupon-0.0.1-SNAPSHOT.jar
4. http://localhost:8080/ 접속 
