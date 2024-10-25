package com.musicstreaming.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "play_count")
public class PlayCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_count_id")
    private Long id;
    @Column(name = "player_count")
    private Long playCount;
    @Column(name = "song_id")
    private Long songID;
}
