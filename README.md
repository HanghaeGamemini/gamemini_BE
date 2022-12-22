### 4조 미니 프로젝트(SA)

https://foremost-tellurium-295.notion.site/4-SA-37c3447f61a24e1a92fa8c5a060fa1e7

# 🎮Gammini(BE)🎮
### 내가 해본 게임 중 재미있던 게임들을 사진과 글로 추천, 설명해주는 사이트


---

 ## 🧩주요기능
 -  회원가입, 로그인 기능
 -  회원탈퇴 기능
 -  게임추천하여 글 작성, 이미지 업로드하기
 -  한 페이지에 게시글 8개만 보이도록 페이징 처리
 -  게시글 좋아요 추천, 추천 취소하기(좋아요 개수 보이기)
 -  게시글에 comment달기
 -  마이페이지에서 프로필 변경하기
 -  마이페이지에서 내가 좋아요한 게시글, 내가 작성한 게시글 조회
 ## 🧩API 명세서
 https://www.notion.so/5c1825c4d4374345a373db15c88a3a68?v=ab6a0dff9e1f435187ca5879724fae9f
 ---
 ##  💉트러블 슈팅💉
#### 1. 파일업로드
>- 배포 후 access denied 문제 : aws의존성 버전 재설정, Iam key 재발급 및 권한 재설정
>- 이후 permission denied 문제 : 받은 파일 임시저장폴더를 ec2서버에 생성하지 못해 발생한 오류. 임시저장폴더를 생성가능한 곳으로 변경 후 저장폴더 생성하여 오류해결


#### 2. post의 nickname으로 검색하여 post 불러올때 user table select N+1 문제
>- comment 와 user join하여 comment와 nickname을 한번에 불러올 수 있도록 처리


#### 3. 전체 post내의 comment에서 User nickname 값 가져올때 N+1문제
>- nativeQuery로 comment불러올때 User table의 nickname값 같이 불러오도록 설정
>- countQuery 사용하여 페이징처리도 동시 적용
 
 ## 🧩swagger
[3.34/98/133/api/doc](http://3.34.98.133/swagger-ui/index.html#/)
 ---
## 🧩 기술스택
- 백엔드

![자바](https://user-images.githubusercontent.com/108880977/209101862-e833ffc2-7cab-4114-8b74-5766d25b226b.svg)
![스프링부트](https://user-images.githubusercontent.com/108880977/209099782-f0f6fbb6-8c55-4a0e-a7a2-53fd5a000493.svg)
![시큐리티](https://user-images.githubusercontent.com/108880977/209101809-e972b9cf-36e1-4db3-a9ed-6474bc88770e.svg)
![JPA](https://user-images.githubusercontent.com/108880977/209104203-cccd4e80-5279-4e89-9453-c9d2333570b5.svg)
![JWT](https://user-images.githubusercontent.com/108880977/209102757-eb3f840f-ca24-4c89-a2b5-c60fff46bf49.svg)
![GRADLE](https://user-images.githubusercontent.com/108880977/209101888-8ea11829-e1b1-4de2-b7b4-8716e99dcf05.svg)
![MYSQL](https://user-images.githubusercontent.com/108880977/209101897-c8a4fa60-6fb0-4501-b30f-06269e75ce11.svg)
![아마존 EC2](https://user-images.githubusercontent.com/108880977/209103415-bf1aed4c-5cc3-442b-8a2a-9fe6271e8920.svg)
![아마존 RDS](https://user-images.githubusercontent.com/108880977/209103424-828b0d5b-9419-4ebb-8a85-24bbc3072213.svg)
![아마존 AWS](https://user-images.githubusercontent.com/108880977/209103421-1cf57ef4-8620-4932-8704-60d0ec14ed1f.svg)
![EC22](https://user-images.githubusercontent.com/108880977/209104209-b04b40b7-a847-4263-aeb8-de19bc7fa8d9.svg)
 - 프론트엔드 https://github.com/HanghaeGamemini/gmini_front
 ---
 ### Member 
 BE 😶장영주, 😶최재하,😶 김수예
 
 
 FE 😀허정은, 😀한예진
 
 
 - FE 프론트엔드 깃허브동 이동
 https://github.com/HanghaeGamemini/gmini_front
 
