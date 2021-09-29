package com.example.jwtdemo.nju.service;

import com.example.jwtdemo.domain.NjuUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NjuUserDetailService implements UserDetailsService {
    private final NjuUserRepository njuUserRepository;

    @Override
    public UserDetails loadUserByUsername(String ssn) throws UsernameNotFoundException {
        return njuUserRepository.findByNjuSsn(ssn).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
