** ���� ȯ�� 
- JAVA 8
- Spring Boot 1.5.2
- DB: H2 (in-memory db)
- Angularjs 
- Bootstrap Ȱ��

** �����ذ� ���� 
- Java, Spring Boot�� Ȱ���Ͽ� ���ø����̼� ���� 
- SPA (Single Page Application)�� ����
  > Angularjs�� Ȱ���Ͽ� Front-End ���� 
- ������ȣ ���� 
  > 16�ڸ��� ���ĺ�, ���ڷ� �̷���� ������ȣ ����
  > �߱� �� ������ȣ�� �ߺ��Ǵ� ��� ��߱� ó��(�ݺ�)
- �ߺ��� �̸��� ���� ���� �Ұ� 
  > ���� �߱� ��û �� �̸��� ��ȸ�� ���� �ߺ� �߱� �Ұ� ó��
- ������ȣ ������ ���̺귯�� ������ ���� ����
  > ���� �����ϴ� �κп��� java.util.Random ���
  > ������ ����Ͽ� 0-9, a-z, A-Z�� ���ڸ� ���� ����
- REST API ���� �� JSON Object�� ���
  > ������� ��ȸ : GET http://localhost:8080/coupons
  > ������� : POST http://localhost:8080/coupons
- Pagination ��� ����
  > JPA�� Pageable�� ���� Pagination ��� ����(Request�ÿ� page��ȣ �Է�)

** ������Ʈ ���� �� ���� ��� 
- ���� ��� 
1. ���� ����̺� jar ���� �ٿ� 
  > https://drive.google.com/file/d/1WHH4NwbfloLS5cicXYE1RS8seo5NdbPI/view?usp=sharing
2. ����: java -jar spring-boot-coupon-0.0.1-SNAPSHOT.jar
3. http://localhost:8080/ ���� 

- �ҽ� �ٿ�ε� �� ���� ��� 
1. github ������Ʈ �ٿ�ε� 
  > https://github.com/hansolJo/hswork.git
  > zip ���� �������� �� ������ spring-boot-copuon ������ �̵� 
2. ����: mvnw package
3. ����: java -jar target/spring-boot-coupon-0.0.1-SNAPSHOT.jar
4. http://localhost:8080/ ���� 
