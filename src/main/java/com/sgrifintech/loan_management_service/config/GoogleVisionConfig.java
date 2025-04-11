//package com.sgrifintech.loan_management_service.config;
//
//import com.google.api.gax.core.FixedCredentialsProvider;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.vision.v1.ImageAnnotatorClient;
//import com.google.cloud.vision.v1.ImageAnnotatorSettings;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.InputStream;
//
//@Configuration
//public class GoogleVisionConfig {
////    @Bean
//    public ImageAnnotatorClient imageAnnotatorClient() throws Exception {
//        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("gcp-key.json");
//
//        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
//        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
//                .setCredentialsProvider(() -> credentials)
//                .build();
//        return ImageAnnotatorClient.create(settings);
//    }
//}
