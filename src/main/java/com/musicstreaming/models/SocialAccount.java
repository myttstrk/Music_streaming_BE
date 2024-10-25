package com.musicstreaming.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Social_accounts")
public class SocialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider", nullable = false, length = 1000)
    private String provider;

    @Column(name = "provider_id", nullable = false, length = 1000)
    private String providerId;

    @Column(name = "email", nullable = false, length = 1000)
    private String email;

    @Column(name = "name", nullable = false, length = 1000)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
