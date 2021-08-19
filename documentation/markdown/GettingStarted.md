# Getting Started

프로그램을 실행하기 위한 방법입니다.

## 1. Installation

### Clone the repository

```
git clone https://lab.ssafy.com/s05-webmobile2-sub3/S05P13A106.git
```

## 2. Backend Setting

### 각종 설정
#### application.properties
backend/src/main/ 내부에 resources폴더를 생성하여 application.properties 파일을 만들어 세팅해야 합니다.
```
# db
spring.datasource.driverClassName={클래스이름}
spring.datasource.url={DB URL}
spring.datasource.username={유저이름}
spring.datasource.password={비밀번호}

# find pw using email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= {구글 이메일주소}
spring.mail.password={구글 이메일 2차 비밀번호}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.starttls.enable=true

# aws s3
cloud.aws.credentials.accessKey={accessKey}
cloud.aws.credentials.secretKey={secretKey}
cloud.aws.stack.auto=false
cloud.aws.s3.bucket={bucket명}
cloud.aws.region.static=ap-northeast-2
cloud.aws.s3.bucket.url={s3 bucket url}
spring.servlet.multipart.maxFileSize=50MB
spring.servlet.multipart.maxRequestSize=50MB

# slack
logging.level.root=info
logging.config=classpath:logback.xml
logging.slack.webhook-uri={webhook-url}
```
#### Database
documentation/db/course2go.mwb 파일을 MySQL 워크벤치로 실행하면 ERD를 통해 DB를 구성할 수 있습니다.

place 테이블에 데이터를 넣어줘야 프로그램을 정상적으로 사용할 수 있으며, 배포된 사이트에서는 [공공 데이터 포탈의 제주도 장소 데이터](https://www.data.go.kr/data/15004770/fileData.do)를 사용하였습니다.

## 3. Frontend Setting

### 지도 설정
#### kakao jskey 받아오기
카카오지도를 사용하기 위해서는 카카오 개발자 인증을 받아 인증키를 발급받아야 합니다.<br />
[Kakao Developer](https://developers.kakao.com/) 사이트에 회원가입 후 애플리케이션을 추가합니다.<br />
메뉴 - 앱설정 - 플랙폼 - Web - 수정을 클릭하여 사용할 frontend 도메인을 입력합니다.<br />
메뉴 - 앱설정 - 요약정보 - 앱키 부분의 'Javascript 키'가 우리가 사용할 키입니다.
#### js file
mapkey.js 파일을 생성하여 다음과 같이 발급받은 키를 입력해줍니다.
```
export const mapkey = () => {
    const jskey = '발급받은 키';
    return { jskey }
}
```
