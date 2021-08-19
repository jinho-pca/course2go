### 1-1) 사용한 tool 종류 및 설정값, 버전
> JVM :  11.0.11
> nginx : 1.18.0
> IDE : STS(3.9.14, 4), IntelliJ(2021.2, 2019.3.5), VSCode(1.59.0)
### 1-2)
* application.properties (backend/src/main/resources/)
```
spring.datasource.driverClassName=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mysql://stg-yswa-kr-practice-db-master.mariadb.database.azure.com:3306/S05P13A106?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8
spring.datasource.username=S05P13A106
spring.datasource.password=D047sfCJVS

# Find PW using Email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= ssafy.course2go@gmail.com
spring.mail.password=pefecfrcqypbswzq
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.starttls.enable=true

# AWS S3
cloud.aws.credentials.accessKey=AKIARPWZ72XB36GM4AWL
cloud.aws.credentials.secretKey=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
cloud.aws.stack.auto=true
cloud.aws.s3.bucket=ssafy-5th
cloud.aws.region.static=ap-northeast-2
cloud.aws.s3.bucket.url=https://s3.ap-northeast-2.amazonaws.com/ssafy-5th

logging.level.root=info
```
* mapkey.js (frontend/)
```
export const mapkey = () => {
    const jskey = {후술할 'JavaScript 키'가 들어갈 자리};
    return { jskey }
}
```
### 1-3)
* nginx 설정 (/etc/nginx/sites-available/ 에 있는 default 설정)
```
server{
        listen 80 default_server;
        listen [::]:80 default_server;
        server_name i5a106.p.ssafy.io/;
        return 301 HTTPS://$server_name$request_uri;
        index index.html index.htm ;
}
server {
        listen 443 ssl default_server;
        listen [::]:443 ssl default_server;
        ssl_certificate /etc/letsencrypt/live/i5a106.p.ssafy.io/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/i5a106.p.ssafy.io/privkey.pem;
        ssl_protocols   TLSv1 TLSv1.1 TLSv1.2;
        ssl_ciphers     HIGH:!aNULL:!MD5;

        root /var/www/html/dist;

        index index.html index.htm index.nginx-debian.html;

        server_name i5a106.p.ssafy.io www.i5a106.p.ssafy.io www.course2go.site;

        location ~/course2go {
                proxy_pass http://localhost:8080;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header Host $http_host;
        }
        location / {
                index index.php index.html;
                try_files $uri $uri/ /index.html?$args;
		}
}
```
### 1-4) DB 접속정보 등 프로젝트 주요계정 및 프로퍼티가 정의된 파일 목록
* application.properties (상술함)

## 2. 프로젝트에서 사용하는 외부서비스 정보문서
* 카카오맵 사용을 위한 [Kakao Developer 링크](https://developers.kakao.com/)및 절차
1. 회원가입
2. 내 애플리케이션 > 애플리케이션 추가하기
3. 'JavaScript 키' 사용, 메뉴 > 플랫폼 > Web 에 도메인 등록
4. mapkey.js 파일에 해당 키 입력

## 3. 데이터베이스 덤프 파일 최신본
* [덤프파일 링크](../documentation/db/Course2goDump.sql)

## 4. 시연 시나리오
