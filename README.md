# News-Clipping


> 
>Java SE 8u171, JSP, JDBC, Jsoup(라이브러리), Eclipse(IDE), Oracle DB,  
>HTML5/CSS, Tomcat 9.0, MVC pattern, UML(Unified Modeling Language)

**1. 프로젝트 목표/개요**
- 사용자가 (신문사/조회수/좋아요/댓글수 순으로)신문기사를 조회하고 원하는 기사를 스크랩하여 의견을 기록할 수 있는 웹 애플리케이션을 구현

**2. 개발 과정**
- 신문 기사 데이터베이스를 구축하기 위해 Jsoup 라이브러리를 활용하여 (Naver 뉴스 사이트에서) 한국 10대 신문사의 1주일간의 기사와 해당 기자, 신문사 데이터를 크롤링
- 크롤링한 데이터를 뉴스/신문사/기자/사용자 정보를 저장한 테이블로 나누고 각각을 CSV 파일로 만든 후 Oracle DB에 테이블로 만들어 저장
- 웹 애플리케이션을 구축하기 위해 MVC 패턴을 이용
  - Model 역할을 할 신문사, 기사, 기자, 사용자 객체(VO)
  - DB에 접근하여 CRUD 기능을 수행할 DAO를 생성
  - JSP를 활용하여 사용자에게 보여줄 View를 구현
  - Servlet을 활용하여 사용자의 요청에 따라 Model의 데이터 흐름을 제어하고 이에 따라 View를 갱신하는 Controller를 구현
 
 
**3. 주요 기능**
   1. 상세 조회  
       - 사용자가 View(JSP)에서 원하는 조회 옵션(조회수/댓글수/좋아요수)을 선택하면 Servlet(Controller)이 그 옵션을 파라미터로 받은 후 
       해당 기능을 수행하는 DAO(Model)를 호출
       - DB에서 옵션에 따라 정렬된 기사를 추출하여 다시 사용자에게 보여줌
   
   2. 의견 관리
       - 사용자가 원하는 뉴스 기사를 선택, 의견 입력 기능을 선택하면 의견 입력을 위한 페이지로 이동
       - 기사의 상세 정보와 함께 의견 입력 양식을 출력해 줌
       - 의견을 제출하면 (Servelt을 통해) DAO가 호출되어 의견 데이터가 DB에 저장됨
       - 사용자가 입력한 의견 목록 페이지로 이동하면 DB에 저장된 데이터가 추출되어 사용자에게 보여짐
       - 사용자는 자신이 입력한 의견 데이터를 수정 및 삭제할 수 있음 
