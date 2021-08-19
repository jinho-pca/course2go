# Getting Started

프로그램을 실행하기 위한 방법입니다.

## 1. Installation

### Clone the repository

```
git clone https://lab.ssafy.com/s05-webmobile2-sub3/S05P13A106.git
```

## 2. Backend Setting

### DB 설정
#### application.properties
backend/src/main/ 내부에 resources폴더를 생성하여 application.properties 파일을 만들어 DB를 세팅해야 합니다.
```
spring.datasource.driverClassName={클래스이름}
spring.datasource.url={DB URL}
spring.datasource.username={유저이름}
spring.datasource.password={비밀번호}
```
#### Database
documentation/db/course2go.mwb 파일을 MySQL 워크벤치로 실행하면 ERD를 통해 DB를 구성할 수 있습니다.

place 테이블에 데이터를 넣어줘야 프로그램을 정상적으로 사용할 수 있으며, 배포된 사이트에서는 [공공 데이터 포탈의 제주도 장소 데이터](https://www.data.go.kr/data/15004770/fileData.do)를 사용하였습니다.

## 3. 
