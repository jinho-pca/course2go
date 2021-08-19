# Backend

## JWT
![Classes](../documentation/img/SecurityFlow.png)

(출처: https://springbootdev.com/)

최초 로그인 시 위와같은 Architecture에 따라 발급되고 사용자의 LocalStorage에 저장하여 사용한다.

Architecture는 다음과 같은 절차를 따른다.

1. course2go/user/login 으로 email과 비밀번호를 정보로 담아 요청을 보낸다. 이 요청은 CustomAuthenticationFilter에 의해 처리된다.

2. CustomAuthenticationFilter는 해당 정보를 바탕으로 UsernamePasswordAuthenticationToken을 생성한다.

3. AuthenticationManager는 CustomAuthenticationProvider를 통해서 UsernamePasswordAuthenticationToken에 있는 정보가 유저 정보에 있는지 확인한다. 

4. 정보의 유무에 따라 CustomAuthenticationFilter 는 successfulAuthentication() 혹은 unsuccessfulAuthentication() 메서드가 실행된다.

5. 성공시 SuccessHandler에 의해서 Token이 생성되고 Response Header에 JWT token을 담아 보낸다.



로그인 후 사용자가 본인인증이 필요한 API 요청을 보낼 때는 Header에 JWT token이 포함되어 있어야한다. 



## JPA


## MVC design
![Classes](../documentation/img/BackendFlow.png)
