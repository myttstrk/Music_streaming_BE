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
@Table(name = "albumsong")
public class AlbumSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asID;

    @ManyToOne
    @JoinColumn(name = "albumID", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "songID", nullable = false)
    private Song song;
}
