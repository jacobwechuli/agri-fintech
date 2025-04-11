//package com.sgrifintech.loan_management_service.controller;
//
//import com.sgrifintech.loan_management_service.service.OCRService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/v1/ocr")
//@RequiredArgsConstructor
//public class OCRController {
//    private final OCRService ocrService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            var result = ocrService.extractTextFromImage(file);
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//    }
//
//}
