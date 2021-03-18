## Book Api Example



> ### 요약
- TDD
    - TDD (Test Driven Development) 테스트 주도 개발 

    - TDD 를 도입하기 위해서는 많은 인프라적인 요소를 갖춰야 한다

    - 규모가 작은 곳에서 TDD 개발 환경을 갖추기 위해서는 자체적인 인프라 개발보다는
 
    - Travis CI 를 이용하여 인프라 쪽에 집중을 최소화 할 필요가 있다
- JPA
    - JPA를 이용하여 많은 양을 Batch 데이터를 처리하는 방법 적절한 방법이 아니다
    - 현재까지는 JPA 로 왠만한 서버의 Batch 처리를 감당 할 수 있으나 (카카오페이),  
    처리해야 할 양이 많아진다면, 다른 아키텍처를 이용해야 한다
     
 
<br><br>
> ### 구성
1단계 : TEST 코드 작성  
2단계 : CI & CD 구성  
3단계 : 어떻게 테스트 코드를 돌릴 지  
4단계 : 테스트 결과는 어떻게 report 받을 지  
5단계 : 테스트 Coverage 측정은 어떻게 하고, 어떻게 피드백 받을 것 인지  
<br><br>
> ### 순서
테스트 코드 작성 > PR(Pull Request) > Git Gub Hook Intercept -> Travis CI 또는 Circle CI Build   
\> Test Code Run > Run Success > Test Coverage 측정 > Code Review
<br><br>
 