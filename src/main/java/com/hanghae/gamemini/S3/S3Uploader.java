package com.hanghae.gamemini.S3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class S3Uploader {
     
     private final AmazonS3Client amazonS3Client;
     
     @Value ("${cloud.aws.s3.bucket}")
     private String bucket;
     
     public String upload(MultipartFile multipartFile, String dirName) {
          if(multipartFile == null) return null;
          // MultipartFile을 File객체로 변환
     
          // 이미지형식이 아닐경우
          if (multipartFile.getContentType() == null || !(multipartFile.getContentType().equals("image/png") || multipartFile.getContentType().equals("image/jpeg")))
               throw new RestApiException(CommonStatusCode.WRONG_IMAGE_FORMAT);
          
          File uploadFile = null;
          try {
               uploadFile = convert(multipartFile)
                    .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
          //현재 프로젝트 경로에 업로드 한다.
          return upload(uploadFile, dirName);
     }
     
     // S3 에 업로드
     private String upload(File uploadFile, String dirName) {
          String fileName = dirName + "/" + createFileName(uploadFile.getName());  // directory 추가 & UUID 추가
          // bucket 에 저장 후 get url
          String uploadImageUrl = putS3(uploadFile, fileName);
          
          removeNewFile(uploadFile);  // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)
          
          return uploadImageUrl;      // 업로드된 파일의 S3 URL 주소 반환
     }
     
     private String putS3(File uploadFile, String fileName) {
          amazonS3Client.putObject(
               new PutObjectRequest(bucket, fileName, uploadFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead)// PublicRead 권한으로 업로드 됨
          );
          return amazonS3Client.getUrl(bucket, fileName).toString();
     }
     
     private void removeNewFile(File targetFile) {
          if(targetFile.delete()) {
               log.info("파일이 삭제되었습니다.");
          }else {
               log.info("파일이 삭제되지 못했습니다.");
          }
     }
     
     private Optional<File> convert(MultipartFile file) throws IOException {
          log.info("🛑file.getOriginalFilename() : {}" , file.getOriginalFilename());
          File convertFile = new File(file.getOriginalFilename()); // 이름겹치는 오류생길경우 여기부터 UUID 적용해도 될듯
          if(convertFile.createNewFile()) {
               try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                    fos.write(file.getBytes());
               }
               return Optional.of(convertFile);
          }
          return Optional.empty();
     }
     
     public void remove(String imgUrl, String dirName) {
          String fileName = dirName + "/" + imgUrl;
          if (!amazonS3Client.doesObjectExist(bucket, fileName)) {
               throw new AmazonS3Exception("Object " + imgUrl+ " does not exist!");
          }
          amazonS3Client.deleteObject(bucket, fileName);
     }
     
     private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 변환
          return UUID.randomUUID().toString().concat("_" + fileName);
     }
}
