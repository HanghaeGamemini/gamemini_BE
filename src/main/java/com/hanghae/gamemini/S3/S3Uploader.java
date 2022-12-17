package com.hanghae.gamemini.S3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class S3Uploader {
     
//     private final AmazonS3Client amazonS3Client;
     
//     @Value ("${cloud.aws.s3.bucket}")
     private String bucket;
     
}
