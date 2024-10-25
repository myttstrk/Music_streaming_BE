package com.musicstreaming.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "UserAds")
public class UserAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Ads_id", nullable = false)
    private Ad ad;

    @Column(name = "DatePlayed", nullable = false)
    private LocalDate datePlayed;

    @Column(name = "TimePlayed", nullable = false)
    private Long timePlayed;
}
