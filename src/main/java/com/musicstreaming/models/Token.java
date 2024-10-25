package com.musicstreaming.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String token;

    @Column(name = "refresh_token", nullable = false, length = 1000)
    private String refreshToken;

    @Column(name = "expire_time", nullable = false)
    private Instant expireTime;

    @Column(nullable = false)
    private boolean revoked;

    @Column(name = "token_type", nullable = false, length = 1000)
    private String tokenType;


    @Column(name = "expired", nullable = false)
    private boolean expired;

    @Column(name = "is_mobile", nullable = false)
    private boolean isMobile;

    @Column(name = "refresh_expiration_time", nullable = false)
    private Instant refreshExpirationTime;

    @Column(name = "device_id", nullable = false, length = 255)
    private String deviceId;

    @Column(name = "user_agent", nullable = false, length = 255)
    private String userAgent;

    @Column(name = "last_used", nullable = true)
    private LocalDateTime lastUsed;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}