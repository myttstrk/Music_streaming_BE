package com.musicstreaming.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

//    @ManyToOne
//    @JoinColumn(name = "album_id")
//    private Album album;

    @Column(name = "title",length = 255)
    private String title;

//    @Column(name = "duration")
//    private int duration; // Duration in seconds

//    @Column(name = "release_date")
//    private LocalDateTime releaseDate;

//    @Column(name = "cover_image", columnDefinition = "TEXT")
//    private String coverImage;

    @Column(name = "audio_url", columnDefinition = "TEXT")
    private String audioUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "song_category",
            joinColumns = @JoinColumn(name = "id_song"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<AlbumSong> albumSongs;

}
