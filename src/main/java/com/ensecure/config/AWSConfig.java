package com.ensecure.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    // AWS 자격 증명 정보
    @Value("${aws.ses.accessKey}")
    private String accessKey;

    @Value("${aws.ses.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        AWSStaticCredentialsProvider awsStaticCredentialsProvider =
            new AWSStaticCredentialsProvider(basicAWSCredentials());

        return AmazonSimpleEmailServiceClientBuilder.standard()
            .withCredentials(awsStaticCredentialsProvider)
            .withRegion(region)
            .build();
    }
}