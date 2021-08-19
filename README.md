# Course2go

![logo](./documentation/img/logo_course2go.png)
> <b> 프로젝트 기간: 2021.07.12 ~ 2021.08.20 </b>

![StyleCI](https://img.shields.io/badge/vue-3-brightgreen)
![StyleCI](https://img.shields.io/badge/vue/cli-4.5.13-brightgreen)

![StyleCI](https://img.shields.io/badge/spring--boot-2.5.2-green) 

![StyleCI](https://img.shields.io/badge/mariaDB-10.3.23-blue)

![StyleCI](https://img.shields.io/badge/ec2(ubuntu)-20.04-orange)
![StyleCI](https://img.shields.io/badge/pm2-5.1.0-orange)
![StyleCI](https://img.shields.io/badge/nginx-1.18.0-orange)

![StyleCI](https://img.shields.io/badge/android-9.0(pie)-yellow)

## 서비스 개요

* <b> 여행 동선 공유를 위한 SNS 서비스 Course2go </b>
* Course2go는 다양한 여행자들의 여행동선을 공유하기 위한 SNS입니다

## 역할 분담
  
> Course2go developer 

| **🙋 [이진호](https://github.com/dk-master)** | **🙋‍ [안기훈](https://github.com/)** | **🙋 [나희승](https://github.com/sjsjsjghkdwp)** | **🙋‍ [조규홍](https://github.com/)** |
| :---: | :---: | :---: | :---: |
| ![logo](./documentation/img/이진호.png) |![logo](./documentation/img/안기훈.png)| ![logo](./documentation/img/나희승.png) |![logo](./documentation/img/조규홍.png)|
| 백엔드 개발자 | 프론트엔드 개발자 | 백엔드 개발자 | 백엔드 개발자 |
| 배포 환경 구축 <br /> 추가요함 <br /> 추가요함 <br /> 추가요함  <br /> 추가요함 <br />| 추가요함 <br /> 추가요함 <br /> 추가요함 <br /> 추가요함 <br /> 추가요함 | 게시글 <br /> 좋아요 <br /> 뉴스피드 <br /> 알림  <br /> 댓글 <br />| Spring Security <br /> 추가요함 <br /> 추가요함 <br /> 추가요함 <br /> 추가요함 |

## [Git Convention](https://www.notion.so/Git-Convention-f8d4466d57504092b7bca7abf3f1be83)

### 1. Commit types

- **init**: 새로운 프로젝트 초기 설정
- **feat**: 새로운 기능을 추가할 경우
- **fix**: 버그를 고친 경우
- **design**: CSS 등 디자인 변경
- **docs**: 문서를 수정한 경우 (제품 코드 수정 X)
- **rename:** 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우
- **remove**: 파일을 삭제하는 작업만 수행한 경우sts

```bash
type: subject(제목)
docs: Update project Readme.md

body(본문)
프로젝트 markdown 파일 업데이트

footer(푸터)
Resolves: #231
```



### 2. Branch

- **feature**
  - feature/{분류}/{기능}
    - feature/fe/user_login
    - feature/be/social_login
- **fix**
  - fix/{분류}/{에러}
    - fix/fe/route_card
    - fix/be/board_delete
- **develop**
  - 이름 그대로 사용
- **master**
  - 이름 그대로 사용

##

## Back-end

 ### DB
  #### ERD
  ![logo](./documentation/img/ERD.png)
