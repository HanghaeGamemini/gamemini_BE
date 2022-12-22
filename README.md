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
 
 
 
 ### Member
 😶장영주, 😶최재하,😶 김수예
