package com.sparta.campproject.security.controller;

import com.sparta.campproject.security.dto.MemberRequestDto;
import com.sparta.campproject.security.dto.MemberResponseDto;
import com.sparta.campproject.security.dto.TokenDto;
import com.sparta.campproject.security.dto.TokenRequestDto;
import com.sparta.campproject.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        if(memberRequestDto == null) {
            return signup(null);
        }
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

     //로그인
//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
//        return ResponseEntity.ok(authService.login(memberRequestDto));
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto memberRequestDto) {
        String token = authService.login(memberRequestDto).getAccessToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer : " + token);

        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);

    }

    @GetMapping("/logout")
    public String logout(@RequestBody TokenRequestDto tokenRequestDto) {
        authService.logout(tokenRequestDto);
        System.out.println("ok!");
        return "/auth/login";
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}