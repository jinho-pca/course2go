## Convention

### 1. Commit types

- **init**: 새로운 프로젝트 초기 설정
- **feat**: 새로운 기능을 추가할 경우
- **fix**: 버그를 고친 경우
- **design**: CSS 등 디자인 변경
- **docs**: 문서를 수정한 경우 (제품 코드 수정 X)
- **style**: 코드 포맷 변경, 세미 콜론 누락, 주석 등의 변경
- **refactor**: 프로덕션 코드 리팩토링
- **test**: 테스트 추가, 테스트 리팩토링 (프로덕션 코드 변경 X)
- **chore**: 빌드 테스트 업데이트, 패키지 매니저를 설정/수정하는 경우 (프로덕션 코드 변경 X)
  - package.json의 변경이나 dotenv의 요소 변경, 모듈 변경 등
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



#### 참고. 유의사항

1. 제목과 본문을 한 줄 띄워 분리하기
2. 제목은 영문 기준 50자 이내로
3. 제목 첫글자를 대문자로
4. 제목 끝에 . 금지
5. 제목은 명령조로
6. 본문은 영문 기준 72자마다 줄 바꾸기
7. 본문은 어떻게보다 무엇을, 왜에 맞춰 작성하기




### 2. Branch

- **feature**
  - **feature/{위치}/{기능대분류}_{기능명}**
    - feature/fe/user_login
    - feature/be/social_login
    - fe/be로만 나뉘기 때문에 이 방법이 좋을 것 같음
  - feature/{위치}_{기능}
    - feature/mobile_admin_dashboard
    - 브랜치명이 길게 나올 것 같으면 깔끔하게 나타낼 수 있음
- **develop**
  - 이름 그대로 사용
- **master**
  - 이름 그대로 사용

