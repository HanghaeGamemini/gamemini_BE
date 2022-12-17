package com.hanghae.gamemini.service;

import com.hanghae.gamemini.S3.S3Uploader;
import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
     
     private final S3Uploader s3Uploader;
     
     private PostRepository postRepository;
     
     
     @Value ("${part.upload.path}")
     private String uploadPath;
     
     
     //전체글 조회
     @Transactional (readOnly = true)
     public ResponseEntity<?> getPost() {
          List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
          return null;
     }
     
     //글 상세 조회
     @Transactional (readOnly = true)
     public ResponseEntity<PrivateResponseBody> detailPost(Long id) {
          Post post = postRepository.findById(id).orElseThrow(
               () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
          );
//        return new PrivateResponseBody(post);
          return null;
     }
     
     //게시글 작성
     @Transactional
     public String createPost(PostRequestDto postRequestDto, User user) {
          Post post = new Post(postRequestDto, user);
          postRepository.save(post);
          return CommonStatusCode.OK.getStatusMsg();
          
     }
     
     //게시글 수정
     public String updatePost(Long id, PostRequestDto postRequestDto, User user) {
          Post post = postRepository.findById(id).orElseThrow(
               () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
          );
          if (post.getUser().getUsername().equals(user.getUsername())) {
               post.update(postRequestDto);
          }
          return CommonStatusCode.OK.getStatusMsg();
     }
     //게시글 삭제
     
     @Transactional
     public String deletePost(Long id, User user) {
          Post post = postRepository.findById(id).orElseThrow(
               () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
          );
          if (post.getUser().getUsername().equals(user.getUsername())) {
               postRepository.deleteById(id);
          }
          return CommonStatusCode.OK.getStatusMsg();
     }
     
     public void createPost2(PostRequestDto postRequestDto, MultipartFile file, String realPath){
//          User user = SecurityUtil.getCurrentUser();
          // 이미지 업로드 .upload(파일, 경로)
          String originalName = file.getOriginalFilename();//파일명:모든 경로를 포함한 파일이름
          String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
          //예를 들어 getOriginalFileName()을 해서 나온 값이 /Users/Document/bootEx 이라고 한다면
          //"마지막으로온 "/"부분으로부터 +1 해준 부분부터 출력하겠습니다." 라는 뜻입니다.따라서 bootEx가 됩니다.
          String savedImagePath = uploadFile(file, realPath);
//          String imgPath = s3Uploader.upload(file,"images");
     }
     
     public String uploadFile(MultipartFile file, String realPath){
          // 파일이 없을경우
          if (file.isEmpty()) return null;
          // 이미지형식이 아닐경우
          if (file.getContentType() == null || !(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")))
               throw new RestApiException(CommonStatusCode.WRONG_IMAGE_FORMAT);
          
          String originalFileName = file.getOriginalFilename();
          String fileUUName = createFileName(file.getOriginalFilename()); // 중복되지않는 새 파일이름 생성
          log.info("✅ originalFileName : {}, fileUUName : {}" ,originalFileName, fileUUName);
          // s3 적용전 버전
          String saveName = uploadPath + fileUUName; // 저장할 파일경로.파일이름 // File.separator +
          Path savePath = Paths.get(saveName); // 파일의 저장경로(경로 정의)
          log.info("✅ saveName : {}, savePath : {}", saveName, savePath);
          try {
               file.transferTo(savePath); // 지정 경로에 파일저장
          }catch (IOException e){
               log.info("🛑" );
               e.printStackTrace();
               throw new RestApiException(CommonStatusCode.FILE_SAVE_FAIL);
          }
          log.info("파일저장 완료");
          return saveName;
     }
     
     private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 변환
          return UUID.randomUUID().toString().concat("_"+fileName);
     }
     
     private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
          try {
               return fileName.substring(fileName.lastIndexOf("."));
          } catch (StringIndexOutOfBoundsException e) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
          }
     }
}


