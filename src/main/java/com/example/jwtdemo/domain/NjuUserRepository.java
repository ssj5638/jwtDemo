package com.example.jwtdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NjuUserRepository extends JpaRepository<NjuUser, Long> {

    Optional<NjuUser> findByNjuSsn(String ssn);
}
