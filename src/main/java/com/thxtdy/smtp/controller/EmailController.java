package com.thxtdy.smtp.controller;

import com.thxtdy.smtp.dto.EmailPostDTO;
import com.thxtdy.smtp.dto.EmailResponseDTO;
import com.thxtdy.smtp.entity.EmailMessage;
import com.thxtdy.smtp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/send-mail")
@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/emailPage")
    public String sendEmailPage() {
        System.out.println("EmailPage 들어옴");
        return "user/sendEmail";
    }


    // 비밀번호 재설정 링크
    @PostMapping("/password")
    public ResponseEntity sendPasswordMail(@RequestBody EmailPostDTO emailPostDto) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostDto.getEmail())
                .subject("[SAVIEW] 임시 비밀번호 발급")
                .build();

        emailService.sendMail(emailMessage, "password");

        return ResponseEntity.ok().build();
    }

    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("/email")
    public ResponseEntity sendJoinMail(@RequestBody EmailPostDTO emailPostDto) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostDto.getEmail())
                .subject("[SAVIEW] 이메일 인증을 위한 인증 코드 발송")
                .build();

        String code = emailService.sendMail(emailMessage, "email");

        EmailResponseDTO emailResponseDto = new EmailResponseDTO();
        emailResponseDto.setCode(code);

        return ResponseEntity.ok(emailResponseDto);
    }
}