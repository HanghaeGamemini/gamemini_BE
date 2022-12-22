package com.hanghae.gamemini.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfiguration {

  @Value("${cloud.aws.credentials.access-key}")
  String AWS_S3_ACCESS_KEY;

  @Value("${cloud.aws.credentials.secret-key}")
  String AWS_S3_SECRET_KEY;
  
  @Value("${cloud.aws.region.static}")
  private String REGION;

  @Bean
  @Primary
  public AwsBasicCredentials awsS3CredentialProvider() {
    return AwsBasicCredentials.create(AWS_S3_ACCESS_KEY, AWS_S3_SECRET_KEY);
  }

  @Bean
  public S3Client s3Client() { // AmazonS3 객체를 Bean으로 등록
    return S3Client.builder()
        .credentialsProvider(
            this::awsS3CredentialProvider
        )
        .region(Region.of(REGION))
        .build();
  }

}
