package com.musicstreaming.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long     id;

    @Column(name = "AdTitle", nullable = false, length = 255)
    private String adTitle;

    @Column(name = "AdContent", nullable = false, columnDefinition = "TEXT")
    private String adContent;

    @Column(name = "AdImage", columnDefinition = "TEXT")
    private String adImage;

    @Column(name = "AdType", nullable = false, length = 255)
    private String adType;

    @Column(name = "TargetAudience", nullable = false)
    private Long targetAudience;

    @Column(name = "StartDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDateTime endDate;
}
