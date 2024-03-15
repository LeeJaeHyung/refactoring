### Controller, Service 패키지 내 클래스 개선

### 1. Controller Advice 로 예외 공통화 처리하기
- com.sparta.hmpah.exception.GlobalExceptionHandler
- package com.sparta.hmpah.exception.RestApiException

### 2. Service 인터페이스와 구현체 분리하여 추상화 하기
- com.sparta.hmpah.service.* 하위클래스 전부 처리 완료

### 1. CustomException 정의
- package com.sparta.hmpah.exception.CommentException

### 2. Spring AOP 적용
- package com.sparta.hmpah.aop.ParameterAop

### QueryDSL 을 사용하여 검색 기능 만들기

- QueryDSL 의 jpaQueryFactory 를 사용해서 검색기능을 만들어주세요!
- com.sparta.hmpah.service.PostServiceImpl.getPostListByOption

### Pageable 을 사용하여 페이징 및 정렬 기능 만들기

- Pageable 을 사용해서 원하는 페이지 사이즈만큼만 조회 해주세요! (JpaRepository, QueryDSL 모두)
- JpaRepository : com.sparta.hmpah.service.PostServiceImpl.getPostListByOption
- QueryDSL : test에 있는 querydsl 패키지에 테스트 코드로 테스하였습니다 실제 작성은 CommentServiceImpl

### 코드를 체크할 수 있는 테스트 코드 작성
-이전 팀원들이 작성한 코드를 리뷰하면서 이해하는것으로 대체했습니다

### AWS EC2 를 이용해 애플리케이션 .jar 파일 배포하기
JPA심화 과정을 이해하는 것을 중점으로 학습하고 있었습니다.
시간이 부족하여 강의를 아직 듣지 못하여 수행하지 못하였습니다.


