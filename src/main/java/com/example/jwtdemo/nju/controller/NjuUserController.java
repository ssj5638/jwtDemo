package com.example.jwtdemo.nju.controller;

import com.example.jwtdemo.common.component.JwtTokenProvider;
import com.example.jwtdemo.domain.NjuUser;
import com.example.jwtdemo.domain.NjuUserRepository;
import com.example.jwtdemo.domain.vo.NjuUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
public class NjuUserController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final NjuUserRepository njuUserRepository;

    @PostMapping("/nju/signup")
    public Long singup(@RequestBody NjuUserRequest user) {
        return njuUserRepository.save(NjuUser.builder()
                .njuSsn(user.getNjuSsn())
                .password(passwordEncoder.encode(user.getNjuPassword()))
                .njuBirthday(user.getNjuBirthday())
                .njuName(user.getNjuName())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    @PostMapping("/nju/login")
    public String login(@RequestBody NjuUserRequest user) {
        NjuUser njuUser = njuUserRepository.findByNjuSsn(user.getNjuSsn())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-mail 입니다."));
        if (!passwordEncoder.matches(user.getNjuPassword(), njuUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        return jwtTokenProvider.createToken(njuUser.getUsername(), njuUser.getRoles());
    }
}
