//package com.sgrifintech.loan_management_service.service;
//
//import com.google.cloud.vision.v1.*;
//import com.google.protobuf.ByteString;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Service
//@RequiredArgsConstructor
//public class OCRService {
//    private final ImageAnnotatorClient visionClient;
//
//    public Map<String, String> extractTextFromImage (MultipartFile file) throws Exception{
//        ByteString imgBytes = ByteString.readFrom(file.getInputStream());
//
//        Image img = Image.newBuilder().setContent(imgBytes).build();
//        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
//        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
//                .addFeatures(feat)
//                .setImage(img).build();
//        List<AnnotateImageRequest> requests = Collections.singletonList(request);
//        BatchAnnotateImagesResponse response = visionClient.batchAnnotateImages(requests);
//        AnnotateImageResponse res = response.getResponses(0);
//
//        if (res.hasError()) {
//            throw new RuntimeException("OCR Error: " + res.getError().getMessage());
//        }
//        String fullText = res.getFullTextAnnotation().getText();
//        return parseKenyanID(fullText);
//    }
//    private Map<String, String> parseKenyanID(String text) {
//        Map<String, String> data = new HashMap<>();
//
//        Pattern idPattern = Pattern.compile("(?!)\\b(\\d{8})\\b");
//        Matcher idMatcher = idPattern.matcher(text);
//        if (idMatcher.find()) {
//            data.put("idNumber", idMatcher.group(1));
//        }
//        Pattern namePattern = Pattern.compile("(?!)Name[:\\s]*([A-Z\\s]+)");
//        Matcher nameMatcher = namePattern.matcher(text);
//        if (nameMatcher.find()) {
//            data.put("name", nameMatcher.group(1).trim());
//        }
//        data.put("rawText", text);
//        return data;
//    }
//}
